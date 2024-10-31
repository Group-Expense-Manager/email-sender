package pl.edu.agh.gem.internal.service

import io.kotest.core.spec.style.ShouldSpec
import jakarta.mail.internet.MimeMessage
import org.mockito.kotlin.any
import org.mockito.kotlin.anyVararg
import org.mockito.kotlin.eq
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import pl.edu.agh.gem.internal.client.AttachmentStoreClient
import pl.edu.agh.gem.internal.client.ExternalEmailSenderClient
import pl.edu.agh.gem.internal.factory.EmailFactory
import pl.edu.agh.gem.internal.filereader.FileReader
import pl.edu.agh.gem.util.DummyData.DUMMY_HTML
import pl.edu.agh.gem.util.TestHelper.CSV_FILE
import pl.edu.agh.gem.util.createPasswordEmailDetails
import pl.edu.agh.gem.util.createPasswordRecoveryEmailDetails
import pl.edu.agh.gem.util.createReportEmailDetails
import pl.edu.agh.gem.util.createVerificationEmailDetails

class EmailServiceTest : ShouldSpec({
    val externalEmailSenderClient = mock<ExternalEmailSenderClient> { }
    val attachmentStoreClient = mock<AttachmentStoreClient> { }
    val fileReader = mock<FileReader>()
    val emailFactory = mock<EmailFactory> {}
    val mimeMessage = mock<MimeMessage> {}

    val emailService = EmailService(
        externalEmailSenderClient = externalEmailSenderClient,
        attachmentStoreClient = attachmentStoreClient,
        fileReader = fileReader,
        emailFactory = emailFactory,
    )

    should("send verification email") {
        // given
        val verificationEmailDetails = createVerificationEmailDetails()
        whenever(fileReader.read(any())).thenReturn(DUMMY_HTML)
        whenever(emailFactory.createEmail(any(), any(), any(), eq(null))).thenReturn(mimeMessage)

        // when
        emailService.sendVerificationEmail(verificationEmailDetails)

        // then
        verify(fileReader, times(1)).read(any())
        verify(externalEmailSenderClient, times(1)).sendEmail(any())
    }

    should("send password-recovery email") {
        // given
        val passwordRecoveryEmailDetails = createPasswordRecoveryEmailDetails()
        whenever(fileReader.read(anyVararg())).thenReturn(DUMMY_HTML)
        whenever(emailFactory.createEmail(any(), any(), any(), eq(null))).thenReturn(mimeMessage)

        // when
        emailService.sendPasswordRecoveryEmail(passwordRecoveryEmailDetails)

        // then
        verify(fileReader, times(1)).read(any())
        verify(externalEmailSenderClient, times(1)).sendEmail(any())
    }

    should("send password email") {
        // given
        val passwordEmailDetails = createPasswordEmailDetails()
        whenever(fileReader.read(anyVararg())).thenReturn(DUMMY_HTML)
        whenever(emailFactory.createEmail(any(), any(), any(), eq(null))).thenReturn(mimeMessage)

        // when
        emailService.sendPasswordEmail(passwordEmailDetails)

        // then
        verify(fileReader, times(1)).read(any())
        verify(externalEmailSenderClient, times(1)).sendEmail(any())
    }

    should("send report email") {
        // given
        val reportEmailDetails = createReportEmailDetails()
        whenever(fileReader.read(anyVararg())).thenReturn(DUMMY_HTML)
        whenever(emailFactory.createEmail(any(), any(), any(), any())).thenReturn(mimeMessage)
        whenever(attachmentStoreClient.getReport(any(), any())).thenReturn(CSV_FILE)
        // when
        emailService.sendReport(reportEmailDetails)

        // then
        verify(fileReader, times(1)).read(any())
        verify(externalEmailSenderClient, times(1)).sendEmail(any())
        verify(attachmentStoreClient, times(1)).getReport(any(), any())
    }
},)
