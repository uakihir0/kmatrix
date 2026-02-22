package work.socialhub.kmatrix.apis

import kotlinx.coroutines.test.runTest
import work.socialhub.kmatrix.AbstractTest
import kotlin.test.Test

class RoomsMembersTest : AbstractTest() {

    @Test
    fun testGetJoinedMembers() = runTest {
        val rooms = matrix().rooms()

        val joinedRooms = rooms.getJoinedRooms().data.joinedRooms
        assert(joinedRooms.isNotEmpty()) { "No joined rooms found" }
        val roomId = joinedRooms.first()

        val response = rooms.getJoinedMembers(roomId)
        println("=== Joined Members ===")
        println("  Room ID > $roomId")
        for ((userId, member) in response.data.joined) {
            println("  ---")
            println("  User ID      > $userId")
            println("  Display Name > ${member.displayName}")
            println("  Avatar URL   > ${member.avatarUrl}")
        }
        assert(response.data.joined.isNotEmpty())
    }

    @Test
    fun testGetMembers() = runTest {
        val rooms = matrix().rooms()

        val joinedRooms = rooms.getJoinedRooms().data.joinedRooms
        assert(joinedRooms.isNotEmpty()) { "No joined rooms found" }
        val roomId = joinedRooms.first()

        val response = rooms.getMembers(roomId)
        println("=== Members ===")
        println("  Room ID > $roomId")
        println("  Count   > ${response.data.chunk.size}")
        for (event in response.data.chunk) {
            println("  ---")
            println("  Event ID  > ${event.eventId}")
            println("  Sender    > ${event.sender}")
            println("  State Key > ${event.stateKey}")
            val membership = event.content["membership"]
            println("  Membership > $membership")
        }
    }
}
