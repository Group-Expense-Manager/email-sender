package pl.edu.agh.gem.internal.client

interface ExternalEmailSenderClient {
    fun sendEmail(email: String, subject: String, text: String)
}
class RetryableEmailSenderClientException(override val message: String?) : RuntimeException()
