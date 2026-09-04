package io.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

/**
 * Characterization tests for the `text` / `content` precedence every generated component with a
 * `text` parameter shares.
 *
 * These pin behaviour that already exists; they are not a proposal to change it. The generator
 * emits `when { content != null -> content(); text != null -> +text }`, so **`content` wins and
 * `text` is dropped entirely** — and because `content` is the last parameter, the ordinary Kotlin
 * trailing-lambda call `daisyButton(text = "Save") { … }` silently takes that branch.
 *
 * That cost a red end-to-end test to diagnose while writing the popover-modal demo: a button
 * rendered with its attribute set and no label at all, and `getByText` waited ten seconds for
 * something that was never there. The KDoc says "takes precedence over [text]", so the behaviour
 * is documented — but nothing failed when it bit, which is what these tests change.
 *
 * If a future codegen change alters the precedence, or drops the `text` branch, or reorders the
 * parameters so a trailing lambda binds elsewhere, one of these fails and says so.
 */
class TextAndContentPrecedenceTest {

    @Test
    fun text_alone_is_rendered() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(text = "Save")
        }
        assertTrue(html.contains(">Save<"), "expected the text to be rendered, got: $html")
    }

    @Test
    fun content_alone_is_rendered() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(content = { +"Save" })
        }
        assertTrue(html.contains(">Save<"), "expected the content to be rendered, got: $html")
    }

    @Test
    fun content_wins_and_text_is_dropped_when_both_are_given() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(text = "Dropped", content = { +"Rendered" })
        }
        assertTrue(html.contains(">Rendered<"), "expected the content, got: $html")
        assertFalse(html.contains("Dropped"), "expected the text to be dropped, got: $html")
    }

    /**
     * The trap itself. A trailing lambda binds to `content`, the last parameter — so this reads
     * like "a button labelled Save, with an attribute" and renders a button with no label.
     */
    @Test
    fun a_trailing_lambda_binds_to_content_and_therefore_drops_the_text() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(text = "Save") { attributes["data-probe"] = "yes" }
        }
        assertTrue(html.contains("data-probe=\"yes\""), "expected the lambda to have run, got: $html")
        assertFalse(html.contains("Save"), "expected the text to be dropped, got: $html")
    }

    /** Passing the attribute block as `attrs` is what the caller almost always means. */
    @Test
    fun attrs_leaves_the_text_intact() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(text = "Save", attrs = { attributes["data-probe"] = "yes" })
        }
        assertTrue(html.contains("data-probe=\"yes\""), "expected the attribute, got: $html")
        assertTrue(html.contains(">Save<"), "expected the text to survive, got: $html")
    }

    /** Neither given renders an empty element rather than failing. */
    @Test
    fun neither_text_nor_content_renders_an_empty_element() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton()
        }
        assertEquals("<div><button class=\"btn\"></button></div>", html)
    }
}
