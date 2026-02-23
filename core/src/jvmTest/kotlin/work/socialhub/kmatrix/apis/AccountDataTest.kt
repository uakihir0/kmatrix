package work.socialhub.kmatrix.apis

import kotlinx.coroutines.test.runTest
import work.socialhub.kmatrix.AbstractTest
import work.socialhub.kmatrix.api.request.accountdata.AccountDataGetRequest
import work.socialhub.kmatrix.api.request.accountdata.AccountDataSetRequest
import kotlin.test.Test

class AccountDataTest : AbstractTest() {

    @Test
    fun testSetAndGetAccountData() = runTest {
        val userId = matrix().accounts().whoami().data.userId

        // Set account data
        matrix().accountData().setAccountData(
            AccountDataSetRequest().also {
                it.userId = userId
                it.type = "work.socialhub.kmatrix.test"
                it.data = """{"test_key":"test_value"}"""
            }
        )
        println("=== Set Account Data ===")
        println("  Set successfully")

        // Get account data
        val response = matrix().accountData().getAccountData(
            AccountDataGetRequest().also {
                it.userId = userId
                it.type = "work.socialhub.kmatrix.test"
            }
        )
        println("=== Get Account Data ===")
        println("  Data > ${response.data}")
    }

    @Test
    fun testSetAndGetRoomAccountData() = runTest {
        val userId = matrix().accounts().whoami().data.userId
        val joinedRooms = matrix().rooms().getJoinedRooms().data.joinedRooms
        if (joinedRooms.isNotEmpty()) {
            val roomId = joinedRooms.first()

            // Set room account data
            matrix().accountData().setRoomAccountData(
                AccountDataSetRequest().also {
                    it.userId = userId
                    it.roomId = roomId
                    it.type = "work.socialhub.kmatrix.room_test"
                    it.data = """{"room_key":"room_value"}"""
                }
            )
            println("=== Set Room Account Data ===")
            println("  Room ID > $roomId")
            println("  Set successfully")

            // Get room account data
            val response = matrix().accountData().getRoomAccountData(
                AccountDataGetRequest().also {
                    it.userId = userId
                    it.roomId = roomId
                    it.type = "work.socialhub.kmatrix.room_test"
                }
            )
            println("=== Get Room Account Data ===")
            println("  Data > ${response.data}")
        }
    }
}
