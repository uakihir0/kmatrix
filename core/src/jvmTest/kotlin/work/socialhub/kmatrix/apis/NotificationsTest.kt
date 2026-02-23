package work.socialhub.kmatrix.apis

import kotlinx.coroutines.test.runTest
import work.socialhub.kmatrix.AbstractTest
import work.socialhub.kmatrix.api.request.notifications.NotificationsGetRequest
import kotlin.test.Test

class NotificationsTest : AbstractTest() {

    @Test
    fun testGetNotifications() = runTest {
        val response = matrix().notifications().getNotifications(
            NotificationsGetRequest().also {
                it.limit = 5
            }
        )
        println("=== Notifications ===")
        println("  Next Token > ${response.data.nextToken}")
        for (notification in response.data.notifications) {
            println("  Room  > ${notification.roomId}")
            println("  Event > ${notification.event.type} from ${notification.event.sender}")
            println("  Read  > ${notification.read}")
            println("  ---")
        }
    }
}
