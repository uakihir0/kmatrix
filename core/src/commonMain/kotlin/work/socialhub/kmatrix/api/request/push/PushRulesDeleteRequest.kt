package work.socialhub.kmatrix.api.request.push

import kotlin.js.JsExport

@JsExport
class PushRulesDeleteRequest {
    /** The scope to delete from. Usually "global". */
    var scope: String? = "global"

    /** The kind of rule. One of: "override", "underride", "sender", "room", "content". */
    var kind: String? = null

    /** The identifier for the rule. */
    var ruleId: String? = null
}
