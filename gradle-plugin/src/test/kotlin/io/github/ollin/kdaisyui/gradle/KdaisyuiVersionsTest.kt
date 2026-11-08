package io.github.ollin.kdaisyui.gradle

import java.io.File
import kotlin.test.Test
import kotlin.test.assertEquals

class KdaisyuiVersionsTest {

    @Test
    fun theJarCarriesTheCatalogsAndToolVersionsVersions() {
        assertEquals(catalog("webjar-tailwindcss-browser"), KdaisyuiVersions.tailwind)
        assertEquals(catalog("daisyui"), KdaisyuiVersions.daisyui)
        assertEquals(toolVersion("nodejs"), KdaisyuiVersions.node)
    }

    private fun catalog(key: String): String =
        File("../gradle/libs.versions.toml").readLines().first { it.startsWith("$key = ") }.substringAfter('"').substringBefore('"')

    private fun toolVersion(tool: String): String =
        File("../.tool-versions").readLines().first { it.startsWith("$tool ") }.substringAfter(" ").trim()
}
