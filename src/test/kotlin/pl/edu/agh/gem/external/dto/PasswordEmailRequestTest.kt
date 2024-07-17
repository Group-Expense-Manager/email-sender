package pl.edu.agh.gem.external.dto

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import pl.edu.agh.gem.util.createPasswordEmailRequest

class PasswordEmailRequestTest : ShouldSpec({

    should("map correct to PasswordEmailDetails") {
        // given
        val email = "email@email.com"
        val password = "password"
        val passwordEmailRequest = createPasswordEmailRequest(
            email = email,
            password = password,
        )

        // when
        val passwordEmailDetail = passwordEmailRequest.toDomain()

        // then
        passwordEmailDetail.also {
            it.email shouldBe email
            it.password shouldBe password
        }
    }
},)
