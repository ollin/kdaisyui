package io.github.ollin.kdaisyui.gradle

import org.gradle.testfixtures.ProjectBuilder
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.test.assertTrue

class KdaisyuiPluginTest {

    private val project = ProjectBuilder.builder().build()

    @Test
    fun withJavaItRegistersTheTaskOverTheMainSourceSetWritingWhereTailwindLooks() {
        project.pluginManager.apply("java")
        project.pluginManager.apply("io.github.ollin.kdaisyui")
        val task = project.tasks.getByName("kdaisyuiClasses") as KdaisyuiClasses
        assertEquals(project.layout.buildDirectory.file("kdaisyui/kdaisyui-classes.txt").get().asFile, task.output.get().asFile)
        assertTrue(task.classes.files.any { it.path.endsWith("classes/java/main") })
        assertEquals("Writes the classes of the kdaisyui components this build uses, for Tailwind's @source.", task.description)
        assertTrue(task.runtimeClasspath.none { it.path.contains("resources/main") }, "reads the dependencies, not the project's own resources")
    }

    @Test
    fun withoutJavaThereIsNothingToScan() {
        project.pluginManager.apply("io.github.ollin.kdaisyui")
        assertNull(project.tasks.findByName("kdaisyuiClasses"))
    }
}
