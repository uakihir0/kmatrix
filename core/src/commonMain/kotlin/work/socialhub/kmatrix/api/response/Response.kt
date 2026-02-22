package work.socialhub.kmatrix.api.response

import work.socialhub.kmatrix.entity.share.RateLimit
import kotlin.js.JsExport

@JsExport
class Response<T>(
    var data: T
) {
    var limit: RateLimit? = null
    var json: String? = null
}
