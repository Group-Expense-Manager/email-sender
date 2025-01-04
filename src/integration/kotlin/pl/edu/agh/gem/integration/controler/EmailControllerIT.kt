package pl.edu.agh.gem.integration.controler

import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import jakarta.mail.Part
import jakarta.mail.internet.MimeMessage
import jakarta.mail.internet.MimeMultipart
import org.springframework.http.HttpStatus.OK
import pl.edu.agh.gem.assertion.shouldHaveHttpStatus
import pl.edu.agh.gem.helper.group.DummyGroup.GROUP_ID
import pl.edu.agh.gem.helper.user.DummyUser.EMAIL
import pl.edu.agh.gem.helper.user.DummyUser.USER_ID
import pl.edu.agh.gem.integration.BaseIntegrationSpec
import pl.edu.agh.gem.integration.ability.ServiceTestClient
import pl.edu.agh.gem.integration.ability.stubEmailAddress
import pl.edu.agh.gem.integration.ability.stubGetUsername
import pl.edu.agh.gem.integration.ability.stubReport
import pl.edu.agh.gem.integration.environment.ProjectConfig.greenMail
import pl.edu.agh.gem.integration.environment.ProjectConfig.stubGreenMail
import pl.edu.agh.gem.util.DummyData.ATTACHMENT_ID
import pl.edu.agh.gem.util.DummyData.DUMMY_USERNAME
import pl.edu.agh.gem.util.TestHelper.CSV_FILE
import pl.edu.agh.gem.util.createEmailAddressResponse
import pl.edu.agh.gem.util.createPasswordEmailRequest
import pl.edu.agh.gem.util.createPasswordRecoveryEmailRequest
import pl.edu.agh.gem.util.createReportEmailRequest
import pl.edu.agh.gem.util.createUsernameResponse
import pl.edu.agh.gem.util.createVerificationEmailRequest

class EmailControllerIT(
    private val service: ServiceTestClient,
) : BaseIntegrationSpec(
        {
            should("send verification email") {
                // given

                val verificationEmailRequest = createVerificationEmailRequest()
                val (login, _) = stubGreenMail("spring", "boot")

                // when
                val response = service.sendVerificationEmail(verificationEmailRequest)

                // then
                response shouldHaveHttpStatus OK

                greenMail.receivedMessages.size shouldBe 1
                greenMail.receivedMessages.first().also {
                    it.from.size shouldBe 1
                    it.from.first().toString() shouldBe login
                    it.allRecipients.size shouldBe 1
                    it.allRecipients.first().toString() shouldBe verificationEmailRequest.email
                    it.subject.shouldNotBeNull()
                    it.content.shouldNotBeNull()
                }
            }

            should("send password-recovery email") {
                // given

                val passwordRecoveryEmailRequest = createPasswordRecoveryEmailRequest()
                val (login, _) = stubGreenMail("spring", "boot")
                stubGetUsername(createUsernameResponse(DUMMY_USERNAME), USER_ID)

                // when
                val response = service.sendPasswordRecoveryEmail(passwordRecoveryEmailRequest)

                // then
                response shouldHaveHttpStatus OK

                greenMail.receivedMessages.size shouldBe 2
                greenMail.receivedMessages[1].also {
                    it.from.size shouldBe 1
                    it.from.first().toString() shouldBe login
                    it.allRecipients.size shouldBe 1
                    it.allRecipients.first().toString() shouldBe passwordRecoveryEmailRequest.email
                    it.subject.shouldNotBeNull()
                    it.content.shouldNotBeNull()
                }
            }

            should("send password email") {
                // given

                val passwordEmailRequest = createPasswordEmailRequest()
                val (login, _) = stubGreenMail("spring", "boot")
                stubGetUsername(createUsernameResponse(DUMMY_USERNAME), USER_ID)

                // when
                val response = service.sendPasswordEmail(passwordEmailRequest)

                // then
                response shouldHaveHttpStatus OK

                greenMail.receivedMessages.size shouldBe 3
                greenMail.receivedMessages.last().also {
                    it.from.size shouldBe 1
                    it.from.first().toString() shouldBe login
                    it.allRecipients.size shouldBe 1
                    it.allRecipients.first().toString() shouldBe passwordEmailRequest.email
                    it.subject.shouldNotBeNull()
                    it.content.shouldNotBeNull()
                }
            }

            should("send report email") {
                // given
                val reportEmailRequest = createReportEmailRequest(creatorId = USER_ID)
                val (login, _) = stubGreenMail("spring", "boot")
                stubReport(CSV_FILE, GROUP_ID, ATTACHMENT_ID)
                stubEmailAddress(createEmailAddressResponse(EMAIL), USER_ID)
                stubGetUsername(createUsernameResponse(DUMMY_USERNAME), USER_ID)
                // when
                val response = service.sendReport(reportEmailRequest)

                // then
                response shouldHaveHttpStatus OK

                greenMail.receivedMessages.size shouldBe 4
                greenMail.receivedMessages.last().also {
                    it.from.size shouldBe 1
                    it.from.first().toString() shouldBe login
                    it.allRecipients.size shouldBe 1
                    it.allRecipients.first().toString() shouldBe EMAIL
                    it.subject.shouldNotBeNull()
                    it.content.shouldNotBeNull()
                    hasAttachment(it) shouldBe true
                }
            }
        },
    )

fun hasAttachment(message: MimeMessage): Boolean {
    val content = message.content
    return content is MimeMultipart &&
        (0 until content.count).any { index ->
            content.getBodyPart(index).disposition.equals(Part.ATTACHMENT, ignoreCase = true)
        }
}
