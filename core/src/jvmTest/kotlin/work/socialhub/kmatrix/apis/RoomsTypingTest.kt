package work.socialhub.kmatrix.apis

import kotlinx.coroutines.test.runTest
import work.socialhub.kmatrix.AbstractTest
import work.socialhub.kmatrix.api.request.rooms.RoomsTypingRequest
import kotlin.test.Test

class RoomsTypingTest : AbstractTest() {

    @Test
    fun testSetTyping() = runTest {
        val matrix = matrix()
        val rooms = matrix.rooms()

        // Get user ID from whoami
        val userId = matrix.accounts().whoami().data.userId

        // Get a room to test with
        val joinedRooms = rooms.getJoinedRooms().data.joinedRooms
        assert(joinedRooms.isNotEmpty()) { "No joined rooms found" }
        val roomId = joinedRooms.first()

        // Start typing
        rooms.setTyping(
            RoomsTypingRequest().also {
                it.roomId = roomId
                it.userId = userId
                it.typing = true
                it.timeout = 5000
            }
        )
        println("=== Set Typing ===")
        println("  Room ID  > $roomId")
        println("  User ID  > $userId")
        println("  Typing   > true")

        // Stop typing
        rooms.setTyping(
            RoomsTypingRequest().also {
                it.roomId = roomId
                it.userId = userId
                it.typing = false
            }
        )
        println("=== Stop Typing ===")
        println("  Typing   > false")
    }
}
