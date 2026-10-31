package io.github.ollin.kdaisyui.buildchecks

import kotlin.io.path.createTempFile
import kotlin.io.path.writeText
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class CheckGradleProblemsTest {

    private val koverProblem = """{"problemId":[{"name":"set-visible"}],"locations":[{"pluginId":"org.example.kover"}]}"""
    private val buildProblem = """{"problemId":[{"name":"external-process"}]}"""

    @Test
    fun eachProblemNoLineAcceptsFailsTheCheckAndIsNamed() {
        val failure = assertFailsWith<IllegalStateException> { check("$koverProblem,$buildProblem", allowlist = "") }
        assertEquals("Gradle problems no line of the allowlist accepts:\n  set-visible (org.example.kover)\n  external-process (this build)", failure.message)
    }

    @Test
    fun aReportWithOnlyAcceptedProblemsPasses() {
        check(koverProblem, allowlist = "set-visible org.example.kover\n")
    }

    private fun check(report: String, allowlist: String) =
        main(arrayOf(fileWith(report), fileWith(allowlist)))

    private fun fileWith(text: String) = createTempFile().apply { writeText(text) }.toString()
}
