package work.socialhub.kmatrix.apis

import kotlinx.coroutines.test.runTest
import work.socialhub.kmatrix.AbstractTest
import work.socialhub.kmatrix.api.request.rooms.RoomsCreateRoomRequest
import work.socialhub.kmatrix.api.request.rooms.RoomsLeaveRoomRequest
import kotlin.test.Test

class RoomsMembershipTest : AbstractTest() {

    @Test
    fun testCreateAndLeaveRoom() = runTest {
        val rooms = matrix().rooms()

        // Create a room
        val createResponse = rooms.createRoom(
            RoomsCreateRoomRequest().also {
                it.name = "kmatrix-membership-test"
                it.preset = "private_chat"
            }
        )
        val roomId = createResponse.data.roomId
        println("=== Created Room ===")
        println("  Room ID > $roomId")
        assert(roomId.isNotEmpty())

        // Verify we joined the room
        val joinedBefore = rooms.getJoinedRooms().data.joinedRooms
        assert(joinedBefore.contains(roomId))
        println("  Joined rooms contains new room: true")

        // Leave the room
        rooms.leaveRoom(
            RoomsLeaveRoomRequest().also {
                it.roomId = roomId
                it.reason = "Test completed"
            }
        )
        println("=== Left Room ===")
        println("  Room ID > $roomId")

        // Verify we left the room
        val joinedAfter = rooms.getJoinedRooms().data.joinedRooms
        assert(!joinedAfter.contains(roomId))
        println("  Joined rooms no longer contains room: true")
    }
}
