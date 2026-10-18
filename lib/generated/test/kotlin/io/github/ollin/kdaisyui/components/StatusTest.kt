package io.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals

class StatusTest {

    @Test
    fun status() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatus() {
            }
        }
        val expectedClasses = "status"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Status")
    }

    @Test
    fun status_sizes() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatus(size = StatusSize.Xs) {
            }
        }
        val expectedClasses = "status status-xs"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Status sizes")
    }

    @Test
    fun status_sizes_2() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatus(size = StatusSize.Sm) {
            }
        }
        val expectedClasses = "status status-sm"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Status sizes")
    }

    @Test
    fun status_sizes_3() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatus(size = StatusSize.Md) {
            }
        }
        val expectedClasses = "status status-md"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Status sizes")
    }

    @Test
    fun status_sizes_4() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatus(size = StatusSize.Lg) {
            }
        }
        val expectedClasses = "status status-lg"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Status sizes")
    }

    @Test
    fun status_sizes_5() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatus(size = StatusSize.Xl) {
            }
        }
        val expectedClasses = "status status-xl"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Status sizes")
    }

    @Test
    fun status_with_colors() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatus(color = StatusColor.Primary) {
            }
        }
        val expectedClasses = "status status-primary"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Status with colors")
    }

    @Test
    fun status_with_colors_2() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatus(color = StatusColor.Secondary) {
            }
        }
        val expectedClasses = "status status-secondary"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Status with colors")
    }

    @Test
    fun status_with_colors_3() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatus(color = StatusColor.Accent) {
            }
        }
        val expectedClasses = "status status-accent"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Status with colors")
    }

    @Test
    fun status_with_colors_4() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatus(color = StatusColor.Neutral) {
            }
        }
        val expectedClasses = "status status-neutral"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Status with colors")
    }

    @Test
    fun status_with_colors_5() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatus(color = StatusColor.Info) {
            }
        }
        val expectedClasses = "status status-info"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Status with colors")
    }

    @Test
    fun status_with_colors_6() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatus(color = StatusColor.Success) {
            }
        }
        val expectedClasses = "status status-success"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Status with colors")
    }

    @Test
    fun status_with_colors_7() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatus(color = StatusColor.Warning) {
            }
        }
        val expectedClasses = "status status-warning"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Status with colors")
    }

    @Test
    fun status_with_colors_8() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatus(color = StatusColor.Error) {
            }
        }
        val expectedClasses = "status status-error"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Status with colors")
    }
}
