package io.github.ollin.kdaisyui.buildchecks

import kotlin.io.path.Path
import kotlin.io.path.readText

/** `<problems report> <allowlist>`: fails, naming each, when the report has a problem the allowlist does not accept. */
fun main(args: Array<String>) {
    val (report, allowlist) = args.map { Path(it).readText() }
    val found = unexpected(problemsIn(report), allowlist)
    check(found.isEmpty()) { "Gradle problems no line of the allowlist accepts:\n" + found.joinToString("\n") { "  ${it.id} (${it.pluginId ?: "this build"})" } }
}
