package pl.edu.agh.gem.external.dto

import pl.edu.agh.gem.internal.model.Attachment

fun ByteArray.toAttachment(
    mediaType: String,
    title: String,
) = Attachment(
    title = title,
    file = this,
    type = mediaType,
)
