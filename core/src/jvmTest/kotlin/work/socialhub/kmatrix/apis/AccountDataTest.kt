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
}
