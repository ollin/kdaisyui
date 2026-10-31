package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class ToggleCoverageTest {

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
    fun toggle_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyToggle()
        }
        assertRendered(html, Expected("Toggle defaults", "toggle"))
        assertTrue(html.contains("type=\""), "Toggle sets type")
    }

    @Test
    fun toggle_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyToggle(
                id = htmlId("x-cov-id"),
                checked = true,
                disabled = true,
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
            )
        }
        val expected = Expected("Toggle all flags", "toggle zz-extra", content = false)
        assertRendered(html, expected)
        assertCommonFlags(html, expected)
        assertTrue(html.contains("type=\""), "Toggle sets type")
        assertTrue(html.contains("checked=\""), "Toggle sets checked")
        assertTrue(html.contains("disabled=\""), "Toggle sets disabled")
    }

    @Test
    fun toggle_color_primary() {
        val html = createHTML(prettyPrint = false).div {
            daisyToggle(
                color = ToggleColor.Primary,
            )
        }
        assertRendered(html, Expected("Toggle color Primary", "toggle toggle-primary"))
    }

    @Test
    fun toggle_color_secondary() {
        val html = createHTML(prettyPrint = false).div {
            daisyToggle(
                color = ToggleColor.Secondary,
            )
        }
        assertRendered(html, Expected("Toggle color Secondary", "toggle toggle-secondary"))
    }

    @Test
    fun toggle_color_accent() {
        val html = createHTML(prettyPrint = false).div {
            daisyToggle(
                color = ToggleColor.Accent,
            )
        }
        assertRendered(html, Expected("Toggle color Accent", "toggle toggle-accent"))
    }

    @Test
    fun toggle_color_neutral() {
        val html = createHTML(prettyPrint = false).div {
            daisyToggle(
                color = ToggleColor.Neutral,
            )
        }
        assertRendered(html, Expected("Toggle color Neutral", "toggle toggle-neutral"))
    }

    @Test
    fun toggle_color_success() {
        val html = createHTML(prettyPrint = false).div {
            daisyToggle(
                color = ToggleColor.Success,
            )
        }
        assertRendered(html, Expected("Toggle color Success", "toggle toggle-success"))
    }

    @Test
    fun toggle_color_warning() {
        val html = createHTML(prettyPrint = false).div {
            daisyToggle(
                color = ToggleColor.Warning,
            )
        }
        assertRendered(html, Expected("Toggle color Warning", "toggle toggle-warning"))
    }

    @Test
    fun toggle_color_info() {
        val html = createHTML(prettyPrint = false).div {
            daisyToggle(
                color = ToggleColor.Info,
            )
        }
        assertRendered(html, Expected("Toggle color Info", "toggle toggle-info"))
    }

    @Test
    fun toggle_color_error() {
        val html = createHTML(prettyPrint = false).div {
            daisyToggle(
                color = ToggleColor.Error,
            )
        }
        assertRendered(html, Expected("Toggle color Error", "toggle toggle-error"))
    }

    @Test
    fun toggle_size_xs() {
        val html = createHTML(prettyPrint = false).div {
            daisyToggle(
                size = ToggleSize.Xs,
            )
        }
        assertRendered(html, Expected("Toggle size Xs", "toggle toggle-xs"))
    }

    @Test
    fun toggle_size_sm() {
        val html = createHTML(prettyPrint = false).div {
            daisyToggle(
                size = ToggleSize.Sm,
            )
        }
        assertRendered(html, Expected("Toggle size Sm", "toggle toggle-sm"))
    }

    @Test
    fun toggle_size_md() {
        val html = createHTML(prettyPrint = false).div {
            daisyToggle(
                size = ToggleSize.Md,
            )
        }
        assertRendered(html, Expected("Toggle size Md", "toggle toggle-md"))
    }

    @Test
    fun toggle_size_lg() {
        val html = createHTML(prettyPrint = false).div {
            daisyToggle(
                size = ToggleSize.Lg,
            )
        }
        assertRendered(html, Expected("Toggle size Lg", "toggle toggle-lg"))
    }

    @Test
    fun toggle_size_xl() {
        val html = createHTML(prettyPrint = false).div {
            daisyToggle(
                size = ToggleSize.Xl,
            )
        }
        assertRendered(html, Expected("Toggle size Xl", "toggle toggle-xl"))
    }

    @Test
    fun toggleClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyToggleClasses()
        }
        assertRendered(html, Expected("ToggleClasses defaults", "toggle"))
    }

    @Test
    fun toggleClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyToggleClasses()
        }
        val expected = Expected("ToggleClasses all flags", "toggle", content = false)
        assertRendered(html, expected)
    }

    @Test
    fun toggleClasses_color_primary() {
        val html = createHTML(prettyPrint = false).div {
            daisyToggleClasses(
                color = ToggleColor.Primary,
            )
        }
        assertRendered(html, Expected("ToggleClasses color Primary", "toggle toggle-primary"))
    }

    @Test
    fun toggleClasses_color_secondary() {
        val html = createHTML(prettyPrint = false).div {
            daisyToggleClasses(
                color = ToggleColor.Secondary,
            )
        }
        assertRendered(html, Expected("ToggleClasses color Secondary", "toggle toggle-secondary"))
    }

    @Test
    fun toggleClasses_color_accent() {
        val html = createHTML(prettyPrint = false).div {
            daisyToggleClasses(
                color = ToggleColor.Accent,
            )
        }
        assertRendered(html, Expected("ToggleClasses color Accent", "toggle toggle-accent"))
    }

    @Test
    fun toggleClasses_color_neutral() {
        val html = createHTML(prettyPrint = false).div {
            daisyToggleClasses(
                color = ToggleColor.Neutral,
            )
        }
        assertRendered(html, Expected("ToggleClasses color Neutral", "toggle toggle-neutral"))
    }

    @Test
    fun toggleClasses_color_success() {
        val html = createHTML(prettyPrint = false).div {
            daisyToggleClasses(
                color = ToggleColor.Success,
            )
        }
        assertRendered(html, Expected("ToggleClasses color Success", "toggle toggle-success"))
    }

    @Test
    fun toggleClasses_color_warning() {
        val html = createHTML(prettyPrint = false).div {
            daisyToggleClasses(
                color = ToggleColor.Warning,
            )
        }
        assertRendered(html, Expected("ToggleClasses color Warning", "toggle toggle-warning"))
    }

    @Test
    fun toggleClasses_color_info() {
        val html = createHTML(prettyPrint = false).div {
            daisyToggleClasses(
                color = ToggleColor.Info,
            )
        }
        assertRendered(html, Expected("ToggleClasses color Info", "toggle toggle-info"))
    }

    @Test
    fun toggleClasses_color_error() {
        val html = createHTML(prettyPrint = false).div {
            daisyToggleClasses(
                color = ToggleColor.Error,
            )
        }
        assertRendered(html, Expected("ToggleClasses color Error", "toggle toggle-error"))
    }

    @Test
    fun toggleClasses_size_xs() {
        val html = createHTML(prettyPrint = false).div {
            daisyToggleClasses(
                size = ToggleSize.Xs,
            )
        }
        assertRendered(html, Expected("ToggleClasses size Xs", "toggle toggle-xs"))
    }

    @Test
    fun toggleClasses_size_sm() {
        val html = createHTML(prettyPrint = false).div {
            daisyToggleClasses(
                size = ToggleSize.Sm,
            )
        }
        assertRendered(html, Expected("ToggleClasses size Sm", "toggle toggle-sm"))
    }

    @Test
    fun toggleClasses_size_md() {
        val html = createHTML(prettyPrint = false).div {
            daisyToggleClasses(
                size = ToggleSize.Md,
            )
        }
        assertRendered(html, Expected("ToggleClasses size Md", "toggle toggle-md"))
    }

    @Test
    fun toggleClasses_size_lg() {
        val html = createHTML(prettyPrint = false).div {
            daisyToggleClasses(
                size = ToggleSize.Lg,
            )
        }
        assertRendered(html, Expected("ToggleClasses size Lg", "toggle toggle-lg"))
    }

    @Test
    fun toggleClasses_size_xl() {
        val html = createHTML(prettyPrint = false).div {
            daisyToggleClasses(
                size = ToggleSize.Xl,
            )
        }
        assertRendered(html, Expected("ToggleClasses size Xl", "toggle toggle-xl"))
    }
}
