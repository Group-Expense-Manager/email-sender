package pl.edu.agh.gem.util

import pl.edu.agh.gem.external.dto.EmailAddressResponse
import pl.edu.agh.gem.external.dto.PasswordEmailRequest
import pl.edu.agh.gem.external.dto.PasswordRecoveryEmailRequest
import pl.edu.agh.gem.external.dto.ReportEmailRequest
import pl.edu.agh.gem.external.dto.UsernameResponse
import pl.edu.agh.gem.external.dto.VerificationEmailRequest
import pl.edu.agh.gem.helper.group.DummyGroup.GROUP_ID
import pl.edu.agh.gem.helper.user.DummyUser.EMAIL
import pl.edu.agh.gem.helper.user.DummyUser.USER_ID
import pl.edu.agh.gem.internal.model.Attachment
import pl.edu.agh.gem.internal.model.PasswordEmailDetails
import pl.edu.agh.gem.internal.model.PasswordRecoveryEmailDetails
import pl.edu.agh.gem.internal.model.ReportEmailDetails
import pl.edu.agh.gem.internal.model.VerificationEmailDetails
import pl.edu.agh.gem.util.DummyData.ATTACHMENT_ID
import pl.edu.agh.gem.util.DummyData.DUMMY_CODE
import pl.edu.agh.gem.util.DummyData.DUMMY_LINK
import pl.edu.agh.gem.util.DummyData.DUMMY_PASSWORD
import pl.edu.agh.gem.util.DummyData.DUMMY_USERNAME
import pl.edu.agh.gem.util.ResourceLoader.loadResourceAsByteArray

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
    userId: String = USER_ID,
    email: String = EMAIL,
    link: String = DUMMY_LINK,
) = PasswordRecoveryEmailRequest(
    userId = userId,
    email = email,
    link = link,
)

fun createReportEmailRequest(
    creatorId: String = USER_ID,
    title: String = "My report",
    groupId: String = GROUP_ID,
    attachmentId: String = ATTACHMENT_ID,
) = ReportEmailRequest(
    creatorId = creatorId,
    title = title,
    groupId = groupId,
    attachmentId = attachmentId,
)

fun createPasswordRecoveryEmailDetails(
    userId: String = USER_ID,
    email: String = EMAIL,
    link: String = DUMMY_LINK,
) = PasswordRecoveryEmailDetails(
    userId = userId,
    email = email,
    link = link,
)

fun createPasswordEmailRequest(
    userId: String = USER_ID,
    email: String = EMAIL,
    password: String = DUMMY_PASSWORD,
) = PasswordEmailRequest(
    userId = userId,
    email = email,
    password = password,
)

fun createPasswordEmailDetails(
    userId: String = USER_ID,
    email: String = EMAIL,
    password: String = DUMMY_PASSWORD,
) = PasswordEmailDetails(
    userId = userId,
    email = email,
    password = password,
)

fun createReportEmailDetails(
    creatorId: String = USER_ID,
    title: String = "My report",
    groupId: String = GROUP_ID,
    attachmentId: String = ATTACHMENT_ID,
) = ReportEmailDetails(
    creatorId = creatorId,
    title = title,
    groupId = groupId,
    attachmentId = attachmentId,
)

fun createEmailAddressResponse(email: String = EMAIL) =
    EmailAddressResponse(
        email = email,
    )

fun createUsernameResponse(username: String = DUMMY_USERNAME) =
    UsernameResponse(
        username = username,
    )

fun createAttachment(
    title: String = "My report",
    file: ByteArray = TestHelper.CSV_FILE,
    type: String = "csv",
) = Attachment(
    title = title,
    file = file,
    type = type,
)

object DummyData {
    const val DUMMY_USERNAME = "user123"
    const val DUMMY_CODE = "123123"
    const val DUMMY_LINK = "http:/some.url/some/link"
    const val DUMMY_PASSWORD = "Password!123"
    const val DUMMY_SUBJECT = "HI"
    const val DUMMY_HTML = "<html></html>"
    const val ATTACHMENT_ID = "attachmentId"
}

object TestHelper {
    val CSV_FILE = loadResourceAsByteArray("example.csv")
}
