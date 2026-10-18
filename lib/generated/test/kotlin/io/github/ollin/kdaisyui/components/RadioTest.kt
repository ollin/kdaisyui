package io.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals

class RadioTest {

    @Test
    fun radio() {
        val html = createHTML(prettyPrint = false).div {
            daisyRadio() {
            }
        }
        val expectedClasses = "radio"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Radio")
    }

    @Test
    fun radio_sizes() {
        val html = createHTML(prettyPrint = false).div {
            daisyRadio(size = RadioSize.Xs) {
            }
        }
        val expectedClasses = "radio radio-xs"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Radio sizes")
    }

    @Test
    fun radio_sizes_2() {
        val html = createHTML(prettyPrint = false).div {
            daisyRadio(size = RadioSize.Sm) {
            }
        }
        val expectedClasses = "radio radio-sm"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Radio sizes")
    }

    @Test
    fun radio_sizes_3() {
        val html = createHTML(prettyPrint = false).div {
            daisyRadio(size = RadioSize.Md) {
            }
        }
        val expectedClasses = "radio radio-md"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Radio sizes")
    }

    @Test
    fun radio_sizes_4() {
        val html = createHTML(prettyPrint = false).div {
            daisyRadio(size = RadioSize.Lg) {
            }
        }
        val expectedClasses = "radio radio-lg"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Radio sizes")
    }

    @Test
    fun radio_sizes_5() {
        val html = createHTML(prettyPrint = false).div {
            daisyRadio(size = RadioSize.Xl) {
            }
        }
        val expectedClasses = "radio radio-xl"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Radio sizes")
    }

    @Test
    fun neutral_color() {
        val html = createHTML(prettyPrint = false).div {
            daisyRadio(color = RadioColor.Neutral) {
            }
        }
        val expectedClasses = "radio radio-neutral"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Neutral color")
    }

    @Test
    fun primary_color() {
        val html = createHTML(prettyPrint = false).div {
            daisyRadio(color = RadioColor.Primary) {
            }
        }
        val expectedClasses = "radio radio-primary"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Primary color")
    }

    @Test
    fun secondary_color() {
        val html = createHTML(prettyPrint = false).div {
            daisyRadio(color = RadioColor.Secondary) {
            }
        }
        val expectedClasses = "radio radio-secondary"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Secondary color")
    }

    @Test
    fun accent_color() {
        val html = createHTML(prettyPrint = false).div {
            daisyRadio(color = RadioColor.Accent) {
            }
        }
        val expectedClasses = "radio radio-accent"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Accent color")
    }

    @Test
    fun success_color() {
        val html = createHTML(prettyPrint = false).div {
            daisyRadio(color = RadioColor.Success) {
            }
        }
        val expectedClasses = "radio radio-success"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Success color")
    }

    @Test
    fun warning_color() {
        val html = createHTML(prettyPrint = false).div {
            daisyRadio(color = RadioColor.Warning) {
            }
        }
        val expectedClasses = "radio radio-warning"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Warning color")
    }

    @Test
    fun info_color() {
        val html = createHTML(prettyPrint = false).div {
            daisyRadio(color = RadioColor.Info) {
            }
        }
        val expectedClasses = "radio radio-info"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Info color")
    }

    @Test
    fun error_color() {
        val html = createHTML(prettyPrint = false).div {
            daisyRadio(color = RadioColor.Error) {
            }
        }
        val expectedClasses = "radio radio-error"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Error color")
    }
}
