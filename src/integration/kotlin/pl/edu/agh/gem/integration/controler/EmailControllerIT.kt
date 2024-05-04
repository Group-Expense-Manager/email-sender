package pl.edu.agh.gem.integration.controler

import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldContain
import org.springframework.http.HttpStatus
import pl.edu.agh.gem.assertion.shouldHaveHttpStatus
import pl.edu.agh.gem.integration.BaseIntegrationSpec
import pl.edu.agh.gem.integration.ability.ServiceTestClient
import pl.edu.agh.gem.integration.environment.ProjectConfig.greenMail
import pl.edu.agh.gem.util.createVerificationEmailRequest

class EmailControllerIT(
    private val service: ServiceTestClient,
) : BaseIntegrationSpec(
    {
        should("send verification email") {
            // given
            val verificationEmailRequest = createVerificationEmailRequest("receiver", "123456")
            greenMail.setUser("spring", "boot")

            // when
            val response = service.sendVerificationEmail(verificationEmailRequest)

            // then
            response shouldHaveHttpStatus HttpStatus.OK

            val actualReceivedMessage = greenMail.receivedMessages[0]

            actualReceivedMessage.also {
                it.from.size shouldBe 1
                it.from[0].toString() shouldBe "spring"
                it.allRecipients.size shouldBe 1
                it.allRecipients[0].toString() shouldBe "receiver"
                it.subject shouldBe "Group Expense Manager - verification code"
                it.content.toString() shouldContain "123456"
            }
        }
    },
)
