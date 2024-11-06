package pl.edu.agh.gem.external.dto

import pl.edu.agh.gem.internal.model.ReportEmailDetails

data class ReportEmailRequest(
    val userId: String,
    val title: String,
    val groupId: String,
    val attachmentId: String,
) {
    fun toDomain() = ReportEmailDetails(
        userId = userId,
        title = title,
        groupId = groupId,
        attachmentId = attachmentId,
    )
}
