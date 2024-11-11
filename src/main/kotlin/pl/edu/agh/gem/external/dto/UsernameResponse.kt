package pl.edu.agh.gem.external.dto

data class UsernameResponse(
    val username: String,
)

fun String.toUsernameResponse() = UsernameResponse(this)
