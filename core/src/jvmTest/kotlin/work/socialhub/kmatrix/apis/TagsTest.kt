package work.socialhub.kmatrix.apis

import kotlinx.coroutines.test.runTest
import work.socialhub.kmatrix.AbstractTest
import work.socialhub.kmatrix.api.request.tags.TagsDeleteRequest
import work.socialhub.kmatrix.api.request.tags.TagsSetRequest
import kotlin.test.Test

class TagsTest : AbstractTest() {

    @Test
    fun testGetTags() = runTest {
        val whoami = matrix().accounts().whoami()
        val userId = whoami.data.userId
        val rooms = matrix().rooms().getJoinedRooms()
        if (rooms.data.joinedRooms.isNotEmpty()) {
            val roomId = rooms.data.joinedRooms.first()
            val response = matrix().tags().getTags(userId, roomId)
            println("=== Tags ===")
            for ((tag, content) in response.data.tags) {
                println("  Tag   > $tag")
                println("  Order > ${content.order}")
                println("  ---")
            }
        }
    }

    @Test
    fun testSetAndDeleteTag() = runTest {
        val whoami = matrix().accounts().whoami()
        val userId = whoami.data.userId
        val rooms = matrix().rooms().getJoinedRooms()
        if (rooms.data.joinedRooms.isNotEmpty()) {
            val roomId = rooms.data.joinedRooms.first()

            // Set tag
            val setRequest = TagsSetRequest().also {
                it.userId = userId
                it.roomId = roomId
                it.tag = "u.kmatrix_test"
                it.order = 0.5
            }
            matrix().tags().setTag(setRequest)
            println("=== Set Tag ===")
            println("  Tag set successfully")

            // Verify
            val tagsResponse = matrix().tags().getTags(userId, roomId)
            println("=== Tags after set ===")
            for ((tag, content) in tagsResponse.data.tags) {
                println("  Tag   > $tag")
                println("  Order > ${content.order}")
            }

            // Delete tag
            val deleteRequest = TagsDeleteRequest().also {
                it.userId = userId
                it.roomId = roomId
                it.tag = "u.kmatrix_test"
            }
            matrix().tags().deleteTag(deleteRequest)
            println("=== Delete Tag ===")
            println("  Tag deleted successfully")
        }
    }
}
