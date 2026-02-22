package work.socialhub.kmatrix.apis

import kotlinx.coroutines.test.runTest
import work.socialhub.kmatrix.AbstractTest
import work.socialhub.kmatrix.MatrixException
import work.socialhub.kmatrix.MatrixFactory
import work.socialhub.kmatrix.api.request.login.LoginPasswordRequest
import kotlin.test.Test

class LogoutTest : AbstractTest() {

    /**
     * To run this test, set MATRIX_USER and MATRIX_PASSWORD
     * environment variables.
     */
    @Test
    fun testLogout() = runTest {
        val user = System.getenv("MATRIX_USER") ?: return@runTest
        val password = System.getenv("MATRIX_PASSWORD") ?: return@runTest
        val host = HOST ?: "https://matrix.org"

        // Login to get a fresh token
        val matrix = MatrixFactory.instance(host)
        val loginResponse = matrix.login().loginWithPassword(
            LoginPasswordRequest().also {
                it.user = user
                it.password = password
                it.initialDeviceDisplayName = "kmatrix-logout-test"
            }
        )
        val token = loginResponse.data.accessToken
        println("=== Login ===")
        println("  Access Token > ${token.take(10)}...")

        // Verify the token works
        val authed = MatrixFactory.instance(host, token)
        val whoami = authed.accounts().whoami()
        println("  User ID      > ${whoami.data.userId}")

        // Logout
        authed.accounts().logout()
        println("=== Logout ===")
        println("  Token invalidated")

        // Verify the token no longer works
        try {
            val invalid = MatrixFactory.instance(host, token)
            invalid.accounts().whoami()
            assert(false) { "Token should have been invalidated" }
        } catch (e: MatrixException) {
            println("  Verified: token is invalid (${e.message})")
        }
    }
}
