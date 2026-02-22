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
