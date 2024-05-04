package pl.edu.agh.gem.external.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import pl.edu.agh.gem.external.dto.verification.VerificationEmailRequest
import pl.edu.agh.gem.internal.service.EmailService
import pl.edu.agh.gem.media.InternalApiMediaType.APPLICATION_JSON_INTERNAL_VER_1

@RestController
class EmailController(
    @Autowired private val emailService: EmailService,
) {

    @PostMapping("/internal/verification", consumes = [APPLICATION_JSON_INTERNAL_VER_1])
    @ResponseStatus(HttpStatus.OK)
    fun sendVerificationEmail(
        @RequestBody verificationEmailRequest: VerificationEmailRequest,
    ) {
        emailService.sendVerificationEmail(verificationEmailRequest.toDomain())
    }
}
