package io.github.ollin.kdaisyui.gradle

import kotlin.test.Test
import kotlin.test.assertEquals

class ComponentReferencesTest {

    @Test
    fun theComponentClassesAClassFileNamesAreFoundOnceEachNestedOnesAsTheirOuterClass() {
        val classFile = byteArrayOf(0xCA.toByte(), 0xFE.toByte()) +
            "\u0001\u0000'io/github/ollin/kdaisyui/components/ButtonKt\u0001io/github/ollin/kdaisyui/components/ButtonColor".toByteArray() +
            "\u0001io/github/ollin/kdaisyui/components/ButtonKt\$daisyButton\u0001io/github/ollin/kdaisyui/core/HtmlId".toByteArray()
        assertEquals(setOf("ButtonKt", "ButtonColor"), componentClassesNamedIn(classFile))
    }
}
