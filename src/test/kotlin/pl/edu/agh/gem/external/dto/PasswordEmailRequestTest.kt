package pl.edu.agh.gem.external.dto

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import pl.edu.agh.gem.util.createPasswordEmailRequest

class PasswordEmailRequestTest : ShouldSpec({

    should("map correct to PasswordEmailDetails") {
        // given
        val passwordEmailRequest = createPasswordEmailRequest()

        // when
        val passwordEmailDetail = passwordEmailRequest.toDomain()

        // then
        passwordEmailDetail.also {
            it.username shouldBe passwordEmailRequest.username
            it.email shouldBe passwordEmailRequest.email
            it.password shouldBe passwordEmailRequest.password
        }
    }
},)
