package work.socialhub.kmatrix.apis

import kotlinx.coroutines.test.runTest
import work.socialhub.kmatrix.AbstractTest
import work.socialhub.kmatrix.api.request.rooms.RoomsGetMessagesRequest
import work.socialhub.kmatrix.api.request.rooms.RoomsSendReceiptRequest
import work.socialhub.kmatrix.api.request.rooms.RoomsSetReadMarkersRequest
import kotlin.test.Test

class RoomsReceiptTest : AbstractTest() {

    @Test
    fun testSendReceiptAndReadMarkers() = runTest {
        val rooms = matrix().rooms()

        // Get a room
        val joinedRooms = rooms.getJoinedRooms().data.joinedRooms
        assert(joinedRooms.isNotEmpty()) { "No joined rooms found" }
        val roomId = joinedRooms.first()

        // Get the latest event in the room
        val messages = rooms.getMessages(
            RoomsGetMessagesRequest().also {
                it.roomId = roomId
                it.dir = "b"
                it.limit = 1
            }
        )
        val latestEvent = messages.data.chunk.firstOrNull()
        assert(latestEvent != null) { "No events found in room" }
        val eventId = latestEvent!!.eventId

        // Send a read receipt
        rooms.sendReceipt(
            RoomsSendReceiptRequest().also {
                it.roomId = roomId
                it.eventId = eventId
                it.receiptType = "m.read"
            }
        )
        println("=== Send Receipt ===")
        println("  Room ID  > $roomId")
        println("  Event ID > $eventId")
        println("  Type     > m.read")

        // Set read markers
        rooms.setReadMarkers(
            RoomsSetReadMarkersRequest().also {
                it.roomId = roomId
                it.fullyRead = eventId
                it.read = eventId
            }
        )
        println("=== Set Read Markers ===")
        println("  Fully Read > $eventId")
        println("  Read       > $eventId")
    }
}
