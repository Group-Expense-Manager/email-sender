package pl.edu.agh.gem.external.dto

import pl.edu.agh.gem.internal.model.PasswordEmailDetails

data class PasswordEmailRequest(
    val userId: String,
    val email: String,
    val password: String,
) {
    fun toDomain() =
        PasswordEmailDetails(
            userId = userId,
            email = email,
            password = password,
        )
}
