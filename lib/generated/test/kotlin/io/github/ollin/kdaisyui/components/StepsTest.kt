package io.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.html.ul

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
    fun horizontal_daisyStep() {
        val html = createHTML(prettyPrint = false).ul {
            daisyStep(color = StepColor.Primary) {
            }
        }
        val expectedClasses = "step step-primary"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Horizontal")
    }

    @Test
    fun horizontal_daisyStep_2() {
        val html = createHTML(prettyPrint = false).ul {
            daisyStep() {
            }
        }
        val expectedClasses = "step"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Horizontal")
    }

    @Test
    fun vertical() {
        val html = createHTML(prettyPrint = false).div {
            daisySteps(direction = StepsDirection.Vertical) {
            }
        }
        val expectedClasses = "steps steps-vertical"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Vertical")
    }

    @Test
    fun responsive_vertical_on_small_screen_horizontal_on_large_screen() {
        val html = createHTML(prettyPrint = false).div {
            daisySteps(direction = StepsDirection.Vertical, extraClasses = "lg:steps-horizontal") {
            }
        }
        val expectedClasses = "lg:steps-horizontal steps steps-vertical"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for responsive (vertical on small screen, horizontal on large screen)")
    }

    @Test
    fun with_custom_content_in_step_icon_daisyStep() {
        val html = createHTML(prettyPrint = false).ul {
            daisyStep(color = StepColor.Neutral) {
            }
        }
        val expectedClasses = "step step-neutral"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for With custom content in step-icon")
    }

    @Test
    fun with_custom_content_in_step_icon_daisyStepIcon() {
        val html = createHTML(prettyPrint = false).div {
            daisyStepIcon() {
            }
        }
        val expectedClasses = "step-icon"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for With custom content in step-icon")
    }

    @Test
    fun custom_colors_daisyStep() {
        val html = createHTML(prettyPrint = false).ul {
            daisyStep(color = StepColor.Info) {
            }
        }
        val expectedClasses = "step step-info"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Custom colors")
    }

    @Test
    fun custom_colors_daisyStep_2() {
        val html = createHTML(prettyPrint = false).ul {
            daisyStep(color = StepColor.Error) {
            }
        }
        val expectedClasses = "step step-error"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Custom colors")
    }

    @Test
    fun with_scrollable_wrapper_daisyStep() {
        val html = createHTML(prettyPrint = false).ul {
            daisyStep(color = StepColor.Secondary) {
            }
        }
        val expectedClasses = "step step-secondary"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for With scrollable wrapper")
    }

    @Test
    fun with_scrollable_wrapper_daisyStep_2() {
        val html = createHTML(prettyPrint = false).ul {
            daisyStep(color = StepColor.Accent) {
            }
        }
        val expectedClasses = "step step-accent"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for With scrollable wrapper")
    }

    @Test
    fun with_scrollable_wrapper_daisyStep_3() {
        val html = createHTML(prettyPrint = false).ul {
            daisyStep(color = StepColor.Warning) {
            }
        }
        val expectedClasses = "step step-warning"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for With scrollable wrapper")
    }
}
