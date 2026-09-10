package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class FileInputCoverageTest {

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
    fun fileInput_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyFileInput(
                content = { },
            )
        }
        assertRendered(html, "file-input", "FileInput defaults")
    }

    @Test
    fun fileInput_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyFileInput(
                id = htmlId("x-cov-id"),
                ghost = true,
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        assertRendered(html, "file-input file-input-ghost zz-extra", "FileInput all flags")
        assertCommonFlags(html, "FileInput")
    }

    @Test
    fun fileInput_variant_neutral() {
        val html = createHTML(prettyPrint = false).div {
            daisyFileInput(
                variant = FileInputVariant.Neutral,
                content = { },
            )
        }
        assertRendered(html, "file-input file-input-neutral", "FileInput variant Neutral")
    }

    @Test
    fun fileInput_variant_primary() {
        val html = createHTML(prettyPrint = false).div {
            daisyFileInput(
                variant = FileInputVariant.Primary,
                content = { },
            )
        }
        assertRendered(html, "file-input file-input-primary", "FileInput variant Primary")
    }

    @Test
    fun fileInput_variant_secondary() {
        val html = createHTML(prettyPrint = false).div {
            daisyFileInput(
                variant = FileInputVariant.Secondary,
                content = { },
            )
        }
        assertRendered(html, "file-input file-input-secondary", "FileInput variant Secondary")
    }

    @Test
    fun fileInput_variant_accent() {
        val html = createHTML(prettyPrint = false).div {
            daisyFileInput(
                variant = FileInputVariant.Accent,
                content = { },
            )
        }
        assertRendered(html, "file-input file-input-accent", "FileInput variant Accent")
    }

    @Test
    fun fileInput_variant_info() {
        val html = createHTML(prettyPrint = false).div {
            daisyFileInput(
                variant = FileInputVariant.Info,
                content = { },
            )
        }
        assertRendered(html, "file-input file-input-info", "FileInput variant Info")
    }

    @Test
    fun fileInput_variant_success() {
        val html = createHTML(prettyPrint = false).div {
            daisyFileInput(
                variant = FileInputVariant.Success,
                content = { },
            )
        }
        assertRendered(html, "file-input file-input-success", "FileInput variant Success")
    }

    @Test
    fun fileInput_variant_warning() {
        val html = createHTML(prettyPrint = false).div {
            daisyFileInput(
                variant = FileInputVariant.Warning,
                content = { },
            )
        }
        assertRendered(html, "file-input file-input-warning", "FileInput variant Warning")
    }

    @Test
    fun fileInput_variant_error() {
        val html = createHTML(prettyPrint = false).div {
            daisyFileInput(
                variant = FileInputVariant.Error,
                content = { },
            )
        }
        assertRendered(html, "file-input file-input-error", "FileInput variant Error")
    }

    @Test
    fun fileInput_size_xs() {
        val html = createHTML(prettyPrint = false).div {
            daisyFileInput(
                size = FileInputSize.Xs,
                content = { },
            )
        }
        assertRendered(html, "file-input file-input-xs", "FileInput size Xs")
    }

    @Test
    fun fileInput_size_sm() {
        val html = createHTML(prettyPrint = false).div {
            daisyFileInput(
                size = FileInputSize.Sm,
                content = { },
            )
        }
        assertRendered(html, "file-input file-input-sm", "FileInput size Sm")
    }

    @Test
    fun fileInput_size_md() {
        val html = createHTML(prettyPrint = false).div {
            daisyFileInput(
                size = FileInputSize.Md,
                content = { },
            )
        }
        assertRendered(html, "file-input file-input-md", "FileInput size Md")
    }

    @Test
    fun fileInput_size_lg() {
        val html = createHTML(prettyPrint = false).div {
            daisyFileInput(
                size = FileInputSize.Lg,
                content = { },
            )
        }
        assertRendered(html, "file-input file-input-lg", "FileInput size Lg")
    }

    @Test
    fun fileInput_size_xl() {
        val html = createHTML(prettyPrint = false).div {
            daisyFileInput(
                size = FileInputSize.Xl,
                content = { },
            )
        }
        assertRendered(html, "file-input file-input-xl", "FileInput size Xl")
    }
}
