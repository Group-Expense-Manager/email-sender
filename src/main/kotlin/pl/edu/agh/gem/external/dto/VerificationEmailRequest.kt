package pl.edu.agh.gem.external.dto

import pl.edu.agh.gem.internal.model.VerificationEmailDetails

data class VerificationEmailRequest(
    val email: String,
    val code: String,
) {
    fun toDomain() =
        VerificationEmailDetails(
            email = email,
            code = code,
        )
}
