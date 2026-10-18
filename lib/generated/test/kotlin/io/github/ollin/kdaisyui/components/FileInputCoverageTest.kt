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
            daisyFileInput()
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
            )
        }
        assertRendered(html, "file-input file-input-ghost zz-extra", "FileInput all flags")
        assertCommonFlags(html, "FileInput", content = false)
    }

    @Test
    fun fileInput_color_neutral() {
        val html = createHTML(prettyPrint = false).div {
            daisyFileInput(
                color = FileInputColor.Neutral,
            )
        }
        assertRendered(html, "file-input file-input-neutral", "FileInput color Neutral")
    }

    @Test
    fun fileInput_color_primary() {
        val html = createHTML(prettyPrint = false).div {
            daisyFileInput(
                color = FileInputColor.Primary,
            )
        }
        assertRendered(html, "file-input file-input-primary", "FileInput color Primary")
    }

    @Test
    fun fileInput_color_secondary() {
        val html = createHTML(prettyPrint = false).div {
            daisyFileInput(
                color = FileInputColor.Secondary,
            )
        }
        assertRendered(html, "file-input file-input-secondary", "FileInput color Secondary")
    }

    @Test
    fun fileInput_color_accent() {
        val html = createHTML(prettyPrint = false).div {
            daisyFileInput(
                color = FileInputColor.Accent,
            )
        }
        assertRendered(html, "file-input file-input-accent", "FileInput color Accent")
    }

    @Test
    fun fileInput_color_info() {
        val html = createHTML(prettyPrint = false).div {
            daisyFileInput(
                color = FileInputColor.Info,
            )
        }
        assertRendered(html, "file-input file-input-info", "FileInput color Info")
    }

    @Test
    fun fileInput_color_success() {
        val html = createHTML(prettyPrint = false).div {
            daisyFileInput(
                color = FileInputColor.Success,
            )
        }
        assertRendered(html, "file-input file-input-success", "FileInput color Success")
    }

    @Test
    fun fileInput_color_warning() {
        val html = createHTML(prettyPrint = false).div {
            daisyFileInput(
                color = FileInputColor.Warning,
            )
        }
        assertRendered(html, "file-input file-input-warning", "FileInput color Warning")
    }

    @Test
    fun fileInput_color_error() {
        val html = createHTML(prettyPrint = false).div {
            daisyFileInput(
                color = FileInputColor.Error,
            )
        }
        assertRendered(html, "file-input file-input-error", "FileInput color Error")
    }

    @Test
    fun fileInput_size_xs() {
        val html = createHTML(prettyPrint = false).div {
            daisyFileInput(
                size = FileInputSize.Xs,
            )
        }
        assertRendered(html, "file-input file-input-xs", "FileInput size Xs")
    }

    @Test
    fun fileInput_size_sm() {
        val html = createHTML(prettyPrint = false).div {
            daisyFileInput(
                size = FileInputSize.Sm,
            )
        }
        assertRendered(html, "file-input file-input-sm", "FileInput size Sm")
    }

    @Test
    fun fileInput_size_md() {
        val html = createHTML(prettyPrint = false).div {
            daisyFileInput(
                size = FileInputSize.Md,
            )
        }
        assertRendered(html, "file-input file-input-md", "FileInput size Md")
    }

    @Test
    fun fileInput_size_lg() {
        val html = createHTML(prettyPrint = false).div {
            daisyFileInput(
                size = FileInputSize.Lg,
            )
        }
        assertRendered(html, "file-input file-input-lg", "FileInput size Lg")
    }

    @Test
    fun fileInput_size_xl() {
        val html = createHTML(prettyPrint = false).div {
            daisyFileInput(
                size = FileInputSize.Xl,
            )
        }
        assertRendered(html, "file-input file-input-xl", "FileInput size Xl")
    }
}
