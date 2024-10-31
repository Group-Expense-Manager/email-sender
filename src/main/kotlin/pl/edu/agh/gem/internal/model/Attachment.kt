package pl.edu.agh.gem.internal.model

import org.springframework.core.io.ByteArrayResource

data class Attachment(
    val title: String,
    val file: ByteArrayResource,
)
