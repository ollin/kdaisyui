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

    @Test
    fun aClassFileReportsItsRawClassListsUnderItsFileClass() {
        val classFile = classFile(utf8("btn btn-sm"), string(slot(1)), utf8("Open the link"), string(slot(3)), utf8("p-2"), string(slot(5)))
        val map = ComponentMap("button\tButtonKt\tbtn btn-sm\n")
        assertEquals(listOf(RawClassList("a/PageKt", "btn btn-sm", setOf("button"))), rawClassListsIn("a/PageKt\$page\$1", classFile, map))
    }
}
