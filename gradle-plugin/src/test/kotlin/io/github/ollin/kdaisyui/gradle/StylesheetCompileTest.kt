package io.github.ollin.kdaisyui.gradle

import java.io.File
import kotlin.test.Test
import kotlin.test.assertEquals

class StylesheetCompileTest {

    private val npm = "npm install --userconfig /deps/.npmrc --no-fund --no-audit --silent"
    private val packages = "@tailwindcss/cli@4.3.4 tailwindcss@4.3.4 daisyui@5.7.50"
    private val install = "cd /deps && ($npm --prefer-offline $packages || $npm $packages) " +
        "&& cd project && mkdir -p out && /deps/node_modules/.bin/tailwindcss --input css/app.css --output out/app.css"

    @Test
    fun installsThePinnedPackagesThenCompilesMinified() {
        assertEquals(listOf("sh", "-c", "$install --minify"), StylesheetCompile.command("4.3.4", "5.7.50", minify = true))
    }

    @Test
    fun leavesTheOutputReadableWhenAsked() {
        assertEquals(listOf("sh", "-c", install), StylesheetCompile.command("4.3.4", "5.7.50", minify = false))
    }

    @Test
    fun twoSourceDirectoriesWithOneNameGetTheirOwnMountPoint() {
        val app = File("app/src/main/kotlin")
        val shared = File("shared/src/main/kotlin")
        assertEquals(
            mapOf(app to "/deps/project/sources/0-kotlin", shared to "/deps/project/sources/1-kotlin"),
            StylesheetCompile.mountPoints(listOf(app, shared)),
        )
    }
}
