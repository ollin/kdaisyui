package io.github.ollin.kdaisyui.gradle

import java.io.File
import java.util.zip.ZipFile

const val COMPONENT_MAP = "kdaisyui-components.txt"

/**
 * The component map in one runtime classpath entry, or null: a jar, as the published kdaisyui is, a
 * resources directory, as a project dependency is, or a path that does not exist, which Gradle lists too.
 */
fun componentMapIn(entry: File): String? = when {
    entry.isDirectory -> entry.resolve(COMPONENT_MAP).takeIf(File::isFile)?.readText()
    entry.isFile -> componentMapInJar(ZipFile(entry))
    else -> null
}

// readBytes and try/finally rather than bufferedReader() and use {}: both inline a branch no test
// can reach (an InputStreamReader is never a BufferedReader; a close that fails), and the branch
// gate counts them.
private fun componentMapInJar(jar: ZipFile): String? = try {
    jar.getEntry(COMPONENT_MAP)?.let { jar.getInputStream(it).readBytes().decodeToString() }
} finally {
    jar.close()
}
