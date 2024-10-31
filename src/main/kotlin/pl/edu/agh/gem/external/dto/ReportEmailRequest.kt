package pl.edu.agh.gem.external.dto

import pl.edu.agh.gem.internal.model.ReportEmailDetails

data class ReportEmailRequest(
    val username: String,
    val email: String,
    val title: String,
    val groupId: String,
    val attachmentId: String,
) {
    fun toDomain() = ReportEmailDetails(
        username = username,
        email = email,
        title = title,
        groupId = groupId,
        attachmentId = attachmentId,
    )
}
