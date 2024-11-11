package pl.edu.agh.gem.internal.client

interface AttachmentStoreClient {
    fun getReport(groupId: String, attachmentId: String): ByteArray
}

class AttachmentStoreClientException(override val message: String?) : RuntimeException()

class RetryableAttachmentStoreClientException(override val message: String?) : RuntimeException()
