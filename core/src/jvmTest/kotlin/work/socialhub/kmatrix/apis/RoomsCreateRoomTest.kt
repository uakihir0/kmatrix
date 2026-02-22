package work.socialhub.kmatrix.apis

import kotlinx.coroutines.test.runTest
import work.socialhub.kmatrix.AbstractTest
import work.socialhub.kmatrix.api.request.rooms.RoomsCreateRoomRequest
import kotlin.test.Test

class RoomsCreateRoomTest : AbstractTest() {

    @Test
    fun testCreateRoom() = runTest {
        val response = matrix().rooms().createRoom(
            RoomsCreateRoomRequest().also {
                it.name = "kmatrix-test"
                it.topic = "Test room created by kmatrix"
                it.preset = "private_chat"
            }
        )
        println("=== Create Room ===")
        println("  Room ID > ${response.data.roomId}")
        assert(response.data.roomId.isNotEmpty())
    }
}
