package work.socialhub.kmatrix.apis

import kotlinx.coroutines.test.runTest
import work.socialhub.kmatrix.AbstractTest
import work.socialhub.kmatrix.api.request.sync.SyncRequest
import kotlin.test.Test

class SyncTest : AbstractTest() {

    @Test
    fun testInitialSync() = runTest {
        val response = matrix().sync().sync(
            SyncRequest().also {
                it.timeout = 0
                it.setPresence = "offline"
            }
        )
        println("=== Initial Sync ===")
        println("  Next Batch > ${response.data.nextBatch}")

        val joinedRooms = response.data.rooms?.join
        if (joinedRooms != null) {
            println("  Joined Rooms > ${joinedRooms.size}")
            for ((roomId, room) in joinedRooms) {
                val timelineEvents = room.timeline?.events?.size ?: 0
                val stateEvents = room.state?.events?.size ?: 0
                println("  ---")
                println("  Room ID         > $roomId")
                println("  Timeline Events > $timelineEvents")
                println("  State Events    > $stateEvents")
                val unread = room.unreadNotifications
                if (unread != null) {
                    println("  Notifications   > ${unread.notificationCount}")
                    println("  Highlights      > ${unread.highlightCount}")
                }
            }
        }

        val invitedRooms = response.data.rooms?.invite
        if (invitedRooms != null && invitedRooms.isNotEmpty()) {
            println("  Invited Rooms > ${invitedRooms.size}")
        }

        assert(response.data.nextBatch.isNotEmpty())
    }
}
