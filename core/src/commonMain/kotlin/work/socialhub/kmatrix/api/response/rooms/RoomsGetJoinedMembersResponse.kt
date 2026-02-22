package work.socialhub.kmatrix.api.response.rooms

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@JsExport
@Serializable
class RoomsGetJoinedMembersResponse {

    @SerialName("joined")
    var joined: Map<String, RoomMember> = mapOf()
}

@JsExport
@Serializable
class RoomMember {

    @SerialName("display_name")
    var displayName: String? = null

    @SerialName("avatar_url")
    var avatarUrl: String? = null
}
