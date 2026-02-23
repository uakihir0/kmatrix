package work.socialhub.kmatrix.api.response.capabilities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@JsExport
@Serializable
class CapabilitiesGetResponse {

    @SerialName("capabilities")
    var capabilities: Capabilities = Capabilities()

    @Serializable
    class Capabilities {

        @SerialName("m.change_password")
        var changePassword: ChangePassword? = null

        @SerialName("m.room_versions")
        var roomVersions: RoomVersions? = null

        @SerialName("m.set_displayname")
        var setDisplayname: BooleanCapability? = null

        @SerialName("m.set_avatar_url")
        var setAvatarUrl: BooleanCapability? = null

        @SerialName("m.3pid_changes")
        var thirdPartyIdChanges: BooleanCapability? = null
    }

    @Serializable
    class ChangePassword {
        @SerialName("enabled")
        var enabled: Boolean = true
    }

    @Serializable
    class BooleanCapability {
        @SerialName("enabled")
        var enabled: Boolean = true
    }

    @Serializable
    class RoomVersions {
        @SerialName("default")
        var default: String = ""

        @SerialName("available")
        var available: Map<String, String> = mapOf()
    }
}
