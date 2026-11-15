package io.github.ollin.kdaisyui.buildchecks

/** What the breaking-change check reads of a pull request: title, body, the API baselines' diff, and its `docs/upgrading.md`. */
class PullRequest(val title: String, val body: String, val apiDiff: String, val upgrading: String)

/** Each way a breaking pull request fails to say so, in words a pull request author can act on. */
fun breakingChangeFindings(pullRequest: PullRequest): List<String> = buildList {
    if (pullRequest.removesApi() && !pullRequest.titleSaysBreaking()) add("The title lacks `!`, although lib/api loses a line.")
    if (pullRequest.isBreaking() && !pullRequest.lastParagraph().startsWith(FOOTER)) add(MISSING_FOOTER)
}

private const val FOOTER = "BREAKING CHANGE:"

internal const val MISSING_FOOTER = "The body's last paragraph is not a `BREAKING CHANGE:` footer; JReleaser renders everything after the footer as part of it."

private fun PullRequest.isBreaking() = titleSaysBreaking() || removesApi()

/** What follows the last blank line, in GitHub's CRLF bodies as in plain ones. */
private fun PullRequest.lastParagraph() = body.replace("\r\n", "\n").trim().split(Regex("\n\\s*\n")).last()

private val breakingTitle = Regex("""^\w+(\(.+\))?!:""")

private fun PullRequest.titleSaysBreaking() = breakingTitle.containsMatchIn(title)

/** A removed line of a unified diff, not its `---` file header. */
private fun PullRequest.removesApi() = apiDiff.lines().any { it.startsWith("-") && !it.startsWith("---") }
