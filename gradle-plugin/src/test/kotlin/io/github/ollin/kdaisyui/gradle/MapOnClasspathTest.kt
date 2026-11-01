package io.github.ollin.kdaisyui.gradle

import java.util.zip.ZipEntry
import java.util.zip.ZipOutputStream
import kotlin.io.path.createTempDirectory
import kotlin.test.Test
import kotlin.test.assertEquals

class MapOnClasspathTest {

    private val dir = createTempDirectory().toFile()

    @Test
    fun aResourcesDirectoryHoldsTheMapOrNot() {
        val resources = dir.resolve("resources").apply { mkdirs() }
        assertEquals(null, componentMapIn(resources))
        resources.resolve(COMPONENT_MAP).writeText("button")
        assertEquals("button", componentMapIn(resources))
    }

    @Test
    fun aJarHoldsTheMapOrNot() {
        assertEquals("card", componentMapIn(jarWith(COMPONENT_MAP, "card")))
        assertEquals(null, componentMapIn(jarWith("other.txt", "card")))
    }

    @Test
    fun aPathThatDoesNotExistHoldsNothing() {
        assertEquals(null, componentMapIn(dir.resolve("missing")))
    }

    private fun jarWith(name: String, text: String) = dir.resolve("$name.jar").apply {
        ZipOutputStream(outputStream()).use { zip -> zip.putNextEntry(ZipEntry(name)); zip.write(text.toByteArray()) }
    }
}
