package pl.edu.agh.gem.internal.model

data class PasswordRecoveryEmailDetails(
    val username: String,
    val email: String,
    val link: String,
)
