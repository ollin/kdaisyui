package io.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals

class LinkTest {

    @Test
    fun link() {
        val html = createHTML(prettyPrint = false).div {
            daisyLink() {
            }
        }
        val expectedClasses = "link"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Link")
        assertEquals("a", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Link")
    }

    @Test
    fun primary_color() {
        val html = createHTML(prettyPrint = false).div {
            daisyLink(color = LinkColor.Primary) {
            }
        }
        val expectedClasses = "link link-primary"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Primary color")
        assertEquals("a", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Primary color")
    }

    @Test
    fun secondary_color() {
        val html = createHTML(prettyPrint = false).div {
            daisyLink(color = LinkColor.Secondary) {
            }
        }
        val expectedClasses = "link link-secondary"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Secondary color")
        assertEquals("a", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Secondary color")
    }

    @Test
    fun accent_color() {
        val html = createHTML(prettyPrint = false).div {
            daisyLink(color = LinkColor.Accent) {
            }
        }
        val expectedClasses = "link link-accent"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Accent color")
        assertEquals("a", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Accent color")
    }

    @Test
    fun success_color() {
        val html = createHTML(prettyPrint = false).div {
            daisyLink(color = LinkColor.Success) {
            }
        }
        val expectedClasses = "link link-success"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Success color")
        assertEquals("a", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Success color")
    }

    @Test
    fun info_color() {
        val html = createHTML(prettyPrint = false).div {
            daisyLink(color = LinkColor.Info) {
            }
        }
        val expectedClasses = "link link-info"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Info color")
        assertEquals("a", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Info color")
    }

    @Test
    fun warning_color() {
        val html = createHTML(prettyPrint = false).div {
            daisyLink(color = LinkColor.Warning) {
            }
        }
        val expectedClasses = "link link-warning"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Warning color")
        assertEquals("a", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Warning color")
    }

    @Test
    fun error_color() {
        val html = createHTML(prettyPrint = false).div {
            daisyLink(color = LinkColor.Error) {
            }
        }
        val expectedClasses = "link link-error"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Error color")
        assertEquals("a", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Error color")
    }

    @Test
    fun show_underline_only_on_hover() {
        val html = createHTML(prettyPrint = false).div {
            daisyLink(hover = true) {
            }
        }
        val expectedClasses = "link link-hover"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Show underline only on hover")
        assertEquals("a", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Show underline only on hover")
    }
}
