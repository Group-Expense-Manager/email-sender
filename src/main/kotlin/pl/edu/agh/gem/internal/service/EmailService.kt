package pl.edu.agh.gem.internal.service

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Service
import pl.edu.agh.gem.internal.client.ExternalEmailSenderClient
import pl.edu.agh.gem.internal.filereader.FileReader
import pl.edu.agh.gem.internal.model.PasswordEmailDetails
import pl.edu.agh.gem.internal.model.PasswordRecoveryEmailDetails
import pl.edu.agh.gem.internal.model.VerificationEmailDetails

@Service
class EmailService(
    private val externalEmailSenderClient: ExternalEmailSenderClient,
    private val fileReader: FileReader,

) {
    fun sendVerificationEmail(emailDetails: VerificationEmailDetails) {
        val subject = "Group Expense Manager - Verification code"
        val text = fileReader.read("templates/verification.html")
            .replace("\${emailDetails.username}", emailDetails.username)
            .replace("\${emailDetails.code}", emailDetails.code)

        externalEmailSenderClient.sendEmail(emailDetails.email, subject, text)
    }

    fun sendPasswordRecoveryEmail(emailDetails: PasswordRecoveryEmailDetails) {
        val subject = "Group Expense Manager - Password Recovery"
        val text = fileReader.read("templates/password-recovery.html")
            .replace("\${emailDetails.username}", emailDetails.username)
            .replace("\${emailDetails.link}", emailDetails.link)
        externalEmailSenderClient.sendEmail(emailDetails.email, subject, text)
    }

    fun sendPasswordEmail(emailDetails: PasswordEmailDetails) {
        val subject = "Group Expense Manager - New Password"
        val text = fileReader.read("templates/new-password.html")
            .replace("\${emailDetails.username}", emailDetails.username)
            .replace("\${emailDetails.password}", emailDetails.password)
        externalEmailSenderClient.sendEmail(emailDetails.email, subject, text)
    }
}

@ConfigurationProperties(prefix = "spring.mail")
data class EmailProperties(
    val username: String,
)
