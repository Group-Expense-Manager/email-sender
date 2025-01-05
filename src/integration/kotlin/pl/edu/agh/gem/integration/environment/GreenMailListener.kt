package pl.edu.agh.gem.integration.environment

import com.icegreen.greenmail.util.GreenMail
import io.github.oshai.kotlinlogging.KotlinLogging
import io.kotest.core.listeners.AfterProjectListener
import io.kotest.core.listeners.BeforeProjectListener

class GreenMailListener(
    private val greenMail: GreenMail,
) :
    BeforeProjectListener,
        AfterProjectListener {
    override suspend fun beforeProject() {
        logger.info { "Starting GreenMail" }
        greenMail.start()
    }

    override suspend fun afterProject() {
        greenMail.stop()
    }

    companion object {
        private val logger = KotlinLogging.logger { }
    }
}
