package pl.edu.agh.gem.internal.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service
import pl.edu.agh.gem.internal.model.VerificationEmailDetails

@Service
class EmailService(
    @Autowired private val javaMailSender: JavaMailSender,
    @Autowired private val emailProperties: EmailProperties,

) {
    fun sendVerificationEmail(emailDetails: VerificationEmailDetails) {
        val email = SimpleMailMessage()
        email.from = emailProperties.username
        email.setTo(emailDetails.email)

        email.subject = "Group Expense Manager - verification code"
        email.text = "Here is your verification code: ${emailDetails.code}. Use it to verify your account.\nregards,\nGEM team"

        javaMailSender.send(email)
    }
}

@ConfigurationProperties(prefix = "spring.mail")
data class EmailProperties(
    val username: String,
)
