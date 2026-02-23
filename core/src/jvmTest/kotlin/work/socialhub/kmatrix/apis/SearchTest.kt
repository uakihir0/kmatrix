package work.socialhub.kmatrix.apis

import kotlinx.coroutines.test.runTest
import work.socialhub.kmatrix.AbstractTest
import work.socialhub.kmatrix.api.request.search.SearchRequest
import kotlin.test.Test

class SearchTest : AbstractTest() {

    @Test
    fun testSearch() = runTest {
        val request = SearchRequest().also {
            it.searchTerm = "hello"
            it.orderBy = "recent"
        }
        val response = matrix().search().search(request)
        val roomEvents = response.data.searchCategories?.roomEvents
        println("=== Search Results ===")
        println("  Count > ${roomEvents?.count}")
        println("  Next Batch > ${roomEvents?.nextBatch}")
        for (result in roomEvents?.results ?: arrayOf()) {
            println("  Event ID > ${result.result?.eventId}")
            println("  Sender   > ${result.result?.sender}")
            println("  Body     > ${result.result?.content?.body}")
            println("  Rank     > ${result.rank}")
            println("  ---")
        }
    }

    @Test
    fun testSearchWithKeys() = runTest {
        val request = SearchRequest().also {
            it.searchTerm = "test"
            it.keys = arrayOf("content.body")
        }
        val response = matrix().search().search(request)
        val roomEvents = response.data.searchCategories?.roomEvents
        println("=== Search Results (with keys) ===")
        println("  Count > ${roomEvents?.count}")
        for (result in roomEvents?.results ?: arrayOf()) {
            println("  Body > ${result.result?.content?.body}")
        }
    }
}
