package pl.edu.agh.gem.external.client

import io.github.resilience4j.retry.annotation.Retry
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod.GET
import org.springframework.stereotype.Component
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.HttpServerErrorException
import org.springframework.web.client.RestTemplate
import pl.edu.agh.gem.config.AuthenticatorProperties
import pl.edu.agh.gem.external.dto.EmailAddressResponse
import pl.edu.agh.gem.headers.HeadersUtils.withAppAcceptType
import pl.edu.agh.gem.internal.client.AuthenticatorClient
import pl.edu.agh.gem.internal.client.AuthenticatorClientException
import pl.edu.agh.gem.internal.client.RetryableAuthenticatorClientException
import pl.edu.agh.gem.paths.Paths.INTERNAL

@Component
class RestAuthenticatorClient(
    @Qualifier("AuthenticatorRestTemplate") val restTemplate: RestTemplate,
    val authenticatorProperties: AuthenticatorProperties,
) : AuthenticatorClient {

    @Retry(name = "authenticator")
    override fun getEmailAddress(userId: String): String {
        return try {
            restTemplate.exchange(
                resolveEmailAddressUrl(userId),
                GET,
                HttpEntity<Any>(HttpHeaders().withAppAcceptType()),
                EmailAddressResponse::class.java,
            ).body?.email ?: throw AuthenticatorClientException("While trying to retrieve email address we receive empty body")
        } catch (ex: HttpClientErrorException) {
            logger.warn(ex) { "Client side exception while trying to retrieve email address" }
            throw AuthenticatorClientException(ex.message)
        } catch (ex: HttpServerErrorException) {
            println(ex.message)
            logger.warn(ex) { "Server side exception while trying to retrieve email address" }
            throw RetryableAuthenticatorClientException(ex.message)
        } catch (ex: Exception) {
            println(ex.message)
            logger.warn(ex) { "Unexpected exception while trying to retrieve email address" }
            throw AuthenticatorClientException(ex.message)
        }
    }

    private fun resolveEmailAddressUrl(userId: String) =
        "${authenticatorProperties.url}$INTERNAL/users/$userId/email"

    companion object {
        private val logger = KotlinLogging.logger {}
    }
}
