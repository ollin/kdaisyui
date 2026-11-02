package io.github.ollin.kdaisyui.components

import kotlinx.html.TagConsumer
import kotlinx.html.button
import kotlinx.html.div
import kotlinx.html.stream.appendHTML
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

/**
 * The kotlinx.html rule a generated function's body order rests on: an attribute can be set on an
 * element only until its first content is written.
 *
 * Both consumers the documentation uses, `createHTML` and `appendHTML`, delay a tag's start until
 * its first child or text and then pass it downstream; an attribute change after that throws.
 * That is why a function's block runs before its `text` is appended: the other order would make
 * `daisyButton("Save") { popoverTarget(menu) }` throw at render time.
 */
class AttributeOrderTest {

    private fun TagConsumer<*>.attributeAfterText() = div { button { +"Save"; attributes["data-probe"] = "yes" } }

    private fun TagConsumer<*>.attributeBeforeText() = div { button { attributes["data-probe"] = "yes"; +"Save" } }

    @Test
    fun `an attribute before the text is rendered`() {
        assertEquals(
            "<div><button data-probe=\"yes\">Save</button></div>",
            createHTML(prettyPrint = false).attributeBeforeText(),
        )
    }

    @Test
    fun `an attribute after the text throws with createHTML`() {
        assertFailsWith<IllegalStateException> { createHTML(prettyPrint = false).attributeAfterText() }
    }

    @Test
    fun `an attribute after the text throws with appendHTML`() {
        assertFailsWith<IllegalStateException> { StringBuilder().appendHTML(prettyPrint = false).attributeAfterText() }
    }
}
