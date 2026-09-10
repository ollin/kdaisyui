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

    @Test
    fun status_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatus(
                content = { },
            )
        }
        assertRendered(html, "status", "Status defaults", closes = "</span></div>")
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
        assertRendered(html, "status zz-extra", "Status all flags", closes = "</span></div>")
        assertTrue(html.contains("id=\"x-cov-id\""), "Status id")
        assertTrue(html.contains("data-attrs=\"yes\""), "Status attrs")
        assertTrue(html.contains("data-content=\"yes\""), "Status content")
        assertTrue(html.contains("role=\""), "Status sets role")
    }

    @Test
    fun status_variant_neutral() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatus(
                variant = StatusVariant.Neutral,
                content = { },
            )
        }
        assertRendered(html, "status status-neutral", "Status variant Neutral")
    }

    @Test
    fun status_variant_primary() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatus(
                variant = StatusVariant.Primary,
                content = { },
            )
        }
        assertRendered(html, "status status-primary", "Status variant Primary")
    }

    @Test
    fun status_variant_secondary() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatus(
                variant = StatusVariant.Secondary,
                content = { },
            )
        }
        assertRendered(html, "status status-secondary", "Status variant Secondary")
    }

    @Test
    fun status_variant_accent() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatus(
                variant = StatusVariant.Accent,
                content = { },
            )
        }
        assertRendered(html, "status status-accent", "Status variant Accent")
    }

    @Test
    fun status_variant_info() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatus(
                variant = StatusVariant.Info,
                content = { },
            )
        }
        assertRendered(html, "status status-info", "Status variant Info")
    }

    @Test
    fun status_variant_success() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatus(
                variant = StatusVariant.Success,
                content = { },
            )
        }
        assertRendered(html, "status status-success", "Status variant Success")
    }

    @Test
    fun status_variant_warning() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatus(
                variant = StatusVariant.Warning,
                content = { },
            )
        }
        assertRendered(html, "status status-warning", "Status variant Warning")
    }

    @Test
    fun status_variant_error() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatus(
                variant = StatusVariant.Error,
                content = { },
            )
        }
        assertRendered(html, "status status-error", "Status variant Error")
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
