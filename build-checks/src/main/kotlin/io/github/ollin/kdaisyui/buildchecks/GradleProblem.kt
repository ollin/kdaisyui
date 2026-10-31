package io.github.ollin.kdaisyui.buildchecks

/** One problem Gradle reported: its most specific id, and the plugin it is attributed to, if any. */
data class GradleProblem(val id: String, val pluginId: String?)

/** The problems in Gradle's `build/reports/problems/problems-report.html`, read from its embedded JSON. */
fun problemsIn(report: String): List<GradleProblem> = report.split("{\"problemId\":").drop(1).map { entry ->
    GradleProblem(name.findAll(entry.substringBefore("]")).last().groupValues[1], pluginId.find(entry)?.groupValues[1])
}

private val name = Regex("\"name\":\"([^\"]+)\"")
private val pluginId = Regex("\"pluginId\":\"([^\"]+)\"")
