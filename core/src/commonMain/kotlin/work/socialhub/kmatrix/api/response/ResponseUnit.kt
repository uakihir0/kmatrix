package work.socialhub.kmatrix.api.response

import work.socialhub.kmatrix.entity.share.RateLimit
import kotlin.js.JsExport

@JsExport
class ResponseUnit {
    var limit: RateLimit? = null
}
