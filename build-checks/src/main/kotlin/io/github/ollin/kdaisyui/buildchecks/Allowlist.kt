package io.github.ollin.kdaisyui.buildchecks

/**
 * The [problems] no line of [allowlist] accepts, each once. A line is `<problem id> <plugin id>`;
 * a blank line or one starting with `#` says nothing. A problem attributed to no plugin, which is
 * one the build itself causes, can never be accepted.
 */
fun unexpected(problems: List<GradleProblem>, allowlist: String): List<GradleProblem> {
    val accepted = allowlist.lines().filter { it.isNotBlank() && !it.startsWith("#") }
        .map { line -> line.trim().split(Regex("\\s+")).let { (id, plugin) -> GradleProblem(id, plugin) } }
    return problems.filterNot { it in accepted }.distinct()
}
