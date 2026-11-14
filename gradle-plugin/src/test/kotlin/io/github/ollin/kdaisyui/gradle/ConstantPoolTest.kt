package io.github.ollin.kdaisyui.gradle

import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals

class ConstantPoolTest {

    @Test
    fun aFieldReferenceIsResolvedToItsOwnerAndNameAndOtherEntriesAreSkipped() {
        val classFile = classFile(integer(), utf8("a/Owner"), className(slot(2)), utf8("field"), utf8("I"), nameAndType(slot(4), slot(5)), reference(ReferenceKind.Field, slot(3), slot(6)))
        assertEquals(listOf(MemberReference("a/Owner", "field")), memberReferencesIn(classFile))
    }

    @Test
    fun aMethodAndAnInterfaceMethodReferenceAreResolvedToo() {
        val classFile = classFile(utf8("a/Owner"), className(slot(1)), utf8("run"), utf8("()V"), nameAndType(slot(3), slot(4)), reference(ReferenceKind.Method, slot(2), slot(5)), reference(ReferenceKind.InterfaceMethod, slot(2), slot(5)))
        assertEquals(listOf(MemberReference("a/Owner", "run"), MemberReference("a/Owner", "run")), memberReferencesIn(classFile))
    }

    @Test
    fun aLongTakesTwoSlotsSoTheIndicesAfterItStillResolve() {
        val classFile = classFile(long(), utf8("a/Owner"), className(slot(3)), utf8("f"), utf8("J"), nameAndType(slot(5), slot(6)), reference(ReferenceKind.Field, slot(4), slot(7)))
        assertEquals(listOf(MemberReference("a/Owner", "f")), memberReferencesIn(classFile))
    }

    @Test
    fun aRealClassFileOfThePluginNamesWhatItCalls() {
        val classFile = checkNotNull(javaClass.classLoader.getResourceAsStream("io/github/ollin/kdaisyui/gradle/KdaisyuiClasses.class")).readBytes()
        assertContains(memberReferencesIn(classFile), MemberReference("io/github/ollin/kdaisyui/gradle/ComponentReferencesKt", "componentClassesNamedIn"))
    }

    @Test
    fun theStringConstantsOfARealClassFileAreItsLiterals() {
        val classFile = checkNotNull(javaClass.classLoader.getResourceAsStream("io/github/ollin/kdaisyui/gradle/KdaisyuiThemes.class")).readBytes()
        assertContains(stringConstantsIn(classFile), "kdaisyui-themes.txt")
    }
}
