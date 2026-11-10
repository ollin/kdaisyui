package io.github.ollin.kdaisyui.gradle

import kotlin.test.Test
import kotlin.test.assertEquals

class ConstantPoolTest {

    @Test
    fun aFieldReferenceIsResolvedToItsOwnerAndNameAndOtherEntriesAreSkipped() {
        val classFile = classFile(integer(), utf8("a/Owner"), className(2), utf8("field"), utf8("I"), nameAndType(4, 5), fieldRef(3, 6))
        assertEquals(listOf(MemberReference("a/Owner", "field")), memberReferencesIn(classFile))
    }
}
