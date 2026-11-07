package io.github.ollin.kdaisyui

import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * `kdaisyui.css` must reach consumers as a classpath resource, like the class list beside it.
 * Without it an anchored popover opens in the middle of the viewport, and nothing fails.
 */
class KdaisyuiCssTest {

    @Test
    fun it_ships_the_two_anchor_rules_and_nothing_else() {
        val css = javaClass.classLoader.getResourceAsStream("kdaisyui.css")?.bufferedReader()?.readText()
            ?: error("kdaisyui.css is not on the classpath")
        val rules = css.substringAfter("*/").lines().filter { it.isNotBlank() }
        assertEquals(
            listOf(
                "[data-anchor-name] { anchor-name: attr(data-anchor-name type(<custom-ident>)); }",
                "[data-position-anchor] { position-anchor: attr(data-position-anchor type(<custom-ident>)); }",
            ),
            rules,
        )
    }
}
