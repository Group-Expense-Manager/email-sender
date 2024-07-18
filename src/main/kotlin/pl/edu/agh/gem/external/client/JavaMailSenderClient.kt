package pl.edu.agh.gem.external.client

import io.github.resilience4j.retry.annotation.Retry
import jakarta.mail.internet.MimeMessage
import mu.KotlinLogging
import org.springframework.core.io.ClassPathResource
import org.springframework.mail.MailException
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.mail.javamail.MimeMessageHelper.MULTIPART_MODE_RELATED
import org.springframework.stereotype.Component
import pl.edu.agh.gem.internal.client.ExternalEmailSenderClient
import pl.edu.agh.gem.internal.client.RetryableEmailSenderClientException
import pl.edu.agh.gem.internal.service.EmailProperties
import kotlin.text.Charsets.UTF_8

@Component
class JavaMailSenderClient(
    private val javaMailSender: JavaMailSender,
    private val emailProperties: EmailProperties,
) : ExternalEmailSenderClient {

    @Retry(name = "default")
    override fun sendEmail(email: String, subject: String, text: String) {
        try {
            val mimeMessage: MimeMessage = javaMailSender.createMimeMessage()
            val helper = MimeMessageHelper(mimeMessage, MULTIPART_MODE_RELATED, UTF_8.name())
            helper.setFrom(emailProperties.username)
            helper.setTo(email)
            helper.setSubject(subject)
            helper.setText(text, true)

            val contentId = "icon"
            val icon = "images/icon.png"
            val resource = ClassPathResource(icon)
            helper.addInline(contentId, resource)

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
