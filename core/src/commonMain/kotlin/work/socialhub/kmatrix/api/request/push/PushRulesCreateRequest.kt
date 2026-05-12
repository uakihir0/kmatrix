package work.socialhub.kmatrix.api.request.push

import kotlin.js.JsExport

@JsExport
class PushRulesCreateRequest {
    /** The scope to modify. Usually "global". */
    var scope: String? = "global"

    /** The kind of rule. One of: "override", "underride", "sender", "room", "content". */
    var kind: String? = null

    /** The identifier for the rule. */
    var ruleId: String? = null

    /** The conditions for the rule. */
    var conditions: String? = null

    /** Whether the push rule is enabled or not. */
    var enabled: Boolean? = null

    /** Actions to execute when the rule matches. */
    var actions: String? = null

    /** The body of the rule as a JSON string containing all rule properties. */
    var ruleBody: String? = null
}
