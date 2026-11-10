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

    private fun theme(member: String) = MemberReference("io/github/ollin/kdaisyui/themes/DaisyTheme", member)
}
