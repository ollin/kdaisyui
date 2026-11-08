package io.github.ollin.kdaisyui.gradle

import org.testcontainers.DockerClientFactory
import kotlin.io.path.createTempDirectory
import kotlin.test.Test
import kotlin.test.assertContains

/** A real compile in a real container. Needs Docker and the network, as `check` already does for the example app. */
class NodeContainerRunTest {

    private val dir = createTempDirectory().toFile()

    @Test
    fun aTwoLineEntryCompilesIntoAStylesheetWithDaisyuiInIt() {
        val entry = dir.resolve("app.css").apply { writeText("@import \"tailwindcss\" source(none);\n@plugin \"daisyui\";\n@source \"../sources/0-kotlin\";\n") }
        val sources = dir.resolve("kotlin").apply { mkdirs(); resolve("Page.kt").writeText("val classes = \"btn\"\n") }
        val classList = dir.resolve("kdaisyui").apply { mkdirs(); resolve("kdaisyui-classes.txt").writeText("btn\n") }
        val output = dir.resolve("out.css")
        val run = StylesheetRun(
            image = "node:${KdaisyuiVersions.node}-slim",
            npmrc = NpmConfig.npmrc("https://registry.npmjs.org/", null, null),
            command = StylesheetCompile.command(KdaisyuiVersions.tailwind, KdaisyuiVersions.daisyui, minify = false),
            copies = mapOf(entry to "/deps/project/css/app.css", classList to "/deps/project/kdaisyui") + StylesheetCompile.mountPoints(listOf(sources)),
        )

        NodeContainerRun.compile(run, output)

        assertContains(output.readText(), "--color-base-100")
        DockerClientFactory.instance().client().inspectVolumeCmd(NodeContainerRun.NPM_CACHE_VOLUME).exec()
    }
}
