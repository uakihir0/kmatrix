package work.socialhub.kmatrix.apis

import kotlinx.coroutines.test.runTest
import work.socialhub.kmatrix.AbstractTest
import kotlin.test.Test

class PushersTest : AbstractTest() {

    @Test
    fun testGetPushers() = runTest {
        val response = matrix().pushers().getPushers()
        println("=== Pushers ===")
        println("  Count > ${response.data.pushers.size}")
        for (pusher in response.data.pushers) {
            println("  Pushkey      > ${pusher.pushkey}")
            println("  Kind         > ${pusher.kind}")
            println("  App ID       > ${pusher.appId}")
            println("  App Name     > ${pusher.appDisplayName}")
            println("  Device Name  > ${pusher.deviceDisplayName}")
            println("  Lang         > ${pusher.lang}")
            println("  Data URL     > ${pusher.data.url}")
            println("  Data Format  > ${pusher.data.format}")
            println("  ---")
        }
    }
}
