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
        assertEquals(true, MISSING_BANG in breakingChangeFindings(removing))
    }

    @Test
    fun aScopedBreakingTitleSaysBreaking() {
        val removing = PullRequest(title = "feat(lib)!: change a thing", body = "", apiDiff = "-old line\n", upgrading = "")
        assertEquals(false, MISSING_BANG in breakingChangeFindings(removing))
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

    private val upgrading = "# Upgrading\r\n\r\n## How to migrate from 0.12.x \r\n\r\nRename it.\r\n"

    private fun footed(footer: String) = PullRequest(title = "feat!: change a thing", body = "Changes it.\n\n$footer", apiDiff = "", upgrading = upgrading)

    @Test
    fun aFooterNamingNoMigrationHeadingSaysSo() {
        assertEquals(listOf(MISSING_HEADING), breakingChangeFindings(footed("BREAKING CHANGE: it changed.")))
    }

    @Test
    fun aFooterNamingAHeadingUpgradingLacksNamesTheHeading() {
        val findings = breakingChangeFindings(footed("BREAKING CHANGE: see \"How to migrate from 0.13.x\" in docs/upgrading.md."))
        assertEquals(listOf("docs/upgrading.md has no heading `## How to migrate from 0.13.x`."), findings)
    }

    @Test
    fun aCompleteBreakingPullRequestPasses() {
        assertEquals(emptyList(), breakingChangeFindings(footed("BREAKING CHANGE: see \"How to migrate from 0.12.x\" in docs/upgrading.md.")))
    }
}
