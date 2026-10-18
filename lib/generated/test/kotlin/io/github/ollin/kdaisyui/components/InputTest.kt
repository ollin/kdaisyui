package io.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.html.fieldSet
import kotlinx.html.label
import kotlinx.html.p

class InputTest {

    @Test
    fun text_input() {
        val html = createHTML(prettyPrint = false).div {
            daisyInput()
        }
        val expectedClasses = "input"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Text input")
        assertEquals("input", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Text input")
    }

    @Test
    fun text_input_with_text_label_inside_daisyKbd() {
        val html = createHTML(prettyPrint = false).div {
            daisyKbd(size = KbdSize.Sm) {
            }
        }
        val expectedClasses = "kbd kbd-sm"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Text input with text label inside")
        assertEquals("kbd", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Text input with text label inside")
    }

    @Test
    fun text_input_with_text_label_inside_daisyBadge() {
        val html = createHTML(prettyPrint = false).div {
            daisyBadge(color = BadgeColor.Neutral, size = BadgeSize.Xs) {
            }
        }
        val expectedClasses = "badge badge-neutral badge-xs"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Text input with text label inside")
        assertEquals("span", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Text input with text label inside")
    }

    @Test
    fun ghost_style() {
        val html = createHTML(prettyPrint = false).div {
            daisyInput(ghost = true)
        }
        val expectedClasses = "input input-ghost"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Ghost style")
        assertEquals("input", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Ghost style")
    }

    @Test
    fun with_fieldset_and_fieldset_legend_daisyFieldset() {
        val html = createHTML(prettyPrint = false).div {
            daisyFieldset() {
            }
        }
        val expectedClasses = "fieldset"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for With fieldset and fieldset-legend")
        assertEquals("fieldset", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for With fieldset and fieldset-legend")
    }

    @Test
    fun with_fieldset_and_fieldset_legend_daisyFieldsetLegend() {
        val html = createHTML(prettyPrint = false).fieldSet {
            daisyFieldsetLegend() {
            }
        }
        val expectedClasses = "fieldset-legend"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for With fieldset and fieldset-legend")
        assertEquals("legend", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for With fieldset and fieldset-legend")
    }

    @Test
    fun with_fieldset_and_fieldset_legend_daisyLabel() {
        val html = createHTML(prettyPrint = false).div {
            p { daisyLabelClasses() }
        }
        val expectedClasses = "label"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for With fieldset and fieldset-legend")
        assertEquals("p", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for With fieldset and fieldset-legend")
    }

    @Test
    fun input_colors() {
        val html = createHTML(prettyPrint = false).div {
            daisyInput(color = InputColor.Neutral)
        }
        val expectedClasses = "input input-neutral"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Input colors")
        assertEquals("input", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Input colors")
    }

    @Test
    fun input_colors_2() {
        val html = createHTML(prettyPrint = false).div {
            daisyInput(color = InputColor.Primary)
        }
        val expectedClasses = "input input-primary"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Input colors")
        assertEquals("input", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Input colors")
    }

    @Test
    fun input_colors_3() {
        val html = createHTML(prettyPrint = false).div {
            daisyInput(color = InputColor.Secondary)
        }
        val expectedClasses = "input input-secondary"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Input colors")
        assertEquals("input", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Input colors")
    }

    @Test
    fun input_colors_4() {
        val html = createHTML(prettyPrint = false).div {
            daisyInput(color = InputColor.Accent)
        }
        val expectedClasses = "input input-accent"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Input colors")
        assertEquals("input", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Input colors")
    }

    @Test
    fun input_colors_5() {
        val html = createHTML(prettyPrint = false).div {
            daisyInput(color = InputColor.Info)
        }
        val expectedClasses = "input input-info"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Input colors")
        assertEquals("input", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Input colors")
    }

    @Test
    fun input_colors_6() {
        val html = createHTML(prettyPrint = false).div {
            daisyInput(color = InputColor.Success)
        }
        val expectedClasses = "input input-success"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Input colors")
        assertEquals("input", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Input colors")
    }

    @Test
    fun input_colors_7() {
        val html = createHTML(prettyPrint = false).div {
            daisyInput(color = InputColor.Warning)
        }
        val expectedClasses = "input input-warning"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Input colors")
        assertEquals("input", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Input colors")
    }

    @Test
    fun input_colors_8() {
        val html = createHTML(prettyPrint = false).div {
            daisyInput(color = InputColor.Error)
        }
        val expectedClasses = "input input-error"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Input colors")
        assertEquals("input", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Input colors")
    }

    @Test
    fun sizes() {
        val html = createHTML(prettyPrint = false).div {
            daisyInput(size = InputSize.Xs)
        }
        val expectedClasses = "input input-xs"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Sizes")
        assertEquals("input", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Sizes")
    }

    @Test
    fun sizes_2() {
        val html = createHTML(prettyPrint = false).div {
            daisyInput(size = InputSize.Sm)
        }
        val expectedClasses = "input input-sm"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Sizes")
        assertEquals("input", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Sizes")
    }

    @Test
    fun sizes_3() {
        val html = createHTML(prettyPrint = false).div {
            daisyInput(size = InputSize.Md)
        }
        val expectedClasses = "input input-md"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Sizes")
        assertEquals("input", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Sizes")
    }

    @Test
    fun sizes_4() {
        val html = createHTML(prettyPrint = false).div {
            daisyInput(size = InputSize.Lg)
        }
        val expectedClasses = "input input-lg"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Sizes")
        assertEquals("input", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Sizes")
    }

    @Test
    fun sizes_5() {
        val html = createHTML(prettyPrint = false).div {
            daisyInput(size = InputSize.Xl)
        }
        val expectedClasses = "input input-xl"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Sizes")
        assertEquals("input", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Sizes")
    }

    @Test
    fun username_text_input_with_icon_and_validator() {
        val html = createHTML(prettyPrint = false).div {
            label { daisyInputClasses(); daisyValidatorClasses() }
        }
        val expectedClasses = "input validator"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Username text input with icon and validator")
        assertEquals("label", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Username text input with icon and validator")
    }

    @Test
    fun username_text_input_with_icon_and_validator_daisyValidatorHint() {
        val html = createHTML(prettyPrint = false).div {
            daisyValidatorHint() {
            }
        }
        val expectedClasses = "validator-hint"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Username text input with icon and validator")
        assertEquals("p", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Username text input with icon and validator")
    }

    @Test
    fun email_input_with_icon_validator_button_join_daisyJoin() {
        val html = createHTML(prettyPrint = false).div {
            daisyJoin() {
            }
        }
        val expectedClasses = "join"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Email input with icon, validator, button, join")
        assertEquals("div", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Email input with icon, validator, button, join")
    }

    @Test
    fun email_input_with_icon_validator_button_join_daisyButton() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(color = ButtonColor.Neutral) {
            }
        }
        val expectedClasses = "btn btn-neutral"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Email input with icon, validator, button, join")
        assertEquals("button", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Email input with icon, validator, button, join")
    }
}
