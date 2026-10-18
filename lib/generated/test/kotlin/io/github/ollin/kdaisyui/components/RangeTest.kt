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
    fun neutral_color() {
        val html = createHTML(prettyPrint = false).div {
            daisyRange(color = RangeColor.Neutral) {
            }
        }
        val expectedClasses = "range range-neutral"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Neutral color")
    }

    @Test
    fun primary_color() {
        val html = createHTML(prettyPrint = false).div {
            daisyRange(color = RangeColor.Primary) {
            }
        }
        val expectedClasses = "range range-primary"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Primary color")
    }

    @Test
    fun secondary_color() {
        val html = createHTML(prettyPrint = false).div {
            daisyRange(color = RangeColor.Secondary) {
            }
        }
        val expectedClasses = "range range-secondary"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Secondary color")
    }

    @Test
    fun accent_color() {
        val html = createHTML(prettyPrint = false).div {
            daisyRange(color = RangeColor.Accent) {
            }
        }
        val expectedClasses = "range range-accent"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Accent color")
    }

    @Test
    fun success_color() {
        val html = createHTML(prettyPrint = false).div {
            daisyRange(color = RangeColor.Success) {
            }
        }
        val expectedClasses = "range range-success"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Success color")
    }

    @Test
    fun warning_color() {
        val html = createHTML(prettyPrint = false).div {
            daisyRange(color = RangeColor.Warning) {
            }
        }
        val expectedClasses = "range range-warning"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Warning color")
    }

    @Test
    fun info_color() {
        val html = createHTML(prettyPrint = false).div {
            daisyRange(color = RangeColor.Info) {
            }
        }
        val expectedClasses = "range range-info"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Info color")
    }

    @Test
    fun error_color() {
        val html = createHTML(prettyPrint = false).div {
            daisyRange(color = RangeColor.Error) {
            }
        }
        val expectedClasses = "range range-error"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Error color")
    }

    @Test
    fun sizes() {
        val html = createHTML(prettyPrint = false).div {
            daisyRange(size = RangeSize.Xs) {
            }
        }
        val expectedClasses = "range range-xs"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Sizes")
    }

    @Test
    fun sizes_2() {
        val html = createHTML(prettyPrint = false).div {
            daisyRange(size = RangeSize.Sm) {
            }
        }
        val expectedClasses = "range range-sm"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Sizes")
    }

    @Test
    fun sizes_3() {
        val html = createHTML(prettyPrint = false).div {
            daisyRange(size = RangeSize.Md) {
            }
        }
        val expectedClasses = "range range-md"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Sizes")
    }

    @Test
    fun sizes_4() {
        val html = createHTML(prettyPrint = false).div {
            daisyRange(size = RangeSize.Lg) {
            }
        }
        val expectedClasses = "range range-lg"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Sizes")
    }

    @Test
    fun sizes_5() {
        val html = createHTML(prettyPrint = false).div {
            daisyRange(size = RangeSize.Xl) {
            }
        }
        val expectedClasses = "range range-xl"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Sizes")
    }

    @Test
    fun vertical() {
        val html = createHTML(prettyPrint = false).div {
            daisyRange(vertical = true) {
            }
        }
        val expectedClasses = "range range-vertical"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Vertical")
    }
}
