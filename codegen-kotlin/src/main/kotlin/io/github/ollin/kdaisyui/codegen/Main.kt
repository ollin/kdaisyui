package io.github.ollin.kdaisyui.codegen

import java.nio.file.Path
import kotlin.io.path.createDirectories
import kotlin.io.path.div

/**
 * Probe entry point. Emits a couple of components into `build/probe-output/` so they can be
 * put next to what the JavaScript generator produces.
 *
 * The element per component is passed in here rather than parsed. The JavaScript generator
 * scrapes it out of DaisyUI's prose (`codegen/src/parser/llms-txt.js`), which is also what
 * pins the whole project to DaisyUI 5.5.20 — DaisyUI deleted that file in 5.5.23. Sixty-five
 * stable data points do not need a scraper.
 */
fun main() {
    val docs = Path.of("daisyui/packages/docs/src/routes/(routes)/components")
    val out = Path.of("codegen-kotlin/build/probe-output").also { it.createDirectories() }

    listOf("button" to "BUTTON", "tooltip" to "DIV").forEach { (name, element) ->
        val component = Frontmatter.parseFile(docs / name / "+page.md", element)
        Emitter.emit(component).writeTo(out)

        println("$name -> ${component.parameterCount} parameters")
        component.axes.forEach { axis ->
            val kind = if (axis is Axis.Choice) "choice" else "flags "
            println("  $kind ${axis.category.padEnd(10)} ${axis.options.size}")
        }
    }
    println("\nwritten to $out")
}
