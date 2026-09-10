package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class ProgressCoverageTest {

    private fun assertRendered(html: String, classes: String, label: String, closes: String = "") {
        assertEquals(
            classes,
            html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" "),
            label,
        )
        if (closes.isNotEmpty()) assertTrue(html.endsWith(closes), "$label closes")
    }

    @Test
    fun progress_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyProgress(
                content = { },
            )
        }
        assertRendered(html, "progress", "Progress defaults", closes = "</progress></div>")
    }

    @Test
    fun progress_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyProgress(
                id = htmlId("x-cov-id"),
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        assertRendered(html, "progress zz-extra", "Progress all flags", closes = "</progress></div>")
        assertTrue(html.contains("id=\"x-cov-id\""), "Progress id")
        assertTrue(html.contains("data-attrs=\"yes\""), "Progress attrs")
        assertTrue(html.contains("data-content=\"yes\""), "Progress content")
    }

    @Test
    fun progress_variant_neutral() {
        val html = createHTML(prettyPrint = false).div {
            daisyProgress(
                variant = ProgressVariant.Neutral,
                content = { },
            )
        }
        assertRendered(html, "progress progress-neutral", "Progress variant Neutral")
    }

    @Test
    fun progress_variant_primary() {
        val html = createHTML(prettyPrint = false).div {
            daisyProgress(
                variant = ProgressVariant.Primary,
                content = { },
            )
        }
        assertRendered(html, "progress progress-primary", "Progress variant Primary")
    }

    @Test
    fun progress_variant_secondary() {
        val html = createHTML(prettyPrint = false).div {
            daisyProgress(
                variant = ProgressVariant.Secondary,
                content = { },
            )
        }
        assertRendered(html, "progress progress-secondary", "Progress variant Secondary")
    }

    @Test
    fun progress_variant_accent() {
        val html = createHTML(prettyPrint = false).div {
            daisyProgress(
                variant = ProgressVariant.Accent,
                content = { },
            )
        }
        assertRendered(html, "progress progress-accent", "Progress variant Accent")
    }

    @Test
    fun progress_variant_info() {
        val html = createHTML(prettyPrint = false).div {
            daisyProgress(
                variant = ProgressVariant.Info,
                content = { },
            )
        }
        assertRendered(html, "progress progress-info", "Progress variant Info")
    }

    @Test
    fun progress_variant_success() {
        val html = createHTML(prettyPrint = false).div {
            daisyProgress(
                variant = ProgressVariant.Success,
                content = { },
            )
        }
        assertRendered(html, "progress progress-success", "Progress variant Success")
    }

    @Test
    fun progress_variant_warning() {
        val html = createHTML(prettyPrint = false).div {
            daisyProgress(
                variant = ProgressVariant.Warning,
                content = { },
            )
        }
        assertRendered(html, "progress progress-warning", "Progress variant Warning")
    }

    @Test
    fun progress_variant_error() {
        val html = createHTML(prettyPrint = false).div {
            daisyProgress(
                variant = ProgressVariant.Error,
                content = { },
            )
        }
        assertRendered(html, "progress progress-error", "Progress variant Error")
    }
}
