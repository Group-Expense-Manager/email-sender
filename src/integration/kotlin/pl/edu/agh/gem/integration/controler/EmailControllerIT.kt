package pl.edu.agh.gem.integration.controler

import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldContain
import org.springframework.http.HttpStatus.OK
import pl.edu.agh.gem.assertion.shouldHaveHttpStatus
import pl.edu.agh.gem.integration.BaseIntegrationSpec
import pl.edu.agh.gem.integration.ability.ServiceTestClient
import pl.edu.agh.gem.integration.environment.ProjectConfig.greenMail
import pl.edu.agh.gem.integration.environment.ProjectConfig.stubGreenMail
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
                it.content.toString() shouldContain verificationEmailRequest.code
            }
        }
    },
)
