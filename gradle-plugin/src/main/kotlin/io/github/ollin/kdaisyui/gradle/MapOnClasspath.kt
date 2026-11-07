package io.github.ollin.kdaisyui.gradle

import java.io.File
import java.util.zip.ZipFile

const val COMPONENT_MAP = "kdaisyui-components.txt"
const val STYLESHEET = "kdaisyui.css"

/**
 * The resource [name] in one runtime classpath entry, or null: a jar, as the published kdaisyui is, a
 * resources directory, as a project dependency is, or a path that does not exist, which Gradle lists too.
 */
fun resourceIn(entry: File, name: String): String? = when {
    entry.isDirectory -> entry.resolve(name).takeIf(File::isFile)?.readText()
    entry.isFile -> resourceInJar(ZipFile(entry), name)
    else -> null
}

// readBytes and try/finally rather than bufferedReader() and use {}: both inline a branch no test
// can reach (an InputStreamReader is never a BufferedReader; a close that fails), and the branch
// gate counts them.
private fun resourceInJar(jar: ZipFile, name: String): String? = try {
    jar.getEntry(name)?.let { jar.getInputStream(it).readBytes().decodeToString() }
} finally {
    jar.close()
}
