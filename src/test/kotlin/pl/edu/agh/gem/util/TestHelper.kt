package pl.edu.agh.gem.util

import pl.edu.agh.gem.external.dto.PasswordEmailRequest
import pl.edu.agh.gem.external.dto.PasswordRecoveryEmailRequest
import pl.edu.agh.gem.external.dto.VerificationEmailRequest
import pl.edu.agh.gem.internal.model.PasswordEmailDetails
import pl.edu.agh.gem.internal.model.PasswordRecoveryEmailDetails
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

fun createPasswordRecoveryEmailRequest(
    email: String = "email@email.com",
    link: String = "some/link",
) = PasswordRecoveryEmailRequest(
    email = email,
    link = link,
)

fun createPasswordRecoveryEmailDetails(
    email: String = "email@email.com",
    link: String = "some/link",
) = PasswordRecoveryEmailDetails(
    email = email,
    link = link,
)

fun createPasswordEmailRequest(
    email: String = "email@email.com",
    password: String = "Password!123",
) = PasswordEmailRequest(
    email = email,
    password = password,
)

fun createPasswordEmailDetails(
    email: String = "email@email.com",
    password: String = "Password!123",
) = PasswordEmailDetails(
    email = email,
    password = password,
)
