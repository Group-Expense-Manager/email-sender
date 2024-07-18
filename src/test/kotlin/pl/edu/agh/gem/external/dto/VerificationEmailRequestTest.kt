package pl.edu.agh.gem.external.dto

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import pl.edu.agh.gem.util.createVerificationEmailRequest

class VerificationEmailRequestTest : ShouldSpec({

    should("map correct to VerificationEmailDetails") {
        // given
        val verificationEmailRequest = createVerificationEmailRequest()

        // when
        val verificationEmailDetail = verificationEmailRequest.toDomain()

        // then
        verificationEmailDetail.also {
            it.username shouldBe verificationEmailRequest.username
            it.email shouldBe verificationEmailRequest.email
            it.code shouldBe verificationEmailRequest.code
        }
    }
},)
