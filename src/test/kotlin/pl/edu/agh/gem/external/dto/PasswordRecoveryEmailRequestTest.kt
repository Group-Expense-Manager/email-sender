package pl.edu.agh.gem.external.dto

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import pl.edu.agh.gem.util.createPasswordRecoveryEmailRequest

class PasswordRecoveryEmailRequestTest : ShouldSpec({

    should("map correct to PasswordRecoveryEmailDetails") {
        // given
        val passwordRecoveryEmailRequest = createPasswordRecoveryEmailRequest()

        // when
        val passwordRecoveryEmailDetail = passwordRecoveryEmailRequest.toDomain()

        // then
        passwordRecoveryEmailDetail.also {
            it.userId shouldBe passwordRecoveryEmailRequest.userId
            it.email shouldBe passwordRecoveryEmailRequest.email
            it.link shouldBe passwordRecoveryEmailRequest.link
        }
    }
})
