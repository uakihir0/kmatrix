package work.socialhub.kmatrix.internal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import work.socialhub.khttpclient.HttpRequest
import work.socialhub.kmatrix.api.PushRulesResource
import work.socialhub.kmatrix.api.request.push.PushRulesCreateRequest
import work.socialhub.kmatrix.api.request.push.PushRulesDeleteRequest
import work.socialhub.kmatrix.api.request.push.PushRulesSetEnabledRequest
import work.socialhub.kmatrix.api.response.Response
import work.socialhub.kmatrix.api.response.ResponseUnit
import work.socialhub.kmatrix.api.response.push.PushRulesGetEnabledResponse
import work.socialhub.kmatrix.api.response.push.PushRulesGetResponse
import work.socialhub.kmatrix.internal.InternalUtility.toJson
import work.socialhub.kmatrix.util.Headers.AUTHORIZATION
import work.socialhub.kmatrix.util.MediaType
import work.socialhub.kmatrix.util.toBlocking

class PushRulesResourceImpl(
    uri: String,
    accessToken: String,
) : AbstractAuthResourceImpl(uri, accessToken),
    PushRulesResource {

    override suspend fun getPushRules(): Response<PushRulesGetResponse> {
        return proceed {
            HttpRequest()
                .url("${uri}/_matrix/client/v3/pushrules/")
                .header(AUTHORIZATION, bearerToken())
                .accept(MediaType.JSON)
                .get()
        }
    }

    override fun getPushRulesBlocking(): Response<PushRulesGetResponse> {
        return toBlocking { getPushRules() }
    }

    override suspend fun getEnabled(
        scope: String,
        kind: String,
        ruleId: String
    ): Response<PushRulesGetEnabledResponse> {
        return proceed {
            HttpRequest()
                .url("${uri}/_matrix/client/v3/pushrules/${scope}/${kind}/${ruleId}/enabled")
                .header(AUTHORIZATION, bearerToken())
                .accept(MediaType.JSON)
                .get()
        }
    }

    override fun getEnabledBlocking(
        scope: String,
        kind: String,
        ruleId: String
    ): Response<PushRulesGetEnabledResponse> {
        return toBlocking { getEnabled(scope, kind, ruleId) }
    }

    override suspend fun setEnabled(
        request: PushRulesSetEnabledRequest
    ): ResponseUnit {
        return proceedUnit {
            val scope = request.scope ?: "global"
            val kind = request.kind ?: ""
            val ruleId = request.ruleId ?: ""
            val body = toJson(
                SetEnabledBody(
                    enabled = request.enabled ?: true,
                )
            )
            HttpRequest()
                .url("${uri}/_matrix/client/v3/pushrules/${scope}/${kind}/${ruleId}/enabled")
                .header(AUTHORIZATION, bearerToken())
                .accept(MediaType.JSON)
                .json(body)
                .put()
        }
    }

    override fun setEnabledBlocking(
        request: PushRulesSetEnabledRequest
    ): ResponseUnit {
        return toBlocking { setEnabled(request) }
    }

    override suspend fun createRule(
        request: PushRulesCreateRequest
    ): ResponseUnit {
        return proceedUnit {
            val scope = request.scope ?: "global"
            val kind = request.kind ?: ""
            val ruleId = request.ruleId ?: ""
            val body = request.ruleBody ?: "{}"
            HttpRequest()
                .url("${uri}/_matrix/client/v3/pushrules/${scope}/${kind}/${ruleId}")
                .header(AUTHORIZATION, bearerToken())
                .accept(MediaType.JSON)
                .json(body)
                .put()
        }
    }

    override fun createRuleBlocking(
        request: PushRulesCreateRequest
    ): ResponseUnit {
        return toBlocking { createRule(request) }
    }

    override suspend fun deleteRule(
        request: PushRulesDeleteRequest
    ): ResponseUnit {
        return proceedUnit {
            val scope = request.scope ?: "global"
            val kind = request.kind ?: ""
            val ruleId = request.ruleId ?: ""
            HttpRequest()
                .url("${uri}/_matrix/client/v3/pushrules/${scope}/${kind}/${ruleId}")
                .header(AUTHORIZATION, bearerToken())
                .accept(MediaType.JSON)
                .json("{}")
                .delete()
        }
    }

    override fun deleteRuleBlocking(
        request: PushRulesDeleteRequest
    ): ResponseUnit {
        return toBlocking { deleteRule(request) }
    }

    @Serializable
    private data class SetEnabledBody(
        @SerialName("enabled")
        val enabled: Boolean,
    )
}
