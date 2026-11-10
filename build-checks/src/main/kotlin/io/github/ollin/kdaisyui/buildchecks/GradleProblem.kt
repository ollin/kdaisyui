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
    val listed = entriesOf(diagnostics)
    val listedNames = listed.map { it.name }.toSet()
    val unlisted = entriesOf(report.removePrefix(diagnostics)).filter { it.name !in listedNames }
    return listed.map { it.problem } + unlisted.map { GradleProblem(it.name, Source.ThisBuild) }
}

private fun entriesOf(json: String): List<ReportEntry> = json.split("{\"problemId\":").drop(1).map(::ReportEntry)

/** One problem's entry in the report's data, from its `problemId` to the next entry. */
@JvmInline
private value class ReportEntry(private val json: String) {
    /** Its most specific name: generic since Gradle 9.9, and all a summary carries. */
    val name: String get() = namePattern.findAll(json.substringBefore("]")).last().groupValues[1]

    /** The anchor of its documentation link, one per deprecation, else its name. */
    val id: String get() = anchorPattern.findAll(json).map { it.groupValues[1] }.firstOrNull() ?: name

    /** The plugin it is attributed to, else the build itself. */
    val source: Source
        get() = pluginPattern.findAll(json).map { Source.Plugin(it.groupValues[1]) }.firstOrNull() ?: Source.ThisBuild

    val problem: GradleProblem get() = GradleProblem(id, source)
}

/** Where the summaries begin: a key of the report's data up to Gradle 9.8, a script of their own since 9.9. */
private val summaryStart = Regex("\"problemsReport\"|id=\"problems-summary\"")

private val namePattern = Regex("\"name\":\"([^\"]+)\"")
private val anchorPattern = Regex("\"documentationLink\":\"[^\"#]*#([^\"]+)\"")
private val pluginPattern = Regex("\"pluginId\":\"([^\"]+)\"")
