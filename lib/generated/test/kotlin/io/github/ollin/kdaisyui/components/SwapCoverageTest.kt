package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class SwapCoverageTest {

    /** What a case must render: its classes, sorted, the tail it closes with, whether it took content. */
    private class Expected(val label: String, val classes: String, val closes: String = "", val content: Boolean = true)

    private fun assertRendered(html: String, expected: Expected) {
        assertEquals(expected.classes, html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" "), expected.label)
        if (expected.closes.isNotEmpty()) assertTrue(html.endsWith(expected.closes), "${expected.label} closes")
    }

    private fun assertCommonFlags(html: String, expected: Expected) {
        assertTrue(html.contains("id=\"x-cov-id\""), "${expected.label} id")
        assertTrue(html.contains("data-attrs=\"yes\""), "${expected.label} attrs")
        if (expected.content) assertTrue(html.contains("data-content=\"yes\""), "${expected.label} content")
    }

    @Test
    fun swap_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisySwap(
                content = { },
            )
        }
        assertRendered(html, Expected("Swap defaults", "swap", closes = "</label></div>"))
    }

    @Test
    fun swap_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisySwap(
                id = htmlId("x-cov-id"),
                active = true,
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        val expected = Expected("Swap all flags", "swap swap-active zz-extra", closes = "</label></div>")
        assertRendered(html, expected)
        assertCommonFlags(html, expected)
    }

    @Test
    fun swap_style_rotate() {
        val html = createHTML(prettyPrint = false).div {
            daisySwap(
                style = SwapStyle.Rotate,
                content = { },
            )
        }
        assertRendered(html, Expected("Swap style Rotate", "swap swap-rotate"))
    }

    @Test
    fun swap_style_flip() {
        val html = createHTML(prettyPrint = false).div {
            daisySwap(
                style = SwapStyle.Flip,
                content = { },
            )
        }
        assertRendered(html, Expected("Swap style Flip", "swap swap-flip"))
    }

    @Test
    fun swapClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisySwapClasses()
        }
        assertRendered(html, Expected("SwapClasses defaults", "swap"))
    }

    @Test
    fun swapClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisySwapClasses(
                active = true,
            )
        }
        val expected = Expected("SwapClasses all flags", "swap swap-active", content = false)
        assertRendered(html, expected)
    }

    @Test
    fun swapClasses_style_rotate() {
        val html = createHTML(prettyPrint = false).div {
            daisySwapClasses(
                style = SwapStyle.Rotate,
            )
        }
        assertRendered(html, Expected("SwapClasses style Rotate", "swap swap-rotate"))
    }

    @Test
    fun swapClasses_style_flip() {
        val html = createHTML(prettyPrint = false).div {
            daisySwapClasses(
                style = SwapStyle.Flip,
            )
        }
        assertRendered(html, Expected("SwapClasses style Flip", "swap swap-flip"))
    }

    @Test
    fun swapOn_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisySwapOn(
                content = { },
            )
        }
        assertRendered(html, Expected("SwapOn defaults", "swap-on", closes = "</div></div>"))
    }

    @Test
    fun swapOn_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisySwapOn(
                id = htmlId("x-cov-id"),
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        val expected = Expected("SwapOn all flags", "swap-on zz-extra", closes = "</div></div>")
        assertRendered(html, expected)
        assertCommonFlags(html, expected)
    }

    @Test
    fun swapOnClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisySwapOnClasses()
        }
        assertRendered(html, Expected("SwapOnClasses defaults", "swap-on"))
    }

    @Test
    fun swapOnClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisySwapOnClasses()
        }
        val expected = Expected("SwapOnClasses all flags", "swap-on", content = false)
        assertRendered(html, expected)
    }

    @Test
    fun swapOff_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisySwapOff(
                content = { },
            )
        }
        assertRendered(html, Expected("SwapOff defaults", "swap-off", closes = "</div></div>"))
    }

    @Test
    fun swapOff_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisySwapOff(
                id = htmlId("x-cov-id"),
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        val expected = Expected("SwapOff all flags", "swap-off zz-extra", closes = "</div></div>")
        assertRendered(html, expected)
        assertCommonFlags(html, expected)
    }

    @Test
    fun swapOffClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisySwapOffClasses()
        }
        assertRendered(html, Expected("SwapOffClasses defaults", "swap-off"))
    }

    @Test
    fun swapOffClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisySwapOffClasses()
        }
        val expected = Expected("SwapOffClasses all flags", "swap-off", content = false)
        assertRendered(html, expected)
    }

    @Test
    fun swapIndeterminate_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisySwapIndeterminate(
                content = { },
            )
        }
        assertRendered(html, Expected("SwapIndeterminate defaults", "swap-indeterminate", closes = "</div></div>"))
    }

    @Test
    fun swapIndeterminate_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisySwapIndeterminate(
                id = htmlId("x-cov-id"),
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        val expected = Expected("SwapIndeterminate all flags", "swap-indeterminate zz-extra", closes = "</div></div>")
        assertRendered(html, expected)
        assertCommonFlags(html, expected)
    }

    @Test
    fun swapIndeterminateClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisySwapIndeterminateClasses()
        }
        assertRendered(html, Expected("SwapIndeterminateClasses defaults", "swap-indeterminate"))
    }

    @Test
    fun swapIndeterminateClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisySwapIndeterminateClasses()
        }
        val expected = Expected("SwapIndeterminateClasses all flags", "swap-indeterminate", content = false)
        assertRendered(html, expected)
    }
}
