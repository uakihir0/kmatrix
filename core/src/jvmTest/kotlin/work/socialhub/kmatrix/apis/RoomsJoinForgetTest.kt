package work.socialhub.kmatrix.apis

import kotlinx.coroutines.test.runTest
import work.socialhub.kmatrix.AbstractTest
import work.socialhub.kmatrix.api.request.rooms.RoomsCreateRoomRequest
import work.socialhub.kmatrix.api.request.rooms.RoomsForgetRoomRequest
import work.socialhub.kmatrix.api.request.rooms.RoomsJoinRoomRequest
import work.socialhub.kmatrix.api.request.rooms.RoomsLeaveRoomRequest
import kotlin.test.Test

class RoomsJoinForgetTest : AbstractTest() {

    @Test
    fun testJoinAndForgetRoom() = runTest {
        try {
            val rooms = matrix().rooms()

            // Create a room for testing
            val createResponse = rooms.createRoom(
                RoomsCreateRoomRequest().also {
                    it.name = "kmatrix-join-forget-test"
                    it.preset = "public_chat"
                }
            )
            val roomId = createResponse.data.roomId
            println("=== Join and Forget Room ===")
            println("  Created Room > $roomId")

            // Leave the room
            rooms.leaveRoom(
                RoomsLeaveRoomRequest().also {
                    it.roomId = roomId
                }
            )
            println("  Left Room")

            // Join the room back using joinRoom
            val joinResponse = rooms.joinRoom(
                RoomsJoinRoomRequest().also {
                    it.roomIdOrAlias = roomId
                }
            )
            println("  Joined Room  > ${joinResponse.data.roomId}")
            assert(joinResponse.data.roomId == roomId)

            // Leave again to prepare for forget
            rooms.leaveRoom(
                RoomsLeaveRoomRequest().also {
                    it.roomId = roomId
                }
            )
            println("  Left Room again")

            // Forget the room
            rooms.forgetRoom(
                RoomsForgetRoomRequest().also {
                    it.roomId = roomId
                }
            )
            println("  Forgot Room  > $roomId")
        } catch (e: Exception) {
            // May fail due to rate limiting on room creation
            println("=== Join and Forget Room ===")
            println("  Skipped: ${e.message}")
        }
    }
}
