package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class LoadingCoverageTest {

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
    fun loading_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyLoading(
                content = { },
            )
        }
        assertRendered(html, "loading", "Loading defaults", closes = "</span></div>")
    }

    @Test
    fun loading_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyLoading(
                id = htmlId("x-cov-id"),
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        assertRendered(html, "loading zz-extra", "Loading all flags", closes = "</span></div>")
        assertCommonFlags(html, "Loading")
    }

    @Test
    fun loading_size_xs() {
        val html = createHTML(prettyPrint = false).div {
            daisyLoading(
                size = LoadingSize.Xs,
                content = { },
            )
        }
        assertRendered(html, "loading loading-xs", "Loading size Xs")
    }

    @Test
    fun loading_size_sm() {
        val html = createHTML(prettyPrint = false).div {
            daisyLoading(
                size = LoadingSize.Sm,
                content = { },
            )
        }
        assertRendered(html, "loading loading-sm", "Loading size Sm")
    }

    @Test
    fun loading_size_md() {
        val html = createHTML(prettyPrint = false).div {
            daisyLoading(
                size = LoadingSize.Md,
                content = { },
            )
        }
        assertRendered(html, "loading loading-md", "Loading size Md")
    }

    @Test
    fun loading_size_lg() {
        val html = createHTML(prettyPrint = false).div {
            daisyLoading(
                size = LoadingSize.Lg,
                content = { },
            )
        }
        assertRendered(html, "loading loading-lg", "Loading size Lg")
    }

    @Test
    fun loading_size_xl() {
        val html = createHTML(prettyPrint = false).div {
            daisyLoading(
                size = LoadingSize.Xl,
                content = { },
            )
        }
        assertRendered(html, "loading loading-xl", "Loading size Xl")
    }

    @Test
    fun loading_style_spinner() {
        val html = createHTML(prettyPrint = false).div {
            daisyLoading(
                style = LoadingStyle.Spinner,
                content = { },
            )
        }
        assertRendered(html, "loading loading-spinner", "Loading style Spinner")
    }

    @Test
    fun loading_style_dots() {
        val html = createHTML(prettyPrint = false).div {
            daisyLoading(
                style = LoadingStyle.Dots,
                content = { },
            )
        }
        assertRendered(html, "loading loading-dots", "Loading style Dots")
    }

    @Test
    fun loading_style_ring() {
        val html = createHTML(prettyPrint = false).div {
            daisyLoading(
                style = LoadingStyle.Ring,
                content = { },
            )
        }
        assertRendered(html, "loading loading-ring", "Loading style Ring")
    }

    @Test
    fun loading_style_ball() {
        val html = createHTML(prettyPrint = false).div {
            daisyLoading(
                style = LoadingStyle.Ball,
                content = { },
            )
        }
        assertRendered(html, "loading loading-ball", "Loading style Ball")
    }

    @Test
    fun loading_style_bars() {
        val html = createHTML(prettyPrint = false).div {
            daisyLoading(
                style = LoadingStyle.Bars,
                content = { },
            )
        }
        assertRendered(html, "loading loading-bars", "Loading style Bars")
    }

    @Test
    fun loading_style_infinity() {
        val html = createHTML(prettyPrint = false).div {
            daisyLoading(
                style = LoadingStyle.Infinity,
                content = { },
            )
        }
        assertRendered(html, "loading loading-infinity", "Loading style Infinity")
    }
}
