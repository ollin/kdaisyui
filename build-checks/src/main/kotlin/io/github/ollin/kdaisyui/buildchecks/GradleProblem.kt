package io.github.ollin.kdaisyui.buildchecks

/** One problem Gradle reported: its most specific id, and who caused it. */
data class GradleProblem(val id: String, val source: Source)

/** Who caused a Gradle problem: a plugin, or the build itself. */
sealed interface Source {
    data class Plugin(val id: String) : Source {
        override fun toString() = id
    }

    /** The null object: a problem Gradle attributes to no plugin, which only this build can fix. */
    data object ThisBuild : Source {
        override fun toString() = "this build"
    }
}

/**
 * The problems in Gradle's `build/reports/problems/problems-report.html`, read from its embedded JSON.
 *
 * Gradle lists a capped number of diagnostics and folds the rest into `summaries`, which carry no
 * location. A summary of a problem already listed is that problem again; one of a problem never
 * listed cannot be attributed, so it is the build's own.
 */
fun problemsIn(report: String): List<GradleProblem> {
    val diagnostics = report.split(summaryStart, limit = 2).first()
    val entries = entriesOf(diagnostics)
    val listed = entries.map { GradleProblem(idOf(it), sourceOf(it)) }
    val listedNames = entries.map(::nameOf).toSet()
    val unlisted = entriesOf(report.removePrefix(diagnostics)).map(::nameOf).filter { it !in listedNames }
    return listed + unlisted.map { GradleProblem(it, Source.ThisBuild) }
}

private fun entriesOf(json: String): List<String> = json.split("{\"problemId\":").drop(1)

/** The anchor of the entry's documentation link, one per deprecation, else its most specific name. */
private fun idOf(entry: String): String = anchor.findAll(entry).map { it.groupValues[1] }.firstOrNull() ?: nameOf(entry)

private fun sourceOf(entry: String): Source =
    pluginId.findAll(entry).map { Source.Plugin(it.groupValues[1]) }.firstOrNull() ?: Source.ThisBuild

private fun nameOf(entry: String): String = name.findAll(entry.substringBefore("]")).last().groupValues[1]

private val anchor = Regex("\"documentationLink\":\"[^\"#]*#([^\"]+)\"")

/** Where the summaries begin: a key of the report's data up to Gradle 9.8, a script of their own since 9.9. */
private val summaryStart = Regex("\"problemsReport\"|id=\"problems-summary\"")

private val name = Regex("\"name\":\"([^\"]+)\"")
private val pluginId = Regex("\"pluginId\":\"([^\"]+)\"")
