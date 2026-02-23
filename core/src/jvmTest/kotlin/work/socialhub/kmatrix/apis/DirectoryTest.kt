package work.socialhub.kmatrix.apis

import kotlinx.coroutines.test.runTest
import work.socialhub.kmatrix.AbstractTest
import work.socialhub.kmatrix.api.request.directory.DirectoryGetPublicRoomsRequest
import kotlin.test.Test

class DirectoryTest : AbstractTest() {

    @Test
    fun testResolveAlias() = runTest {
        val response = matrix().directory().resolveAlias("#matrix:matrix.org")
        println("=== Resolve Alias ===")
        println("  Room ID > ${response.data.roomId}")
        println("  Servers > ${response.data.servers.joinToString(", ")}")
    }

    @Test
    fun testGetPublicRooms() = runTest {
        val request = DirectoryGetPublicRoomsRequest().also {
            it.limit = 5
        }
        val response = matrix().directory().getPublicRooms(request)
        println("=== Public Rooms ===")
        println("  Total Estimate > ${response.data.totalRoomCountEstimate}")
        for (room in response.data.chunk) {
            println("  Room ID > ${room.roomId}")
            println("  Name    > ${room.name}")
            println("  Topic   > ${room.topic}")
            println("  Members > ${room.numJoinedMembers}")
            println("  ---")
        }
    }
}
