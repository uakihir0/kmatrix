package work.socialhub.kmatrix.apis

import kotlinx.coroutines.test.runTest
import work.socialhub.kmatrix.AbstractTest
import work.socialhub.kmatrix.api.request.directory.DirectoryGetPublicRoomsRequest
import work.socialhub.kmatrix.api.request.directory.DirectorySetAliasRequest
import work.socialhub.kmatrix.api.request.rooms.RoomsCreateRoomRequest
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

    @Test
    fun testSetAndDeleteAlias() = runTest {
        // Create a room for alias testing
        val roomResponse = matrix().rooms().createRoom(
            RoomsCreateRoomRequest().also {
                it.name = "kmatrix-alias-test"
                it.preset = "private_chat"
            }
        )
        val roomId = roomResponse.data.roomId
        val alias = "#kmatrix-test-${System.currentTimeMillis()}:matrix.org"

        try {
            // Set alias
            matrix().directory().setAlias(
                DirectorySetAliasRequest().also {
                    it.roomAlias = alias
                    it.roomId = roomId
                }
            )
            println("=== Set Alias ===")
            println("  Alias   > $alias")
            println("  Room ID > $roomId")

            // Resolve alias
            val resolved = matrix().directory().resolveAlias(alias)
            println("=== Resolve Set Alias ===")
            println("  Room ID > ${resolved.data.roomId}")
            assert(resolved.data.roomId == roomId)

            // Delete alias
            matrix().directory().deleteAlias(alias)
            println("=== Delete Alias ===")
            println("  Deleted > $alias")
        } catch (e: Exception) {
            println("=== Alias Test ===")
            println("  Error: ${e.message}")
        }
    }
}
