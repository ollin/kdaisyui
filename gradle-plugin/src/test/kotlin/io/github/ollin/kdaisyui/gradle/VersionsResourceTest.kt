package io.github.ollin.kdaisyui.gradle

import java.io.File
import java.util.Properties
import kotlin.test.Test
import kotlin.test.assertEquals

class VersionsResourceTest {

    private val resource = checkNotNull(VersionsResourceTest::class.java.getResourceAsStream("/kdaisyui-versions.properties"))
    private val built = Properties().apply { load(resource) }

    @Test
    fun theJarCarriesTheCatalogsAndToolVersionsVersions() {
        assertEquals(catalog("webjar-tailwindcss-browser"), built.getProperty("tailwind"))
        assertEquals(catalog("daisyui"), built.getProperty("daisyui"))
        assertEquals(toolVersion("nodejs"), built.getProperty("node"))
    }

    private fun catalog(key: String): String =
        File("../gradle/libs.versions.toml").readLines().first { it.startsWith("$key = ") }.substringAfter('"').substringBefore('"')

    private fun toolVersion(tool: String): String =
        File("../.tool-versions").readLines().first { it.startsWith("$tool ") }.substringAfter(" ").trim()
}
