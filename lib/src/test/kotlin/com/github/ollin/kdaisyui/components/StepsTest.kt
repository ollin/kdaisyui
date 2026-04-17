package com.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals

class StepsTest {

    @Test
    fun horizontal() {
        val html = createHTML(prettyPrint = false).div {
            daisySteps() {
            }
        }
        val expectedClasses = "steps"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Horizontal")
    }

    @Test
    fun vertical() {
        val html = createHTML(prettyPrint = false).div {
            daisySteps(vertical = true) {
            }
        }
        val expectedClasses = "steps steps-vertical"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Vertical")
    }

    @Test
    fun responsive_vertical_on_small_screen_horizontal_on_large_screen() {
        val html = createHTML(prettyPrint = false).div {
            daisySteps(vertical = true, horizontal = true) {
            }
        }
        val expectedClasses = "steps steps-horizontal steps-vertical"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for responsive (vertical on small screen, horizontal on large screen)")
    }

    @Test
    fun with_custom_content_in_step_icon() {
        val html = createHTML(prettyPrint = false).div {
            daisySteps() {
            }
        }
        val expectedClasses = "steps"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for With custom content in step-icon")
    }

    @Test
    fun with_data_content() {
        val html = createHTML(prettyPrint = false).div {
            daisySteps() {
            }
        }
        val expectedClasses = "steps"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for With data-content")
    }

    @Test
    fun custom_colors() {
        val html = createHTML(prettyPrint = false).div {
            daisySteps() {
            }
        }
        val expectedClasses = "steps"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Custom colors")
    }

    @Test
    fun with_scrollable_wrapper() {
        val html = createHTML(prettyPrint = false).div {
            daisySteps() {
            }
        }
        val expectedClasses = "steps"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for With scrollable wrapper")
    }
}
