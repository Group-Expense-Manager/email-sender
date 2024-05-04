package pl.edu.agh.gem.integration.environment

import com.github.tomakehurst.wiremock.WireMockServer
import com.icegreen.greenmail.util.GreenMail
import com.icegreen.greenmail.util.ServerSetup
import io.kotest.core.config.AbstractProjectConfig
import io.kotest.extensions.spring.SpringExtension
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.util.TestSocketUtils

object ProjectConfig : AbstractProjectConfig() {

    private const val WIREMOCK_SERVER_PORT = 9999
    val GREEN_MAIL_PORT = TestSocketUtils.findAvailableTcpPort()

    private val wiremock = WireMockServer(WIREMOCK_SERVER_PORT)
    private val wiremockListener = WireMockListener(wiremock)
    val greenMail = GreenMail(ServerSetup(GREEN_MAIL_PORT, null, ServerSetup.PROTOCOL_SMTP))
    private val greenMailListener = GreenMailListener(greenMail)

    override fun extensions() = listOf(
        wiremockListener,
        SpringExtension,
        greenMailListener,
    )

    fun updateConfiguration(registry: DynamicPropertyRegistry) {
        registry.add("spring.mail.port") { GREEN_MAIL_PORT.toString() }
    }
}
