package io.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals

class SelectTest {

    @Test
    fun select() {
        val html = createHTML(prettyPrint = false).div {
            daisySelect() {
            }
        }
        val expectedClasses = "select"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Select")
    }

    @Test
    fun ghost_no_background() {
        val html = createHTML(prettyPrint = false).div {
            daisySelect(ghost = true) {
            }
        }
        val expectedClasses = "select select-ghost"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Ghost (no background)")
    }

    @Test
    fun with_fieldset_and_labels() {
        val html = createHTML(prettyPrint = false).div {
            daisySelect() {
            }
        }
        val expectedClasses = "select"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for With fieldset and labels")
    }

    @Test
    fun primary_color() {
        val html = createHTML(prettyPrint = false).div {
            daisySelect() {
            }
        }
        val expectedClasses = "select"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Primary color")
    }

    @Test
    fun secondary_color() {
        val html = createHTML(prettyPrint = false).div {
            daisySelect() {
            }
        }
        val expectedClasses = "select"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Secondary color")
    }

    @Test
    fun accent_color() {
        val html = createHTML(prettyPrint = false).div {
            daisySelect() {
            }
        }
        val expectedClasses = "select"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Accent color")
    }

    @Test
    fun neutral_color() {
        val html = createHTML(prettyPrint = false).div {
            daisySelect() {
            }
        }
        val expectedClasses = "select"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Neutral color")
    }

    @Test
    fun info_color() {
        val html = createHTML(prettyPrint = false).div {
            daisySelect() {
            }
        }
        val expectedClasses = "select"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Info color")
    }

    @Test
    fun success_color() {
        val html = createHTML(prettyPrint = false).div {
            daisySelect() {
            }
        }
        val expectedClasses = "select"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Success color")
    }

    @Test
    fun warning_color() {
        val html = createHTML(prettyPrint = false).div {
            daisySelect() {
            }
        }
        val expectedClasses = "select"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Warning color")
    }

    @Test
    fun error_color() {
        val html = createHTML(prettyPrint = false).div {
            daisySelect() {
            }
        }
        val expectedClasses = "select"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Error color")
    }

    @Test
    fun sizes() {
        val html = createHTML(prettyPrint = false).div {
            daisySelect() {
            }
        }
        val expectedClasses = "select"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Sizes")
    }

    @Test
    fun disabled() {
        val html = createHTML(prettyPrint = false).div {
            daisySelect() {
            }
        }
        val expectedClasses = "select"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Disabled")
    }

    @Test
    fun using_os_native_style_for_the_options_dropdown() {
        val html = createHTML(prettyPrint = false).div {
            daisySelect() {
            }
        }
        val expectedClasses = "select"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Using OS native style for the options dropdown")
    }
}
