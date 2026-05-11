package work.socialhub.kmatrix.api.response.account

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@JsExport
@Serializable
class RegisterResponse(
    @SerialName("access_token")
    val accessToken: String,

    @SerialName("home_server")
    val homeServer: String,

    @SerialName("user_id")
    val userId: String,

    @SerialName("device_id")
    val deviceId: String? = null,

    @SerialName("default_rooms")
    val defaultRooms: Array<String>? = null,

    @SerialName("next_chunk_ms")
    val nextChunkMs: Long? = null,

    @SerialName("device_display_name")
    val deviceDisplayName: String? = null,

    @SerialName("well_known")
    val wellKnown: String? = null,
)
