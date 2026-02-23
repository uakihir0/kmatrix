package work.socialhub.kmatrix.api.request.tags

import kotlin.js.JsExport

@JsExport
class TagsDeleteRequest {
    var userId: String? = null
    var roomId: String? = null
    var tag: String? = null
}
