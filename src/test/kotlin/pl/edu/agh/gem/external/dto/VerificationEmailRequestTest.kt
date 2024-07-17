package pl.edu.agh.gem.external.dto

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import pl.edu.agh.gem.util.createVerificationEmailRequest

class VerificationEmailRequestTest : ShouldSpec({

    should("map correct to VerificationEmailDetails") {
        // given
        val email = "email@email.com"
        val code = "123456"
        val verificationEmailRequest = createVerificationEmailRequest(
            email = email,
            code = code,
        )

        // when
        val verificationEmailDetail = verificationEmailRequest.toDomain()

        // then
        verificationEmailDetail.also {
            it.email shouldBe email
            it.code shouldBe code
        }
    }
},)
