package work.socialhub.kmatrix.api.response.media

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@JsExport
@Serializable
class MediaPreviewUrlResponse {

    @SerialName("og:title")
    var ogTitle: String? = null

    @SerialName("og:description")
    var ogDescription: String? = null

    @SerialName("og:image")
    var ogImage: String? = null

    @SerialName("og:image:type")
    var ogImageType: String? = null

    @SerialName("og:image:width")
    var ogImageWidth: Int? = null

    @SerialName("og:image:height")
    var ogImageHeight: Int? = null

    @SerialName("matrix:image:size")
    var matrixImageSize: Long? = null

    @SerialName("og:type")
    var ogType: String? = null

    @SerialName("og:url")
    var ogUrl: String? = null

    @SerialName("og:site_name")
    var ogSiteName: String? = null
}
