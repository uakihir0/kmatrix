package work.socialhub.kmatrix.apis

import kotlinx.coroutines.test.runTest
import work.socialhub.kmatrix.AbstractTest
import work.socialhub.kmatrix.api.request.rooms.RoomsGetMessagesRequest
import kotlin.test.Test

class RoomsGetMessagesTest : AbstractTest() {

    @Test
    fun testGetMessages() = runTest {
        val rooms = matrix().rooms()

        // Get joined rooms and use the first one
        val joinedRooms = rooms.getJoinedRooms().data.joinedRooms
        assert(joinedRooms.isNotEmpty()) { "No joined rooms found" }
        val roomId = joinedRooms.first()

        val response = rooms.getMessages(
            RoomsGetMessagesRequest().also {
                it.roomId = roomId
                it.dir = "b"
                it.limit = 5
            }
        )
        println("=== Room Messages ===")
        println("  Room ID > $roomId")
        println("  Start   > ${response.data.start}")
        println("  End     > ${response.data.end}")
        println("  Count   > ${response.data.chunk.size}")
        for (event in response.data.chunk) {
            println("  ---")
            println("  Event ID > ${event.eventId}")
            println("  Type     > ${event.type}")
            println("  Sender   > ${event.sender}")
            val body = event.content["body"]
            if (body != null) {
                println("  Body     > $body")
            }
        }
    }
}
