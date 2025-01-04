package pl.edu.agh.gem.external.client

import io.github.oshai.kotlinlogging.KotlinLogging
import io.github.resilience4j.retry.annotation.Retry
import jakarta.mail.internet.MimeMessage
import org.springframework.mail.MailException
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Component
import pl.edu.agh.gem.internal.client.ExternalEmailSenderClient
import pl.edu.agh.gem.internal.client.RetryableEmailSenderClientException
import pl.edu.agh.gem.metrics.MeteredClient

@Component
@MeteredClient
class JavaMailSenderClient(
    private val javaMailSender: JavaMailSender,
) : ExternalEmailSenderClient {
    @Retry(name = "default")
    override fun sendEmail(mimeMessage: MimeMessage) {
        try {
            javaMailSender.send(mimeMessage)
        } catch (ex: MailException) {
            logger.warn(ex) { "Server side exception while trying to send verification email" }
            throw RetryableEmailSenderClientException(ex.message)
        }
    }

    companion object {
        private val logger = KotlinLogging.logger {}
    }
}
