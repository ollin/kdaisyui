package com.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class ButtonTest {

    @Test
    fun button() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton() {
            }
        }
        val expectedClasses = "btn"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Button")
    }

    @Test
    fun button_sizes() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton() {
            }
        }
        val expectedClasses = "btn"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Button sizes")
    }

    @Test
    fun responsive_button() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton() {
            }
        }
        val expectedClasses = "btn"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Responsive button")
    }

    @Test
    fun buttons_colors() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton() {
            }
        }
        val expectedClasses = "btn"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Buttons colors")
    }

    @Test
    fun soft_buttons() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(soft = true) {
            }
        }
        val expectedClasses = "btn btn-soft"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Soft buttons")
    }

    @Test
    fun outline_buttons() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(outline = true) {
            }
        }
        val expectedClasses = "btn btn-outline"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Outline buttons")
    }

    @Test
    fun dash_buttons() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(dash = true) {
            }
        }
        val expectedClasses = "btn btn-dash"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Dash buttons")
    }

    @Test
    fun neutral_button_with_outline_or_dash_style() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(outline = true, dash = true) {
            }
        }
        val expectedClasses = "btn btn-dash btn-outline"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for neutral button with outline or dash style")
    }

    @Test
    fun active_buttons() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(active = true) {
            }
        }
        val expectedClasses = "btn btn-active"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Active buttons")
    }

    @Test
    fun buttons_ghost_and_button_link() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(ghost = true, link = true) {
            }
        }
        val expectedClasses = "btn btn-ghost btn-link"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Buttons ghost and button link")
    }

    @Test
    fun wide_button() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(wide = true) {
            }
        }
        val expectedClasses = "btn btn-wide"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Wide button")
    }

    @Test
    fun buttons_with_any_html_tags() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton() {
            }
        }
        val expectedClasses = "btn"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Buttons with any HTML tags")
    }

    @Test
    fun disabled_buttons() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(disabled = true) {
            }
        }
        val expectedClasses = "btn"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Disabled buttons")
        assertTrue(html.contains("disabled=\"disabled\""), "Expected disabled attribute")
    }

    @Test
    fun square_button_and_circle_button() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(square = true, circle = true) {
            }
        }
        val expectedClasses = "btn btn-circle btn-square"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Square button and circle button")
    }

    @Test
    fun button_with_icon() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton() {
            }
        }
        val expectedClasses = "btn"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Button with Icon")
    }

    @Test
    fun button_block() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(block = true) {
            }
        }
        val expectedClasses = "btn btn-block"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Button block")
    }

    @Test
    fun button_with_loading_spinner() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(square = true) {
            }
        }
        val expectedClasses = "btn btn-square"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Button with loading spinner")
    }

    @Test
    fun login_buttons() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton() {
            }
        }
        val expectedClasses = "btn"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Login buttons")
    }
}
