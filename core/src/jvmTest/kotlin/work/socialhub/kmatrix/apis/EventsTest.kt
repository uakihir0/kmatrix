package work.socialhub.kmatrix.apis

import kotlinx.coroutines.test.runTest
import work.socialhub.kmatrix.AbstractTest
import work.socialhub.kmatrix.api.request.events.EventsGetContextRequest
import work.socialhub.kmatrix.api.request.rooms.RoomsGetMessagesRequest
import kotlin.test.Test

class EventsTest : AbstractTest() {

    @Test
    fun testGetEvent() = runTest {
        // First get a room and a message to test with
        val rooms = matrix().rooms().getJoinedRooms()
        val roomId = rooms.data.joinedRooms.first()

        val messages = matrix().rooms().getMessages(
            RoomsGetMessagesRequest().also {
                it.roomId = roomId
                it.dir = "b"
                it.limit = 1
            }
        )
        val eventId = messages.data.chunk.first().eventId

        // Now get the event
        val response = matrix().events().getEvent(roomId, eventId)
        println("=== Get Event ===")
        println("  Event ID > ${response.data.eventId}")
        println("  Type     > ${response.data.type}")
        println("  Sender   > ${response.data.sender}")
        println("  Room ID  > ${response.data.roomId}")
    }

    @Test
    fun testGetContext() = runTest {
        val rooms = matrix().rooms().getJoinedRooms()
        val roomId = rooms.data.joinedRooms.first()

        val messages = matrix().rooms().getMessages(
            RoomsGetMessagesRequest().also {
                it.roomId = roomId
                it.dir = "b"
                it.limit = 1
            }
        )
        val eventId = messages.data.chunk.first().eventId

        val response = matrix().events().getContext(
            EventsGetContextRequest().also {
                it.roomId = roomId
                it.eventId = eventId
                it.limit = 3
            }
        )
        println("=== Get Context ===")
        println("  Start > ${response.data.start}")
        println("  End   > ${response.data.end}")
        println("  Event > ${response.data.event?.eventId}")
        println("  Before > ${response.data.eventsBefore.size} events")
        println("  After  > ${response.data.eventsAfter.size} events")
        println("  State  > ${response.data.state.size} events")
    }
}
