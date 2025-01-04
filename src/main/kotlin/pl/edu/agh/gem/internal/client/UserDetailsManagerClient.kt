package pl.edu.agh.gem.internal.client

interface UserDetailsManagerClient {
    fun getUsername(userId: String): String
}

class UserDetailsManagerClientException(override val message: String?) : RuntimeException()

class RetryableUserDetailsManagerClientException(override val message: String?) : RuntimeException()
