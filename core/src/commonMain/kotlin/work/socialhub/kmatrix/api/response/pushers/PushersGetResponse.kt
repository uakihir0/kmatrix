package work.socialhub.kmatrix.api.response.pushers

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@JsExport
@Serializable
class PushersGetResponse {

    @SerialName("pushers")
    var pushers: Array<Pusher> = arrayOf()

    @Serializable
    class Pusher {
        @SerialName("pushkey")
        var pushkey: String = ""

        @SerialName("kind")
        var kind: String = ""

        @SerialName("app_id")
        var appId: String = ""

        @SerialName("app_display_name")
        var appDisplayName: String = ""

        @SerialName("device_display_name")
        var deviceDisplayName: String = ""

        @SerialName("profile_tag")
        var profileTag: String? = null

        @SerialName("lang")
        var lang: String = ""

        @SerialName("data")
        var data: PusherData = PusherData()

        @SerialName("enabled")
        var enabled: Boolean? = null
    }

    @Serializable
    class PusherData {
        @SerialName("url")
        var url: String? = null

        @SerialName("format")
        var format: String? = null
    }
}
