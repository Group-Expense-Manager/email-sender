package pl.edu.agh.gem.integration.client

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR
import org.springframework.http.HttpStatus.NOT_ACCEPTABLE
import pl.edu.agh.gem.helper.user.DummyUser.EMAIL
import pl.edu.agh.gem.helper.user.DummyUser.USER_ID
import pl.edu.agh.gem.integration.BaseIntegrationSpec
import pl.edu.agh.gem.integration.ability.stubEmailAddress
import pl.edu.agh.gem.internal.client.AuthenticatorClient
import pl.edu.agh.gem.internal.client.AuthenticatorClientException
import pl.edu.agh.gem.internal.client.RetryableAuthenticatorClientException
import pl.edu.agh.gem.util.createEmailAddressResponse

class AuthenticatorClientIT(
    private val authenticatorClient: AuthenticatorClient,
) : BaseIntegrationSpec({
    should("get email address") {
        // given
        stubEmailAddress(createEmailAddressResponse(EMAIL), USER_ID)

        // when
        val email = authenticatorClient.getEmailAddress(USER_ID)

        // then
        email shouldBe EMAIL
    }

    should("throw AuthenticatorClientException when we send bad request") {
        // given
        stubEmailAddress(createEmailAddressResponse(EMAIL), USER_ID, NOT_ACCEPTABLE)

        // when & then
        shouldThrow<AuthenticatorClientException> {
            authenticatorClient.getEmailAddress(USER_ID)
        }
    }

    should("throw RetryableAuthenticatorClientException when client has internal error") {
        // given
        stubEmailAddress(createEmailAddressResponse(EMAIL), USER_ID, INTERNAL_SERVER_ERROR)

        // when & then
        shouldThrow<RetryableAuthenticatorClientException> {
            authenticatorClient.getEmailAddress(USER_ID)
        }
    }
},)
