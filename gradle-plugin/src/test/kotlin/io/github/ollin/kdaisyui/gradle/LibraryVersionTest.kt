package io.github.ollin.kdaisyui.gradle

import java.io.File
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class LibraryVersionTest {

    @Test
    fun aLibraryOfAnotherVersionIsNamedBesideThePlugin() {
        assertEquals(
            "kdaisyui: the plugin is 0.10.0, the kdaisyui jar on the runtime classpath is 0.9.0; the stylesheet compile installs the toolchain of 0.10.0",
            versionMismatch("0.10.0", listOf(File("kotlin-stdlib-2.4.10.jar"), File("kdaisyui-ktor-integration-0.9.0.jar"), File("kdaisyui-0.9.0.jar"))),
        )
    }

    @Test
    fun theSameVersionWarnsNothing() {
        assertNull(versionMismatch("0.9.0", listOf(File("kdaisyui-0.9.0.jar"))))
    }

    @Test
    fun aProjectDependencyCarriesNoVersionAndWarnsNothing() {
        assertNull(versionMismatch("0.9.0", listOf(File("lib/build/libs/main"), File("kdaisyui-ktor-integration-0.8.0.jar"))))
    }

    @Test
    fun thePluginKnowsItsOwnVersion() {
        assertEquals(File("../gradle.properties").readLines().first { it.startsWith("version=") }.substringAfter("="), KdaisyuiVersions.kdaisyui)
    }
}
