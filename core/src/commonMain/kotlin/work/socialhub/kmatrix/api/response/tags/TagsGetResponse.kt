package work.socialhub.kmatrix.api.response.tags

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@JsExport
@Serializable
class TagsGetResponse {

    @SerialName("tags")
    var tags: Map<String, TagContent> = mapOf()
}

@JsExport
@Serializable
class TagContent {

    @SerialName("order")
    var order: Double? = null
}
