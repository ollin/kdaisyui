package io.github.ollin.kdaisyui.gradle

import kotlin.test.Test
import kotlin.test.assertEquals

class RawClassListsTest {

    @Test
    fun aStringOfClassShapedTokensIsSplitAndAnyOtherIsNot() {
        val strings = listOf("btn  btn-sm", "text-xs\u0001 lg:opacity-50", "Open the link", "")
        assertEquals(listOf(listOf("btn", "btn-sm"), listOf("text-xs", "lg:opacity-50"), null, null), strings.map(::classTokens))
    }

    @Test
    fun aClassListNamesTheComponentsOfItsBareClassesButNotOfItsVariants() {
        val map = ComponentMap("button\tButtonKt\tbtn btn-sm\ncard\tCardKt\tcard\n")
        val lists = listOf(listOf("btn", "btn-sm"), listOf("card", "lg:p-2"), listOf("lg:btn-sm", "p-2"), listOf("card"))
        assertEquals(listOf(setOf("button"), setOf("card"), emptySet(), emptySet()), lists.map { daisyComponentsIn(it, map) })
    }
}
