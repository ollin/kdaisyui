package io.github.ollin.kdaisyui.buildchecks

import kotlin.test.Test
import kotlin.test.assertEquals

class BreakingChangeTest {

    private val nonBreaking = PullRequest(title = "feat: add a thing", body = "Adds a thing.", apiDiff = "+added line\n", upgrading = "")

    @Test
    fun aPullRequestThatBreaksNothingPassesWhateverItsBodySays() {
        assertEquals(emptyList(), breakingChangeFindings(nonBreaking))
    }
}
