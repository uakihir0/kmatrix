package work.socialhub.kmatrix.apis

import kotlinx.coroutines.test.runTest
import work.socialhub.kmatrix.AbstractTest
import work.socialhub.kmatrix.api.request.presence.PresenceSetRequest
import kotlin.test.Test

class PresenceTest : AbstractTest() {

    @Test
    fun testGetPresence() = runTest {
        val response = matrix().presence().getPresence("@uakihir0:matrix.org")
        println("=== Get Presence ===")
        println("  Presence       > ${response.data.presence}")
        println("  Status Message > ${response.data.statusMsg}")
        println("  Last Active    > ${response.data.lastActiveAgo}")
        println("  Currently Active > ${response.data.currentlyActive}")
    }

    @Test
    fun testSetPresence() = runTest {
        val request = PresenceSetRequest().also {
            it.userId = "@uakihir0:matrix.org"
            it.presence = "online"
            it.statusMsg = "Hello from kmatrix"
        }
        matrix().presence().setPresence(request)
        println("=== Set Presence ===")
        println("  Presence set successfully")
    }
}
