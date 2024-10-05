package pl.edu.agh.gem.internal.model

import org.springframework.core.io.ByteArrayResource

data class Attachment(
    val name: String,
    val file: ByteArrayResource,
)
