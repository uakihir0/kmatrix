package work.socialhub.kmatrix.apis

import kotlinx.coroutines.test.runTest
import work.socialhub.kmatrix.AbstractTest
import work.socialhub.kmatrix.api.request.push.PushRulesSetEnabledRequest
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

    @Test
    fun testSetEnabled() = runTest {
        // Get current state of .m.rule.master
        val before = matrix().pushRules().getEnabled(
            "global", "override", ".m.rule.master"
        )
        val originalEnabled = before.data.enabled
        println("=== Set Push Rule Enabled ===")
        println("  Original > $originalEnabled")

        // Toggle the enabled state
        val newEnabled = !originalEnabled
        matrix().pushRules().setEnabled(
            PushRulesSetEnabledRequest().also {
                it.scope = "global"
                it.kind = "override"
                it.ruleId = ".m.rule.master"
                it.enabled = newEnabled
            }
        )
        println("  Updated  > $newEnabled")

        // Verify the change
        val after = matrix().pushRules().getEnabled(
            "global", "override", ".m.rule.master"
        )
        println("  Verified > ${after.data.enabled}")

        // Restore original state
        matrix().pushRules().setEnabled(
            PushRulesSetEnabledRequest().also {
                it.scope = "global"
                it.kind = "override"
                it.ruleId = ".m.rule.master"
                it.enabled = originalEnabled
            }
        )
        println("  Restored > $originalEnabled")
    }
}
