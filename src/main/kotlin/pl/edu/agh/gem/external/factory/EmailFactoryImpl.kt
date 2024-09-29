package pl.edu.agh.gem.external.factory

import jakarta.mail.internet.MimeMessage
import org.springframework.core.io.ClassPathResource
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.mail.javamail.MimeMessageHelper.MULTIPART_MODE_RELATED
import org.springframework.stereotype.Component
import pl.edu.agh.gem.internal.factory.EmailFactory
import pl.edu.agh.gem.internal.model.Attachment
import pl.edu.agh.gem.internal.service.EmailProperties
import kotlin.text.Charsets.UTF_8

@Component
class EmailFactoryImpl(
    private val javaMailSender: JavaMailSender,
    private val emailProperties: EmailProperties,
) : EmailFactory {
    override fun createEmail(email: String, subject: String, html: String, attachment: Attachment?): MimeMessage {
        val mimeMessage: MimeMessage = javaMailSender.createMimeMessage()
        val helper = MimeMessageHelper(mimeMessage, MULTIPART_MODE_RELATED, UTF_8.name())
        helper.setFrom(emailProperties.username)
        helper.setTo(email)
        helper.setSubject(subject)
        helper.setText(html, true)

        attachment?.also {
            helper.addAttachment(attachment.name, attachment.file)
        }

        val contentId = CONTENT_ID
        val icon = ICON_PATH
        val resource = ClassPathResource(icon)
        helper.addInline(contentId, resource)

        return mimeMessage
    }

    companion object {
        const val CONTENT_ID = "icon"
        const val ICON_PATH = "images/icon.png"
    }
}
