package work.socialhub.kmatrix.api.response.sync

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import work.socialhub.kmatrix.api.response.rooms.RoomEvent
import kotlin.js.JsExport

@JsExport
@Serializable
class SyncResponse {

    @SerialName("next_batch")
    var nextBatch: String = ""

    @SerialName("rooms")
    var rooms: SyncRooms? = null

    @SerialName("account_data")
    var accountData: SyncAccountData? = null
}

@JsExport
@Serializable
class SyncRooms {

    @SerialName("join")
    var join: Map<String, SyncJoinedRoom>? = null

    @SerialName("invite")
    var invite: Map<String, SyncInvitedRoom>? = null

    @SerialName("leave")
    var leave: Map<String, SyncLeftRoom>? = null
}

@JsExport
@Serializable
class SyncJoinedRoom {

    @SerialName("summary")
    var summary: SyncRoomSummary? = null

    @SerialName("timeline")
    var timeline: SyncTimeline? = null

    @SerialName("state")
    var state: SyncState? = null

    @SerialName("account_data")
    var accountData: SyncAccountData? = null

    @SerialName("ephemeral")
    var ephemeral: SyncEphemeral? = null

    @SerialName("unread_notifications")
    var unreadNotifications: SyncUnreadNotifications? = null
}

/**
 * Room summary block from `/sync` (MSC688). Lets clients compute a room's
 * display name and member count without fetching the full member list, which
 * is what enables `lazy_load_members`.
 */
@JsExport
@Serializable
class SyncRoomSummary {

    /**
     * A subset of the room members used to name the room when it has no
     * `m.room.name` / `m.room.canonical_alias` (the current user is excluded).
     */
    @SerialName("m.heroes")
    var heroes: Array<String>? = null

    @SerialName("m.joined_member_count")
    var joinedMemberCount: Int? = null

    @SerialName("m.invited_member_count")
    var invitedMemberCount: Int? = null
}

@JsExport
@Serializable
class SyncInvitedRoom {

    @SerialName("invite_state")
    var inviteState: SyncInviteState? = null
}

@JsExport
@Serializable
class SyncLeftRoom {

    @SerialName("timeline")
    var timeline: SyncTimeline? = null

    @SerialName("state")
    var state: SyncState? = null

    @SerialName("account_data")
    var accountData: SyncAccountData? = null
}

@JsExport
@Serializable
class SyncTimeline {

    @SerialName("events")
    var events: Array<RoomEvent> = arrayOf()

    @SerialName("limited")
    var limited: Boolean? = null

    @SerialName("prev_batch")
    var prevBatch: String? = null
}

@JsExport
@Serializable
class SyncState {

    @SerialName("events")
    var events: Array<RoomEvent> = arrayOf()
}

@JsExport
@Serializable
class SyncAccountData {

    @SerialName("events")
    var events: Array<RoomEvent> = arrayOf()
}

@JsExport
@Serializable
class SyncEphemeral {

    @SerialName("events")
    var events: Array<RoomEvent> = arrayOf()
}

@JsExport
@Serializable
class SyncUnreadNotifications {

    @SerialName("highlight_count")
    var highlightCount: Int? = null

    @SerialName("notification_count")
    var notificationCount: Int? = null
}

@JsExport
@Serializable
class SyncInviteState {

    @SerialName("events")
    var events: Array<RoomEvent> = arrayOf()
}
