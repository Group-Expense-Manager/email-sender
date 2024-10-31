package pl.edu.agh.gem.util

import org.springframework.core.io.ByteArrayResource
import pl.edu.agh.gem.external.dto.PasswordEmailRequest
import pl.edu.agh.gem.external.dto.PasswordRecoveryEmailRequest
import pl.edu.agh.gem.external.dto.ReportEmailRequest
import pl.edu.agh.gem.external.dto.VerificationEmailRequest
import pl.edu.agh.gem.helper.group.DummyGroup.GROUP_ID
import pl.edu.agh.gem.helper.user.DummyUser.EMAIL
import pl.edu.agh.gem.internal.model.Attachment
import pl.edu.agh.gem.internal.model.PasswordEmailDetails
import pl.edu.agh.gem.internal.model.PasswordRecoveryEmailDetails
import pl.edu.agh.gem.internal.model.ReportEmailDetails
import pl.edu.agh.gem.internal.model.VerificationEmailDetails
import pl.edu.agh.gem.util.DummyData.ATTACHMENT_ID
import pl.edu.agh.gem.util.DummyData.DUMMY_CODE
import pl.edu.agh.gem.util.DummyData.DUMMY_FILE_NAME
import pl.edu.agh.gem.util.DummyData.DUMMY_LINK
import pl.edu.agh.gem.util.DummyData.DUMMY_PASSWORD
import pl.edu.agh.gem.util.DummyData.DUMMY_USERNAME
import pl.edu.agh.gem.util.ResourceLoader.loadResourceAsByteArray
import pl.edu.agh.gem.util.TestHelper.CSV_FILE

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

fun createReportEmailRequest(
    username: String = DUMMY_USERNAME,
    email: String = EMAIL,
    title: String = "My report",
    groupId: String = GROUP_ID,
    attachmentId: String = ATTACHMENT_ID,
) = ReportEmailRequest(
    username = username,
    email = email,
    title = title,
    groupId = groupId,
    attachmentId = attachmentId,
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

fun createReportEmailDetails(
    username: String = DUMMY_USERNAME,
    email: String = EMAIL,
    title: String = "My report",
    groupId: String = GROUP_ID,
    attachmentId: String = ATTACHMENT_ID,
) = ReportEmailDetails(
    username = username,
    email = email,
    title = title,
    groupId = groupId,
    attachmentId = attachmentId,
)

fun createAttachment(
    name: String = DUMMY_FILE_NAME,
    file: ByteArrayResource = ByteArrayResource(CSV_FILE),
) = Attachment(
    title = name,
    file = file,
)

object DummyData {
    const val DUMMY_USERNAME = "user123"
    const val DUMMY_CODE = "user123"
    const val DUMMY_LINK = "http:/some.url/some/link"
    const val DUMMY_PASSWORD = "Password!123"
    const val DUMMY_SUBJECT = "HI"
    const val DUMMY_HTML = "<html></html>"
    const val DUMMY_FILE_NAME = "example.csv"
    const val ATTACHMENT_ID = "attachmentId"
}

object TestHelper {
    val CSV_FILE = loadResourceAsByteArray("example.csv")
}
