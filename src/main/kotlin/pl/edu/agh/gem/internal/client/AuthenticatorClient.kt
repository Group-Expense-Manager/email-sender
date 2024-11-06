package pl.edu.agh.gem.internal.client

interface AuthenticatorClient {
    fun getEmailAddress(userId: String): String
}

class AuthenticatorClientException(override val message: String?) : RuntimeException()

class RetryableAuthenticatorClientException(override val message: String?) : RuntimeException()
