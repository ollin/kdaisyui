package io.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.html.ul

class StepsTest {

    private fun assertDocumented(html: String, tag: String, classes: String, case: String) {
        assertEquals(classes, html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" "), "Class mismatch for $case")
        assertEquals(tag, html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for $case")
    }

    @Test
    fun horizontal() {
        val html = createHTML(prettyPrint = false).div {
            daisySteps() {
            }
        }
        assertDocumented(html, "ul", "steps", "Horizontal")
    }

    @Test
    fun horizontal_daisyStep() {
        val html = createHTML(prettyPrint = false).ul {
            daisyStep(color = StepColor.Primary) {
            }
        }
        assertDocumented(html, "li", "step step-primary", "Horizontal")
    }

    @Test
    fun horizontal_daisyStep_2() {
        val html = createHTML(prettyPrint = false).ul {
            daisyStep() {
            }
        }
        assertDocumented(html, "li", "step", "Horizontal")
    }

    @Test
    fun vertical() {
        val html = createHTML(prettyPrint = false).div {
            daisySteps(direction = StepsDirection.Vertical) {
            }
        }
        assertDocumented(html, "ul", "steps steps-vertical", "Vertical")
    }

    @Test
    fun responsive_vertical_on_small_screen_horizontal_on_large_screen() {
        val html = createHTML(prettyPrint = false).div {
            daisySteps(direction = StepsDirection.Vertical, extraClasses = "lg:steps-horizontal") {
            }
        }
        assertDocumented(html, "ul", "lg:steps-horizontal steps steps-vertical", "responsive (vertical on small screen, horizontal on large screen)")
    }

    @Test
    fun with_custom_content_in_step_icon_daisyStep() {
        val html = createHTML(prettyPrint = false).ul {
            daisyStep(color = StepColor.Neutral) {
            }
        }
        assertDocumented(html, "li", "step step-neutral", "With custom content in step-icon")
    }

    @Test
    fun with_custom_content_in_step_icon_daisyStepIcon() {
        val html = createHTML(prettyPrint = false).div {
            daisyStepIcon() {
            }
        }
        assertDocumented(html, "span", "step-icon", "With custom content in step-icon")
    }

    @Test
    fun custom_colors_daisyStep() {
        val html = createHTML(prettyPrint = false).ul {
            daisyStep(color = StepColor.Info) {
            }
        }
        assertDocumented(html, "li", "step step-info", "Custom colors")
    }

    @Test
    fun custom_colors_daisyStep_2() {
        val html = createHTML(prettyPrint = false).ul {
            daisyStep(color = StepColor.Error) {
            }
        }
        assertDocumented(html, "li", "step step-error", "Custom colors")
    }

    @Test
    fun with_scrollable_wrapper_daisyStep() {
        val html = createHTML(prettyPrint = false).ul {
            daisyStep(color = StepColor.Secondary) {
            }
        }
        assertDocumented(html, "li", "step step-secondary", "With scrollable wrapper")
    }

    @Test
    fun with_scrollable_wrapper_daisyStep_2() {
        val html = createHTML(prettyPrint = false).ul {
            daisyStep(color = StepColor.Accent) {
            }
        }
        assertDocumented(html, "li", "step step-accent", "With scrollable wrapper")
    }

    @Test
    fun with_scrollable_wrapper_daisyStep_3() {
        val html = createHTML(prettyPrint = false).ul {
            daisyStep(color = StepColor.Warning) {
            }
        }
        assertDocumented(html, "li", "step step-warning", "With scrollable wrapper")
    }
}
