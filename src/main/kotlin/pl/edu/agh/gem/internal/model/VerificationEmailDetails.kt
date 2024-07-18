package pl.edu.agh.gem.internal.model

data class VerificationEmailDetails(
    val username: String,
    val email: String,
    val code: String,
)
