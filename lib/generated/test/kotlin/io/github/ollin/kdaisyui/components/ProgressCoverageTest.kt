package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class ProgressCoverageTest {

    @Test
    fun progress_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyProgress(
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("progress", actualClasses, "Progress defaults")
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
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("progress zz-extra", actualClasses, "Progress all flags")
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
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("progress progress-neutral", actualClasses, "Progress variant Neutral")
    }

    @Test
    fun progress_variant_primary() {
        val html = createHTML(prettyPrint = false).div {
            daisyProgress(
                variant = ProgressVariant.Primary,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("progress progress-primary", actualClasses, "Progress variant Primary")
    }

    @Test
    fun progress_variant_secondary() {
        val html = createHTML(prettyPrint = false).div {
            daisyProgress(
                variant = ProgressVariant.Secondary,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("progress progress-secondary", actualClasses, "Progress variant Secondary")
    }

    @Test
    fun progress_variant_accent() {
        val html = createHTML(prettyPrint = false).div {
            daisyProgress(
                variant = ProgressVariant.Accent,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("progress progress-accent", actualClasses, "Progress variant Accent")
    }

    @Test
    fun progress_variant_info() {
        val html = createHTML(prettyPrint = false).div {
            daisyProgress(
                variant = ProgressVariant.Info,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("progress progress-info", actualClasses, "Progress variant Info")
    }

    @Test
    fun progress_variant_success() {
        val html = createHTML(prettyPrint = false).div {
            daisyProgress(
                variant = ProgressVariant.Success,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("progress progress-success", actualClasses, "Progress variant Success")
    }

    @Test
    fun progress_variant_warning() {
        val html = createHTML(prettyPrint = false).div {
            daisyProgress(
                variant = ProgressVariant.Warning,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("progress progress-warning", actualClasses, "Progress variant Warning")
    }

    @Test
    fun progress_variant_error() {
        val html = createHTML(prettyPrint = false).div {
            daisyProgress(
                variant = ProgressVariant.Error,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("progress progress-error", actualClasses, "Progress variant Error")
    }
}
