package work.socialhub.kmatrix.api.response.push

import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@JsExport
@Serializable
class PushRulesGetResponse {

    @SerialName("global")
    var global: PushRuleset? = null
}

@JsExport
@Serializable
class PushRuleset {

    @SerialName("override")
    var override: Array<PushRule>? = null

    @SerialName("content")
    var content: Array<PushRule>? = null

    @SerialName("room")
    var room: Array<PushRule>? = null

    @SerialName("sender")
    var sender: Array<PushRule>? = null

    @SerialName("underride")
    var underride: Array<PushRule>? = null
}

@JsExport
@Serializable
class PushRule {

    @SerialName("rule_id")
    var ruleId: String = ""

    @SerialName("default")
    var default: Boolean = false

    @SerialName("enabled")
    var enabled: Boolean = true

    @SerialName("conditions")
    var conditions: Array<PushCondition>? = null

    @SerialName("actions")
    var actions: Array<@Contextual Any?> = arrayOf()

    @SerialName("pattern")
    var pattern: String? = null
}

@JsExport
@Serializable
class PushCondition {

    @SerialName("kind")
    var kind: String = ""

    @SerialName("key")
    var key: String? = null

    @SerialName("pattern")
    var pattern: String? = null

    @SerialName("is")
    var `is`: String? = null
}
