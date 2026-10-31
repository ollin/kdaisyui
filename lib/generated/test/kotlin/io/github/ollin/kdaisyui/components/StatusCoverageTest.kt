package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class StatusCoverageTest {

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
    fun status_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatus(
                content = { },
            )
        }
        assertRendered(html, Expected("Status defaults", "status", closes = "</div></div>"))
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
        val expected = Expected("Status all flags", "status zz-extra", closes = "</div></div>")
        assertRendered(html, expected)
        assertCommonFlags(html, expected)
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
        assertRendered(html, Expected("Status color Neutral", "status status-neutral"))
    }

    @Test
    fun status_color_primary() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatus(
                color = StatusColor.Primary,
                content = { },
            )
        }
        assertRendered(html, Expected("Status color Primary", "status status-primary"))
    }

    @Test
    fun status_color_secondary() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatus(
                color = StatusColor.Secondary,
                content = { },
            )
        }
        assertRendered(html, Expected("Status color Secondary", "status status-secondary"))
    }

    @Test
    fun status_color_accent() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatus(
                color = StatusColor.Accent,
                content = { },
            )
        }
        assertRendered(html, Expected("Status color Accent", "status status-accent"))
    }

    @Test
    fun status_color_info() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatus(
                color = StatusColor.Info,
                content = { },
            )
        }
        assertRendered(html, Expected("Status color Info", "status status-info"))
    }

    @Test
    fun status_color_success() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatus(
                color = StatusColor.Success,
                content = { },
            )
        }
        assertRendered(html, Expected("Status color Success", "status status-success"))
    }

    @Test
    fun status_color_warning() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatus(
                color = StatusColor.Warning,
                content = { },
            )
        }
        assertRendered(html, Expected("Status color Warning", "status status-warning"))
    }

    @Test
    fun status_color_error() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatus(
                color = StatusColor.Error,
                content = { },
            )
        }
        assertRendered(html, Expected("Status color Error", "status status-error"))
    }

    @Test
    fun status_size_xs() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatus(
                size = StatusSize.Xs,
                content = { },
            )
        }
        assertRendered(html, Expected("Status size Xs", "status status-xs"))
    }

    @Test
    fun status_size_sm() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatus(
                size = StatusSize.Sm,
                content = { },
            )
        }
        assertRendered(html, Expected("Status size Sm", "status status-sm"))
    }

    @Test
    fun status_size_md() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatus(
                size = StatusSize.Md,
                content = { },
            )
        }
        assertRendered(html, Expected("Status size Md", "status status-md"))
    }

    @Test
    fun status_size_lg() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatus(
                size = StatusSize.Lg,
                content = { },
            )
        }
        assertRendered(html, Expected("Status size Lg", "status status-lg"))
    }

    @Test
    fun status_size_xl() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatus(
                size = StatusSize.Xl,
                content = { },
            )
        }
        assertRendered(html, Expected("Status size Xl", "status status-xl"))
    }

    @Test
    fun statusClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatusClasses()
        }
        assertRendered(html, Expected("StatusClasses defaults", "status"))
    }

    @Test
    fun statusClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatusClasses()
        }
        val expected = Expected("StatusClasses all flags", "status", content = false)
        assertRendered(html, expected)
    }

    @Test
    fun statusClasses_color_neutral() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatusClasses(
                color = StatusColor.Neutral,
            )
        }
        assertRendered(html, Expected("StatusClasses color Neutral", "status status-neutral"))
    }

    @Test
    fun statusClasses_color_primary() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatusClasses(
                color = StatusColor.Primary,
            )
        }
        assertRendered(html, Expected("StatusClasses color Primary", "status status-primary"))
    }

    @Test
    fun statusClasses_color_secondary() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatusClasses(
                color = StatusColor.Secondary,
            )
        }
        assertRendered(html, Expected("StatusClasses color Secondary", "status status-secondary"))
    }

    @Test
    fun statusClasses_color_accent() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatusClasses(
                color = StatusColor.Accent,
            )
        }
        assertRendered(html, Expected("StatusClasses color Accent", "status status-accent"))
    }

    @Test
    fun statusClasses_color_info() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatusClasses(
                color = StatusColor.Info,
            )
        }
        assertRendered(html, Expected("StatusClasses color Info", "status status-info"))
    }

    @Test
    fun statusClasses_color_success() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatusClasses(
                color = StatusColor.Success,
            )
        }
        assertRendered(html, Expected("StatusClasses color Success", "status status-success"))
    }

    @Test
    fun statusClasses_color_warning() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatusClasses(
                color = StatusColor.Warning,
            )
        }
        assertRendered(html, Expected("StatusClasses color Warning", "status status-warning"))
    }

    @Test
    fun statusClasses_color_error() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatusClasses(
                color = StatusColor.Error,
            )
        }
        assertRendered(html, Expected("StatusClasses color Error", "status status-error"))
    }

    @Test
    fun statusClasses_size_xs() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatusClasses(
                size = StatusSize.Xs,
            )
        }
        assertRendered(html, Expected("StatusClasses size Xs", "status status-xs"))
    }

    @Test
    fun statusClasses_size_sm() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatusClasses(
                size = StatusSize.Sm,
            )
        }
        assertRendered(html, Expected("StatusClasses size Sm", "status status-sm"))
    }

    @Test
    fun statusClasses_size_md() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatusClasses(
                size = StatusSize.Md,
            )
        }
        assertRendered(html, Expected("StatusClasses size Md", "status status-md"))
    }

    @Test
    fun statusClasses_size_lg() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatusClasses(
                size = StatusSize.Lg,
            )
        }
        assertRendered(html, Expected("StatusClasses size Lg", "status status-lg"))
    }

    @Test
    fun statusClasses_size_xl() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatusClasses(
                size = StatusSize.Xl,
            )
        }
        assertRendered(html, Expected("StatusClasses size Xl", "status status-xl"))
    }
}
