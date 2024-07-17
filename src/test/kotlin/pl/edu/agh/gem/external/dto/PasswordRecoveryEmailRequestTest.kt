package pl.edu.agh.gem.external.dto

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import pl.edu.agh.gem.util.createPasswordRecoveryEmailRequest

class PasswordRecoveryEmailRequestTest : ShouldSpec({

    should("map correct to PasswordRecoveryEmailDetails") {
        // given
        val email = "email@email.com"
        val link = "some/link"
        val passwordRecoveryEmailRequest = createPasswordRecoveryEmailRequest(
            email = email,
            link = link,
        )

        // when
        val passwordRecoveryEmailDetail = passwordRecoveryEmailRequest.toDomain()

        // then
        passwordRecoveryEmailDetail.also {
            it.email shouldBe email
            it.link shouldBe link
        }
    }
},)
