package pl.edu.agh.gem.internal.factory

import jakarta.mail.internet.MimeMessage
import pl.edu.agh.gem.internal.model.Attachment

interface EmailFactory {
    fun createEmail(email: String, subject: String, html: String, attachment: Attachment? = null): MimeMessage
}
