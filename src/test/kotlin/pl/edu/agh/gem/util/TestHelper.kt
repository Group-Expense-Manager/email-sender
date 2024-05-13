package pl.edu.agh.gem.util

import pl.edu.agh.gem.external.dto.verification.VerificationEmailRequest
import pl.edu.agh.gem.internal.model.VerificationEmailDetails

fun createVerificationEmailRequest(
    email: String = "email@email.com",
    code: String = "123456",
) = VerificationEmailRequest(
    email = email,
    code = code,
)

fun createVerificationEmailDetails(
    email: String = "email@email.com",
    code: String = "123456",
) = VerificationEmailDetails(
    email = email,
    code = code,
)
