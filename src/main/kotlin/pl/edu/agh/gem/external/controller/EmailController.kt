package pl.edu.agh.gem.external.controller

import org.springframework.http.HttpStatus.OK
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import pl.edu.agh.gem.external.dto.PasswordEmailRequest
import pl.edu.agh.gem.external.dto.PasswordRecoveryEmailRequest
import pl.edu.agh.gem.external.dto.ReportEmailRequest
import pl.edu.agh.gem.external.dto.VerificationEmailRequest
import pl.edu.agh.gem.internal.service.EmailService
import pl.edu.agh.gem.media.InternalApiMediaType.APPLICATION_JSON_INTERNAL_VER_1
import pl.edu.agh.gem.paths.Paths.INTERNAL

@RestController
@RequestMapping(INTERNAL)
class EmailController(
    private val emailService: EmailService,
) {

    @PostMapping("verification", consumes = [APPLICATION_JSON_INTERNAL_VER_1])
    @ResponseStatus(OK)
    fun sendVerificationEmail(
        @RequestBody verificationEmailRequest: VerificationEmailRequest,
    ) {
        emailService.sendVerificationEmail(verificationEmailRequest.toDomain())
    }

    @PostMapping("recover-password", consumes = [APPLICATION_JSON_INTERNAL_VER_1])
    @ResponseStatus(OK)
    fun sendPasswordRecoveryEmail(
        @RequestBody passwordRecoveryEmailRequest: PasswordRecoveryEmailRequest,
    ) {
        emailService.sendPasswordRecoveryEmail(passwordRecoveryEmailRequest.toDomain())
    }

    @PostMapping("password", consumes = [APPLICATION_JSON_INTERNAL_VER_1])
    @ResponseStatus(OK)
    fun sendPasswordEmail(
        @RequestBody passwordEmailRequest: PasswordEmailRequest,
    ) {
        emailService.sendPasswordEmail(passwordEmailRequest.toDomain())
    }

    @PostMapping("report", consumes = [APPLICATION_JSON_INTERNAL_VER_1])
    @ResponseStatus(OK)
    fun sendReport(
        @RequestBody reportEmailRequest: ReportEmailRequest,
    ) {
        emailService.sendReport(reportEmailRequest.toDomain())
    }
}
