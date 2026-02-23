package work.socialhub.kmatrix.api.response.rooms

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@JsExport
@Serializable
class RoomsGetStateEventResponse {
    var content: Map<String, @Contextual Any?> = mapOf()
}
