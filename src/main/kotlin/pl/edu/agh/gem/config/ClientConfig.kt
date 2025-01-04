package pl.edu.agh.gem.config

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate
import pl.edu.agh.gem.helper.http.GemRestTemplateFactory
import java.time.Duration

@Configuration
class ClientConfig {
    @Bean
    @Qualifier("AttachmentStoreRestTemplate")
    fun attachmentStoreRestTemplate(
        attachmentStoreProperties: AttachmentStoreProperties,
        gemRestTemplateFactory: GemRestTemplateFactory,
    ): RestTemplate {
        return gemRestTemplateFactory
            .builder()
            .withReadTimeout(attachmentStoreProperties.readTimeout)
            .withConnectTimeout(attachmentStoreProperties.connectTimeout)
            .build()
    }

    @Bean
    @Qualifier("AuthenticatorRestTemplate")
    fun authenticatorRestTemplate(
        authenticatorProperties: AuthenticatorProperties,
        gemRestTemplateFactory: GemRestTemplateFactory,
    ): RestTemplate {
        return gemRestTemplateFactory
            .builder()
            .withReadTimeout(authenticatorProperties.readTimeout)
            .withConnectTimeout(authenticatorProperties.connectTimeout)
            .build()
    }

    @Bean
    @Qualifier("UserDetailsManagerClientRestTemplate")
    fun userDetailsManagerClientRestTemplate(
        userDetailsManagerClientProperties: UserDetailsManagerClientProperties,
        gemRestTemplateFactory: GemRestTemplateFactory,
    ): RestTemplate {
        return gemRestTemplateFactory
            .builder()
            .withReadTimeout(userDetailsManagerClientProperties.readTimeout)
            .withConnectTimeout(userDetailsManagerClientProperties.connectTimeout)
            .build()
    }
}

@ConfigurationProperties(prefix = "attachment-store")
data class AttachmentStoreProperties(
    val url: String,
    val connectTimeout: Duration,
    val readTimeout: Duration,
)

@ConfigurationProperties(prefix = "authenticator")
data class AuthenticatorProperties(
    val url: String,
    val connectTimeout: Duration,
    val readTimeout: Duration,
)

@ConfigurationProperties(prefix = "user-details-manager-client")
data class UserDetailsManagerClientProperties(
    val url: String,
    val connectTimeout: Duration,
    val readTimeout: Duration,
)
