package work.socialhub.kmatrix.apis

import kotlinx.coroutines.test.runTest
import work.socialhub.kmatrix.AbstractTest
import work.socialhub.kmatrix.api.request.userdirectory.UserDirectorySearchRequest
import kotlin.test.Test

class UserDirectoryTest : AbstractTest() {

    @Test
    fun testSearchUsers() = runTest {
        val request = UserDirectorySearchRequest().also {
            it.searchTerm = "uakihir0"
            it.limit = 10
        }
        val response = matrix().userDirectory().search(request)
        println("=== User Directory Search ===")
        println("  Limited > ${response.data.limited}")
        for (user in response.data.results) {
            println("  User ID      > ${user.userId}")
            println("  Display Name > ${user.displayName}")
            println("  Avatar URL   > ${user.avatarUrl}")
            println("  ---")
        }
    }

    @Test
    fun testSearchUsersWithLimit() = runTest {
        val request = UserDirectorySearchRequest().also {
            it.searchTerm = "matrix"
            it.limit = 3
        }
        val response = matrix().userDirectory().search(request)
        println("=== User Directory Search (limited) ===")
        println("  Results > ${response.data.results.size}")
        println("  Limited > ${response.data.limited}")
        for (user in response.data.results) {
            println("  User ID > ${user.userId}")
        }
    }
}
