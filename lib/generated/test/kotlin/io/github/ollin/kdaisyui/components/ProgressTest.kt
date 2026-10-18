package io.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals

class ProgressTest {

    @Test
    fun progress() {
        val html = createHTML(prettyPrint = false).div {
            daisyProgress() {
            }
        }
        val expectedClasses = "progress"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Progress")
        assertEquals("progress", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Progress")
    }

    @Test
    fun primary_color() {
        val html = createHTML(prettyPrint = false).div {
            daisyProgress(color = ProgressColor.Primary) {
            }
        }
        val expectedClasses = "progress progress-primary"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Primary color")
        assertEquals("progress", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Primary color")
    }

    @Test
    fun secondary_color() {
        val html = createHTML(prettyPrint = false).div {
            daisyProgress(color = ProgressColor.Secondary) {
            }
        }
        val expectedClasses = "progress progress-secondary"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Secondary color")
        assertEquals("progress", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Secondary color")
    }

    @Test
    fun accent_color() {
        val html = createHTML(prettyPrint = false).div {
            daisyProgress(color = ProgressColor.Accent) {
            }
        }
        val expectedClasses = "progress progress-accent"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Accent color")
        assertEquals("progress", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Accent color")
    }

    @Test
    fun neutral_color() {
        val html = createHTML(prettyPrint = false).div {
            daisyProgress(color = ProgressColor.Neutral) {
            }
        }
        val expectedClasses = "progress progress-neutral"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Neutral color")
        assertEquals("progress", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Neutral color")
    }

    @Test
    fun info_color() {
        val html = createHTML(prettyPrint = false).div {
            daisyProgress(color = ProgressColor.Info) {
            }
        }
        val expectedClasses = "progress progress-info"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Info color")
        assertEquals("progress", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Info color")
    }

    @Test
    fun success_color() {
        val html = createHTML(prettyPrint = false).div {
            daisyProgress(color = ProgressColor.Success) {
            }
        }
        val expectedClasses = "progress progress-success"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Success color")
        assertEquals("progress", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Success color")
    }

    @Test
    fun warning_color() {
        val html = createHTML(prettyPrint = false).div {
            daisyProgress(color = ProgressColor.Warning) {
            }
        }
        val expectedClasses = "progress progress-warning"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Warning color")
        assertEquals("progress", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Warning color")
    }

    @Test
    fun error_color() {
        val html = createHTML(prettyPrint = false).div {
            daisyProgress(color = ProgressColor.Error) {
            }
        }
        val expectedClasses = "progress progress-error"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Error color")
        assertEquals("progress", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Error color")
    }
}
