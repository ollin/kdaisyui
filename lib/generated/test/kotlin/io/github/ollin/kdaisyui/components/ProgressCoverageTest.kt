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

    private fun assertCommonFlags(html: String, label: String, content: Boolean = true) {
        assertTrue(html.contains("id=\"x-cov-id\""), "$label id")
        assertTrue(html.contains("data-attrs=\"yes\""), "$label attrs")
        if (content) assertTrue(html.contains("data-content=\"yes\""), "$label content")
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
        assertCommonFlags(html, "Progress")
    }

    @Test
    fun progress_color_neutral() {
        val html = createHTML(prettyPrint = false).div {
            daisyProgress(
                color = ProgressColor.Neutral,
                content = { },
            )
        }
        assertRendered(html, "progress progress-neutral", "Progress color Neutral")
    }

    @Test
    fun progress_color_primary() {
        val html = createHTML(prettyPrint = false).div {
            daisyProgress(
                color = ProgressColor.Primary,
                content = { },
            )
        }
        assertRendered(html, "progress progress-primary", "Progress color Primary")
    }

    @Test
    fun progress_color_secondary() {
        val html = createHTML(prettyPrint = false).div {
            daisyProgress(
                color = ProgressColor.Secondary,
                content = { },
            )
        }
        assertRendered(html, "progress progress-secondary", "Progress color Secondary")
    }

    @Test
    fun progress_color_accent() {
        val html = createHTML(prettyPrint = false).div {
            daisyProgress(
                color = ProgressColor.Accent,
                content = { },
            )
        }
        assertRendered(html, "progress progress-accent", "Progress color Accent")
    }

    @Test
    fun progress_color_info() {
        val html = createHTML(prettyPrint = false).div {
            daisyProgress(
                color = ProgressColor.Info,
                content = { },
            )
        }
        assertRendered(html, "progress progress-info", "Progress color Info")
    }

    @Test
    fun progress_color_success() {
        val html = createHTML(prettyPrint = false).div {
            daisyProgress(
                color = ProgressColor.Success,
                content = { },
            )
        }
        assertRendered(html, "progress progress-success", "Progress color Success")
    }

    @Test
    fun progress_color_warning() {
        val html = createHTML(prettyPrint = false).div {
            daisyProgress(
                color = ProgressColor.Warning,
                content = { },
            )
        }
        assertRendered(html, "progress progress-warning", "Progress color Warning")
    }

    @Test
    fun progress_color_error() {
        val html = createHTML(prettyPrint = false).div {
            daisyProgress(
                color = ProgressColor.Error,
                content = { },
            )
        }
        assertRendered(html, "progress progress-error", "Progress color Error")
    }

    @Test
    fun progressClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyProgressClasses()
        }
        assertRendered(html, "progress", "ProgressClasses defaults")
    }

    @Test
    fun progressClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyProgressClasses()
        }
        assertRendered(html, "progress", "ProgressClasses all flags")
    }

    @Test
    fun progressClasses_color_neutral() {
        val html = createHTML(prettyPrint = false).div {
            daisyProgressClasses(
                color = ProgressColor.Neutral,
            )
        }
        assertRendered(html, "progress progress-neutral", "ProgressClasses color Neutral")
    }

    @Test
    fun progressClasses_color_primary() {
        val html = createHTML(prettyPrint = false).div {
            daisyProgressClasses(
                color = ProgressColor.Primary,
            )
        }
        assertRendered(html, "progress progress-primary", "ProgressClasses color Primary")
    }

    @Test
    fun progressClasses_color_secondary() {
        val html = createHTML(prettyPrint = false).div {
            daisyProgressClasses(
                color = ProgressColor.Secondary,
            )
        }
        assertRendered(html, "progress progress-secondary", "ProgressClasses color Secondary")
    }

    @Test
    fun progressClasses_color_accent() {
        val html = createHTML(prettyPrint = false).div {
            daisyProgressClasses(
                color = ProgressColor.Accent,
            )
        }
        assertRendered(html, "progress progress-accent", "ProgressClasses color Accent")
    }

    @Test
    fun progressClasses_color_info() {
        val html = createHTML(prettyPrint = false).div {
            daisyProgressClasses(
                color = ProgressColor.Info,
            )
        }
        assertRendered(html, "progress progress-info", "ProgressClasses color Info")
    }

    @Test
    fun progressClasses_color_success() {
        val html = createHTML(prettyPrint = false).div {
            daisyProgressClasses(
                color = ProgressColor.Success,
            )
        }
        assertRendered(html, "progress progress-success", "ProgressClasses color Success")
    }

    @Test
    fun progressClasses_color_warning() {
        val html = createHTML(prettyPrint = false).div {
            daisyProgressClasses(
                color = ProgressColor.Warning,
            )
        }
        assertRendered(html, "progress progress-warning", "ProgressClasses color Warning")
    }

    @Test
    fun progressClasses_color_error() {
        val html = createHTML(prettyPrint = false).div {
            daisyProgressClasses(
                color = ProgressColor.Error,
            )
        }
        assertRendered(html, "progress progress-error", "ProgressClasses color Error")
    }
}
