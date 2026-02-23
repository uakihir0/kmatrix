package work.socialhub.kmatrix.apis

import kotlinx.coroutines.test.runTest
import work.socialhub.kmatrix.AbstractTest
import work.socialhub.kmatrix.api.request.rooms.RoomsSendStateEventRequest
import kotlin.test.Test

class RoomsBanKickTest : AbstractTest() {

    @Test
    fun testGetStateEvent() = runTest {
        val rooms = matrix().rooms().getJoinedRooms()
        if (rooms.data.joinedRooms.isNotEmpty()) {
            val roomId = rooms.data.joinedRooms.first()
            try {
                val response = matrix().rooms().getStateEvent(roomId, "m.room.name")
                println("=== Get State Event ===")
                println("  JSON > ${response.data}")
            } catch (e: Exception) {
                println("  State event not found (expected for rooms without name)")
            }
        }
    }

    @Test
    fun testSendStateEvent() = runTest {
        val rooms = matrix().rooms().getJoinedRooms()
        if (rooms.data.joinedRooms.isNotEmpty()) {
            val roomId = rooms.data.joinedRooms.first()
            val request = RoomsSendStateEventRequest().also {
                it.roomId = roomId
                it.eventType = "m.room.topic"
                it.body = """{"topic": "Updated by kmatrix test"}"""
            }
            try {
                val response = matrix().rooms().sendStateEvent(request)
                println("=== Send State Event ===")
                println("  Event ID > ${response.data.eventId}")
            } catch (e: Exception) {
                println("  Failed to send state event: ${e.message}")
            }
        }
    }
}
