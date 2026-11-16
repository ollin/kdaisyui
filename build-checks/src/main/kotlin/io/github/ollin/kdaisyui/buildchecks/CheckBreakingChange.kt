package io.github.ollin.kdaisyui.buildchecks

import kotlin.io.path.Path
import kotlin.io.path.readText

/**
 * `<title> <body> <api diff> <upgrading.md>`: fails, naming each, when a breaking pull request does not say so.
 *
 * An object, because a second top-level `main` in this package would conflict with `checkGradleProblems`'s.
 */
object CheckBreakingChange {
    @JvmStatic
    fun main(args: Array<String>) {
        val (title, body, apiDiff, upgrading) = args.map { Path(it).readText() }
        val findings = breakingChangeFindings(PullRequest(title.trim(), body, apiDiff, upgrading))
        check(findings.isEmpty()) { "This pull request is breaking and does not say so:\n" + findings.joinToString("\n") { "  $it" } }
    }
}
