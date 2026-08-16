package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class LoadingCoverageTest {

    @Test
    fun loading_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyLoading(
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("loading", actualClasses, "Loading defaults")
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
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("loading loading-ball loading-bars loading-dots loading-infinity loading-ring loading-spinner zz-extra", actualClasses, "Loading all flags")
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
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("loading loading-xs", actualClasses, "Loading size Xs")
    }

    @Test
    fun loading_size_sm() {
        val html = createHTML(prettyPrint = false).div {
            daisyLoading(
                size = LoadingSize.Sm,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("loading loading-sm", actualClasses, "Loading size Sm")
    }

    @Test
    fun loading_size_md() {
        val html = createHTML(prettyPrint = false).div {
            daisyLoading(
                size = LoadingSize.Md,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("loading loading-md", actualClasses, "Loading size Md")
    }

    @Test
    fun loading_size_lg() {
        val html = createHTML(prettyPrint = false).div {
            daisyLoading(
                size = LoadingSize.Lg,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("loading loading-lg", actualClasses, "Loading size Lg")
    }

    @Test
    fun loading_size_xl() {
        val html = createHTML(prettyPrint = false).div {
            daisyLoading(
                size = LoadingSize.Xl,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("loading loading-xl", actualClasses, "Loading size Xl")
    }
}
