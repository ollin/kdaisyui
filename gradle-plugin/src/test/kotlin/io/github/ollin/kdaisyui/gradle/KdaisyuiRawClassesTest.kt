package io.github.ollin.kdaisyui.gradle

import org.gradle.api.GradleException
import org.gradle.testfixtures.ProjectBuilder
import kotlin.io.path.createTempDirectory
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNull

class KdaisyuiRawClassesTest {

    private val dir = createTempDirectory().toFile()
    private val project = ProjectBuilder.builder().withProjectDir(dir).build()
    private val task = project.tasks.register("kdaisyuiRawClasses", KdaisyuiRawClasses::class.java).get()
    private val compiled = dir.resolve("classes").apply { mkdirs() }
    private val resources = dir.resolve("resources").apply { mkdirs() }

    init {
        // A page writing `summary("btn btn-sm")`, beside a file that is not a class.
        compiled.resolve("a").mkdirs()
        compiled.resolve("a/PageKt.class").writeBytes(classFile(utf8("btn btn-sm"), string(slot(1))))
        compiled.resolve("a/notes.txt").writeText("btn btn-sm")
        task.classes.from(compiled)
        task.runtimeClasspath.from(dir.resolve("without-the-map").apply { mkdirs() }, resources)
    }

    @Test
    fun aRawClassListOfTheCompiledCodeIsWarnedAbout() {
        resources.resolve(COMPONENT_MAP).writeText("button\tButtonKt\tbtn btn-sm\n")
        assertEquals(rawClassesWarning(listOf(RawClassList("a/PageKt", "btn btn-sm", setOf("button")))), task.warning())
        task.check()
    }

    @Test
    fun codeWithoutARawClassListGivesNoWarning() {
        resources.resolve(COMPONENT_MAP).writeText("card\tCardKt\tcard\n")
        assertNull(task.warning())
        task.check()
    }

    @Test
    fun aLibraryWithoutTheComponentMapIsNotChecked() {
        assertNull(task.warning())
    }

    @Test
    fun aBuildThatAsksForItFailsWithTheWarning() {
        resources.resolve(COMPONENT_MAP).writeText("button\tButtonKt\tbtn btn-sm\n")
        task.failBuild.set(true)
        assertEquals(task.warning(), assertFailsWith<GradleException> { task.check() }.message)
    }
}
