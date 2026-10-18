package io.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.html.fieldSet
import kotlinx.html.label
import kotlinx.html.p

class InputTest {

    private fun assertDocumented(html: String, tag: String, classes: String, case: String) {
        assertEquals(classes, html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" "), "Class mismatch for $case")
        assertEquals(tag, html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for $case")
    }

    @Test
    fun text_input() {
        val html = createHTML(prettyPrint = false).div {
            daisyInput()
        }
        assertDocumented(html, "input", "input", "Text input")
    }

    @Test
    fun text_input_with_text_label_inside_daisyKbd() {
        val html = createHTML(prettyPrint = false).div {
            daisyKbd(size = KbdSize.Sm) {
            }
        }
        assertDocumented(html, "kbd", "kbd kbd-sm", "Text input with text label inside")
    }

    @Test
    fun text_input_with_text_label_inside_daisyBadge() {
        val html = createHTML(prettyPrint = false).div {
            daisyBadge(color = BadgeColor.Neutral, size = BadgeSize.Xs) {
            }
        }
        assertDocumented(html, "span", "badge badge-neutral badge-xs", "Text input with text label inside")
    }

    @Test
    fun ghost_style() {
        val html = createHTML(prettyPrint = false).div {
            daisyInput(ghost = true)
        }
        assertDocumented(html, "input", "input input-ghost", "Ghost style")
    }

    @Test
    fun with_fieldset_and_fieldset_legend_daisyFieldset() {
        val html = createHTML(prettyPrint = false).div {
            daisyFieldset() {
            }
        }
        assertDocumented(html, "fieldset", "fieldset", "With fieldset and fieldset-legend")
    }

    @Test
    fun with_fieldset_and_fieldset_legend_daisyFieldsetLegend() {
        val html = createHTML(prettyPrint = false).fieldSet {
            daisyFieldsetLegend() {
            }
        }
        assertDocumented(html, "legend", "fieldset-legend", "With fieldset and fieldset-legend")
    }

    @Test
    fun with_fieldset_and_fieldset_legend_daisyLabel() {
        val html = createHTML(prettyPrint = false).div {
            p { daisyLabelClasses() }
        }
        assertDocumented(html, "p", "label", "With fieldset and fieldset-legend")
    }

    @Test
    fun input_colors() {
        val html = createHTML(prettyPrint = false).div {
            daisyInput(color = InputColor.Neutral)
        }
        assertDocumented(html, "input", "input input-neutral", "Input colors")
    }

    @Test
    fun input_colors_2() {
        val html = createHTML(prettyPrint = false).div {
            daisyInput(color = InputColor.Primary)
        }
        assertDocumented(html, "input", "input input-primary", "Input colors")
    }

    @Test
    fun input_colors_3() {
        val html = createHTML(prettyPrint = false).div {
            daisyInput(color = InputColor.Secondary)
        }
        assertDocumented(html, "input", "input input-secondary", "Input colors")
    }

    @Test
    fun input_colors_4() {
        val html = createHTML(prettyPrint = false).div {
            daisyInput(color = InputColor.Accent)
        }
        assertDocumented(html, "input", "input input-accent", "Input colors")
    }

    @Test
    fun input_colors_5() {
        val html = createHTML(prettyPrint = false).div {
            daisyInput(color = InputColor.Info)
        }
        assertDocumented(html, "input", "input input-info", "Input colors")
    }

    @Test
    fun input_colors_6() {
        val html = createHTML(prettyPrint = false).div {
            daisyInput(color = InputColor.Success)
        }
        assertDocumented(html, "input", "input input-success", "Input colors")
    }

    @Test
    fun input_colors_7() {
        val html = createHTML(prettyPrint = false).div {
            daisyInput(color = InputColor.Warning)
        }
        assertDocumented(html, "input", "input input-warning", "Input colors")
    }

    @Test
    fun input_colors_8() {
        val html = createHTML(prettyPrint = false).div {
            daisyInput(color = InputColor.Error)
        }
        assertDocumented(html, "input", "input input-error", "Input colors")
    }

    @Test
    fun sizes() {
        val html = createHTML(prettyPrint = false).div {
            daisyInput(size = InputSize.Xs)
        }
        assertDocumented(html, "input", "input input-xs", "Sizes")
    }

    @Test
    fun sizes_2() {
        val html = createHTML(prettyPrint = false).div {
            daisyInput(size = InputSize.Sm)
        }
        assertDocumented(html, "input", "input input-sm", "Sizes")
    }

    @Test
    fun sizes_3() {
        val html = createHTML(prettyPrint = false).div {
            daisyInput(size = InputSize.Md)
        }
        assertDocumented(html, "input", "input input-md", "Sizes")
    }

    @Test
    fun sizes_4() {
        val html = createHTML(prettyPrint = false).div {
            daisyInput(size = InputSize.Lg)
        }
        assertDocumented(html, "input", "input input-lg", "Sizes")
    }

    @Test
    fun sizes_5() {
        val html = createHTML(prettyPrint = false).div {
            daisyInput(size = InputSize.Xl)
        }
        assertDocumented(html, "input", "input input-xl", "Sizes")
    }

    @Test
    fun username_text_input_with_icon_and_validator() {
        val html = createHTML(prettyPrint = false).div {
            label { daisyInputClasses(); daisyValidatorClasses() }
        }
        assertDocumented(html, "label", "input validator", "Username text input with icon and validator")
    }

    @Test
    fun username_text_input_with_icon_and_validator_daisyValidatorHint() {
        val html = createHTML(prettyPrint = false).div {
            daisyValidatorHint() {
            }
        }
        assertDocumented(html, "p", "validator-hint", "Username text input with icon and validator")
    }

    @Test
    fun email_input_with_icon_validator_button_join_daisyJoin() {
        val html = createHTML(prettyPrint = false).div {
            daisyJoin() {
            }
        }
        assertDocumented(html, "div", "join", "Email input with icon, validator, button, join")
    }

    @Test
    fun email_input_with_icon_validator_button_join_daisyButton() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(color = ButtonColor.Neutral) {
            }
        }
        assertDocumented(html, "button", "btn btn-neutral", "Email input with icon, validator, button, join")
    }
}
