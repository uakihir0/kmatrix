package work.socialhub.kmatrix.apis

import kotlinx.coroutines.test.runTest
import work.socialhub.kmatrix.AbstractTest
import kotlin.test.Test

class VoIPTest : AbstractTest() {

    @Test
    fun testGetTurnServer() = runTest {
        try {
            val response = matrix().voip().getTurnServer()
            println("=== TURN Server ===")
            println("  Username > ${response.data.username}")
            println("  TTL      > ${response.data.ttl}")
            for (uri in response.data.uris) {
                println("  URI      > $uri")
            }
        } catch (e: Exception) {
            // Some servers may not have TURN configured
            println("=== TURN Server ===")
            println("  Not available: ${e.message}")
        }
    }
}
