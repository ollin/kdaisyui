package io.github.ollin.kdaisyui.gradle

import org.gradle.testfixtures.ProjectBuilder
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class KdaisyuiExtensionTest {

    // `also`, not `apply`: on a Project, Gradle's Kotlin DSL `apply` wins over Kotlin's scope function.
    private val project = ProjectBuilder.builder().build().also { it.pluginManager.apply("io.github.ollin.kdaisyui") }
    private val stylesheet = project.extensions.getByType(KdaisyuiExtension::class.java).stylesheet

    @Test
    fun everythingButTheEntryHasADefault() {
        assertFalse(stylesheet.entry.isPresent)
        assertEquals(setOf(project.file("src/main/kotlin")), stylesheet.sources.files)
        assertEquals(project.layout.buildDirectory.file("kdaisyui/app.css").get().asFile, stylesheet.output.get().asFile)
        assertEquals("node:${KdaisyuiVersions.node}-slim", stylesheet.nodeImage.get())
        assertEquals("https://registry.npmjs.org/", stylesheet.npmRegistry.get())
        assertFalse(stylesheet.npmUser.isPresent || stylesheet.npmPassword.isPresent)
        assertTrue(stylesheet.minify.get())
    }

    @Test
    fun theRawClassCheckWarnsByDefaultAndFailsTheBuildWhenAsked() {
        val rawClasses = project.extensions.getByType(KdaisyuiExtension::class.java).rawClasses
        assertFalse(rawClasses.failBuild.get())
        project.extensions.getByType(KdaisyuiExtension::class.java).rawClasses { failBuild.set(true) }
        assertTrue(rawClasses.failBuild.get())
    }

    @Test
    fun aBuildSetsItInABlock() {
        project.extensions.getByType(KdaisyuiExtension::class.java).stylesheet { entry.set(project.file("src/main/css/app.css")) }
        assertEquals(project.file("src/main/css/app.css"), stylesheet.entry.get().asFile)
    }
}
