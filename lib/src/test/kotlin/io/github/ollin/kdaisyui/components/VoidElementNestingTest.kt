package io.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * A component must leave the consumer's nesting level exactly as it found it.
 *
 * Every other test in this project renders with `prettyPrint = false` and asserts on class
 * strings, and neither can see this: a component that opens an element without closing it
 * emits identical class attributes, and its siblings' *depth* is the only trace left.
 *
 * That matters most for a VOID element. `daisyRange` emits `<input>`, which has no closing
 * tag — so it is tempting to conclude nothing needs to happen at its end. It does:
 * `HTMLStreamBuilder.onTagEnd` decrements `level` BEFORE it checks `emptyTag`, so skipping
 * it silently indents every following sibling one level too deep.
 *
 * Found by mutation testing, which is also the honest provenance: this was the last mutant
 * surviving in the scoped classes, and no assertion in the project could observe it.
 */
class VoidElementNestingTest {
    @Test
    fun rangeLeavesTheNestingLevelIntactForItsSiblings() {
        val html = createHTML(prettyPrint = true).div {
            daisyRange()
            div { +"after" }
        }

        // The sibling sits at ONE level of indentation, the same as the range. Two levels
        // would mean the range never closed and took the rest of the document down with it.
        assertEquals(
            "<div><input type=\"range\" class=\"range\">\n  <div>after</div>\n</div>\n",
            html,
        )
    }
}
