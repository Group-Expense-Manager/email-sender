package pl.edu.agh.gem.internal.service

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Service
import pl.edu.agh.gem.internal.client.AttachmentStoreClient
import pl.edu.agh.gem.internal.client.AuthenticatorClient
import pl.edu.agh.gem.internal.client.ExternalEmailSenderClient
import pl.edu.agh.gem.internal.client.UserDetailsManagerClient
import pl.edu.agh.gem.internal.factory.EmailFactory
import pl.edu.agh.gem.internal.filereader.FileReader
import pl.edu.agh.gem.internal.model.PasswordEmailDetails
import pl.edu.agh.gem.internal.model.PasswordRecoveryEmailDetails
import pl.edu.agh.gem.internal.model.ReportEmailDetails
import pl.edu.agh.gem.internal.model.VerificationEmailDetails

@Service
class EmailService(
    private val externalEmailSenderClient: ExternalEmailSenderClient,
    private val attachmentStoreClient: AttachmentStoreClient,
    private val authenticatorClient: AuthenticatorClient,
    private val userDetailsManagerClient: UserDetailsManagerClient,
    private val fileReader: FileReader,
    private val emailFactory: EmailFactory,
) {
    fun sendVerificationEmail(emailDetails: VerificationEmailDetails) {
        val text =
            fileReader.read(VERIFICATION_HTML_PATH)
                .replace(USERNAME_INTERPOLATION_STRING, emailDetails.username)
                .replace(CODE_INTERPOLATION_STRING, emailDetails.code)

        externalEmailSenderClient.sendEmail(emailFactory.createEmail(emailDetails.email, VERIFICATION_EMAIL_SUBJECT, text))
    }

    fun sendPasswordRecoveryEmail(emailDetails: PasswordRecoveryEmailDetails) {
        val username = userDetailsManagerClient.getUsername(emailDetails.userId)
        val text =
            fileReader.read(PASSWORD_RECOVERY_HTML_PATH)
                .replace(USERNAME_INTERPOLATION_STRING, username)
                .replace(LINK_INTERPOLATION_STRING, emailDetails.link)
        externalEmailSenderClient.sendEmail(emailFactory.createEmail(emailDetails.email, PASSWORD_RECOVERY_EMAIL_SUBJECT, text))
    }

    fun sendPasswordEmail(emailDetails: PasswordEmailDetails) {
        val username = userDetailsManagerClient.getUsername(emailDetails.userId)
        val text =
            fileReader.read(PASSWORD_HTML_PATH)
                .replace(USERNAME_INTERPOLATION_STRING, username)
                .replace(PASSWORD_INTERPOLATION_STRING, emailDetails.password)
        externalEmailSenderClient.sendEmail(emailFactory.createEmail(emailDetails.email, PASSWORD_EMAIL_SUBJECT, text))
    }

    fun sendReport(emailDetails: ReportEmailDetails) {
        val username = userDetailsManagerClient.getUsername(emailDetails.creatorId)
        val text =
            fileReader.read(REPORT_HTML_PATH)
                .replace(USERNAME_INTERPOLATION_STRING, username)
        val email = authenticatorClient.getEmailAddress(emailDetails.creatorId)
        val report = attachmentStoreClient.getReport(emailDetails.groupId, emailDetails.attachmentId, emailDetails.title)
        externalEmailSenderClient.sendEmail(emailFactory.createEmail(email, REPORT_EMAIL_SUBJECT, text, report))
    }

    companion object {
        const val VERIFICATION_EMAIL_SUBJECT = "Group Expense Manager - Verification Code"
        const val PASSWORD_RECOVERY_EMAIL_SUBJECT = "Group Expense Manager - Password Recovery"
        const val PASSWORD_EMAIL_SUBJECT = "Group Expense Manager - New Password"
        const val REPORT_EMAIL_SUBJECT = "Group Expense Manager - Report"

        const val VERIFICATION_HTML_PATH = "templates/verification.html"
        const val PASSWORD_RECOVERY_HTML_PATH = "templates/password-recovery.html"
        const val PASSWORD_HTML_PATH = "templates/new-password.html"
        const val REPORT_HTML_PATH = "templates/report.html"

        const val USERNAME_INTERPOLATION_STRING = "\${emailDetails.username}"
        const val CODE_INTERPOLATION_STRING = "\${emailDetails.code}"
        const val LINK_INTERPOLATION_STRING = "\${emailDetails.link}"
        const val PASSWORD_INTERPOLATION_STRING = "\${emailDetails.password}"
    }
}

@ConfigurationProperties(prefix = "spring.mail")
data class EmailProperties(
    val username: String,
)
