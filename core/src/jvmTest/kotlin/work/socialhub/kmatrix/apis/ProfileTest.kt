package work.socialhub.kmatrix.apis

import kotlinx.coroutines.test.runTest
import work.socialhub.kmatrix.AbstractTest
import kotlin.test.Test

class ProfileTest : AbstractTest() {

    @Test
    fun testGetProfile() = runTest {
        val response = matrix().profile().getProfile("@uakihir0:matrix.org")
        println("=== Profile ===")
        println("  Display Name > ${response.data.displayname}")
        println("  Avatar URL   > ${response.data.avatarUrl}")
    }

    @Test
    fun testGetDisplayName() = runTest {
        val response = matrix().profile().getDisplayName("@uakihir0:matrix.org")
        println("=== Display Name ===")
        println("  Display Name > ${response.data.displayname}")
    }

    @Test
    fun testGetAvatarUrl() = runTest {
        val response = matrix().profile().getAvatarUrl("@uakihir0:matrix.org")
        println("=== Avatar URL ===")
        println("  Avatar URL > ${response.data.avatarUrl}")
    }
}
