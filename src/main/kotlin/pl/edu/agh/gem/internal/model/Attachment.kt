package pl.edu.agh.gem.internal.model

data class Attachment(
    val title: String,
    val file: ByteArray,
    val type: String,
)
