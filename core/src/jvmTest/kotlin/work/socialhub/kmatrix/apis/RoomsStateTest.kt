package work.socialhub.kmatrix.apis

import kotlinx.coroutines.test.runTest
import work.socialhub.kmatrix.AbstractTest
import kotlin.test.Test

class RoomsStateTest : AbstractTest() {

    @Test
    fun testGetState() = runTest {
        val rooms = matrix().rooms().getJoinedRooms()
        val roomId = rooms.data.joinedRooms.first()

        val response = matrix().rooms().getState(roomId)
        println("=== Room State ===")
        println("  State Events > ${response.data.size}")
        for (event in response.data) {
            println("    ${event.type} [${event.stateKey}] from ${event.sender}")
        }
        assert(response.data.isNotEmpty())
    }
}
