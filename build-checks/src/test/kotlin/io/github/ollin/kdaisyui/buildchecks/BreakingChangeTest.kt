package io.github.ollin.kdaisyui.buildchecks

import kotlin.test.Test
import kotlin.test.assertEquals

class BreakingChangeTest {

    private val nonBreaking = PullRequest(title = "feat: add a thing", body = "Adds a thing.", apiDiff = "--- a/lib/api/lib.api\n+++ b/lib/api/lib.api\n+added line\n", upgrading = "")

    @Test
    fun aPullRequestThatBreaksNothingPassesWhateverItsBodySays() {
        assertEquals(emptyList(), breakingChangeFindings(nonBreaking))
    }

    @Test
    fun aRemovedApiLineUnderATitleWithoutBangNamesTheMissingBang() {
        val removing = PullRequest(title = "feat: change a thing", body = "", apiDiff = "-old line\n+new line\n", upgrading = "")
        assertEquals(listOf("The title lacks `!`, although lib/api loses a line."), breakingChangeFindings(removing))
    }

    @Test
    fun aScopedBreakingTitleSaysBreaking() {
        val removing = PullRequest(title = "feat(lib)!: change a thing", body = "", apiDiff = "-old line\n", upgrading = "")
        assertEquals(false, "The title lacks `!`, although lib/api loses a line." in breakingChangeFindings(removing))
    }
}
