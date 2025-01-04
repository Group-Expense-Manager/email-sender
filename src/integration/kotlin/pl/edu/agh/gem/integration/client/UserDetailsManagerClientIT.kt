package pl.edu.agh.gem.integration.client

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR
import org.springframework.http.HttpStatus.NOT_ACCEPTABLE
import pl.edu.agh.gem.external.dto.toUsernameResponse
import pl.edu.agh.gem.helper.user.DummyUser.USER_ID
import pl.edu.agh.gem.integration.BaseIntegrationSpec
import pl.edu.agh.gem.integration.ability.stubGetUsername
import pl.edu.agh.gem.internal.client.RetryableUserDetailsManagerClientException
import pl.edu.agh.gem.internal.client.UserDetailsManagerClient
import pl.edu.agh.gem.internal.client.UserDetailsManagerClientException
import pl.edu.agh.gem.util.DummyData.DUMMY_USERNAME

class UserDetailsManagerClientIT(
    private val userDetailsManagerClient: UserDetailsManagerClient,
) : BaseIntegrationSpec({
        should("get username") {
            // given
            stubGetUsername(DUMMY_USERNAME.toUsernameResponse(), USER_ID)

            // when
            val username = userDetailsManagerClient.getUsername(USER_ID)

            // then
            username shouldBe DUMMY_USERNAME
        }

        should("throw UserDetailsManagerClientException when we send bad request") {
            // given
            stubGetUsername(DUMMY_USERNAME.toUsernameResponse(), USER_ID, NOT_ACCEPTABLE)

            // when & then
            shouldThrow<UserDetailsManagerClientException> {
                userDetailsManagerClient.getUsername(USER_ID)
            }
        }

        should("throw RetryableUserDetailsManagerClientException when client has internal error") {
            // given
            stubGetUsername(DUMMY_USERNAME.toUsernameResponse(), USER_ID, INTERNAL_SERVER_ERROR)

            // when & then
            shouldThrow<RetryableUserDetailsManagerClientException> {
                userDetailsManagerClient.getUsername(USER_ID)
            }
        }
    })
