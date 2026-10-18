package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class StatusCoverageTest {

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
    fun status_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatus(
                content = { },
            )
        }
        assertRendered(html, "status", "Status defaults", closes = "</div></div>")
        assertTrue(html.contains("role=\""), "Status sets role")
    }

    @Test
    fun status_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatus(
                id = htmlId("x-cov-id"),
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        assertRendered(html, "status zz-extra", "Status all flags", closes = "</div></div>")
        assertCommonFlags(html, "Status")
        assertTrue(html.contains("role=\""), "Status sets role")
    }

    @Test
    fun status_color_neutral() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatus(
                color = StatusColor.Neutral,
                content = { },
            )
        }
        assertRendered(html, "status status-neutral", "Status color Neutral")
    }

    @Test
    fun status_color_primary() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatus(
                color = StatusColor.Primary,
                content = { },
            )
        }
        assertRendered(html, "status status-primary", "Status color Primary")
    }

    @Test
    fun status_color_secondary() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatus(
                color = StatusColor.Secondary,
                content = { },
            )
        }
        assertRendered(html, "status status-secondary", "Status color Secondary")
    }

    @Test
    fun status_color_accent() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatus(
                color = StatusColor.Accent,
                content = { },
            )
        }
        assertRendered(html, "status status-accent", "Status color Accent")
    }

    @Test
    fun status_color_info() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatus(
                color = StatusColor.Info,
                content = { },
            )
        }
        assertRendered(html, "status status-info", "Status color Info")
    }

    @Test
    fun status_color_success() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatus(
                color = StatusColor.Success,
                content = { },
            )
        }
        assertRendered(html, "status status-success", "Status color Success")
    }

    @Test
    fun status_color_warning() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatus(
                color = StatusColor.Warning,
                content = { },
            )
        }
        assertRendered(html, "status status-warning", "Status color Warning")
    }

    @Test
    fun status_color_error() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatus(
                color = StatusColor.Error,
                content = { },
            )
        }
        assertRendered(html, "status status-error", "Status color Error")
    }

    @Test
    fun status_size_xs() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatus(
                size = StatusSize.Xs,
                content = { },
            )
        }
        assertRendered(html, "status status-xs", "Status size Xs")
    }

    @Test
    fun status_size_sm() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatus(
                size = StatusSize.Sm,
                content = { },
            )
        }
        assertRendered(html, "status status-sm", "Status size Sm")
    }

    @Test
    fun status_size_md() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatus(
                size = StatusSize.Md,
                content = { },
            )
        }
        assertRendered(html, "status status-md", "Status size Md")
    }

    @Test
    fun status_size_lg() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatus(
                size = StatusSize.Lg,
                content = { },
            )
        }
        assertRendered(html, "status status-lg", "Status size Lg")
    }

    @Test
    fun status_size_xl() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatus(
                size = StatusSize.Xl,
                content = { },
            )
        }
        assertRendered(html, "status status-xl", "Status size Xl")
    }
}
