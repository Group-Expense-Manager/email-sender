package pl.edu.agh.gem.integration.filereader

import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.matchers.shouldBe
import pl.edu.agh.gem.external.filereader.FileReaderImpl
import pl.edu.agh.gem.integration.BaseIntegrationSpec
import pl.edu.agh.gem.internal.filereader.FileReaderException

class FileReaderImplIT(
    private val fileReader: FileReaderImpl,
) : BaseIntegrationSpec({
    should("read text correctly") {
        // given & when
        val result = fileReader.read("templates/test/test.txt")

        // then
        result.trim() shouldBe "Hello World!"
    }

    should("throw exception when file doesn't exist") {
        // given

        // when & then
        shouldThrowExactly<FileReaderException> {
            fileReader.read("templates/test/test2.txt")
        }
    }
},)
