package work.socialhub.kmatrix.apis

import kotlinx.coroutines.test.runTest
import work.socialhub.kmatrix.AbstractTest
import work.socialhub.kmatrix.MatrixFactory
import work.socialhub.kmatrix.api.request.login.LoginPasswordRequest
import kotlin.test.Test

class LoginPasswordTest : AbstractTest() {

    /**
     * To run this test, set MATRIX_USER and MATRIX_PASSWORD
     * environment variables.
     */
    @Test
    fun testLoginWithPassword() = runTest {
        val user = System.getenv("MATRIX_USER") ?: return@runTest
        val password = System.getenv("MATRIX_PASSWORD") ?: return@runTest
        val host = HOST ?: "https://matrix.org"

        val matrix = MatrixFactory.instance(host)
        val response = matrix.login().loginWithPassword(
            LoginPasswordRequest().also {
                it.user = user
                it.password = password
                it.initialDeviceDisplayName = "kmatrix"
            }
        )
        println("=== Login Result ===")
        println("  User ID      > ${response.data.userId}")
        println("  Access Token > ${response.data.accessToken}")
        println("  Device ID    > ${response.data.deviceId}")
        println("  Home Server  > ${response.data.homeServer}")
    }
}
