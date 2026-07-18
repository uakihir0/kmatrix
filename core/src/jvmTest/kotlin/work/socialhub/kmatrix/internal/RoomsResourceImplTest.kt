package work.socialhub.kmatrix.internal

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import kotlin.test.Test
import kotlin.test.assertEquals

class RoomsResourceImplTest {

    @Test
    fun serializesMatrixReactionContent() {
        val body = RoomsResourceImpl("https://example.org", "token")
            .serializeReactionBody("\$event", "\uD83D\uDC4D")
        val relatesTo = Json.parseToJsonElement(body)
            .jsonObject["m.relates_to"]!!
            .jsonObject

        assertEquals("m.annotation", relatesTo["rel_type"]?.jsonPrimitive?.content)
        assertEquals("\$event", relatesTo["event_id"]?.jsonPrimitive?.content)
        assertEquals("\uD83D\uDC4D", relatesTo["key"]?.jsonPrimitive?.content)
        assertEquals(3, relatesTo.size)
    }
}
