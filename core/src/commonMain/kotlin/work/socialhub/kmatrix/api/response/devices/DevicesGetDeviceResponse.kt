package work.socialhub.kmatrix.api.response.devices

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@JsExport
@Serializable
class DevicesGetDeviceResponse {

    @SerialName("device_id")
    var deviceId: String = ""

    @SerialName("display_name")
    var displayName: String? = null

    @SerialName("last_seen_ip")
    var lastSeenIp: String? = null

    @SerialName("last_seen_ts")
    var lastSeenTs: Long? = null
}
