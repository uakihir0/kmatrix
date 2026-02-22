package work.socialhub.kmatrix.apis

import kotlinx.coroutines.test.runTest
import work.socialhub.kmatrix.AbstractTest
import work.socialhub.kmatrix.api.request.rooms.RoomsRedactEventRequest
import work.socialhub.kmatrix.api.request.rooms.RoomsSendMessageRequest
import kotlin.test.Test

class RoomsRedactEventTest : AbstractTest() {

    @Test
    fun testRedactEvent() = runTest {
        val rooms = matrix().rooms()

        // Get a room to test with
        val joinedRooms = rooms.getJoinedRooms().data.joinedRooms
        assert(joinedRooms.isNotEmpty()) { "No joined rooms found" }
        val roomId = joinedRooms.first()

        // Send a message to redact
        val sendResponse = rooms.sendMessage(
            RoomsSendMessageRequest().also {
                it.roomId = roomId
                it.body = "This message will be redacted"
            }
        )
        val eventId = sendResponse.data.eventId
        println("=== Sent Message ===")
        println("  Event ID > $eventId")

        // Redact the message
        val redactResponse = rooms.redactEvent(
            RoomsRedactEventRequest().also {
                it.roomId = roomId
                it.eventId = eventId
                it.reason = "Test redaction"
            }
        )
        println("=== Redacted Event ===")
        println("  Redaction Event ID > ${redactResponse.data.eventId}")
        assert(redactResponse.data.eventId.isNotEmpty())
    }
}
