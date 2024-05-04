package pl.edu.agh.gem.internal.service

import io.kotest.core.spec.style.ShouldSpec
import org.mockito.kotlin.anyVararg
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import pl.edu.agh.gem.util.createVerificationEmailDetails

class EmailServiceTest : ShouldSpec({
    val javaMailSender = mock<JavaMailSender> { }
    val emailProperties = mock<EmailProperties> { }

    val emailService = EmailService(
        javaMailSender = javaMailSender,
        emailProperties = emailProperties,
    )

    should("send email") {
        // given
        val verificationEmailDetails = createVerificationEmailDetails()

        // when
        emailService.sendVerificationEmail(verificationEmailDetails)

        // then
        verify(javaMailSender, times(1)).send(
            anyVararg(
                SimpleMailMessage::class,
            ),
        )
    }
},)
