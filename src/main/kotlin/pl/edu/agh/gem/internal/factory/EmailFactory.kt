package pl.edu.agh.gem.internal.factory

import jakarta.mail.internet.MimeMessage

interface EmailFactory {
    fun createEmail(email: String, subject: String, html: String): MimeMessage
}
