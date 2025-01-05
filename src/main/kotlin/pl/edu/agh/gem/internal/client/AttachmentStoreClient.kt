package pl.edu.agh.gem.internal.client

import pl.edu.agh.gem.internal.model.Attachment

interface AttachmentStoreClient {
    fun getReport(
        groupId: String,
        attachmentId: String,
        title: String,
    ): Attachment
}

class AttachmentStoreClientException(override val message: String?) : RuntimeException()

class RetryableAttachmentStoreClientException(override val message: String?) : RuntimeException()
