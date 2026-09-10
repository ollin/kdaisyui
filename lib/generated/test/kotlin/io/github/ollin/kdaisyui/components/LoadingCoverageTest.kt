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
                ball = true,
                bars = true,
                dots = true,
                infinity = true,
                ring = true,
                spinner = true,
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        assertRendered(html, "loading loading-ball loading-bars loading-dots loading-infinity loading-ring loading-spinner zz-extra", "Loading all flags", closes = "</span></div>")
        assertTrue(html.contains("id=\"x-cov-id\""), "Loading id")
        assertTrue(html.contains("data-attrs=\"yes\""), "Loading attrs")
        assertTrue(html.contains("data-content=\"yes\""), "Loading content")
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
}
