package pl.edu.agh.gem.internal.client

import org.springframework.mail.SimpleMailMessage

interface ExternalEmailSenderClient {
    fun sendEmail(email: SimpleMailMessage)
}
class RetryableEmailSenderClientException(override val message: String?) : RuntimeException()
