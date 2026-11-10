package io.github.ollin.kdaisyui.themes

import kotlin.test.Test
import kotlin.test.assertEquals

/** The Gradle plugin learns the themes from this resource; it must be packaged and match the enum. */
class ThemeMapTest {

    @Test
    fun theThemeMapNamesEveryEntryWithItsValue() {
        val map = checkNotNull(javaClass.classLoader.getResourceAsStream("kdaisyui-themes.txt")).readBytes().decodeToString()
        val lines = map.lines().filter { it.isNotEmpty() && !it.startsWith("#") }
        assertEquals(DaisyTheme.entries.map { "${it.name}\t${it.value}" }, lines)
    }
}
