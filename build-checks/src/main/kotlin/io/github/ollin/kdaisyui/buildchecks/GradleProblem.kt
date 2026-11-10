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
    val diagnostics = report.split(summaryStart, limit = 2).first()
    val listed = entriesOf(diagnostics).map { GradleProblem(idOf(it), pluginId.find(it)?.groupValues[1]) }
    val listedIds = listed.map { it.id }.toSet()
    val unlisted = entriesOf(report.removePrefix(diagnostics)).map(::idOf).filter { it !in listedIds }
    return listed + unlisted.map { GradleProblem(it, null) }
}

private fun entriesOf(json: String): List<String> = json.split("{\"problemId\":").drop(1)

private fun idOf(entry: String): String = name.findAll(entry.substringBefore("]")).last().groupValues[1]

/** Where the summaries begin: a key of the report's data up to Gradle 9.8, a script of their own since 9.9. */
private val summaryStart = Regex("\"problemsReport\"|id=\"problems-summary\"")

private val name = Regex("\"name\":\"([^\"]+)\"")
private val pluginId = Regex("\"pluginId\":\"([^\"]+)\"")
