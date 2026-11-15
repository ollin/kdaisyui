package io.github.ollin.kdaisyui.buildchecks

import kotlin.io.path.createTempFile
import kotlin.io.path.writeText
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class CheckBreakingChangeTest {

    @Test
    fun aBreakingPullRequestWithoutItsFooterFailsAndNamesIt() {
        val failure = assertFailsWith<IllegalStateException> { check(title = "feat!: change it\n", body = "Changes it.") }
        assertEquals("This pull request is breaking and does not say so:\n  $MISSING_FOOTER", failure.message)
    }

    @Test
    fun aPullRequestThatBreaksNothingPasses() {
        check(title = "fix: repair it\n", body = "Repairs it.")
    }

    private fun check(title: String, body: String) =
        CheckBreakingChange.main(arrayOf(fileWith(title), fileWith(body), fileWith(""), fileWith("")))

    private fun fileWith(text: String) = createTempFile().apply { writeText(text) }.toString()
}
