package pl.edu.agh.gem.integration.factory

import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import pl.edu.agh.gem.external.factory.EmailFactoryImpl
import pl.edu.agh.gem.helper.user.DummyUser.EMAIL
import pl.edu.agh.gem.integration.BaseIntegrationSpec
import pl.edu.agh.gem.util.DummyData.DUMMY_HTML
import pl.edu.agh.gem.util.DummyData.DUMMY_SUBJECT

class EmailFactoryImplIT(
    private val emailFactory: EmailFactoryImpl,
) : BaseIntegrationSpec({
    should("create email correctly") {
        // given & when
        val result = emailFactory.createEmail(EMAIL, DUMMY_SUBJECT, DUMMY_HTML)

        // then
        result.also {
            it.from.size shouldBe 1
            it.allRecipients.size shouldBe 1
            it.allRecipients.first().toString() shouldBe EMAIL
            it.subject shouldBe DUMMY_SUBJECT
            it.content.shouldNotBeNull()
        }
    }
},)
