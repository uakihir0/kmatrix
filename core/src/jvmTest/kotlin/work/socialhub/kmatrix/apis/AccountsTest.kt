package work.socialhub.kmatrix.apis

import kotlinx.coroutines.test.runTest
import work.socialhub.kmatrix.AbstractTest
import kotlin.test.Test

class AccountsTest : AbstractTest() {

    @Test
    fun testWhoami() = runTest {
        val response = matrix().accounts().whoami()
        println("=== Whoami ===")
        println("  User ID   > ${response.data.userId}")
        println("  Device ID > ${response.data.deviceId}")
        println("  Is Guest  > ${response.data.isGuest}")
    }
}
