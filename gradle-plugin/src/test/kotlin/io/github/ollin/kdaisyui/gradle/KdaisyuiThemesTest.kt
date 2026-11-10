package io.github.ollin.kdaisyui.gradle

import org.gradle.testfixtures.ProjectBuilder
import kotlin.io.path.createTempDirectory
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class KdaisyuiThemesTest {

    private val dir = createTempDirectory().toFile()
    private val project = ProjectBuilder.builder().withProjectDir(dir).build()
    private val task = project.tasks.register("kdaisyuiThemes", KdaisyuiThemes::class.java).get()
    private val compiled = dir.resolve("classes").apply { mkdirs() }
    private val stylesheet = dir.resolve("app.css")
    private val resources = dir.resolve("resources").apply { mkdirs() }

    init {
        // A class referring to DaisyTheme.Nord, as `daisyTheme(DaisyTheme.Nord)` compiles.
        compiled.resolve("App.class").writeBytes(
            classFile(utf8("io/github/ollin/kdaisyui/themes/DaisyTheme"), className(slot(1)), utf8("Nord"), utf8("Lx;"), nameAndType(slot(3), slot(4)), reference(ReferenceKind.Field, slot(2), slot(5))),
        )
        task.classes.from(compiled)
        task.stylesheet.set(stylesheet)
        task.runtimeClasspath.from(resources)
    }

    @Test
    fun aThemeTheCodeNamesAndTheStylesheetLacksIsWarnedAbout() {
        withThemeMap()
        stylesheet.writeText(":where(:root),[data-theme=light]{}")
        assertEquals(themeWarning(listOf("nord")), task.warning())
        task.check()
    }

    @Test
    fun aStylesheetCarryingTheThemeGivesNoWarning() {
        withThemeMap()
        stylesheet.writeText("[data-theme=light]{}[data-theme=nord]{}")
        assertNull(task.warning())
        task.check()
    }

    @Test
    fun aLibraryWithoutTheThemeMapIsNotChecked() {
        stylesheet.writeText("")
        assertNull(task.warning())
    }

    private fun withThemeMap() {
        resources.resolve(THEME_MAP).writeText("# header\nLight\tlight\nNord\tnord\n")
    }
}
