package io.github.ollin.kdaisyui.gradle

/** A class file that is only its header and a constant pool of [entries], each one slot. */
fun classFile(vararg entries: ByteArray): ByteArray =
    byteArrayOf(0xCA.toByte(), 0xFE.toByte(), 0xBA.toByte(), 0xBE.toByte(), 0, 0, 0, 65) + u2(entries.size + 1) + entries.fold(ByteArray(0), ByteArray::plus)

fun utf8(text: String): ByteArray = byteArrayOf(1) + u2(text.length) + text.toByteArray()

fun className(name: Int): ByteArray = byteArrayOf(7) + u2(name)

fun nameAndType(name: Int, descriptor: Int): ByteArray = byteArrayOf(12) + u2(name) + u2(descriptor)

fun fieldRef(owner: Int, nameAndType: Int): ByteArray = byteArrayOf(9) + u2(owner) + u2(nameAndType)

fun integer(): ByteArray = byteArrayOf(3, 0, 0, 0, 42)

private fun u2(value: Int): ByteArray = byteArrayOf((value shr 8).toByte(), value.toByte())
