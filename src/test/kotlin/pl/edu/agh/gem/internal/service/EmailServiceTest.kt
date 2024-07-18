package pl.edu.agh.gem.internal.service

import io.kotest.core.spec.style.ShouldSpec
import org.mockito.kotlin.anyVararg
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import pl.edu.agh.gem.internal.client.ExternalEmailSenderClient
import pl.edu.agh.gem.internal.filereader.FileReader
import pl.edu.agh.gem.util.createPasswordEmailDetails
import pl.edu.agh.gem.util.createPasswordRecoveryEmailDetails
import pl.edu.agh.gem.util.createVerificationEmailDetails

class EmailServiceTest : ShouldSpec({
    val externalEmailSenderClient = mock<ExternalEmailSenderClient> { }
    val fileReader = mock<FileReader>()

    val emailService = EmailService(
        externalEmailSenderClient = externalEmailSenderClient,
        fileReader = fileReader,
    )

    should("send verification email") {
        // given
        val verificationEmailDetails = createVerificationEmailDetails()
        whenever(fileReader.read(anyVararg())).thenReturn("<html></html>")

        // when
        emailService.sendVerificationEmail(verificationEmailDetails)

        // then
        verify(externalEmailSenderClient, times(1)).sendEmail(
            anyVararg(String::class),
            anyVararg(String::class),
            anyVararg(String::class),
        )
    }

    should("send password-recovery email") {
        // given
        val passwordRecoveryEmailDetails = createPasswordRecoveryEmailDetails()
        whenever(fileReader.read(anyVararg())).thenReturn("<html></html>")

        // when
        emailService.sendPasswordRecoveryEmail(passwordRecoveryEmailDetails)

        // then
        verify(externalEmailSenderClient, times(1)).sendEmail(
            anyVararg(String::class),
            anyVararg(String::class),
            anyVararg(String::class),
        )
    }

    should("send password email") {
        // given
        val passwordEmailDetails = createPasswordEmailDetails()
        whenever(fileReader.read(anyVararg())).thenReturn("<html></html>")

        // when
        emailService.sendPasswordEmail(passwordEmailDetails)

        // then
        verify(externalEmailSenderClient, times(1)).sendEmail(
            anyVararg(String::class),
            anyVararg(String::class),
            anyVararg(String::class),

        )
    }
},)
