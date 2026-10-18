package io.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals

class ProgressTest {

    private fun assertDocumented(html: String, tag: String, classes: String, case: String) {
        assertEquals(classes, html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" "), "Class mismatch for $case")
        assertEquals(tag, html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for $case")
    }

    @Test
    fun progress() {
        val html = createHTML(prettyPrint = false).div {
            daisyProgress() {
            }
        }
        assertDocumented(html, "progress", "progress", "Progress")
    }

    @Test
    fun primary_color() {
        val html = createHTML(prettyPrint = false).div {
            daisyProgress(color = ProgressColor.Primary) {
            }
        }
        assertDocumented(html, "progress", "progress progress-primary", "Primary color")
    }

    @Test
    fun secondary_color() {
        val html = createHTML(prettyPrint = false).div {
            daisyProgress(color = ProgressColor.Secondary) {
            }
        }
        assertDocumented(html, "progress", "progress progress-secondary", "Secondary color")
    }

    @Test
    fun accent_color() {
        val html = createHTML(prettyPrint = false).div {
            daisyProgress(color = ProgressColor.Accent) {
            }
        }
        assertDocumented(html, "progress", "progress progress-accent", "Accent color")
    }

    @Test
    fun neutral_color() {
        val html = createHTML(prettyPrint = false).div {
            daisyProgress(color = ProgressColor.Neutral) {
            }
        }
        assertDocumented(html, "progress", "progress progress-neutral", "Neutral color")
    }

    @Test
    fun info_color() {
        val html = createHTML(prettyPrint = false).div {
            daisyProgress(color = ProgressColor.Info) {
            }
        }
        assertDocumented(html, "progress", "progress progress-info", "Info color")
    }

    @Test
    fun success_color() {
        val html = createHTML(prettyPrint = false).div {
            daisyProgress(color = ProgressColor.Success) {
            }
        }
        assertDocumented(html, "progress", "progress progress-success", "Success color")
    }

    @Test
    fun warning_color() {
        val html = createHTML(prettyPrint = false).div {
            daisyProgress(color = ProgressColor.Warning) {
            }
        }
        assertDocumented(html, "progress", "progress progress-warning", "Warning color")
    }

    @Test
    fun error_color() {
        val html = createHTML(prettyPrint = false).div {
            daisyProgress(color = ProgressColor.Error) {
            }
        }
        assertDocumented(html, "progress", "progress progress-error", "Error color")
    }
}
