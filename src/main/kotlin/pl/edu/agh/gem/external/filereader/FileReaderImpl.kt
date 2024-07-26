package pl.edu.agh.gem.external.filereader

import org.springframework.core.io.ResourceLoader
import org.springframework.stereotype.Component
import pl.edu.agh.gem.internal.filereader.FileReader
import pl.edu.agh.gem.internal.filereader.FileReaderException
import java.io.IOException

@Component
class FileReaderImpl(
    private val resourceLoader: ResourceLoader,
) : FileReader {
    override fun read(filePath: String): String {
        try {
            val resource = resourceLoader.getResource("classpath:$filePath")
            return resource.inputStream.bufferedReader().use { it.readText() }
        } catch (e: IOException) {
            throw FileReaderException()
        }
    }
}
