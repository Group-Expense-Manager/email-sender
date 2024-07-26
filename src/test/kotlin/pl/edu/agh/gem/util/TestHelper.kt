package pl.edu.agh.gem.util

import pl.edu.agh.gem.external.dto.PasswordEmailRequest
import pl.edu.agh.gem.external.dto.PasswordRecoveryEmailRequest
import pl.edu.agh.gem.external.dto.VerificationEmailRequest
import pl.edu.agh.gem.helper.user.DummyUser.EMAIL
import pl.edu.agh.gem.internal.model.PasswordEmailDetails
import pl.edu.agh.gem.internal.model.PasswordRecoveryEmailDetails
import pl.edu.agh.gem.internal.model.VerificationEmailDetails
import pl.edu.agh.gem.util.DummyData.DUMMY_CODE
import pl.edu.agh.gem.util.DummyData.DUMMY_LINK
import pl.edu.agh.gem.util.DummyData.DUMMY_PASSWORD
import pl.edu.agh.gem.util.DummyData.DUMMY_USERNAME

fun createVerificationEmailRequest(
    username: String = DUMMY_USERNAME,
    email: String = EMAIL,
    code: String = DUMMY_CODE,
) = VerificationEmailRequest(
    username = username,
    email = email,
    code = code,
)

fun createVerificationEmailDetails(
    username: String = DUMMY_USERNAME,
    email: String = EMAIL,
    code: String = DUMMY_CODE,
) = VerificationEmailDetails(
    username = username,
    email = email,
    code = code,
)

fun createPasswordRecoveryEmailRequest(
    username: String = DUMMY_USERNAME,
    email: String = EMAIL,
    link: String = DUMMY_LINK,
) = PasswordRecoveryEmailRequest(
    username = username,
    email = email,
    link = link,
)

fun createPasswordRecoveryEmailDetails(
    username: String = DUMMY_USERNAME,
    email: String = EMAIL,
    link: String = DUMMY_LINK,
) = PasswordRecoveryEmailDetails(
    username = username,
    email = email,
    link = link,
)

fun createPasswordEmailRequest(
    username: String = DUMMY_USERNAME,
    email: String = EMAIL,
    password: String = DUMMY_PASSWORD,
) = PasswordEmailRequest(
    username = username,
    email = email,
    password = password,
)

fun createPasswordEmailDetails(
    username: String = DUMMY_USERNAME,
    email: String = EMAIL,
    password: String = DUMMY_PASSWORD,
) = PasswordEmailDetails(
    username = username,
    email = email,
    password = password,
)

object DummyData {
    const val DUMMY_USERNAME = "user123"
    const val DUMMY_CODE = "user123"
    const val DUMMY_LINK = "http:/some.url/some/link"
    const val DUMMY_PASSWORD = "Password!123"
    const val DUMMY_SUBJECT = "HI"
    const val DUMMY_HTML = "<html></html>"
}
