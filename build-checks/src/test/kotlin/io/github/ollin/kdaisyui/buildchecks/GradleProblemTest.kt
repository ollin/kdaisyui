package io.github.ollin.kdaisyui.buildchecks

import kotlin.test.Test
import kotlin.test.assertEquals

class GradleProblemTest {

    @Test
    fun eachProblemIsItsMostSpecificIdAndThePluginItIsAttributedTo() {
        val report = """{"diagnostics":[""" +
            """{"problemId":[{"name":"deprecation"},{"name":"set-visible"}],"locations":[{"pluginId":"org.example.kover"}]},""" +
            """{"problemId":[{"name":"deprecation"},{"name":"external-process"}],"severity":"WARNING"}]}"""
        assertEquals(listOf(GradleProblem("set-visible", "org.example.kover"), GradleProblem("external-process", null)), problemsIn(report))
    }

    @Test
    fun aSummaryOfAListedProblemIsThatProblemAndOneOfAnUnlistedProblemIsTheBuildsOwn() {
        val report = """{"diagnostics":[{"problemId":[{"name":"set-visible"}],"locations":[{"pluginId":"org.example.kover"}]}],""" +
            """"problemsReport":{"summaries":[{"problemId":[{"name":"set-visible"}],"count":5},{"problemId":[{"name":"get-properties"}],"count":1}]}}"""
        assertEquals(listOf(GradleProblem("set-visible", "org.example.kover"), GradleProblem("get-properties", null)), problemsIn(report))
    }

    @Test
    fun gradle99PutsTheSummariesInAScriptOfTheirOwn() {
        val report = """<script type="application/json" id="diagnostics">[{"problemId":[{"name":"set-visible"}],"locations":[{"pluginId":"org.example.kover"}]}]</script>""" +
            """<script type="application/json" id="problems-summary">{"summaries":[{"problemId":[{"name":"set-visible"}],"count":5}]}</script>"""
        assertEquals(listOf(GradleProblem("set-visible", "org.example.kover")), problemsIn(report))
    }

    @Test
    fun aProblemWithADocumentationLinkIsItsAnchorAndItsSummaryStillMatchesByName() {
        val report = """{"diagnostics":[{"problemId":[{"name":"Method usage"}],"documentationLink":"https://docs.gradle.org/9.9.0/userguide/upgrading_version_9.html#deprecate-visible-property",""" +
            """"locations":[{"pluginId":"org.example.kover"}]}],"problemsReport":{"summaries":[{"problemId":[{"name":"Method usage"}],"count":5}]}}"""
        assertEquals(listOf(GradleProblem("deprecate-visible-property", "org.example.kover")), problemsIn(report))
    }
}
