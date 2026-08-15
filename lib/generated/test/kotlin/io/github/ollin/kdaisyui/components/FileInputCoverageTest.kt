package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class FileInputCoverageTest {

    @Test
    fun fileInput_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyFileInput(
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("file-input", actualClasses, "FileInput defaults")
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
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("file-input file-input-ghost zz-extra", actualClasses, "FileInput all flags")
        assertTrue(html.contains("id=\"x-cov-id\""), "FileInput id")
        assertTrue(html.contains("data-attrs=\"yes\""), "FileInput attrs")
        assertTrue(html.contains("data-content=\"yes\""), "FileInput content")
    }

    @Test
    fun fileInput_variant_neutral() {
        val html = createHTML(prettyPrint = false).div {
            daisyFileInput(
                variant = FileInputVariant.Neutral,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("file-input file-input-neutral", actualClasses, "FileInput variant Neutral")
    }

    @Test
    fun fileInput_variant_primary() {
        val html = createHTML(prettyPrint = false).div {
            daisyFileInput(
                variant = FileInputVariant.Primary,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("file-input file-input-primary", actualClasses, "FileInput variant Primary")
    }

    @Test
    fun fileInput_variant_secondary() {
        val html = createHTML(prettyPrint = false).div {
            daisyFileInput(
                variant = FileInputVariant.Secondary,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("file-input file-input-secondary", actualClasses, "FileInput variant Secondary")
    }

    @Test
    fun fileInput_variant_accent() {
        val html = createHTML(prettyPrint = false).div {
            daisyFileInput(
                variant = FileInputVariant.Accent,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("file-input file-input-accent", actualClasses, "FileInput variant Accent")
    }

    @Test
    fun fileInput_variant_info() {
        val html = createHTML(prettyPrint = false).div {
            daisyFileInput(
                variant = FileInputVariant.Info,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("file-input file-input-info", actualClasses, "FileInput variant Info")
    }

    @Test
    fun fileInput_variant_success() {
        val html = createHTML(prettyPrint = false).div {
            daisyFileInput(
                variant = FileInputVariant.Success,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("file-input file-input-success", actualClasses, "FileInput variant Success")
    }

    @Test
    fun fileInput_variant_warning() {
        val html = createHTML(prettyPrint = false).div {
            daisyFileInput(
                variant = FileInputVariant.Warning,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("file-input file-input-warning", actualClasses, "FileInput variant Warning")
    }

    @Test
    fun fileInput_variant_error() {
        val html = createHTML(prettyPrint = false).div {
            daisyFileInput(
                variant = FileInputVariant.Error,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("file-input file-input-error", actualClasses, "FileInput variant Error")
    }

    @Test
    fun fileInput_size_xs() {
        val html = createHTML(prettyPrint = false).div {
            daisyFileInput(
                size = FileInputSize.Xs,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("file-input file-input-xs", actualClasses, "FileInput size Xs")
    }

    @Test
    fun fileInput_size_sm() {
        val html = createHTML(prettyPrint = false).div {
            daisyFileInput(
                size = FileInputSize.Sm,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("file-input file-input-sm", actualClasses, "FileInput size Sm")
    }

    @Test
    fun fileInput_size_md() {
        val html = createHTML(prettyPrint = false).div {
            daisyFileInput(
                size = FileInputSize.Md,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("file-input file-input-md", actualClasses, "FileInput size Md")
    }

    @Test
    fun fileInput_size_lg() {
        val html = createHTML(prettyPrint = false).div {
            daisyFileInput(
                size = FileInputSize.Lg,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("file-input file-input-lg", actualClasses, "FileInput size Lg")
    }

    @Test
    fun fileInput_size_xl() {
        val html = createHTML(prettyPrint = false).div {
            daisyFileInput(
                size = FileInputSize.Xl,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("file-input file-input-xl", actualClasses, "FileInput size Xl")
    }
}
