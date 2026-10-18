package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class DividerCoverageTest {

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
    fun divider_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyDivider(
                content = { },
            )
        }
        assertRendered(html, "divider", "Divider defaults", closes = "</div></div>")
    }

    @Test
    fun divider_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyDivider(
                id = htmlId("x-cov-id"),
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        assertRendered(html, "divider zz-extra", "Divider all flags", closes = "</div></div>")
        assertCommonFlags(html, "Divider")
    }

    @Test
    fun divider_color_neutral() {
        val html = createHTML(prettyPrint = false).div {
            daisyDivider(
                color = DividerColor.Neutral,
                content = { },
            )
        }
        assertRendered(html, "divider divider-neutral", "Divider color Neutral")
    }

    @Test
    fun divider_color_primary() {
        val html = createHTML(prettyPrint = false).div {
            daisyDivider(
                color = DividerColor.Primary,
                content = { },
            )
        }
        assertRendered(html, "divider divider-primary", "Divider color Primary")
    }

    @Test
    fun divider_color_secondary() {
        val html = createHTML(prettyPrint = false).div {
            daisyDivider(
                color = DividerColor.Secondary,
                content = { },
            )
        }
        assertRendered(html, "divider divider-secondary", "Divider color Secondary")
    }

    @Test
    fun divider_color_accent() {
        val html = createHTML(prettyPrint = false).div {
            daisyDivider(
                color = DividerColor.Accent,
                content = { },
            )
        }
        assertRendered(html, "divider divider-accent", "Divider color Accent")
    }

    @Test
    fun divider_color_success() {
        val html = createHTML(prettyPrint = false).div {
            daisyDivider(
                color = DividerColor.Success,
                content = { },
            )
        }
        assertRendered(html, "divider divider-success", "Divider color Success")
    }

    @Test
    fun divider_color_warning() {
        val html = createHTML(prettyPrint = false).div {
            daisyDivider(
                color = DividerColor.Warning,
                content = { },
            )
        }
        assertRendered(html, "divider divider-warning", "Divider color Warning")
    }

    @Test
    fun divider_color_info() {
        val html = createHTML(prettyPrint = false).div {
            daisyDivider(
                color = DividerColor.Info,
                content = { },
            )
        }
        assertRendered(html, "divider divider-info", "Divider color Info")
    }

    @Test
    fun divider_color_error() {
        val html = createHTML(prettyPrint = false).div {
            daisyDivider(
                color = DividerColor.Error,
                content = { },
            )
        }
        assertRendered(html, "divider divider-error", "Divider color Error")
    }

    @Test
    fun divider_direction_vertical() {
        val html = createHTML(prettyPrint = false).div {
            daisyDivider(
                direction = DividerDirection.Vertical,
                content = { },
            )
        }
        assertRendered(html, "divider divider-vertical", "Divider direction Vertical")
    }

    @Test
    fun divider_direction_horizontal() {
        val html = createHTML(prettyPrint = false).div {
            daisyDivider(
                direction = DividerDirection.Horizontal,
                content = { },
            )
        }
        assertRendered(html, "divider divider-horizontal", "Divider direction Horizontal")
    }

    @Test
    fun divider_placement_start() {
        val html = createHTML(prettyPrint = false).div {
            daisyDivider(
                placement = DividerPlacement.Start,
                content = { },
            )
        }
        assertRendered(html, "divider divider-start", "Divider placement Start")
    }

    @Test
    fun divider_placement_end() {
        val html = createHTML(prettyPrint = false).div {
            daisyDivider(
                placement = DividerPlacement.End,
                content = { },
            )
        }
        assertRendered(html, "divider divider-end", "Divider placement End")
    }
}
