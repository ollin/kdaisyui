package io.github.ollin.kdaisyui.gradle

import kotlin.test.Test
import kotlin.test.assertEquals

class ThemeCheckTest {

    private val themes = mapOf("Light" to "light", "Dark" to "dark", "Nord" to "nord")

    @Test
    fun anEntryReferenceNamesItsThemeAndAReferenceElsewhereNamesNone() {
        val references = listOf(theme("Nord"), MemberReference("a/Other", "Dark"))
        assertEquals(setOf("nord"), themesNamedIn(references, themes))
    }

    @Test
    fun aRuntimeChoiceNamesEveryThemeAndReadingAValueNamesNone() {
        val choices = listOf(theme("getEntries"), theme("values"), theme("valueOf"), MemberReference("io/github/ollin/kdaisyui/themes/DaisyThemesKt", "daisyThemeOf"))
        assertEquals(choices.map { setOf("light", "dark", "nord") }, choices.map { themesNamedIn(listOf(it), themes) })
        assertEquals(emptySet(), themesNamedIn(listOf(theme("getValue"), theme("name")), themes))
    }

    @Test
    fun theThemeMapIsReadFromItsResourceWithoutItsHeaderOrEmptyLines() {
        assertEquals(mapOf("Nord" to "nord", "Abyss" to "abyss"), themeMap("# header\n# more\nNord\tnord\n\nAbyss\tabyss\n"))
    }

    private fun theme(member: String) = MemberReference("io/github/ollin/kdaisyui/themes/DaisyTheme", member)
}
