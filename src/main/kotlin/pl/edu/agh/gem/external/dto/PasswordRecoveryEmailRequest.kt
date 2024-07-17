package pl.edu.agh.gem.external.dto

import pl.edu.agh.gem.internal.model.PasswordRecoveryEmailDetails

data class PasswordRecoveryEmailRequest(
    val email: String,
    val link: String,
) {
    fun toDomain() =
        PasswordRecoveryEmailDetails(
            email = email,
            link = link,
        )
}
