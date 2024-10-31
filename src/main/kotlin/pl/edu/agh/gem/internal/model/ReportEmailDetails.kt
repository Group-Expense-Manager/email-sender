package pl.edu.agh.gem.internal.model

data class ReportEmailDetails(
    val username: String,
    val email: String,
    val title: String,
    val groupId: String,
    val attachmentId: String,
)
