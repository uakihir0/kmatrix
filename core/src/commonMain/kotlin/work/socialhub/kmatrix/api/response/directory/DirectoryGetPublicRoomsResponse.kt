package work.socialhub.kmatrix.api.response.directory

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@JsExport
@Serializable
class DirectoryGetPublicRoomsResponse {

    @SerialName("chunk")
    var chunk: Array<PublicRoomChunk> = arrayOf()

    @SerialName("next_batch")
    var nextBatch: String? = null

    @SerialName("prev_batch")
    var prevBatch: String? = null

    @SerialName("total_room_count_estimate")
    var totalRoomCountEstimate: Long? = null
}

@JsExport
@Serializable
class PublicRoomChunk {

    @SerialName("room_id")
    var roomId: String = ""

    @SerialName("name")
    var name: String? = null

    @SerialName("topic")
    var topic: String? = null

    @SerialName("num_joined_members")
    var numJoinedMembers: Long = 0

    @SerialName("canonical_alias")
    var canonicalAlias: String? = null

    @SerialName("aliases")
    var aliases: Array<String>? = null

    @SerialName("world_readable")
    var worldReadable: Boolean = false

    @SerialName("guest_can_join")
    var guestCanJoin: Boolean = false

    @SerialName("avatar_url")
    var avatarUrl: String? = null

    @SerialName("join_rule")
    var joinRule: String? = null
}
