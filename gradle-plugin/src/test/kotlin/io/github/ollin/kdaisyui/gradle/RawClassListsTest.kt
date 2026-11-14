package io.github.ollin.kdaisyui.gradle

import kotlin.test.Test
import kotlin.test.assertEquals

class RawClassListsTest {

    @Test
    fun aStringOfClassShapedTokensIsSplitAndAnyOtherIsNot() {
        val strings = listOf("btn  btn-sm", "text-xs\u0001 lg:opacity-50", "Open the link", "")
        assertEquals(listOf(listOf("btn", "btn-sm"), listOf("text-xs", "lg:opacity-50"), null, null), strings.map(::classTokens))
    }
}
