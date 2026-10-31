package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.FlowContent
import kotlinx.html.Tag
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class DividerCoverageTest {

    /** What a case must render: its classes, sorted, the tail it closes with, whether it took content. */
    private class Expected(val label: String, val classes: String, val closes: String = "", val content: Boolean = true)

    private fun assertRendered(html: String, expected: Expected) {
        assertEquals(expected.classes, html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" "), expected.label)
        if (expected.closes.isNotEmpty()) assertTrue(html.endsWith(expected.closes), "${expected.label} closes")
    }

    private val ID = htmlId("x-cov-id")
    private val EXTRA = "zz-extra"
    private val ATTRS: Tag.() -> Unit = { attributes["data-attrs"] = "yes" }
    private val CONTENT: Tag.() -> Unit = { attributes["data-content"] = "yes" }

    /** Renders a call that passes every escape hatch, and asserts its classes and the hatches. */
    private fun allFlags(expected: Expected, render: FlowContent.() -> Unit): String {
        val html = createHTML(prettyPrint = false).div { render() }
        assertRendered(html, expected)
        assertCommonFlags(html, expected)
        return html
    }

    private fun assertCommonFlags(html: String, expected: Expected) {
        assertTrue(html.contains("id=\"x-cov-id\""), "${expected.label} id")
        assertTrue(html.contains("data-attrs=\"yes\""), "${expected.label} attrs")
        if (expected.content) assertTrue(html.contains("data-content=\"yes\""), "${expected.label} content")
    }

    @Test
    fun divider_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyDivider(
                content = { },
            )
        }
        assertRendered(html, Expected("Divider defaults", "divider", closes = "</div></div>"))
    }

    @Test
    fun divider_all_flags() {
        val html = allFlags(Expected("Divider all flags", "divider zz-extra", closes = "</div></div>")) { daisyDivider(id = ID, extraClasses = EXTRA, attrs = ATTRS, content = CONTENT) }
    }

    @Test
    fun divider_color_neutral() {
        val html = createHTML(prettyPrint = false).div {
            daisyDivider(
                color = DividerColor.Neutral,
                content = { },
            )
        }
        assertRendered(html, Expected("Divider color Neutral", "divider divider-neutral"))
    }

    @Test
    fun divider_color_primary() {
        val html = createHTML(prettyPrint = false).div {
            daisyDivider(
                color = DividerColor.Primary,
                content = { },
            )
        }
        assertRendered(html, Expected("Divider color Primary", "divider divider-primary"))
    }

    @Test
    fun divider_color_secondary() {
        val html = createHTML(prettyPrint = false).div {
            daisyDivider(
                color = DividerColor.Secondary,
                content = { },
            )
        }
        assertRendered(html, Expected("Divider color Secondary", "divider divider-secondary"))
    }

    @Test
    fun divider_color_accent() {
        val html = createHTML(prettyPrint = false).div {
            daisyDivider(
                color = DividerColor.Accent,
                content = { },
            )
        }
        assertRendered(html, Expected("Divider color Accent", "divider divider-accent"))
    }

    @Test
    fun divider_color_success() {
        val html = createHTML(prettyPrint = false).div {
            daisyDivider(
                color = DividerColor.Success,
                content = { },
            )
        }
        assertRendered(html, Expected("Divider color Success", "divider divider-success"))
    }

    @Test
    fun divider_color_warning() {
        val html = createHTML(prettyPrint = false).div {
            daisyDivider(
                color = DividerColor.Warning,
                content = { },
            )
        }
        assertRendered(html, Expected("Divider color Warning", "divider divider-warning"))
    }

    @Test
    fun divider_color_info() {
        val html = createHTML(prettyPrint = false).div {
            daisyDivider(
                color = DividerColor.Info,
                content = { },
            )
        }
        assertRendered(html, Expected("Divider color Info", "divider divider-info"))
    }

    @Test
    fun divider_color_error() {
        val html = createHTML(prettyPrint = false).div {
            daisyDivider(
                color = DividerColor.Error,
                content = { },
            )
        }
        assertRendered(html, Expected("Divider color Error", "divider divider-error"))
    }

    @Test
    fun divider_direction_vertical() {
        val html = createHTML(prettyPrint = false).div {
            daisyDivider(
                direction = DividerDirection.Vertical,
                content = { },
            )
        }
        assertRendered(html, Expected("Divider direction Vertical", "divider divider-vertical"))
    }

    @Test
    fun divider_direction_horizontal() {
        val html = createHTML(prettyPrint = false).div {
            daisyDivider(
                direction = DividerDirection.Horizontal,
                content = { },
            )
        }
        assertRendered(html, Expected("Divider direction Horizontal", "divider divider-horizontal"))
    }

    @Test
    fun divider_placement_start() {
        val html = createHTML(prettyPrint = false).div {
            daisyDivider(
                placement = DividerPlacement.Start,
                content = { },
            )
        }
        assertRendered(html, Expected("Divider placement Start", "divider divider-start"))
    }

    @Test
    fun divider_placement_end() {
        val html = createHTML(prettyPrint = false).div {
            daisyDivider(
                placement = DividerPlacement.End,
                content = { },
            )
        }
        assertRendered(html, Expected("Divider placement End", "divider divider-end"))
    }

    @Test
    fun dividerClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyDividerClasses()
        }
        assertRendered(html, Expected("DividerClasses defaults", "divider"))
    }

    @Test
    fun dividerClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyDividerClasses()
        }
        val expected = Expected("DividerClasses all flags", "divider", content = false)
        assertRendered(html, expected)
    }

    @Test
    fun dividerClasses_color_neutral() {
        val html = createHTML(prettyPrint = false).div {
            daisyDividerClasses(
                color = DividerColor.Neutral,
            )
        }
        assertRendered(html, Expected("DividerClasses color Neutral", "divider divider-neutral"))
    }

    @Test
    fun dividerClasses_color_primary() {
        val html = createHTML(prettyPrint = false).div {
            daisyDividerClasses(
                color = DividerColor.Primary,
            )
        }
        assertRendered(html, Expected("DividerClasses color Primary", "divider divider-primary"))
    }

    @Test
    fun dividerClasses_color_secondary() {
        val html = createHTML(prettyPrint = false).div {
            daisyDividerClasses(
                color = DividerColor.Secondary,
            )
        }
        assertRendered(html, Expected("DividerClasses color Secondary", "divider divider-secondary"))
    }

    @Test
    fun dividerClasses_color_accent() {
        val html = createHTML(prettyPrint = false).div {
            daisyDividerClasses(
                color = DividerColor.Accent,
            )
        }
        assertRendered(html, Expected("DividerClasses color Accent", "divider divider-accent"))
    }

    @Test
    fun dividerClasses_color_success() {
        val html = createHTML(prettyPrint = false).div {
            daisyDividerClasses(
                color = DividerColor.Success,
            )
        }
        assertRendered(html, Expected("DividerClasses color Success", "divider divider-success"))
    }

    @Test
    fun dividerClasses_color_warning() {
        val html = createHTML(prettyPrint = false).div {
            daisyDividerClasses(
                color = DividerColor.Warning,
            )
        }
        assertRendered(html, Expected("DividerClasses color Warning", "divider divider-warning"))
    }

    @Test
    fun dividerClasses_color_info() {
        val html = createHTML(prettyPrint = false).div {
            daisyDividerClasses(
                color = DividerColor.Info,
            )
        }
        assertRendered(html, Expected("DividerClasses color Info", "divider divider-info"))
    }

    @Test
    fun dividerClasses_color_error() {
        val html = createHTML(prettyPrint = false).div {
            daisyDividerClasses(
                color = DividerColor.Error,
            )
        }
        assertRendered(html, Expected("DividerClasses color Error", "divider divider-error"))
    }

    @Test
    fun dividerClasses_direction_vertical() {
        val html = createHTML(prettyPrint = false).div {
            daisyDividerClasses(
                direction = DividerDirection.Vertical,
            )
        }
        assertRendered(html, Expected("DividerClasses direction Vertical", "divider divider-vertical"))
    }

    @Test
    fun dividerClasses_direction_horizontal() {
        val html = createHTML(prettyPrint = false).div {
            daisyDividerClasses(
                direction = DividerDirection.Horizontal,
            )
        }
        assertRendered(html, Expected("DividerClasses direction Horizontal", "divider divider-horizontal"))
    }

    @Test
    fun dividerClasses_placement_start() {
        val html = createHTML(prettyPrint = false).div {
            daisyDividerClasses(
                placement = DividerPlacement.Start,
            )
        }
        assertRendered(html, Expected("DividerClasses placement Start", "divider divider-start"))
    }

    @Test
    fun dividerClasses_placement_end() {
        val html = createHTML(prettyPrint = false).div {
            daisyDividerClasses(
                placement = DividerPlacement.End,
            )
        }
        assertRendered(html, Expected("DividerClasses placement End", "divider divider-end"))
    }
}
