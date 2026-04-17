package kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals

class InputTest {

    @Test
    fun text_input() {
        val html = createHTML(prettyPrint = false).div {
            daisyInput() {
            }
        }
        val expectedClasses = "input"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Text input")
    }

    @Test
    fun text_input_with_text_label_inside() {
        val html = createHTML(prettyPrint = false).div {
            daisyInput() {
            }
        }
        val expectedClasses = "input"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Text input with text label inside")
    }

    @Test
    fun ghost_style() {
        val html = createHTML(prettyPrint = false).div {
            daisyInput(ghost = true) {
            }
        }
        val expectedClasses = "input input-ghost"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Ghost style")
    }

    @Test
    fun with_fieldset_and_fieldset_legend() {
        val html = createHTML(prettyPrint = false).div {
            daisyInput() {
            }
        }
        val expectedClasses = "input"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for With fieldset and fieldset-legend")
    }

    @Test
    fun input_colors() {
        val html = createHTML(prettyPrint = false).div {
            daisyInput() {
            }
        }
        val expectedClasses = "input"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Input colors")
    }

    @Test
    fun sizes() {
        val html = createHTML(prettyPrint = false).div {
            daisyInput() {
            }
        }
        val expectedClasses = "input"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Sizes")
    }

    @Test
    fun disabled() {
        val html = createHTML(prettyPrint = false).div {
            daisyInput() {
            }
        }
        val expectedClasses = "input"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Disabled")
    }

    @Test
    fun text_input_with_data_list_suggestion() {
        val html = createHTML(prettyPrint = false).div {
            daisyInput() {
            }
        }
        val expectedClasses = "input"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Text input with data list suggestion")
    }

    @Test
    fun date_input() {
        val html = createHTML(prettyPrint = false).div {
            daisyInput() {
            }
        }
        val expectedClasses = "input"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Date input")
    }

    @Test
    fun time_input() {
        val html = createHTML(prettyPrint = false).div {
            daisyInput() {
            }
        }
        val expectedClasses = "input"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Time input")
    }

    @Test
    fun datetime_local_input() {
        val html = createHTML(prettyPrint = false).div {
            daisyInput() {
            }
        }
        val expectedClasses = "input"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for datetime-local input")
    }

    @Test
    fun username_text_input_with_icon_and_validator() {
        val html = createHTML(prettyPrint = false).div {
            daisyInput() {
            }
        }
        val expectedClasses = "input"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Username text input with icon and validator")
    }

    @Test
    fun search_input_with_icon() {
        val html = createHTML(prettyPrint = false).div {
            daisyInput() {
            }
        }
        val expectedClasses = "input"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Search input with icon")
    }

    @Test
    fun email_input_with_icon_and_validator() {
        val html = createHTML(prettyPrint = false).div {
            daisyInput() {
            }
        }
        val expectedClasses = "input"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Email input with icon and validator")
    }

    @Test
    fun email_input_with_icon_validator_button_join() {
        val html = createHTML(prettyPrint = false).div {
            daisyInput() {
            }
        }
        val expectedClasses = "input"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Email input with icon, validator, button, join")
    }

    @Test
    fun password_input_with_icon_and_validator() {
        val html = createHTML(prettyPrint = false).div {
            daisyInput() {
            }
        }
        val expectedClasses = "input"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Password input with icon and validator")
    }

    @Test
    fun number_input_with_validator() {
        val html = createHTML(prettyPrint = false).div {
            daisyInput() {
            }
        }
        val expectedClasses = "input"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Number input with validator")
    }

    @Test
    fun telephone_number_input_with_icon_and_validator() {
        val html = createHTML(prettyPrint = false).div {
            daisyInput() {
            }
        }
        val expectedClasses = "input"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Telephone number input with icon and validator")
    }

    @Test
    fun url_with_icon_and_validator() {
        val html = createHTML(prettyPrint = false).div {
            daisyInput() {
            }
        }
        val expectedClasses = "input"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for URL with icon and validator")
    }
}
