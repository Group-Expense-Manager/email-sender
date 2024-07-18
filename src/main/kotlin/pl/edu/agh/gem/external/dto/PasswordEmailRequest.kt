package pl.edu.agh.gem.external.dto

import pl.edu.agh.gem.internal.model.PasswordEmailDetails

data class PasswordEmailRequest(
    val username: String,
    val email: String,
    val password: String,
) {
    fun toDomain() =
        PasswordEmailDetails(
            username = username,
            email = email,
            password = password,
        )
}
