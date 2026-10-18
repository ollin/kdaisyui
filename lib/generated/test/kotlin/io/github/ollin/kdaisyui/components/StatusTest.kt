package io.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.html.span

class StatusTest {

    private fun assertDocumented(html: String, tag: String, classes: String, case: String) {
        assertEquals(classes, html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" "), "Class mismatch for $case")
        assertEquals(tag, html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for $case")
    }

    @Test
    fun status() {
        val html = createHTML(prettyPrint = false).div {
            span { daisyStatusClasses() }
        }
        assertDocumented(html, "span", "status", "Status")
    }

    @Test
    fun status_sizes() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatus(size = StatusSize.Xs) {
            }
        }
        assertDocumented(html, "div", "status status-xs", "Status sizes")
    }

    @Test
    fun status_sizes_2() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatus(size = StatusSize.Sm) {
            }
        }
        assertDocumented(html, "div", "status status-sm", "Status sizes")
    }

    @Test
    fun status_sizes_3() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatus(size = StatusSize.Md) {
            }
        }
        assertDocumented(html, "div", "status status-md", "Status sizes")
    }

    @Test
    fun status_sizes_4() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatus(size = StatusSize.Lg) {
            }
        }
        assertDocumented(html, "div", "status status-lg", "Status sizes")
    }

    @Test
    fun status_sizes_5() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatus(size = StatusSize.Xl) {
            }
        }
        assertDocumented(html, "div", "status status-xl", "Status sizes")
    }

    @Test
    fun status_with_colors() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatus(color = StatusColor.Primary) {
            }
        }
        assertDocumented(html, "div", "status status-primary", "Status with colors")
    }

    @Test
    fun status_with_colors_2() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatus(color = StatusColor.Secondary) {
            }
        }
        assertDocumented(html, "div", "status status-secondary", "Status with colors")
    }

    @Test
    fun status_with_colors_3() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatus(color = StatusColor.Accent) {
            }
        }
        assertDocumented(html, "div", "status status-accent", "Status with colors")
    }

    @Test
    fun status_with_colors_4() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatus(color = StatusColor.Neutral) {
            }
        }
        assertDocumented(html, "div", "status status-neutral", "Status with colors")
    }

    @Test
    fun status_with_colors_5() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatus(color = StatusColor.Info) {
            }
        }
        assertDocumented(html, "div", "status status-info", "Status with colors")
    }

    @Test
    fun status_with_colors_6() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatus(color = StatusColor.Success) {
            }
        }
        assertDocumented(html, "div", "status status-success", "Status with colors")
    }

    @Test
    fun status_with_colors_7() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatus(color = StatusColor.Warning) {
            }
        }
        assertDocumented(html, "div", "status status-warning", "Status with colors")
    }

    @Test
    fun status_with_colors_8() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatus(color = StatusColor.Error) {
            }
        }
        assertDocumented(html, "div", "status status-error", "Status with colors")
    }
}
