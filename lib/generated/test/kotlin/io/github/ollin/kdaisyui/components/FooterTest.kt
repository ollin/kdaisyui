package io.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals

class FooterTest {

    @Test
    fun footer_vertical_by_default_horizontal_for_sm_and_up() {
        val html = createHTML(prettyPrint = false).div {
            daisyFooter(extraClasses = "sm:footer-horizontal") {
            }
        }
        val expectedClasses = "footer sm:footer-horizontal"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Footer (vertical by default, horizontal for sm and up)")
    }

    @Test
    fun footer_vertical_by_default_horizontal_for_sm_and_up_daisyFooterTitle() {
        val html = createHTML(prettyPrint = false).div {
            daisyFooterTitle() {
            }
        }
        val expectedClasses = "footer-title"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Footer (vertical by default, horizontal for sm and up)")
    }

    @Test
    fun footer_vertical_by_default_horizontal_for_sm_and_up_daisyLink() {
        val html = createHTML(prettyPrint = false).div {
            daisyLink(hover = true) {
            }
        }
        val expectedClasses = "link link-hover"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Footer (vertical by default, horizontal for sm and up)")
    }

    @Test
    fun footer_with_a_form_daisyJoin() {
        val html = createHTML(prettyPrint = false).div {
            daisyJoin() {
            }
        }
        val expectedClasses = "join"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Footer with a form")
    }

    @Test
    fun footer_with_a_form_daisyInput() {
        val html = createHTML(prettyPrint = false).div {
            daisyInput() {
            }
        }
        val expectedClasses = "input"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Footer with a form")
    }

    @Test
    fun footer_with_a_form_daisyButton() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(color = ButtonColor.Primary) {
            }
        }
        val expectedClasses = "btn btn-primary"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Footer with a form")
    }

    @Test
    fun footer_with_copyright_text() {
        val html = createHTML(prettyPrint = false).div {
            daisyFooter(center = true, extraClasses = "sm:footer-horizontal") {
            }
        }
        val expectedClasses = "footer footer-center sm:footer-horizontal"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Footer with copyright text")
    }

    @Test
    fun centered_footer_with_logo_and_social_icons() {
        val html = createHTML(prettyPrint = false).div {
            daisyFooter(direction = FooterDirection.Horizontal, center = true) {
            }
        }
        val expectedClasses = "footer footer-center footer-horizontal"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Centered footer with logo and social icons")
    }

    @Test
    fun two_footer() {
        val html = createHTML(prettyPrint = false).div {
            daisyFooter() {
            }
        }
        val expectedClasses = "footer"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Two footer")
    }
}
