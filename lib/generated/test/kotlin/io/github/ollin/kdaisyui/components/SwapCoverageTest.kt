package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class SwapCoverageTest {

    @Test
    fun swap_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisySwap(
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("swap", actualClasses, "Swap defaults")
        assertTrue(html.endsWith("</label></div>"), "Swap closes <label>")
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
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("swap swap-active swap-flip swap-rotate zz-extra", actualClasses, "Swap all flags")
        assertTrue(html.contains("id=\"x-cov-id\""), "Swap id")
        assertTrue(html.contains("data-attrs=\"yes\""), "Swap attrs")
        assertTrue(html.contains("data-content=\"yes\""), "Swap content")
        assertTrue(html.endsWith("</label></div>"), "Swap closes <label>")
    }

    @Test
    fun swapOn_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisySwapOn(
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("swap-on", actualClasses, "SwapOn defaults")
        assertTrue(html.endsWith("</div></div>"), "SwapOn closes <div>")
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
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("swap-on zz-extra", actualClasses, "SwapOn all flags")
        assertTrue(html.contains("id=\"x-cov-id\""), "SwapOn id")
        assertTrue(html.contains("data-attrs=\"yes\""), "SwapOn attrs")
        assertTrue(html.contains("data-content=\"yes\""), "SwapOn content")
        assertTrue(html.endsWith("</div></div>"), "SwapOn closes <div>")
    }

    @Test
    fun swapOff_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisySwapOff(
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("swap-off", actualClasses, "SwapOff defaults")
        assertTrue(html.endsWith("</div></div>"), "SwapOff closes <div>")
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
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("swap-off zz-extra", actualClasses, "SwapOff all flags")
        assertTrue(html.contains("id=\"x-cov-id\""), "SwapOff id")
        assertTrue(html.contains("data-attrs=\"yes\""), "SwapOff attrs")
        assertTrue(html.contains("data-content=\"yes\""), "SwapOff content")
        assertTrue(html.endsWith("</div></div>"), "SwapOff closes <div>")
    }

    @Test
    fun swapIndeterminate_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisySwapIndeterminate(
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("swap-indeterminate", actualClasses, "SwapIndeterminate defaults")
        assertTrue(html.endsWith("</div></div>"), "SwapIndeterminate closes <div>")
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
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("swap-indeterminate zz-extra", actualClasses, "SwapIndeterminate all flags")
        assertTrue(html.contains("id=\"x-cov-id\""), "SwapIndeterminate id")
        assertTrue(html.contains("data-attrs=\"yes\""), "SwapIndeterminate attrs")
        assertTrue(html.contains("data-content=\"yes\""), "SwapIndeterminate content")
        assertTrue(html.endsWith("</div></div>"), "SwapIndeterminate closes <div>")
    }
}
