package work.socialhub.kmatrix.apis

import kotlinx.coroutines.test.runTest
import work.socialhub.kmatrix.AbstractTest
import kotlin.test.Test

class PushRulesTest : AbstractTest() {

    @Test
    fun testGetPushRules() = runTest {
        val response = matrix().pushRules().getPushRules()
        println("=== Push Rules ===")
        val global = response.data.global
        println("  Override rules  > ${global?.override?.size ?: 0}")
        println("  Content rules   > ${global?.content?.size ?: 0}")
        println("  Room rules      > ${global?.room?.size ?: 0}")
        println("  Sender rules    > ${global?.sender?.size ?: 0}")
        println("  Underride rules > ${global?.underride?.size ?: 0}")

        global?.override?.forEach { rule ->
            println("  [override] ${rule.ruleId} enabled=${rule.enabled}")
        }
        global?.underride?.forEach { rule ->
            println("  [underride] ${rule.ruleId} enabled=${rule.enabled}")
        }
    }

    @Test
    fun testGetEnabled() = runTest {
        val response = matrix().pushRules().getEnabled(
            "global", "override", ".m.rule.master"
        )
        println("=== Push Rule Enabled ===")
        println("  .m.rule.master enabled > ${response.data.enabled}")
    }
}
