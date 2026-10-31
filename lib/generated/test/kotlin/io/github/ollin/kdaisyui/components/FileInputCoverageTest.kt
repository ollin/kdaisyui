package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class FileInputCoverageTest {

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
    fun fileInput_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyFileInput()
        }
        assertRendered(html, Expected("FileInput defaults", "file-input"))
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
        val expected = Expected("FileInput all flags", "file-input file-input-ghost zz-extra", content = false)
        assertRendered(html, expected)
        assertCommonFlags(html, expected)
    }

    @Test
    fun fileInput_color_neutral() {
        val html = createHTML(prettyPrint = false).div {
            daisyFileInput(
                color = FileInputColor.Neutral,
            )
        }
        assertRendered(html, Expected("FileInput color Neutral", "file-input file-input-neutral"))
    }

    @Test
    fun fileInput_color_primary() {
        val html = createHTML(prettyPrint = false).div {
            daisyFileInput(
                color = FileInputColor.Primary,
            )
        }
        assertRendered(html, Expected("FileInput color Primary", "file-input file-input-primary"))
    }

    @Test
    fun fileInput_color_secondary() {
        val html = createHTML(prettyPrint = false).div {
            daisyFileInput(
                color = FileInputColor.Secondary,
            )
        }
        assertRendered(html, Expected("FileInput color Secondary", "file-input file-input-secondary"))
    }

    @Test
    fun fileInput_color_accent() {
        val html = createHTML(prettyPrint = false).div {
            daisyFileInput(
                color = FileInputColor.Accent,
            )
        }
        assertRendered(html, Expected("FileInput color Accent", "file-input file-input-accent"))
    }

    @Test
    fun fileInput_color_info() {
        val html = createHTML(prettyPrint = false).div {
            daisyFileInput(
                color = FileInputColor.Info,
            )
        }
        assertRendered(html, Expected("FileInput color Info", "file-input file-input-info"))
    }

    @Test
    fun fileInput_color_success() {
        val html = createHTML(prettyPrint = false).div {
            daisyFileInput(
                color = FileInputColor.Success,
            )
        }
        assertRendered(html, Expected("FileInput color Success", "file-input file-input-success"))
    }

    @Test
    fun fileInput_color_warning() {
        val html = createHTML(prettyPrint = false).div {
            daisyFileInput(
                color = FileInputColor.Warning,
            )
        }
        assertRendered(html, Expected("FileInput color Warning", "file-input file-input-warning"))
    }

    @Test
    fun fileInput_color_error() {
        val html = createHTML(prettyPrint = false).div {
            daisyFileInput(
                color = FileInputColor.Error,
            )
        }
        assertRendered(html, Expected("FileInput color Error", "file-input file-input-error"))
    }

    @Test
    fun fileInput_size_xs() {
        val html = createHTML(prettyPrint = false).div {
            daisyFileInput(
                size = FileInputSize.Xs,
            )
        }
        assertRendered(html, Expected("FileInput size Xs", "file-input file-input-xs"))
    }

    @Test
    fun fileInput_size_sm() {
        val html = createHTML(prettyPrint = false).div {
            daisyFileInput(
                size = FileInputSize.Sm,
            )
        }
        assertRendered(html, Expected("FileInput size Sm", "file-input file-input-sm"))
    }

    @Test
    fun fileInput_size_md() {
        val html = createHTML(prettyPrint = false).div {
            daisyFileInput(
                size = FileInputSize.Md,
            )
        }
        assertRendered(html, Expected("FileInput size Md", "file-input file-input-md"))
    }

    @Test
    fun fileInput_size_lg() {
        val html = createHTML(prettyPrint = false).div {
            daisyFileInput(
                size = FileInputSize.Lg,
            )
        }
        assertRendered(html, Expected("FileInput size Lg", "file-input file-input-lg"))
    }

    @Test
    fun fileInput_size_xl() {
        val html = createHTML(prettyPrint = false).div {
            daisyFileInput(
                size = FileInputSize.Xl,
            )
        }
        assertRendered(html, Expected("FileInput size Xl", "file-input file-input-xl"))
    }

    @Test
    fun fileInputClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyFileInputClasses()
        }
        assertRendered(html, Expected("FileInputClasses defaults", "file-input"))
    }

    @Test
    fun fileInputClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyFileInputClasses(
                ghost = true,
            )
        }
        val expected = Expected("FileInputClasses all flags", "file-input file-input-ghost", content = false)
        assertRendered(html, expected)
    }

    @Test
    fun fileInputClasses_color_neutral() {
        val html = createHTML(prettyPrint = false).div {
            daisyFileInputClasses(
                color = FileInputColor.Neutral,
            )
        }
        assertRendered(html, Expected("FileInputClasses color Neutral", "file-input file-input-neutral"))
    }

    @Test
    fun fileInputClasses_color_primary() {
        val html = createHTML(prettyPrint = false).div {
            daisyFileInputClasses(
                color = FileInputColor.Primary,
            )
        }
        assertRendered(html, Expected("FileInputClasses color Primary", "file-input file-input-primary"))
    }

    @Test
    fun fileInputClasses_color_secondary() {
        val html = createHTML(prettyPrint = false).div {
            daisyFileInputClasses(
                color = FileInputColor.Secondary,
            )
        }
        assertRendered(html, Expected("FileInputClasses color Secondary", "file-input file-input-secondary"))
    }

    @Test
    fun fileInputClasses_color_accent() {
        val html = createHTML(prettyPrint = false).div {
            daisyFileInputClasses(
                color = FileInputColor.Accent,
            )
        }
        assertRendered(html, Expected("FileInputClasses color Accent", "file-input file-input-accent"))
    }

    @Test
    fun fileInputClasses_color_info() {
        val html = createHTML(prettyPrint = false).div {
            daisyFileInputClasses(
                color = FileInputColor.Info,
            )
        }
        assertRendered(html, Expected("FileInputClasses color Info", "file-input file-input-info"))
    }

    @Test
    fun fileInputClasses_color_success() {
        val html = createHTML(prettyPrint = false).div {
            daisyFileInputClasses(
                color = FileInputColor.Success,
            )
        }
        assertRendered(html, Expected("FileInputClasses color Success", "file-input file-input-success"))
    }

    @Test
    fun fileInputClasses_color_warning() {
        val html = createHTML(prettyPrint = false).div {
            daisyFileInputClasses(
                color = FileInputColor.Warning,
            )
        }
        assertRendered(html, Expected("FileInputClasses color Warning", "file-input file-input-warning"))
    }

    @Test
    fun fileInputClasses_color_error() {
        val html = createHTML(prettyPrint = false).div {
            daisyFileInputClasses(
                color = FileInputColor.Error,
            )
        }
        assertRendered(html, Expected("FileInputClasses color Error", "file-input file-input-error"))
    }

    @Test
    fun fileInputClasses_size_xs() {
        val html = createHTML(prettyPrint = false).div {
            daisyFileInputClasses(
                size = FileInputSize.Xs,
            )
        }
        assertRendered(html, Expected("FileInputClasses size Xs", "file-input file-input-xs"))
    }

    @Test
    fun fileInputClasses_size_sm() {
        val html = createHTML(prettyPrint = false).div {
            daisyFileInputClasses(
                size = FileInputSize.Sm,
            )
        }
        assertRendered(html, Expected("FileInputClasses size Sm", "file-input file-input-sm"))
    }

    @Test
    fun fileInputClasses_size_md() {
        val html = createHTML(prettyPrint = false).div {
            daisyFileInputClasses(
                size = FileInputSize.Md,
            )
        }
        assertRendered(html, Expected("FileInputClasses size Md", "file-input file-input-md"))
    }

    @Test
    fun fileInputClasses_size_lg() {
        val html = createHTML(prettyPrint = false).div {
            daisyFileInputClasses(
                size = FileInputSize.Lg,
            )
        }
        assertRendered(html, Expected("FileInputClasses size Lg", "file-input file-input-lg"))
    }

    @Test
    fun fileInputClasses_size_xl() {
        val html = createHTML(prettyPrint = false).div {
            daisyFileInputClasses(
                size = FileInputSize.Xl,
            )
        }
        assertRendered(html, Expected("FileInputClasses size Xl", "file-input file-input-xl"))
    }
}
