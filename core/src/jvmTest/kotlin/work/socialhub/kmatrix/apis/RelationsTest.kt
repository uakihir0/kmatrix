package work.socialhub.kmatrix.apis

import kotlinx.coroutines.test.runTest
import work.socialhub.kmatrix.AbstractTest
import work.socialhub.kmatrix.api.request.relations.RelationsGetRequest
import work.socialhub.kmatrix.api.request.relations.ThreadsGetRequest
import work.socialhub.kmatrix.api.request.rooms.RoomsGetMessagesRequest
import kotlin.test.Test

class RelationsTest : AbstractTest() {

    @Test
    fun testGetRelations() = runTest {
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

        val response = matrix().relations().getRelations(
            RelationsGetRequest().also {
                it.roomId = roomId
                it.eventId = eventId
                it.limit = 5
            }
        )
        println("=== Relations ===")
        println("  Next Batch > ${response.data.nextBatch}")
        println("  Chunk Size > ${response.data.chunk.size}")
        for (event in response.data.chunk) {
            println("    Event > ${event.type} from ${event.sender}")
        }
    }

    @Test
    fun testGetThreads() = runTest {
        val rooms = matrix().rooms().getJoinedRooms()
        val roomId = rooms.data.joinedRooms.first()

        val response = matrix().relations().getThreads(
            ThreadsGetRequest().also {
                it.roomId = roomId
                it.limit = 5
            }
        )
        println("=== Threads ===")
        println("  Next Batch > ${response.data.nextBatch}")
        println("  Chunk Size > ${response.data.chunk.size}")
        for (thread in response.data.chunk) {
            println("    Thread > ${thread.eventId} by ${thread.sender}")
        }
    }
}
