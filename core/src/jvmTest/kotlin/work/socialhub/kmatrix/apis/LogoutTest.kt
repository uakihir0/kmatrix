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

    /**
     * To run this test, set MATRIX_USER and MATRIX_PASSWORD
     * environment variables.
     */
    @Test
    fun testLogoutAll() = runTest {
        val user = System.getenv("MATRIX_USER") ?: return@runTest
        val password = System.getenv("MATRIX_PASSWORD") ?: return@runTest
        val host = HOST ?: "https://matrix.org"

        // Login twice to get two separate tokens
        val matrix = MatrixFactory.instance(host)
        val login1 = matrix.login().loginWithPassword(
            LoginPasswordRequest().also {
                it.user = user
                it.password = password
                it.initialDeviceDisplayName = "kmatrix-logoutall-test-1"
            }
        )
        val token1 = login1.data.accessToken

        val login2 = matrix.login().loginWithPassword(
            LoginPasswordRequest().also {
                it.user = user
                it.password = password
                it.initialDeviceDisplayName = "kmatrix-logoutall-test-2"
            }
        )
        val token2 = login2.data.accessToken
        println("=== Logout All ===")
        println("  Token 1 > ${token1.take(10)}...")
        println("  Token 2 > ${token2.take(10)}...")

        // Logout all using token1
        val authed1 = MatrixFactory.instance(host, token1)
        authed1.accounts().logoutAll()
        println("  All tokens invalidated")

        // Verify both tokens are now invalid
        try {
            MatrixFactory.instance(host, token1).accounts().whoami()
            assert(false) { "Token 1 should have been invalidated" }
        } catch (e: MatrixException) {
            println("  Token 1 invalid: ${e.message}")
        }

        try {
            MatrixFactory.instance(host, token2).accounts().whoami()
            assert(false) { "Token 2 should have been invalidated" }
        } catch (e: MatrixException) {
            println("  Token 2 invalid: ${e.message}")
        }
    }
}
