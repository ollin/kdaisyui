package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class StatusCoverageTest {

    @Test
    fun status_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatus(
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("status", actualClasses, "Status defaults")
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
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("status zz-extra", actualClasses, "Status all flags")
        assertTrue(html.contains("id=\"x-cov-id\""), "Status id")
        assertTrue(html.contains("data-attrs=\"yes\""), "Status attrs")
        assertTrue(html.contains("data-content=\"yes\""), "Status content")
    }

    @Test
    fun status_variant_neutral() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatus(
                variant = StatusVariant.Neutral,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("status status-neutral", actualClasses, "Status variant Neutral")
    }

    @Test
    fun status_variant_primary() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatus(
                variant = StatusVariant.Primary,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("status status-primary", actualClasses, "Status variant Primary")
    }

    @Test
    fun status_variant_secondary() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatus(
                variant = StatusVariant.Secondary,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("status status-secondary", actualClasses, "Status variant Secondary")
    }

    @Test
    fun status_variant_accent() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatus(
                variant = StatusVariant.Accent,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("status status-accent", actualClasses, "Status variant Accent")
    }

    @Test
    fun status_variant_info() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatus(
                variant = StatusVariant.Info,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("status status-info", actualClasses, "Status variant Info")
    }

    @Test
    fun status_variant_success() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatus(
                variant = StatusVariant.Success,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("status status-success", actualClasses, "Status variant Success")
    }

    @Test
    fun status_variant_warning() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatus(
                variant = StatusVariant.Warning,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("status status-warning", actualClasses, "Status variant Warning")
    }

    @Test
    fun status_variant_error() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatus(
                variant = StatusVariant.Error,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("status status-error", actualClasses, "Status variant Error")
    }

    @Test
    fun status_size_xs() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatus(
                size = StatusSize.Xs,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("status status-xs", actualClasses, "Status size Xs")
    }

    @Test
    fun status_size_sm() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatus(
                size = StatusSize.Sm,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("status status-sm", actualClasses, "Status size Sm")
    }

    @Test
    fun status_size_md() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatus(
                size = StatusSize.Md,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("status status-md", actualClasses, "Status size Md")
    }

    @Test
    fun status_size_lg() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatus(
                size = StatusSize.Lg,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("status status-lg", actualClasses, "Status size Lg")
    }

    @Test
    fun status_size_xl() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatus(
                size = StatusSize.Xl,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("status status-xl", actualClasses, "Status size Xl")
    }
}
