package pl.edu.agh.gem.internal.model

data class PasswordRecoveryEmailDetails(
    val userId: String,
    val email: String,
    val link: String,
)
