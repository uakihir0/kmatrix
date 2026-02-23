package work.socialhub.kmatrix.apis

import kotlinx.coroutines.test.runTest
import work.socialhub.kmatrix.AbstractTest
import work.socialhub.kmatrix.api.request.profile.ProfileSetDisplayNameRequest
import work.socialhub.kmatrix.api.request.profile.ProfileSetAvatarUrlRequest
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

    @Test
    fun testSetDisplayName() = runTest {
        val userId = matrix().accounts().whoami().data.userId

        // Get current display name
        val before = matrix().profile().getDisplayName(userId)
        val originalName = before.data.displayname
        println("=== Set Display Name ===")
        println("  Original > $originalName")

        // Set a new display name
        val testName = (originalName ?: "kmatrix") + " (test)"
        matrix().profile().setDisplayName(
            ProfileSetDisplayNameRequest().also {
                it.userId = userId
                it.displayname = testName
            }
        )
        println("  Updated  > $testName")

        // Verify it was updated
        val after = matrix().profile().getDisplayName(userId)
        println("  Verified > ${after.data.displayname}")

        // Restore original display name
        matrix().profile().setDisplayName(
            ProfileSetDisplayNameRequest().also {
                it.userId = userId
                it.displayname = originalName ?: ""
            }
        )
        println("  Restored > $originalName")
    }

    @Test
    fun testSetAvatarUrl() = runTest {
        val userId = matrix().accounts().whoami().data.userId

        // Get current avatar URL
        val before = matrix().profile().getAvatarUrl(userId)
        val originalUrl = before.data.avatarUrl
        println("=== Set Avatar URL ===")
        println("  Original > $originalUrl")

        // Set a test avatar URL (using mxc:// format)
        val testUrl = originalUrl ?: "mxc://matrix.org/test"
        matrix().profile().setAvatarUrl(
            ProfileSetAvatarUrlRequest().also {
                it.userId = userId
                it.avatarUrl = testUrl
            }
        )
        println("  Updated  > $testUrl")

        // Verify it was updated
        val after = matrix().profile().getAvatarUrl(userId)
        println("  Verified > ${after.data.avatarUrl}")

        // Restore original avatar URL (if there was one)
        if (originalUrl != null) {
            matrix().profile().setAvatarUrl(
                ProfileSetAvatarUrlRequest().also {
                    it.userId = userId
                    it.avatarUrl = originalUrl
                }
            )
            println("  Restored > $originalUrl")
        }
    }
}
