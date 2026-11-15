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
        assertEquals(true, "The title lacks `!`, although lib/api loses a line." in breakingChangeFindings(removing))
    }

    @Test
    fun aScopedBreakingTitleSaysBreaking() {
        val removing = PullRequest(title = "feat(lib)!: change a thing", body = "", apiDiff = "-old line\n", upgrading = "")
        assertEquals(false, "The title lacks `!`, although lib/api loses a line." in breakingChangeFindings(removing))
    }

    private fun breakingWith(body: String) = PullRequest(title = "feat!: change a thing", body = body, apiDiff = "", upgrading = "")

    @Test
    fun aBreakingTitleWithoutAFooterNamesTheMissingFooter() {
        assertEquals(true, MISSING_FOOTER in breakingChangeFindings(breakingWith("Changes a thing.")))
    }

    @Test
    fun aFooterFollowedByAnotherParagraphIsNotAFooter() {
        assertEquals(true, MISSING_FOOTER in breakingChangeFindings(breakingWith("Changes it.\n\nBREAKING CHANGE: it changed.\n\nThanks.")))
    }

    @Test
    fun aFooterLastInACrlfBodyIsAFooter() {
        assertEquals(false, MISSING_FOOTER in breakingChangeFindings(breakingWith("Changes it.\r\n\r\nBREAKING CHANGE: it changed.\r\n")))
    }
}
