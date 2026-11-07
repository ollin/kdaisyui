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
        assertEquals(null, resourceIn(resources, COMPONENT_MAP))
        resources.resolve(COMPONENT_MAP).writeText("button")
        assertEquals("button", resourceIn(resources, COMPONENT_MAP))
    }

    @Test
    fun aJarHoldsTheMapOrNot() {
        assertEquals("card", resourceIn(jarWith(COMPONENT_MAP, "card"), COMPONENT_MAP))
        assertEquals(null, resourceIn(jarWith("other.txt", "card"), COMPONENT_MAP))
    }

    @Test
    fun aPathThatDoesNotExistHoldsNothing() {
        assertEquals(null, resourceIn(dir.resolve("missing"), COMPONENT_MAP))
    }

    private fun jarWith(name: String, text: String) = dir.resolve("$name.jar").apply {
        ZipOutputStream(outputStream()).use { zip -> zip.putNextEntry(ZipEntry(name)); zip.write(text.toByteArray()) }
    }
}
