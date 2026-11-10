package io.github.ollin.kdaisyui.gradle

import kotlin.test.Test
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
}
