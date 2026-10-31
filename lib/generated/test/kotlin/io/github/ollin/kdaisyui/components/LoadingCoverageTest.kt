package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class LoadingCoverageTest {

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
    fun loading_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyLoading(
                content = { },
            )
        }
        assertRendered(html, Expected("Loading defaults", "loading", closes = "</span></div>"))
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
        val expected = Expected("Loading all flags", "loading zz-extra", closes = "</span></div>")
        assertRendered(html, expected)
        assertCommonFlags(html, expected)
    }

    @Test
    fun loading_size_xs() {
        val html = createHTML(prettyPrint = false).div {
            daisyLoading(
                size = LoadingSize.Xs,
                content = { },
            )
        }
        assertRendered(html, Expected("Loading size Xs", "loading loading-xs"))
    }

    @Test
    fun loading_size_sm() {
        val html = createHTML(prettyPrint = false).div {
            daisyLoading(
                size = LoadingSize.Sm,
                content = { },
            )
        }
        assertRendered(html, Expected("Loading size Sm", "loading loading-sm"))
    }

    @Test
    fun loading_size_md() {
        val html = createHTML(prettyPrint = false).div {
            daisyLoading(
                size = LoadingSize.Md,
                content = { },
            )
        }
        assertRendered(html, Expected("Loading size Md", "loading loading-md"))
    }

    @Test
    fun loading_size_lg() {
        val html = createHTML(prettyPrint = false).div {
            daisyLoading(
                size = LoadingSize.Lg,
                content = { },
            )
        }
        assertRendered(html, Expected("Loading size Lg", "loading loading-lg"))
    }

    @Test
    fun loading_size_xl() {
        val html = createHTML(prettyPrint = false).div {
            daisyLoading(
                size = LoadingSize.Xl,
                content = { },
            )
        }
        assertRendered(html, Expected("Loading size Xl", "loading loading-xl"))
    }

    @Test
    fun loading_style_spinner() {
        val html = createHTML(prettyPrint = false).div {
            daisyLoading(
                style = LoadingStyle.Spinner,
                content = { },
            )
        }
        assertRendered(html, Expected("Loading style Spinner", "loading loading-spinner"))
    }

    @Test
    fun loading_style_dots() {
        val html = createHTML(prettyPrint = false).div {
            daisyLoading(
                style = LoadingStyle.Dots,
                content = { },
            )
        }
        assertRendered(html, Expected("Loading style Dots", "loading loading-dots"))
    }

    @Test
    fun loading_style_ring() {
        val html = createHTML(prettyPrint = false).div {
            daisyLoading(
                style = LoadingStyle.Ring,
                content = { },
            )
        }
        assertRendered(html, Expected("Loading style Ring", "loading loading-ring"))
    }

    @Test
    fun loading_style_ball() {
        val html = createHTML(prettyPrint = false).div {
            daisyLoading(
                style = LoadingStyle.Ball,
                content = { },
            )
        }
        assertRendered(html, Expected("Loading style Ball", "loading loading-ball"))
    }

    @Test
    fun loading_style_bars() {
        val html = createHTML(prettyPrint = false).div {
            daisyLoading(
                style = LoadingStyle.Bars,
                content = { },
            )
        }
        assertRendered(html, Expected("Loading style Bars", "loading loading-bars"))
    }

    @Test
    fun loading_style_infinity() {
        val html = createHTML(prettyPrint = false).div {
            daisyLoading(
                style = LoadingStyle.Infinity,
                content = { },
            )
        }
        assertRendered(html, Expected("Loading style Infinity", "loading loading-infinity"))
    }

    @Test
    fun loadingClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyLoadingClasses()
        }
        assertRendered(html, Expected("LoadingClasses defaults", "loading"))
    }

    @Test
    fun loadingClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyLoadingClasses()
        }
        val expected = Expected("LoadingClasses all flags", "loading", content = false)
        assertRendered(html, expected)
    }

    @Test
    fun loadingClasses_size_xs() {
        val html = createHTML(prettyPrint = false).div {
            daisyLoadingClasses(
                size = LoadingSize.Xs,
            )
        }
        assertRendered(html, Expected("LoadingClasses size Xs", "loading loading-xs"))
    }

    @Test
    fun loadingClasses_size_sm() {
        val html = createHTML(prettyPrint = false).div {
            daisyLoadingClasses(
                size = LoadingSize.Sm,
            )
        }
        assertRendered(html, Expected("LoadingClasses size Sm", "loading loading-sm"))
    }

    @Test
    fun loadingClasses_size_md() {
        val html = createHTML(prettyPrint = false).div {
            daisyLoadingClasses(
                size = LoadingSize.Md,
            )
        }
        assertRendered(html, Expected("LoadingClasses size Md", "loading loading-md"))
    }

    @Test
    fun loadingClasses_size_lg() {
        val html = createHTML(prettyPrint = false).div {
            daisyLoadingClasses(
                size = LoadingSize.Lg,
            )
        }
        assertRendered(html, Expected("LoadingClasses size Lg", "loading loading-lg"))
    }

    @Test
    fun loadingClasses_size_xl() {
        val html = createHTML(prettyPrint = false).div {
            daisyLoadingClasses(
                size = LoadingSize.Xl,
            )
        }
        assertRendered(html, Expected("LoadingClasses size Xl", "loading loading-xl"))
    }

    @Test
    fun loadingClasses_style_spinner() {
        val html = createHTML(prettyPrint = false).div {
            daisyLoadingClasses(
                style = LoadingStyle.Spinner,
            )
        }
        assertRendered(html, Expected("LoadingClasses style Spinner", "loading loading-spinner"))
    }

    @Test
    fun loadingClasses_style_dots() {
        val html = createHTML(prettyPrint = false).div {
            daisyLoadingClasses(
                style = LoadingStyle.Dots,
            )
        }
        assertRendered(html, Expected("LoadingClasses style Dots", "loading loading-dots"))
    }

    @Test
    fun loadingClasses_style_ring() {
        val html = createHTML(prettyPrint = false).div {
            daisyLoadingClasses(
                style = LoadingStyle.Ring,
            )
        }
        assertRendered(html, Expected("LoadingClasses style Ring", "loading loading-ring"))
    }

    @Test
    fun loadingClasses_style_ball() {
        val html = createHTML(prettyPrint = false).div {
            daisyLoadingClasses(
                style = LoadingStyle.Ball,
            )
        }
        assertRendered(html, Expected("LoadingClasses style Ball", "loading loading-ball"))
    }

    @Test
    fun loadingClasses_style_bars() {
        val html = createHTML(prettyPrint = false).div {
            daisyLoadingClasses(
                style = LoadingStyle.Bars,
            )
        }
        assertRendered(html, Expected("LoadingClasses style Bars", "loading loading-bars"))
    }

    @Test
    fun loadingClasses_style_infinity() {
        val html = createHTML(prettyPrint = false).div {
            daisyLoadingClasses(
                style = LoadingStyle.Infinity,
            )
        }
        assertRendered(html, Expected("LoadingClasses style Infinity", "loading loading-infinity"))
    }
}
