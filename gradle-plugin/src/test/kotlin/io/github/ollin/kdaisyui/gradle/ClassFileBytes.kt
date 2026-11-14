package io.github.ollin.kdaisyui.gradle

/** An index into a constant pool, which starts at 1. */
@JvmInline
value class Slot(val index: Int) {
    val bytes: ByteArray get() = byteArrayOf((index shr 8).toByte(), index.toByte())
}

fun slot(index: Int): Slot = Slot(index)

/** One entry of a constant pool: its bytes, tag included, and how many slots it takes. */
class PoolEntry(val bytes: ByteArray, val slots: Int = 1)

/** A class file that is only its header and a constant pool of [entries]. */
fun classFile(vararg entries: PoolEntry): ByteArray =
    byteArrayOf(0xCA.toByte(), 0xFE.toByte(), 0xBA.toByte(), 0xBE.toByte(), 0, 0, 0, 65) +
        Slot(entries.sumOf { it.slots } + 1).bytes + entries.fold(ByteArray(0)) { all, entry -> all + entry.bytes }

fun utf8(text: String): PoolEntry = PoolEntry(byteArrayOf(1) + Slot(text.length).bytes + text.toByteArray())

fun string(text: Slot): PoolEntry = PoolEntry(byteArrayOf(8) + text.bytes)

fun className(name: Slot): PoolEntry = PoolEntry(byteArrayOf(7) + name.bytes)

fun nameAndType(name: Slot, descriptor: Slot): PoolEntry = PoolEntry(byteArrayOf(12) + name.bytes + descriptor.bytes)

/** The three kinds of member reference, by their tag. */
enum class ReferenceKind(val tag: Byte) { Field(9), Method(10), InterfaceMethod(11) }

fun reference(kind: ReferenceKind, owner: Slot, nameAndType: Slot): PoolEntry =
    PoolEntry(byteArrayOf(kind.tag) + owner.bytes + nameAndType.bytes)

/** A Long constant: two slots, as the class file format counts them. */
fun long(): PoolEntry = PoolEntry(byteArrayOf(5, 0, 0, 0, 0, 0, 0, 0, 42), slots = 2)

fun integer(): PoolEntry = PoolEntry(byteArrayOf(3, 0, 0, 0, 42))
