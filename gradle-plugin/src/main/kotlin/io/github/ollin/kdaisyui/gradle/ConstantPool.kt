package io.github.ollin.kdaisyui.gradle

import java.nio.ByteBuffer

/** A field or method a class file refers to: the internal name of its owner, and its own name. */
data class MemberReference(val owner: String, val name: String)

/**
 * The fields and methods a class file refers to, read from its constant pool. A reference is a
 * pair of indices into the pool, so unlike a class name it cannot be found by scanning the bytes.
 */
fun memberReferencesIn(classFile: ByteArray): List<MemberReference> {
    val pool = constantPool(ByteBuffer.wrap(classFile).position(POOL_COUNT_OFFSET))
    fun text(index: Int) = (pool.getValue(index) as Utf8).text
    return pool.values.filterIsInstance<Reference>().map { reference ->
        MemberReference(text((pool.getValue(reference.owner) as ClassName).name), text((pool.getValue(reference.nameAndType) as NameAndType).name))
    }
}

/** The text of every string constant of a class file: its literals, and the recipes of its string concatenations. */
fun stringConstantsIn(classFile: ByteArray): List<String> {
    val pool = constantPool(ByteBuffer.wrap(classFile).position(POOL_COUNT_OFFSET))
    return pool.values.filterIsInstance<StringConstant>().map { (pool.getValue(it.text) as Utf8).text }
}

private sealed interface Entry
private class Utf8(val text: String) : Entry
private class ClassName(val name: Int) : Entry
private class StringConstant(val text: Int) : Entry
private class NameAndType(val name: Int) : Entry
private class Reference(val owner: Int, val nameAndType: Int) : Entry
private object Other : Entry

private fun constantPool(buffer: ByteBuffer): Map<Int, Entry> {
    val count = buffer.char.code
    val pool = HashMap<Int, Entry>()
    var index = 1
    while (index < count) {
        val tag = buffer.get().toInt()
        pool[index] = entry(tag, buffer)
        index += if (tag in TWO_SLOT_TAGS) 2 else 1
    }
    return pool
}

// Conditions, not `when (tag)`: dense tag values compile to a table switch whose every gap Kover
// counts as a branch of its own, which no test of a real pool can reach.
private fun entry(tag: Int, buffer: ByteBuffer): Entry = when {
    tag == UTF8 -> Utf8(ByteArray(buffer.char.code).also { buffer.get(it) }.decodeToString())
    tag == CLASS -> ClassName(buffer.char.code)
    tag == STRING -> StringConstant(buffer.char.code)
    tag == NAME_AND_TYPE -> NameAndType(buffer.char.code).also { buffer.char }
    tag in REFERENCE_TAGS -> Reference(buffer.char.code, buffer.char.code)
    else -> Other.also { buffer.position(buffer.position() + OTHER_SIZES.getValue(tag)) }
}

// The class file format: magic (4 bytes), minor and major version (2 each), then the pool's count.
private const val POOL_COUNT_OFFSET = 8
private const val UTF8 = 1
private const val CLASS = 7
private const val STRING = 8
/** Long and Double: each takes two slots of the pool (JVMS 4.4.5). */
private val TWO_SLOT_TAGS = setOf(5, 6)

/** Fieldref, Methodref and InterfaceMethodref. */
private val REFERENCE_TAGS = setOf(9, 10, 11)
private const val NAME_AND_TYPE = 12

/** The size after the tag of every entry this reader does not keep (JVMS 4.4). */
private val OTHER_SIZES = mapOf(3 to 4, 4 to 4, 5 to 8, 6 to 8, 15 to 3, 16 to 2, 17 to 4, 18 to 4, 19 to 2, 20 to 2)
