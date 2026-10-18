package io.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals

class LinkTest {

    private fun assertDocumented(html: String, tag: String, classes: String, case: String) {
        assertEquals(classes, html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" "), "Class mismatch for $case")
        assertEquals(tag, html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for $case")
    }

    @Test
    fun link() {
        val html = createHTML(prettyPrint = false).div {
            daisyLink() {
            }
        }
        assertDocumented(html, "a", "link", "Link")
    }

    @Test
    fun primary_color() {
        val html = createHTML(prettyPrint = false).div {
            daisyLink(color = LinkColor.Primary) {
            }
        }
        assertDocumented(html, "a", "link link-primary", "Primary color")
    }

    @Test
    fun secondary_color() {
        val html = createHTML(prettyPrint = false).div {
            daisyLink(color = LinkColor.Secondary) {
            }
        }
        assertDocumented(html, "a", "link link-secondary", "Secondary color")
    }

    @Test
    fun accent_color() {
        val html = createHTML(prettyPrint = false).div {
            daisyLink(color = LinkColor.Accent) {
            }
        }
        assertDocumented(html, "a", "link link-accent", "Accent color")
    }

    @Test
    fun success_color() {
        val html = createHTML(prettyPrint = false).div {
            daisyLink(color = LinkColor.Success) {
            }
        }
        assertDocumented(html, "a", "link link-success", "Success color")
    }

    @Test
    fun info_color() {
        val html = createHTML(prettyPrint = false).div {
            daisyLink(color = LinkColor.Info) {
            }
        }
        assertDocumented(html, "a", "link link-info", "Info color")
    }

    @Test
    fun warning_color() {
        val html = createHTML(prettyPrint = false).div {
            daisyLink(color = LinkColor.Warning) {
            }
        }
        assertDocumented(html, "a", "link link-warning", "Warning color")
    }

    @Test
    fun error_color() {
        val html = createHTML(prettyPrint = false).div {
            daisyLink(color = LinkColor.Error) {
            }
        }
        assertDocumented(html, "a", "link link-error", "Error color")
    }

    @Test
    fun show_underline_only_on_hover() {
        val html = createHTML(prettyPrint = false).div {
            daisyLink(hover = true) {
            }
        }
        assertDocumented(html, "a", "link link-hover", "Show underline only on hover")
    }
}
