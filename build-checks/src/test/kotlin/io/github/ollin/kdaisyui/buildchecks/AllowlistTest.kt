package io.github.ollin.kdaisyui.buildchecks

import kotlin.test.Test
import kotlin.test.assertEquals

class AllowlistTest {

    private val kover = GradleProblem("set-visible", Source.Plugin("org.example.kover"))
    private val ours = GradleProblem("external-process", Source.ThisBuild)

    @Test
    fun aProblemNoLineAcceptsIsUnexpectedOnce() {
        val allowlist = "# Kover 0.9.11 still calls it\n\nset-visible org.example.kover\n"
        assertEquals(listOf(ours), unexpected(listOf(kover, ours, kover, ours), allowlist))
    }
}
