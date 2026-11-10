package io.github.ollin.kdaisyui.gradle

import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals

class ConstantPoolTest {

    @Test
    fun aFieldReferenceIsResolvedToItsOwnerAndNameAndOtherEntriesAreSkipped() {
        val classFile = classFile(integer(), utf8("a/Owner"), className(2), utf8("field"), utf8("I"), nameAndType(4, 5), fieldRef(3, 6))
        assertEquals(listOf(MemberReference("a/Owner", "field")), memberReferencesIn(classFile))
    }

    @Test
    fun aMethodAndAnInterfaceMethodReferenceAreResolvedToo() {
        val classFile = classFile(utf8("a/Owner"), className(1), utf8("run"), utf8("()V"), nameAndType(3, 4), methodRef(2, 5), methodRef(2, 5, tag = 11))
        assertEquals(listOf(MemberReference("a/Owner", "run"), MemberReference("a/Owner", "run")), memberReferencesIn(classFile))
    }

    @Test
    fun aLongTakesTwoSlotsSoTheIndicesAfterItStillResolve() {
        val classFile = classFile(long(), utf8("a/Owner"), className(3), utf8("f"), utf8("J"), nameAndType(5, 6), fieldRef(4, 7), slots = 8)
        assertEquals(listOf(MemberReference("a/Owner", "f")), memberReferencesIn(classFile))
    }

    @Test
    fun aRealClassFileOfThePluginNamesWhatItCalls() {
        val classFile = checkNotNull(javaClass.classLoader.getResourceAsStream("io/github/ollin/kdaisyui/gradle/KdaisyuiClasses.class")).readBytes()
        assertContains(memberReferencesIn(classFile), MemberReference("io/github/ollin/kdaisyui/gradle/ComponentReferencesKt", "componentClassesNamedIn"))
    }
}
