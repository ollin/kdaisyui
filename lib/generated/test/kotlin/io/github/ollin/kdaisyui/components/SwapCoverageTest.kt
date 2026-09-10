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
                flip = true,
                rotate = true,
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        assertRendered(html, "swap swap-active swap-flip swap-rotate zz-extra", "Swap all flags", closes = "</label></div>")
        assertTrue(html.contains("id=\"x-cov-id\""), "Swap id")
        assertTrue(html.contains("data-attrs=\"yes\""), "Swap attrs")
        assertTrue(html.contains("data-content=\"yes\""), "Swap content")
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
        assertTrue(html.contains("id=\"x-cov-id\""), "SwapOn id")
        assertTrue(html.contains("data-attrs=\"yes\""), "SwapOn attrs")
        assertTrue(html.contains("data-content=\"yes\""), "SwapOn content")
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
        assertTrue(html.contains("id=\"x-cov-id\""), "SwapOff id")
        assertTrue(html.contains("data-attrs=\"yes\""), "SwapOff attrs")
        assertTrue(html.contains("data-content=\"yes\""), "SwapOff content")
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
        assertTrue(html.contains("id=\"x-cov-id\""), "SwapIndeterminate id")
        assertTrue(html.contains("data-attrs=\"yes\""), "SwapIndeterminate attrs")
        assertTrue(html.contains("data-content=\"yes\""), "SwapIndeterminate content")
    }
}
