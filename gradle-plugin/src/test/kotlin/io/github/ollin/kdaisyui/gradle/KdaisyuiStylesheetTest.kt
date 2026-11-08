package io.github.ollin.kdaisyui.gradle

import org.gradle.testfixtures.ProjectBuilder
import kotlin.io.path.createTempDirectory
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class KdaisyuiStylesheetTest {

    private val dir = createTempDirectory().toFile()
    private val project = ProjectBuilder.builder().withProjectDir(dir).build()
    private val task = project.tasks.register("kdaisyuiStylesheet", KdaisyuiStylesheet::class.java).get().also {
        it.entry.set(dir.resolve("src/main/css/app.css"))
        it.sources.from(dir.resolve("src/main/kotlin"))
        it.classList.set(dir.resolve("build/kdaisyui"))
        it.nodeImage.set("node:26.12.1-slim")
        it.tailwind.set("4.3.4")
        it.daisyui.set("5.7.50")
        it.npmRegistry.set("https://registry.npmjs.org/")
        it.minify.set(true)
    }

    @Test
    fun thePlanNamesTheImageTheNpmrcTheCommandAndWhereEachInputGoes() {
        assertEquals(
            StylesheetRun(
                image = "node:26.12.1-slim",
                npmrc = "registry=https://registry.npmjs.org/\n",
                command = StylesheetCompile.command("4.3.4", "5.7.50", minify = true),
                copies = mapOf(
                    dir.resolve("src/main/css/app.css") to "/deps/project/css/app.css",
                    dir.resolve("build/kdaisyui") to "/deps/project/kdaisyui",
                    dir.resolve("src/main/kotlin") to "/deps/project/sources/0-kotlin",
                ),
            ),
            task.plan(),
        )
    }

    @Test
    fun halfACredentialFailsTheTaskBeforeAnyContainer() {
        task.npmUser.set("user")
        val failure = assertFailsWith<IllegalArgumentException> { task.compile() }
        assertEquals("kdaisyui: npmUser is set but npmPassword is missing", failure.message)
    }
}
