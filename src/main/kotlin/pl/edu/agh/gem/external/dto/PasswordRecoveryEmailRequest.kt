package pl.edu.agh.gem.external.dto

import pl.edu.agh.gem.internal.model.PasswordRecoveryEmailDetails

data class PasswordRecoveryEmailRequest(
    val userId: String,
    val email: String,
    val link: String,
) {
    fun toDomain() =
        PasswordRecoveryEmailDetails(
            userId = userId,
            email = email,
            link = link,
        )
}
