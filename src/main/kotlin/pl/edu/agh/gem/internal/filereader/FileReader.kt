package pl.edu.agh.gem.internal.filereader

interface FileReader {
    fun read(filePath: String): String
}

class FileReaderException : RuntimeException("Failed to read email template")
