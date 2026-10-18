package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class SwapCoverageTest {

    private fun assertRendered(html: String, classes: String, label: String, closes: String = "") {
        assertEquals(
            classes,
            html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" "),
            label,
        )
        if (closes.isNotEmpty()) assertTrue(html.endsWith(closes), "$label closes")
    }

    private fun assertCommonFlags(html: String, label: String, content: Boolean = true) {
        assertTrue(html.contains("id=\"x-cov-id\""), "$label id")
        assertTrue(html.contains("data-attrs=\"yes\""), "$label attrs")
        if (content) assertTrue(html.contains("data-content=\"yes\""), "$label content")
    }

    @Test
    fun swap_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisySwap(
                content = { },
            )
        }
        assertRendered(html, "swap", "Swap defaults", closes = "</label></div>")
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
        assertRendered(html, "swap swap-active zz-extra", "Swap all flags", closes = "</label></div>")
        assertCommonFlags(html, "Swap")
    }

    @Test
    fun swap_style_rotate() {
        val html = createHTML(prettyPrint = false).div {
            daisySwap(
                style = SwapStyle.Rotate,
                content = { },
            )
        }
        assertRendered(html, "swap swap-rotate", "Swap style Rotate")
    }

    @Test
    fun swap_style_flip() {
        val html = createHTML(prettyPrint = false).div {
            daisySwap(
                style = SwapStyle.Flip,
                content = { },
            )
        }
        assertRendered(html, "swap swap-flip", "Swap style Flip")
    }

    @Test
    fun swapClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisySwapClasses()
        }
        assertRendered(html, "swap", "SwapClasses defaults")
    }

    @Test
    fun swapClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisySwapClasses(
                active = true,
            )
        }
        assertRendered(html, "swap swap-active", "SwapClasses all flags")
    }

    @Test
    fun swapClasses_style_rotate() {
        val html = createHTML(prettyPrint = false).div {
            daisySwapClasses(
                style = SwapStyle.Rotate,
            )
        }
        assertRendered(html, "swap swap-rotate", "SwapClasses style Rotate")
    }

    @Test
    fun swapClasses_style_flip() {
        val html = createHTML(prettyPrint = false).div {
            daisySwapClasses(
                style = SwapStyle.Flip,
            )
        }
        assertRendered(html, "swap swap-flip", "SwapClasses style Flip")
    }

    @Test
    fun swapOn_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisySwapOn(
                content = { },
            )
        }
        assertRendered(html, "swap-on", "SwapOn defaults", closes = "</div></div>")
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
        assertRendered(html, "swap-on zz-extra", "SwapOn all flags", closes = "</div></div>")
        assertCommonFlags(html, "SwapOn")
    }

    @Test
    fun swapOnClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisySwapOnClasses()
        }
        assertRendered(html, "swap-on", "SwapOnClasses defaults")
    }

    @Test
    fun swapOnClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisySwapOnClasses()
        }
        assertRendered(html, "swap-on", "SwapOnClasses all flags")
    }

    @Test
    fun swapOff_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisySwapOff(
                content = { },
            )
        }
        assertRendered(html, "swap-off", "SwapOff defaults", closes = "</div></div>")
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
        assertRendered(html, "swap-off zz-extra", "SwapOff all flags", closes = "</div></div>")
        assertCommonFlags(html, "SwapOff")
    }

    @Test
    fun swapOffClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisySwapOffClasses()
        }
        assertRendered(html, "swap-off", "SwapOffClasses defaults")
    }

    @Test
    fun swapOffClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisySwapOffClasses()
        }
        assertRendered(html, "swap-off", "SwapOffClasses all flags")
    }

    @Test
    fun swapIndeterminate_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisySwapIndeterminate(
                content = { },
            )
        }
        assertRendered(html, "swap-indeterminate", "SwapIndeterminate defaults", closes = "</div></div>")
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
        assertRendered(html, "swap-indeterminate zz-extra", "SwapIndeterminate all flags", closes = "</div></div>")
        assertCommonFlags(html, "SwapIndeterminate")
    }

    @Test
    fun swapIndeterminateClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisySwapIndeterminateClasses()
        }
        assertRendered(html, "swap-indeterminate", "SwapIndeterminateClasses defaults")
    }

    @Test
    fun swapIndeterminateClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisySwapIndeterminateClasses()
        }
        assertRendered(html, "swap-indeterminate", "SwapIndeterminateClasses all flags")
    }
}
