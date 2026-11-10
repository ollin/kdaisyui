package io.github.ollin.kdaisyui.themes

import kotlin.test.Test
import kotlin.test.assertEquals

class DaisyThemeTest {

    @Test
    fun offersEveryBuiltInThemeOfThePinnedDaisyUi() {
        assertEquals(35, DaisyTheme.entries.size)
        assertEquals("nord", DaisyTheme.Nord.value)
    }
}
