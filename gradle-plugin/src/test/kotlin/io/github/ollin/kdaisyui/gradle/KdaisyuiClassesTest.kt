package io.github.ollin.kdaisyui.gradle

import org.gradle.testfixtures.ProjectBuilder
import kotlin.io.path.createTempDirectory
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class KdaisyuiClassesTest {

    private val dir = createTempDirectory().toFile()
    private val project = ProjectBuilder.builder().withProjectDir(dir).build()
    private val task = project.tasks.register("kdaisyuiClasses", KdaisyuiClasses::class.java).get()
    private val compiled = dir.resolve("classes").apply { mkdirs() }
    private val resources = dir.resolve("resources").apply {
        mkdirs()
        resolve(COMPONENT_MAP).writeText("button\tButtonKt\tbtn btn-primary\ncard\tCardKt\tcard\n")
    }

    init {
        task.classes.from(compiled)
        // Gradle creates an output file's directory before a task runs; calling the action directly does not.
        task.output.set(dir.resolve("out").apply { mkdirs() }.resolve("kdaisyui-classes.txt"))
    }

    @Test
    fun theClassesOfTheComponentsTheCompiledCodeNamesAreWritten() {
        compiled.resolve("App.class").writeBytes("\u0001io/github/ollin/kdaisyui/components/ButtonKt".toByteArray())
        task.runtimeClasspath.from(dir.resolve("missing"), resources)
        task.write()
        assertEquals(listOf("btn", "btn-primary"), writtenClasses())
        assertEquals(2, comments().size, "a narrowed list gives no reason")
    }

    @Test
    fun noComponentFoundWritesEveryClass() {
        task.runtimeClasspath.from(resources)
        task.write()
        assertEquals(listOf("btn", "btn-primary", "card"), writtenClasses())
        assertEquals("# Every class, because the compiled classes name no kdaisyui component.", comments().last())
    }

    @Test
    fun noMapOnTheClasspathFailsNamingTheDependency() {
        val failure = assertFailsWith<IllegalStateException> { task.write() }
        assertEquals("$COMPONENT_MAP is on no runtime classpath entry: does the build depend on io.github.ollin.kdaisyui:kdaisyui?", failure.message)
    }

    private fun writtenClasses() = task.output.get().asFile.readLines().filterNot { it.startsWith("#") }

    private fun comments() = task.output.get().asFile.readLines().filter { it.startsWith("#") }
}
