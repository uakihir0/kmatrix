package work.socialhub.kmatrix.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@JsExport
@Serializable
class Room {

    @SerialName("room_id")
    lateinit var roomId: String

    @SerialName("name")
    var name: String? = null

    @SerialName("topic")
    var topic: String? = null
}
