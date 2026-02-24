package work.socialhub.kmatrix.api.request.pushers

import kotlin.js.JsExport

@JsExport
class PushersSetRequest {
    /** The pushkey for this pusher (e.g., email address or push token). */
    var pushkey: String? = null

    /** The kind of pusher. "http" is a pusher that sends HTTP pokes. "email" sends email notifications. Use null to delete. */
    var kind: String? = null

    /** The application ID (reverse DNS style, e.g., "com.example.app"). */
    var appId: String? = null

    /** A human readable string for the application name. */
    var appDisplayName: String? = null

    /** A human readable string for the device name. */
    var deviceDisplayName: String? = null

    /** Identifies which set of device specific rules this pusher executes. */
    var profileTag: String? = null

    /** The preferred language for receiving notifications (ISO 639, e.g., "en"). */
    var lang: String? = null

    /** A dictionary of data for the pusher. For "http" kind, must contain "url". */
    var data: PusherData? = null

    /** If true, the server should add another pusher instead of replacing existing ones. */
    var append: Boolean? = null

    /** Whether the pusher should actively create push notifications. */
    var enabled: Boolean? = null
}

@JsExport
class PusherData {
    /** The URL to use to send notifications to (required for "http" kind). */
    var url: String? = null

    /** The format to use when sending notifications (e.g., "event_id_only"). */
    var format: String? = null
}
