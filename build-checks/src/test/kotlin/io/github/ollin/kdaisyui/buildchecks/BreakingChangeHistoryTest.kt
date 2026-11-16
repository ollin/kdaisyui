package io.github.ollin.kdaisyui.buildchecks

import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * The check against three real pull requests, copied from their merge commits on `main`: the
 * title, the body, and the first lines of their `lib/api` diff.
 *
 * #471 (v0.9.0) and #492 (v0.11.0) are the two breaking pull requests since v0.9.0 whose release
 * notes lacked the migration pointer, the reason the check exists. #515 broke nothing.
 */
class BreakingChangeHistoryTest {

    private fun resource(name: String) =
        checkNotNull(javaClass.getResource("/pull-requests/$name")) { "no fixture $name" }.readText()

    private fun findingsOf(number: Int) = breakingChangeFindings(
        PullRequest(
            title = resource("$number-title.txt").trim(),
            body = resource("$number-body.md"),
            apiDiff = resource("$number-api.diff"),
            upgrading = "",
        ),
    )

    @Test
    fun pullRequest471LackedOnlyTheFooter() {
        assertEquals(listOf(MISSING_FOOTER), findingsOf(471))
    }

    @Test
    fun pullRequest492LackedTheBangAndTheFooter() {
        assertEquals(listOf(MISSING_BANG, MISSING_FOOTER), findingsOf(492))
    }

    @Test
    fun pullRequest515BrokeNothing() {
        assertEquals(emptyList(), findingsOf(515))
    }
}
