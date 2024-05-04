package pl.edu.agh.gem.integration.ability

import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Component
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.servlet.client.MockMvcWebTestClient.bindToApplicationContext
import org.springframework.web.context.WebApplicationContext
import pl.edu.agh.gem.external.dto.verification.VerificationEmailRequest
import pl.edu.agh.gem.headers.HeadersUtils.withAppContentType
import java.net.URI

@Component
@Lazy
class ServiceTestClient(applicationContext: WebApplicationContext) {
    private val webClient = bindToApplicationContext(applicationContext)
        .configureClient()
        .build()

    fun sendVerificationEmail(body: VerificationEmailRequest): WebTestClient.ResponseSpec {
        return webClient.post()
            .uri(URI("/internal/verification"))
            .headers { it.withAppContentType() }
            .bodyValue(body)
            .exchange()
    }
}
