package io.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

/**
 * What the join scope does at run time, which no generated test and no compiler can answer.
 *
 * Three of these facts are Kotlin resolution behaviour rather than emitted class strings, so
 * the codegen's own tests cannot reach them and `generated-sources-drift` cannot see them. The
 * first one in particular: a member delegating to a top-level extension of the same name
 * COMPILES whether it reaches the extension or itself, and the difference between the two is a
 * `StackOverflowError`.
 */
class JoinScopeTest {

    @Test
    fun `a component call directly in a join is a join item`() {
        val html = createHTML(prettyPrint = false).div { daisyJoin { daisyButton("A") } }

        assertTrue(html.contains("class=\"btn join-item\""), html)
    }

    @Test
    fun `a call nested in another builder is not a join item`() {
        val html = createHTML(prettyPrint = false).div { daisyJoin { div { daisyInput() } } }

        assertFalse(html.contains("join-item"), html)
    }

    @Test
    fun `a nested call reaches the scope when it names it`() {
        val html = createHTML(prettyPrint = false).div {
            daisyJoin { div { this@daisyJoin.daisyInput() } }
        }

        assertTrue(html.contains("class=\"input join-item\""), html)
    }

    @Test
    fun `the element lands inside the wrapper, as DaisyUI documents it`() {
        val html = createHTML(prettyPrint = false).div {
            daisyJoin { div { this@daisyJoin.daisyInput() } }
        }

        // The nesting, not the attributes: kotlinx.html writes positionally into one shared
        // consumer, so the input lands inside the wrapper rather than at join level. That is
        // the `div > div > input.join-item` DaisyUI documents, and it is what makes marking a
        // nested call meaningful at all.
        assertTrue(html.contains("<div class=\"join\"><div><input"), html)
        assertTrue(html.contains("class=\"input join-item\""), html)
    }

    @Test
    fun `the marker comes before whatever the caller wrote`() {
        val html = createHTML(prettyPrint = false).div {
            daisyJoin { daisyButton("A", extraClasses = "w-32") }
        }

        assertTrue(html.contains("class=\"btn join-item w-32\""), html)
    }

    @Test
    fun `the same call outside a join is untouched`() {
        val html = createHTML(prettyPrint = false).div { daisyButton("A") }

        assertEquals("<div><button class=\"btn\">A</button></div>", html)
    }

    @Test
    fun `the join itself still takes its own parameters`() {
        val html = createHTML(prettyPrint = false).div {
            daisyJoin(direction = JoinDirection.Vertical, extraClasses = "shadow") { daisyButton("A") }
        }

        assertTrue(html.contains("class=\"join join-vertical shadow\""), html)
    }

    /**
     * The set pin. It is hand-written on purpose: a GENERATED assertion would be regenerated
     * alongside the thing it pins and could never go red, and the task asks for a test that a
     * DaisyUI bump moving the set must fail.
     *
     * Measured 2026-09-19 at v5.7.17 — every component class DaisyUI documents on an element
     * that also carries `join-item`, across all 66 pages.
     */
    @Test
    fun `the scope has a member for each of the seven documented join items`() {
        val members = JoinScope::class.java.declaredMethods
            .map { it.name }
            .filter { it.startsWith("daisy") && !it.contains('$') }
            .distinct()
            .sorted()

        assertEquals(
            listOf(
                "daisyButton",
                "daisyCard",
                "daisyCollapse",
                "daisyInput",
                "daisySelect",
                "daisyThemeController",
                "daisyValidator",
            ),
            members,
        )
    }
}
