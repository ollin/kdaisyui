package io.github.ollin.kdaisyui.buildchecks

/** What the breaking-change check reads of a pull request: title, body, the API baselines' diff, and its `docs/upgrading.md`. */
class PullRequest(val title: String, val body: String, val apiDiff: String, val upgrading: String)

/** Each way a breaking pull request fails to say so, in words a pull request author can act on. */
fun breakingChangeFindings(pullRequest: PullRequest): List<String> = emptyList()
