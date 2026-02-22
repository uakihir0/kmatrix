package work.socialhub.kmatrix.apis

import kotlinx.coroutines.test.runTest
import work.socialhub.kmatrix.AbstractTest
import work.socialhub.kmatrix.api.request.login.LoginSsoRedirectRequest
import kotlin.test.Test

class LoginTest : AbstractTest() {

    @Test
    fun testGetLoginFlows() = runTest {
        val response = matrixNoAuth().login().getLoginFlows()
        println("=== Login Flows ===")
        for (flow in response.data.flows) {
            println("  Type > ${flow.type}")
        }
        assert(response.data.flows.isNotEmpty())
    }

    @Test
    fun testSsoRedirectUrl() {
        val url = matrixNoAuth().login().ssoRedirectUrl(
            LoginSsoRedirectRequest().also {
                it.redirectUrl = "http://localhost:8080/callback"
            }
        )
        println("=== SSO Redirect URL ===")
        println("  URL > $url")
        assert(url.contains("/_matrix/client/v3/login/sso/redirect"))
    }
}
