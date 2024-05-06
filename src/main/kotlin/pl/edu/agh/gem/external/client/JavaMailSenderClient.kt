package pl.edu.agh.gem.external.client

import io.github.resilience4j.retry.annotation.Retry
import mu.KotlinLogging
import org.springframework.mail.MailException
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Component
import pl.edu.agh.gem.internal.client.ExternalEmailSenderClient
import pl.edu.agh.gem.internal.client.RetryableEmailSenderClientException

@Component
class JavaMailSenderClient(
    private val javaMailSender: JavaMailSender,
) : ExternalEmailSenderClient {

    @Retry(name = "default")
    override fun sendEmail(email: SimpleMailMessage) {
        try {
            javaMailSender.send(email)
        } catch (ex: MailException) {
            logger.warn(ex) { "Server side exception while trying to send verification email" }
            throw RetryableEmailSenderClientException(ex.message)
        }
    }

    companion object {
        private val logger = KotlinLogging.logger {}
    }
}
