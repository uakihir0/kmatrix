package work.socialhub.kmatrix.apis

import kotlinx.coroutines.test.runTest
import work.socialhub.kmatrix.AbstractTest
import work.socialhub.kmatrix.api.request.filter.FilterCreateRequest
import work.socialhub.kmatrix.api.request.filter.FilterGetRequest
import kotlin.test.Test

class FilterCreateTest : AbstractTest() {

    @Test
    fun testCreateFilter() = runTest {
        val request = FilterCreateRequest().also {
            it.userId = "@uakihir0:matrix.org"
            it.eventTypes = arrayOf("m.room.message")
            it.limit = 10
        }
        val response = matrix().filter().createFilter(request)
        println("=== Create Filter ===")
        println("  Filter ID > ${response.data.filterId}")
    }

    @Test
    fun testCreateAndGetFilter() = runTest {
        val createRequest = FilterCreateRequest().also {
            it.userId = "@uakihir0:matrix.org"
            it.eventTypes = arrayOf("m.room.message")
            it.limit = 20
        }
        val createResponse = matrix().filter().createFilter(createRequest)
        val filterId = createResponse.data.filterId
        println("=== Created Filter ===")
        println("  Filter ID > $filterId")

        val getRequest = FilterGetRequest().also {
            it.userId = "@uakihir0:matrix.org"
            it.filterId = filterId
        }
        val getResponse = matrix().filter().getFilter(getRequest)
        println("=== Get Filter ===")
        println("  Room > ${getResponse.data.room}")
        println("  JSON > ${getResponse.json}")
    }
}
