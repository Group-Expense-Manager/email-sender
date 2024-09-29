package pl.edu.agh.gem.integration.ability

import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Component
import org.springframework.test.web.reactive.server.WebTestClient.ResponseSpec
import org.springframework.test.web.servlet.client.MockMvcWebTestClient.bindToApplicationContext
import org.springframework.web.context.WebApplicationContext
import pl.edu.agh.gem.external.dto.PasswordEmailRequest
import pl.edu.agh.gem.external.dto.PasswordRecoveryEmailRequest
import pl.edu.agh.gem.external.dto.VerificationEmailRequest
import pl.edu.agh.gem.headers.HeadersUtils.withAppContentType
import pl.edu.agh.gem.paths.Paths.INTERNAL
import java.net.URI

@Component
@Lazy
class ServiceTestClient(applicationContext: WebApplicationContext) {
    private val webClient = bindToApplicationContext(applicationContext)
        .configureClient()
        .build()

    fun sendVerificationEmail(body: VerificationEmailRequest): ResponseSpec {
        return webClient.post()
            .uri(URI("$INTERNAL/verification"))
            .headers { it.withAppContentType() }
            .bodyValue(body)
            .exchange()
    }

    fun sendPasswordRecoveryEmail(body: PasswordRecoveryEmailRequest): ResponseSpec {
        return webClient.post()
            .uri(URI("$INTERNAL/recover-password"))
            .headers { it.withAppContentType() }
            .bodyValue(body)
            .exchange()
    }

    fun sendPasswordEmail(body: PasswordEmailRequest): ResponseSpec {
        return webClient.post()
            .uri(URI("$INTERNAL/password"))
            .headers { it.withAppContentType() }
            .bodyValue(body)
            .exchange()
    }

    fun sendReport(body: ByteArray, email: String, username: String, fileName: String): ResponseSpec {
        return webClient.post()
            .uri {
                it.path("$INTERNAL/report")
                    .queryParam("email", email)
                    .queryParam("username", username)
                    .queryParam("fileName", fileName)
                    .build()
            }
            .headers { it.withAppContentType() }
            .bodyValue(body)
            .exchange()
    }
}
