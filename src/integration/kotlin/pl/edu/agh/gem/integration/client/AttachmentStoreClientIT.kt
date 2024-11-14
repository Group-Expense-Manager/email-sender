package pl.edu.agh.gem.integration.client

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldNotBe
import org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR
import org.springframework.http.HttpStatus.NOT_ACCEPTABLE
import pl.edu.agh.gem.helper.group.DummyGroup.GROUP_ID
import pl.edu.agh.gem.integration.BaseIntegrationSpec
import pl.edu.agh.gem.integration.ability.stubReport
import pl.edu.agh.gem.internal.client.AttachmentStoreClient
import pl.edu.agh.gem.internal.client.AttachmentStoreClientException
import pl.edu.agh.gem.internal.client.RetryableAttachmentStoreClientException
import pl.edu.agh.gem.util.DummyData.ATTACHMENT_ID
import pl.edu.agh.gem.util.DummyData.DUMMY_SUBJECT
import pl.edu.agh.gem.util.TestHelper.CSV_FILE

class AttachmentStoreClientIT(
    private val attachmentStoreClient: AttachmentStoreClient,
) : BaseIntegrationSpec({
    should("get report") {
        // given
        stubReport(CSV_FILE, GROUP_ID, ATTACHMENT_ID)

        // when
        val result = attachmentStoreClient.getReport(GROUP_ID, ATTACHMENT_ID, DUMMY_SUBJECT)

        // then
        result shouldNotBe null
    }

    should("throw AttachmentStoreClientException when we send bad request") {
        // given
        stubReport(CSV_FILE, GROUP_ID, ATTACHMENT_ID, NOT_ACCEPTABLE)

        // when & then
        shouldThrow<AttachmentStoreClientException> {
            attachmentStoreClient.getReport(GROUP_ID, ATTACHMENT_ID, DUMMY_SUBJECT)
        }
    }

    should("throw RetryableAttachmentStoreClientException when client has internal error") {
        // given
        stubReport(CSV_FILE, GROUP_ID, ATTACHMENT_ID, INTERNAL_SERVER_ERROR)

        // when & then
        shouldThrow<RetryableAttachmentStoreClientException> {
            attachmentStoreClient.getReport(GROUP_ID, ATTACHMENT_ID, DUMMY_SUBJECT)
        }
    }
},)
