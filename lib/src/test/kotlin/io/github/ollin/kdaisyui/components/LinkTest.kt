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
    }

    @Test
    fun link_2() {
        val html = createHTML(prettyPrint = false).div {
            daisyLink() {
            }
        }
        val expectedClasses = "link"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Link")
    }

    @Test
    fun primary_color() {
        val html = createHTML(prettyPrint = false).div {
            daisyLink() {
            }
        }
        val expectedClasses = "link"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Primary color")
    }

    @Test
    fun secondary_color() {
        val html = createHTML(prettyPrint = false).div {
            daisyLink() {
            }
        }
        val expectedClasses = "link"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Secondary color")
    }

    @Test
    fun accent_color() {
        val html = createHTML(prettyPrint = false).div {
            daisyLink() {
            }
        }
        val expectedClasses = "link"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Accent color")
    }

    @Test
    fun success_color() {
        val html = createHTML(prettyPrint = false).div {
            daisyLink() {
            }
        }
        val expectedClasses = "link"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Success color")
    }

    @Test
    fun info_color() {
        val html = createHTML(prettyPrint = false).div {
            daisyLink() {
            }
        }
        val expectedClasses = "link"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Info color")
    }

    @Test
    fun warning_color() {
        val html = createHTML(prettyPrint = false).div {
            daisyLink() {
            }
        }
        val expectedClasses = "link"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Warning color")
    }

    @Test
    fun error_color() {
        val html = createHTML(prettyPrint = false).div {
            daisyLink() {
            }
        }
        val expectedClasses = "link"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Error color")
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
    }
}
