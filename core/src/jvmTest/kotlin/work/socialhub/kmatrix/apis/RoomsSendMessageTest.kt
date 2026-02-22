package work.socialhub.kmatrix.apis

import kotlinx.coroutines.test.runTest
import work.socialhub.kmatrix.AbstractTest
import work.socialhub.kmatrix.api.request.rooms.RoomsSendMessageRequest
import kotlin.test.Test

class RoomsSendMessageTest : AbstractTest() {

    @Test
    fun testSendMessageToMokumoku() = runTest {
        val matrix = matrix()
        val rooms = matrix.rooms()

        // Get joined rooms
        val joinedRooms = rooms.getJoinedRooms().data.joinedRooms
        println("=== Joined Rooms ===")

        // Find the mokumoku room by name
        var mokumokuRoomId: String? = null
        for (roomId in joinedRooms) {
            try {
                val name = rooms.getRoomName(roomId).data.name
                println("  Room > $roomId ($name)")
                if (name.contains("mokumoku", ignoreCase = true)) {
                    mokumokuRoomId = roomId
                }
            } catch (e: Exception) {
                println("  Room > $roomId (name unavailable)")
            }
        }

        if (mokumokuRoomId == null) {
            println("mokumoku room not found!")
            return@runTest
        }

        println()
        println("=== Sending Message ===")
        println("  Target > $mokumokuRoomId")

        val response = rooms.sendMessage(
            RoomsSendMessageRequest().also {
                it.roomId = mokumokuRoomId
                it.body = "Hello from kmatrix! \uD83D\uDE80"
            }
        )
        println("  Event ID > ${response.data.eventId}")
        println("  Message sent successfully!")
    }
}
