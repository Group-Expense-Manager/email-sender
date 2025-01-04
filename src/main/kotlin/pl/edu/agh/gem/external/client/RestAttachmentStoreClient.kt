package pl.edu.agh.gem.external.client

import io.github.resilience4j.retry.annotation.Retry
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod.GET
import org.springframework.stereotype.Component
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.HttpServerErrorException
import org.springframework.web.client.RestTemplate
import pl.edu.agh.gem.config.AttachmentStoreProperties
import pl.edu.agh.gem.external.dto.toAttachment
import pl.edu.agh.gem.headers.HeadersUtils.withAppAcceptType
import pl.edu.agh.gem.internal.client.AttachmentStoreClient
import pl.edu.agh.gem.internal.client.AttachmentStoreClientException
import pl.edu.agh.gem.internal.client.RetryableAttachmentStoreClientException
import pl.edu.agh.gem.internal.model.Attachment
import pl.edu.agh.gem.metrics.MeteredClient
import pl.edu.agh.gem.paths.Paths.INTERNAL

@Component
@MeteredClient
class RestAttachmentStoreClient(
    @Qualifier("AttachmentStoreRestTemplate") val restTemplate: RestTemplate,
    val attachmentStoreProperties: AttachmentStoreProperties,
) : AttachmentStoreClient {

    @Retry(name = "attachmentStore")
    override fun getReport(groupId: String, attachmentId: String, title: String): Attachment {
        return try {
            val result = restTemplate.exchange(
                resolveReportUrl(groupId, attachmentId),
                GET,
                HttpEntity<Any>(HttpHeaders().withAppAcceptType()),
                ByteArray::class.java,
            )
            result.headers.contentType?.let { result.body?.toAttachment(it.toString(), title) } ?: throw AttachmentStoreClientException(
                "While trying to retrieve report attachment we receive empty body",
            )
        } catch (ex: HttpClientErrorException) {
            logger.warn(ex) { "Client side exception while trying to retrieve report attachment" }
            throw AttachmentStoreClientException(ex.message)
        } catch (ex: HttpServerErrorException) {
            println(ex.message)
            logger.warn(ex) { "Server side exception while trying to retrieve report attachment" }
            throw RetryableAttachmentStoreClientException(ex.message)
        } catch (ex: Exception) {
            println(ex.message)
            logger.warn(ex) { "Unexpected exception while trying to retrieve report attachment" }
            throw AttachmentStoreClientException(ex.message)
        }
    }

    private fun resolveReportUrl(groupId: String, attachmentId: String) =
        "${attachmentStoreProperties.url}$INTERNAL/groups/$groupId/attachments/$attachmentId"

    companion object {
        private val logger = KotlinLogging.logger {}
    }
}
