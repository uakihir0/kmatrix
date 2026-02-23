package work.socialhub.kmatrix.api

import work.socialhub.kmatrix.api.request.push.PushRulesSetEnabledRequest
import work.socialhub.kmatrix.api.response.Response
import work.socialhub.kmatrix.api.response.ResponseUnit
import work.socialhub.kmatrix.api.response.push.PushRulesGetEnabledResponse
import work.socialhub.kmatrix.api.response.push.PushRulesGetResponse
import kotlin.js.JsExport

@JsExport
interface PushRulesResource {

    /**
     * GET /_matrix/client/v3/pushrules/
     * Retrieve all push rulesets.
     */
    suspend fun getPushRules(): Response<PushRulesGetResponse>

    @JsExport.Ignore
    fun getPushRulesBlocking(): Response<PushRulesGetResponse>

    /**
     * GET /_matrix/client/v3/pushrules/{scope}/{kind}/{ruleId}/enabled
     * Get whether a push rule is enabled.
     */
    suspend fun getEnabled(
        scope: String,
        kind: String,
        ruleId: String
    ): Response<PushRulesGetEnabledResponse>

    @JsExport.Ignore
    fun getEnabledBlocking(
        scope: String,
        kind: String,
        ruleId: String
    ): Response<PushRulesGetEnabledResponse>

    /**
     * PUT /_matrix/client/v3/pushrules/{scope}/{kind}/{ruleId}/enabled
     * Enable or disable a push rule.
     */
    suspend fun setEnabled(request: PushRulesSetEnabledRequest): ResponseUnit

    @JsExport.Ignore
    fun setEnabledBlocking(request: PushRulesSetEnabledRequest): ResponseUnit
}
