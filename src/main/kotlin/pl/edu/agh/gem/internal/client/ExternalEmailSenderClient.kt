package pl.edu.agh.gem.internal.client

import jakarta.mail.internet.MimeMessage

interface ExternalEmailSenderClient {
    fun sendEmail(mimeMessage: MimeMessage)
}
class RetryableEmailSenderClientException(override val message: String?) : RuntimeException()
