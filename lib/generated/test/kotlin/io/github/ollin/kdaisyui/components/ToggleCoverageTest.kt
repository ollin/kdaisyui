package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class ToggleCoverageTest {

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
    fun toggle_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyToggle()
        }
        assertRendered(html, "toggle", "Toggle defaults")
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
        assertRendered(html, "toggle zz-extra", "Toggle all flags")
        assertCommonFlags(html, "Toggle", content = false)
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
        assertRendered(html, "toggle toggle-primary", "Toggle color Primary")
    }

    @Test
    fun toggle_color_secondary() {
        val html = createHTML(prettyPrint = false).div {
            daisyToggle(
                color = ToggleColor.Secondary,
            )
        }
        assertRendered(html, "toggle toggle-secondary", "Toggle color Secondary")
    }

    @Test
    fun toggle_color_accent() {
        val html = createHTML(prettyPrint = false).div {
            daisyToggle(
                color = ToggleColor.Accent,
            )
        }
        assertRendered(html, "toggle toggle-accent", "Toggle color Accent")
    }

    @Test
    fun toggle_color_neutral() {
        val html = createHTML(prettyPrint = false).div {
            daisyToggle(
                color = ToggleColor.Neutral,
            )
        }
        assertRendered(html, "toggle toggle-neutral", "Toggle color Neutral")
    }

    @Test
    fun toggle_color_success() {
        val html = createHTML(prettyPrint = false).div {
            daisyToggle(
                color = ToggleColor.Success,
            )
        }
        assertRendered(html, "toggle toggle-success", "Toggle color Success")
    }

    @Test
    fun toggle_color_warning() {
        val html = createHTML(prettyPrint = false).div {
            daisyToggle(
                color = ToggleColor.Warning,
            )
        }
        assertRendered(html, "toggle toggle-warning", "Toggle color Warning")
    }

    @Test
    fun toggle_color_info() {
        val html = createHTML(prettyPrint = false).div {
            daisyToggle(
                color = ToggleColor.Info,
            )
        }
        assertRendered(html, "toggle toggle-info", "Toggle color Info")
    }

    @Test
    fun toggle_color_error() {
        val html = createHTML(prettyPrint = false).div {
            daisyToggle(
                color = ToggleColor.Error,
            )
        }
        assertRendered(html, "toggle toggle-error", "Toggle color Error")
    }

    @Test
    fun toggle_size_xs() {
        val html = createHTML(prettyPrint = false).div {
            daisyToggle(
                size = ToggleSize.Xs,
            )
        }
        assertRendered(html, "toggle toggle-xs", "Toggle size Xs")
    }

    @Test
    fun toggle_size_sm() {
        val html = createHTML(prettyPrint = false).div {
            daisyToggle(
                size = ToggleSize.Sm,
            )
        }
        assertRendered(html, "toggle toggle-sm", "Toggle size Sm")
    }

    @Test
    fun toggle_size_md() {
        val html = createHTML(prettyPrint = false).div {
            daisyToggle(
                size = ToggleSize.Md,
            )
        }
        assertRendered(html, "toggle toggle-md", "Toggle size Md")
    }

    @Test
    fun toggle_size_lg() {
        val html = createHTML(prettyPrint = false).div {
            daisyToggle(
                size = ToggleSize.Lg,
            )
        }
        assertRendered(html, "toggle toggle-lg", "Toggle size Lg")
    }

    @Test
    fun toggle_size_xl() {
        val html = createHTML(prettyPrint = false).div {
            daisyToggle(
                size = ToggleSize.Xl,
            )
        }
        assertRendered(html, "toggle toggle-xl", "Toggle size Xl")
    }

    @Test
    fun toggleClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyToggleClasses()
        }
        assertRendered(html, "toggle", "ToggleClasses defaults")
    }

    @Test
    fun toggleClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyToggleClasses()
        }
        assertRendered(html, "toggle", "ToggleClasses all flags")
    }

    @Test
    fun toggleClasses_color_primary() {
        val html = createHTML(prettyPrint = false).div {
            daisyToggleClasses(
                color = ToggleColor.Primary,
            )
        }
        assertRendered(html, "toggle toggle-primary", "ToggleClasses color Primary")
    }

    @Test
    fun toggleClasses_color_secondary() {
        val html = createHTML(prettyPrint = false).div {
            daisyToggleClasses(
                color = ToggleColor.Secondary,
            )
        }
        assertRendered(html, "toggle toggle-secondary", "ToggleClasses color Secondary")
    }

    @Test
    fun toggleClasses_color_accent() {
        val html = createHTML(prettyPrint = false).div {
            daisyToggleClasses(
                color = ToggleColor.Accent,
            )
        }
        assertRendered(html, "toggle toggle-accent", "ToggleClasses color Accent")
    }

    @Test
    fun toggleClasses_color_neutral() {
        val html = createHTML(prettyPrint = false).div {
            daisyToggleClasses(
                color = ToggleColor.Neutral,
            )
        }
        assertRendered(html, "toggle toggle-neutral", "ToggleClasses color Neutral")
    }

    @Test
    fun toggleClasses_color_success() {
        val html = createHTML(prettyPrint = false).div {
            daisyToggleClasses(
                color = ToggleColor.Success,
            )
        }
        assertRendered(html, "toggle toggle-success", "ToggleClasses color Success")
    }

    @Test
    fun toggleClasses_color_warning() {
        val html = createHTML(prettyPrint = false).div {
            daisyToggleClasses(
                color = ToggleColor.Warning,
            )
        }
        assertRendered(html, "toggle toggle-warning", "ToggleClasses color Warning")
    }

    @Test
    fun toggleClasses_color_info() {
        val html = createHTML(prettyPrint = false).div {
            daisyToggleClasses(
                color = ToggleColor.Info,
            )
        }
        assertRendered(html, "toggle toggle-info", "ToggleClasses color Info")
    }

    @Test
    fun toggleClasses_color_error() {
        val html = createHTML(prettyPrint = false).div {
            daisyToggleClasses(
                color = ToggleColor.Error,
            )
        }
        assertRendered(html, "toggle toggle-error", "ToggleClasses color Error")
    }

    @Test
    fun toggleClasses_size_xs() {
        val html = createHTML(prettyPrint = false).div {
            daisyToggleClasses(
                size = ToggleSize.Xs,
            )
        }
        assertRendered(html, "toggle toggle-xs", "ToggleClasses size Xs")
    }

    @Test
    fun toggleClasses_size_sm() {
        val html = createHTML(prettyPrint = false).div {
            daisyToggleClasses(
                size = ToggleSize.Sm,
            )
        }
        assertRendered(html, "toggle toggle-sm", "ToggleClasses size Sm")
    }

    @Test
    fun toggleClasses_size_md() {
        val html = createHTML(prettyPrint = false).div {
            daisyToggleClasses(
                size = ToggleSize.Md,
            )
        }
        assertRendered(html, "toggle toggle-md", "ToggleClasses size Md")
    }

    @Test
    fun toggleClasses_size_lg() {
        val html = createHTML(prettyPrint = false).div {
            daisyToggleClasses(
                size = ToggleSize.Lg,
            )
        }
        assertRendered(html, "toggle toggle-lg", "ToggleClasses size Lg")
    }

    @Test
    fun toggleClasses_size_xl() {
        val html = createHTML(prettyPrint = false).div {
            daisyToggleClasses(
                size = ToggleSize.Xl,
            )
        }
        assertRendered(html, "toggle toggle-xl", "ToggleClasses size Xl")
    }
}
