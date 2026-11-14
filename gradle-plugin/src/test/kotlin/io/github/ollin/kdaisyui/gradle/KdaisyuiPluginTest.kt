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
    fun theStylesheetTaskReadsTheExtensionThePinnedVersionsAndTheClassList() {
        project.pluginManager.apply("java")
        project.pluginManager.apply("io.github.ollin.kdaisyui")
        project.extensions.getByType(KdaisyuiExtension::class.java).stylesheet { entry.set(project.file("src/main/css/app.css")) }
        val task = project.tasks.getByName("kdaisyuiStylesheet") as KdaisyuiStylesheet
        assertEquals(project.file("src/main/css/app.css"), task.entry.get().asFile)
        assertEquals(project.layout.buildDirectory.file("kdaisyui/kdaisyui-classes.txt").get().asFile, task.classList.get().asFile)
        assertEquals(listOf(KdaisyuiVersions.tailwind, KdaisyuiVersions.daisyui), listOf(task.tailwind.get(), task.daisyui.get()))
        assertEquals(project.layout.buildDirectory.file("kdaisyui/app.css").get().asFile, task.output.get().asFile)
        assertTrue(task.taskDependencies.getDependencies(task).any { it.name == "kdaisyuiClasses" }, "the class list is written first")
    }

    @Test
    fun theStylesheetTaskTakesTheMirrorTheCredentialAndMinifyFromTheExtension() {
        project.pluginManager.apply("java")
        project.pluginManager.apply("io.github.ollin.kdaisyui")
        project.extensions.getByType(KdaisyuiExtension::class.java).stylesheet {
            nodeImage.set("mirror.example/node:26-slim")
            npmRegistry.set("https://npm.mirror.example/")
            npmUser.set("user")
            npmPassword.set("secret")
            minify.set(false)
        }
        val task = project.tasks.getByName("kdaisyuiStylesheet") as KdaisyuiStylesheet
        assertEquals("Compiles the stylesheet with the plugin's Tailwind and DaisyUI, in a Node container.", task.description)
        assertEquals("mirror.example/node:26-slim", task.nodeImage.get())
        assertEquals("https://npm.mirror.example/", task.npmRegistry.get())
        assertEquals(listOf("user", "secret"), listOf(task.npmUser.get(), task.npmPassword.get()))
        assertEquals(false, task.minify.get())
    }

    @Test
    fun theThemeCheckFinalizesTheStylesheetTaskReadingItsOutputAndIsNoPartOfCheck() {
        project.pluginManager.apply("java")
        project.pluginManager.apply("io.github.ollin.kdaisyui")
        val themes = project.tasks.getByName("kdaisyuiThemes") as KdaisyuiThemes
        val stylesheet = project.tasks.getByName("kdaisyuiStylesheet")
        assertEquals(setOf(themes), stylesheet.finalizedBy.getDependencies(stylesheet))
        assertEquals(project.layout.buildDirectory.file("kdaisyui/app.css").get().asFile, themes.stylesheet.get().asFile)
        assertTrue(themes.classes.files.any { it.path.endsWith("classes/java/main") } && themes.description!!.startsWith("Warns when the code"))
        val check = project.tasks.getByName("check")
        assertTrue(check.taskDependencies.getDependencies(check).none { it.name.startsWith("kdaisyui") })
    }

    @Test
    fun theRawClassCheckFinalizesTheClassListTakesTheOptionAndIsNoPartOfCheck() {
        project.pluginManager.apply("java")
        project.pluginManager.apply("io.github.ollin.kdaisyui")
        project.extensions.getByType(KdaisyuiExtension::class.java).rawClasses { failBuild.set(true) }
        val rawClasses = project.tasks.getByName("kdaisyuiRawClasses") as KdaisyuiRawClasses
        val classList = project.tasks.getByName("kdaisyuiClasses")
        assertEquals(setOf(rawClasses), classList.finalizedBy.getDependencies(classList))
        assertTrue(rawClasses.failBuild.get() && rawClasses.classes.files.any { it.path.endsWith("classes/java/main") })
        assertTrue(rawClasses.runtimeClasspath.none { it.path.contains("resources/main") } && rawClasses.description!!.startsWith("Warns about DaisyUI classes"))
        val check = project.tasks.getByName("check")
        assertTrue(check.taskDependencies.getDependencies(check).none { it.name.startsWith("kdaisyui") })
    }

    @Test
    fun withoutJavaThereIsNothingToScan() {
        project.pluginManager.apply("io.github.ollin.kdaisyui")
        assertNull(project.tasks.findByName("kdaisyuiClasses"))
    }
}
