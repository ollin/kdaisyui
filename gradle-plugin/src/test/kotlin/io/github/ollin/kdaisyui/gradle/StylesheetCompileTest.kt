package io.github.ollin.kdaisyui.gradle

import kotlin.test.Test
import kotlin.test.assertEquals

class StylesheetCompileTest {

    private val install = "cd /deps && npm install --userconfig /deps/.npmrc --prefer-offline --no-fund --no-audit --silent " +
        "@tailwindcss/cli@4.3.4 tailwindcss@4.3.4 daisyui@5.7.50 " +
        "&& cd project && mkdir -p out && /deps/node_modules/.bin/tailwindcss --input css/app.css --output out/app.css"

    @Test
    fun installsThePinnedPackagesThenCompilesMinified() {
        assertEquals(listOf("sh", "-c", "$install --minify"), StylesheetCompile.command("4.3.4", "5.7.50", minify = true))
    }

    @Test
    fun leavesTheOutputReadableWhenAsked() {
        assertEquals(listOf("sh", "-c", install), StylesheetCompile.command("4.3.4", "5.7.50", minify = false))
    }
}
