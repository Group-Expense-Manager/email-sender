package pl.edu.agh.gem.internal.model

data class PasswordRecoveryEmailDetails(
    val email: String,
    val link: String,
)
