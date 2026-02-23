package work.socialhub.kmatrix.api.request.devices

import kotlin.js.JsExport

@JsExport
class DevicesUpdateRequest {
    var deviceId: String? = null

    /** A display name to set for the device. */
    var displayName: String? = null
}
