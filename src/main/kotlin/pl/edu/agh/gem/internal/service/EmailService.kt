package pl.edu.agh.gem.internal.service

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.mail.SimpleMailMessage
import org.springframework.stereotype.Service
import pl.edu.agh.gem.internal.client.ExternalEmailSenderClient
import pl.edu.agh.gem.internal.model.PasswordEmailDetails
import pl.edu.agh.gem.internal.model.PasswordRecoveryEmailDetails
import pl.edu.agh.gem.internal.model.VerificationEmailDetails

@Service
class EmailService(
    private val externalEmailSenderClient: ExternalEmailSenderClient,
    private val emailProperties: EmailProperties,

) {
    fun sendVerificationEmail(emailDetails: VerificationEmailDetails) {
        val subject = "Group Expense Manager - Verification code"
        val text = """
            Dear user,
            
            Welcome to Group Expense Manager!
            To complete your registration, please verify your email address by entering the verification code below in the app:
            
            ${emailDetails.code}
            
            If you did not sign up for an account with Group Expense Manager, please ignore this email. For any assistance, feel free to contact our support team.
        
            Best regards,
            GEM Support Team
        """.trimIndent()
        sendEmail(emailDetails.email, subject, text)
    }

    fun sendPasswordRecoveryEmail(emailDetails: PasswordRecoveryEmailDetails) {
        val subject = "Group Expense Manager - Password Recovery"
        val text = """
            Dear user,
            
            We received a request to reset the password for your account. To proceed with the password recovery, please click the link below:
            
            ${emailDetails.link}
            
            After clicking the link, a new password will be generated and sent to you in a separate email.
            If you did not request a password reset, please ignore this email. For further assistance, contact our support team.

            Best regards,
            GEM Support Team
        """.trimIndent()

        sendEmail(emailDetails.email, subject, text)
    }

    fun sendPasswordEmail(emailDetails: PasswordEmailDetails) {
        val subject = "Group Expense Manager - New Password"
        val text = """
            Dear user,
            
            Your password has been successfully reset. Below is your new password:    
                    
            ${emailDetails.password}
            
            We recommend that you change this password to something more memorable once you log in. You can update your password in the app under the account settings section.
            If you did not request a password reset or if you encounter any issues, please contact our support team immediately.
            
            Best regards,
            GEM Support Team
        """.trimIndent()

        sendEmail(emailDetails.email, subject, text)
    }

    private fun sendEmail(email: String, subject: String, text: String) {
        val simpleMailMessage = prepareEmail(email)
        simpleMailMessage.subject = subject
        simpleMailMessage.text = text
        externalEmailSenderClient.sendEmail(simpleMailMessage)
    }

    private fun prepareEmail(email: String): SimpleMailMessage {
        val simpleMailMessage = SimpleMailMessage()
        simpleMailMessage.from = emailProperties.username
        simpleMailMessage.setTo(email)

        return simpleMailMessage
    }
}

@ConfigurationProperties(prefix = "spring.mail")
data class EmailProperties(
    val username: String,
)
