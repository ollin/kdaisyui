package io.github.ollin.kdaisyui.buildchecks

/**
 * The [problems] no line of [allowlist] accepts, each once. A line is `<problem id> <plugin id>`;
 * a blank line or one starting with `#` says nothing. A line names a plugin, so a problem of
 * [Source.ThisBuild] can never be accepted.
 */
fun unexpected(problems: List<GradleProblem>, allowlist: String): List<GradleProblem> {
    val accepted = allowlist.lines().filter { it.isNotBlank() && !it.startsWith("#") }
        .map { line -> line.trim().split(Regex("\\s+")).let { (id, plugin) -> GradleProblem(id, Source.Plugin(plugin)) } }
    return problems.filterNot { it in accepted }.distinct()
}
