package io.github.ollin.kdaisyui.buildchecks

/** What the breaking-change check reads of a pull request: title, body, the API baselines' diff, and its `docs/upgrading.md`. */
class PullRequest(val title: String, val body: String, val apiDiff: String, val upgrading: String)

/** Each way a breaking pull request fails to say so, in words a pull request author can act on. */
fun breakingChangeFindings(pullRequest: PullRequest): List<String> = buildList {
    if (pullRequest.removesApi() && !pullRequest.titleSaysBreaking()) add(MISSING_BANG)
    if (pullRequest.isBreaking()) addAll(pullRequest.footerFindings())
}

/** The footer has to be last, and has to point at a migration heading `docs/upgrading.md` has. */
private fun PullRequest.footerFindings(): List<String> {
    val footer = lastParagraph()
    if (!footer.startsWith(FOOTER)) return listOf(MISSING_FOOTER)
    val heading = migrationHeading.find(footer) ?: return listOf(MISSING_HEADING)
    val headings = upgrading.lines().map { it.trimEnd() }
    return if ("## ${heading.value}" in headings) emptyList() else listOf("docs/upgrading.md has no heading `## ${heading.value}`.")
}

internal const val MISSING_HEADING = "The footer names no `How to migrate from <version>` heading of docs/upgrading.md."

/** The heading's version may close the footer's sentence, so it never ends on a full stop. */
private val migrationHeading = Regex("""How to migrate from [^\s"`]*[^\s"`.]""")

internal const val MISSING_BANG = "The title lacks `!`, although lib/api loses a line."

private const val FOOTER = "BREAKING CHANGE:"

internal const val MISSING_FOOTER = "The body's last paragraph is not a `BREAKING CHANGE:` footer; JReleaser renders everything after the footer as part of it."

private fun PullRequest.isBreaking() = titleSaysBreaking() || removesApi()

/** What follows the last blank line, in GitHub's CRLF bodies as in plain ones. */
private fun PullRequest.lastParagraph() = body.replace("\r\n", "\n").trim().split(Regex("\n\\s*\n")).last()

private val breakingTitle = Regex("""^\w+(\(.+\))?!:""")

private fun PullRequest.titleSaysBreaking() = breakingTitle.containsMatchIn(title)

/** A removed line of a unified diff, not its `---` file header. */
private fun PullRequest.removesApi() = apiDiff.lines().any { it.startsWith("-") && !it.startsWith("---") }
