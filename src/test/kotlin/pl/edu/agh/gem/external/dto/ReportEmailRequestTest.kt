package pl.edu.agh.gem.external.dto

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import pl.edu.agh.gem.util.createReportEmailRequest

class ReportEmailRequestTest : ShouldSpec({

    should("map correct to ReportEmailDetails") {
        // given
        val reportEmailRequest = createReportEmailRequest()

        // when
        val reportEmailDetails = reportEmailRequest.toDomain()

        // then
        reportEmailDetails.also {
            it.userId shouldBe reportEmailRequest.userId
            it.title shouldBe reportEmailRequest.title
            it.groupId shouldBe reportEmailRequest.groupId
            it.attachmentId shouldBe reportEmailRequest.attachmentId
        }
    }
},)
