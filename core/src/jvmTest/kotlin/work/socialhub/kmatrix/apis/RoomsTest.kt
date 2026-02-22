package work.socialhub.kmatrix.apis

import kotlinx.coroutines.test.runTest
import work.socialhub.kmatrix.AbstractTest
import kotlin.test.Test

class RoomsTest : AbstractTest() {

    @Test
    fun testGetJoinedRooms() = runTest {
        val response = matrix().rooms().getJoinedRooms()
        println("=== Joined Rooms ===")
        for (room in response.data.joinedRooms) {
            println("  Room > $room")
        }
    }
}
