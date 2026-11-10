package io.github.ollin.kdaisyui.themes

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull
import io.github.ollin.kdaisyui.components.daisyThemeController
import kotlinx.html.div
import kotlinx.html.html
import kotlinx.html.stream.createHTML

class DaisyThemeTest {

    @Test
    fun offersEveryBuiltInThemeOfThePinnedDaisyUi() {
        assertEquals(35, DaisyTheme.entries.size)
        assertEquals("nord", DaisyTheme.Nord.value)
    }

    @Test
    fun daisyThemeWritesTheThemeAsDataTheme() {
        val html = createHTML(prettyPrint = false).html { daisyTheme(DaisyTheme.Nord) }
        assertEquals("<html data-theme=\"nord\"></html>", html)
    }

    @Test
    fun theThemeControllerWritesItsThemeAsValueAndNoValueWithoutOne() {
        val withTheme = createHTML(prettyPrint = false).div { daisyThemeController(theme = DaisyTheme.Dark) }
        val without = createHTML(prettyPrint = false).div { daisyThemeController() }
        assertEquals("<div><input class=\"theme-controller\" value=\"dark\"></div>", withTheme)
        assertEquals("<div><input class=\"theme-controller\"></div>", without)
    }

    @Test
    fun daisyThemeOfReadsEveryThemeBackAndNothingElse() {
        DaisyTheme.entries.forEach { assertEquals(it, daisyThemeOf(it.value)) }
        assertNull(daisyThemeOf("Nord"))
        assertNull(daisyThemeOf(""))
    }
}
