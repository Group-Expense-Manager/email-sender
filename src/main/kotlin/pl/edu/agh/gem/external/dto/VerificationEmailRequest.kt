package pl.edu.agh.gem.external.dto

import pl.edu.agh.gem.internal.model.VerificationEmailDetails

data class VerificationEmailRequest(
    val username: String,
    val email: String,
    val code: String,
) {
    fun toDomain() =
        VerificationEmailDetails(
            username = username,
            email = email,
            code = code,
        )
}
