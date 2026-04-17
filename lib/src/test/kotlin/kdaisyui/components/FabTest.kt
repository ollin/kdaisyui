package kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals

class FabTest {

    @Test
    fun fab_and_speed_dial_vertical() {
        val html = createHTML(prettyPrint = false).div {
            daisyFab() {
            }
        }
        val expectedClasses = "fab"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for FAB and Speed Dial (vertical)")
    }

    @Test
    fun fab_and_speed_dial_with_svg_icons() {
        val html = createHTML(prettyPrint = false).div {
            daisyFab() {
            }
        }
        val expectedClasses = "fab"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for FAB and Speed Dial with SVG icons")
    }

    @Test
    fun fab_and_speed_dial_with_labels() {
        val html = createHTML(prettyPrint = false).div {
            daisyFab() {
            }
        }
        val expectedClasses = "fab"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for FAB and Speed Dial with labels")
    }

    @Test
    fun fab_and_speed_dial_with_rectangle_buttons() {
        val html = createHTML(prettyPrint = false).div {
            daisyFab() {
            }
        }
        val expectedClasses = "fab"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for FAB and Speed Dial with rectangle buttons")
    }

    @Test
    fun fab_and_speed_dial_with_labels_and_fab_close_button() {
        val html = createHTML(prettyPrint = false).div {
            daisyFab() {
            }
        }
        val expectedClasses = "fab"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for FAB and Speed Dial with labels and fab-close button")
    }

    @Test
    fun fab_and_speed_dial_with_labels_and_fab_main_action_button() {
        val html = createHTML(prettyPrint = false).div {
            daisyFab() {
            }
        }
        val expectedClasses = "fab"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for FAB and Speed Dial with labels and fab-main-action Button")
    }

    @Test
    fun a_single_fab_floating_action_button() {
        val html = createHTML(prettyPrint = false).div {
            daisyFab() {
            }
        }
        val expectedClasses = "fab"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for A single FAB (Floating Action Button)")
    }

    @Test
    fun fab_flower_and_speed_dial() {
        val html = createHTML(prettyPrint = false).div {
            daisyFab(flower = true) {
            }
        }
        val expectedClasses = "fab fab-flower"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for FAB Flower and Speed Dial")
    }

    @Test
    fun fab_and_flower_speed_dial_with_svg_icons() {
        val html = createHTML(prettyPrint = false).div {
            daisyFab(flower = true) {
            }
        }
        val expectedClasses = "fab fab-flower"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for FAB and Flower Speed Dial with SVG icons")
    }

    @Test
    fun fab_and_flower_speed_dial_with_tooltip() {
        val html = createHTML(prettyPrint = false).div {
            daisyFab(flower = true) {
            }
        }
        val expectedClasses = "fab fab-flower"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for FAB and Flower Speed Dial with tooltip")
    }
}
