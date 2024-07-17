package pl.edu.agh.gem.internal.service

import io.kotest.core.spec.style.ShouldSpec
import org.mockito.kotlin.anyVararg
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.springframework.mail.SimpleMailMessage
import pl.edu.agh.gem.internal.client.ExternalEmailSenderClient
import pl.edu.agh.gem.util.createPasswordEmailDetails
import pl.edu.agh.gem.util.createPasswordRecoveryEmailDetails
import pl.edu.agh.gem.util.createVerificationEmailDetails

class EmailServiceTest : ShouldSpec({
    val externalEmailSenderClient = mock<ExternalEmailSenderClient> { }
    val emailProperties = mock<EmailProperties> { }

    val emailService = EmailService(
        externalEmailSenderClient = externalEmailSenderClient,
        emailProperties = emailProperties,
    )

    should("send verification email") {
        // given
        val verificationEmailDetails = createVerificationEmailDetails()

        // when
        emailService.sendVerificationEmail(verificationEmailDetails)

        // then
        verify(externalEmailSenderClient, times(1)).sendEmail(
            anyVararg(
                SimpleMailMessage::class,
            ),
        )
    }

    should("send password-recovery email") {
        // given
        val passwordRecoveryEmailDetails = createPasswordRecoveryEmailDetails()

        // when
        emailService.sendPasswordRecoveryEmail(passwordRecoveryEmailDetails)

        // then
        verify(externalEmailSenderClient, times(1)).sendEmail(
            anyVararg(
                SimpleMailMessage::class,
            ),
        )
    }

    should("send password email") {
        // given
        val passwordEmailDetails = createPasswordEmailDetails()

        // when
        emailService.sendPasswordEmail(passwordEmailDetails)

        // then
        verify(externalEmailSenderClient, times(1)).sendEmail(
            anyVararg(
                SimpleMailMessage::class,
            ),
        )
    }
},)
