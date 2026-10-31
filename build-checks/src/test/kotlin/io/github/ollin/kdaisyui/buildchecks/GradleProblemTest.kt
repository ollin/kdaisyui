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
}
