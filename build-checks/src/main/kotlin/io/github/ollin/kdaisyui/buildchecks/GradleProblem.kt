package io.github.ollin.kdaisyui.buildchecks

/** One problem Gradle reported: its most specific id, and the plugin it is attributed to, if any. */
data class GradleProblem(val id: String, val pluginId: String?)

/**
 * The problems in Gradle's `build/reports/problems/problems-report.html`, read from its embedded JSON.
 *
 * Gradle lists a capped number of diagnostics and folds the rest into `summaries`, which carry no
 * location. A summary of a problem already listed is that problem again; one of a problem never
 * listed cannot be attributed, so it is the build's own.
 */
fun problemsIn(report: String): List<GradleProblem> {
    val diagnostics = report.substringBefore("\"problemsReport\"")
    val listed = entriesOf(diagnostics).map { GradleProblem(idOf(it), pluginId.find(it)?.groupValues[1]) }
    val unlisted = entriesOf(report.removePrefix(diagnostics)).map(::idOf).filter { id -> listed.none { it.id == id } }
    return listed + unlisted.map { GradleProblem(it, null) }
}

private fun entriesOf(json: String): List<String> = json.split("{\"problemId\":").drop(1)

private fun idOf(entry: String): String = name.findAll(entry.substringBefore("]")).last().groupValues[1]

private val name = Regex("\"name\":\"([^\"]+)\"")
private val pluginId = Regex("\"pluginId\":\"([^\"]+)\"")
