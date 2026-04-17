package io.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals

class RangeTest {

    @Test
    fun range() {
        val html = createHTML(prettyPrint = false).div {
            daisyRange() {
            }
        }
        val expectedClasses = "range"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Range")
    }

    @Test
    fun with_steps_and_measure() {
        val html = createHTML(prettyPrint = false).div {
            daisyRange() {
            }
        }
        val expectedClasses = "range"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for With steps and measure")
    }

    @Test
    fun neutral_color() {
        val html = createHTML(prettyPrint = false).div {
            daisyRange() {
            }
        }
        val expectedClasses = "range"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Neutral color")
    }

    @Test
    fun primary_color() {
        val html = createHTML(prettyPrint = false).div {
            daisyRange() {
            }
        }
        val expectedClasses = "range"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Primary color")
    }

    @Test
    fun secondary_color() {
        val html = createHTML(prettyPrint = false).div {
            daisyRange() {
            }
        }
        val expectedClasses = "range"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Secondary color")
    }

    @Test
    fun accent_color() {
        val html = createHTML(prettyPrint = false).div {
            daisyRange() {
            }
        }
        val expectedClasses = "range"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Accent color")
    }

    @Test
    fun success_color() {
        val html = createHTML(prettyPrint = false).div {
            daisyRange() {
            }
        }
        val expectedClasses = "range"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Success color")
    }

    @Test
    fun warning_color() {
        val html = createHTML(prettyPrint = false).div {
            daisyRange() {
            }
        }
        val expectedClasses = "range"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Warning color")
    }

    @Test
    fun info_color() {
        val html = createHTML(prettyPrint = false).div {
            daisyRange() {
            }
        }
        val expectedClasses = "range"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Info color")
    }

    @Test
    fun error_color() {
        val html = createHTML(prettyPrint = false).div {
            daisyRange() {
            }
        }
        val expectedClasses = "range"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Error color")
    }

    @Test
    fun sizes() {
        val html = createHTML(prettyPrint = false).div {
            daisyRange() {
            }
        }
        val expectedClasses = "range"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Sizes")
    }

    @Test
    fun range_with_custom_color_and_no_fill() {
        val html = createHTML(prettyPrint = false).div {
            daisyRange() {
            }
        }
        val expectedClasses = "range"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Range with custom color and no fill")
    }
}
