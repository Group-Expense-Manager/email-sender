package pl.edu.agh.gem.external.dto

import pl.edu.agh.gem.internal.model.ReportEmailDetails

data class ReportEmailRequest(
    val creatorId: String,
    val title: String,
    val groupId: String,
    val attachmentId: String,
) {
    fun toDomain() = ReportEmailDetails(
        creatorId = creatorId,
        title = title,
        groupId = groupId,
        attachmentId = attachmentId,
    )
}
