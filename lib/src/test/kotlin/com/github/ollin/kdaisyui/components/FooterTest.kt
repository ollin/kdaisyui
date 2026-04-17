package com.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals

class FooterTest {

    @Test
    fun footer_vertical_by_default_horizontal_for_sm_and_up() {
        val html = createHTML(prettyPrint = false).div {
            daisyFooter(horizontal = true) {
            }
        }
        val expectedClasses = "footer footer-horizontal"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Footer (vertical by default, horizontal for sm and up)")
    }

    @Test
    fun footer_with_a_logo_section() {
        val html = createHTML(prettyPrint = false).div {
            daisyFooter(horizontal = true) {
            }
        }
        val expectedClasses = "footer footer-horizontal"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Footer with a logo section")
    }

    @Test
    fun footer_with_a_form() {
        val html = createHTML(prettyPrint = false).div {
            daisyFooter(horizontal = true) {
            }
        }
        val expectedClasses = "footer footer-horizontal"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Footer with a form")
    }

    @Test
    fun footer_with_logo_and_social_icons() {
        val html = createHTML(prettyPrint = false).div {
            daisyFooter() {
            }
        }
        val expectedClasses = "footer"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Footer with logo and social icons")
    }

    @Test
    fun footer_with_copyright_text() {
        val html = createHTML(prettyPrint = false).div {
            daisyFooter(center = true) {
            }
        }
        val expectedClasses = "footer footer-center"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Footer with copyright text")
    }

    @Test
    fun footer_with_copyright_text_and_social_icons() {
        val html = createHTML(prettyPrint = false).div {
            daisyFooter(horizontal = true) {
            }
        }
        val expectedClasses = "footer footer-horizontal"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Footer with copyright text and social icons")
    }

    @Test
    fun footer_with_links_and_social_icons() {
        val html = createHTML(prettyPrint = false).div {
            daisyFooter(horizontal = true) {
            }
        }
        val expectedClasses = "footer footer-horizontal"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Footer with links and social icons")
    }

    @Test
    fun footer_with_2_rows() {
        val html = createHTML(prettyPrint = false).div {
            daisyFooter(horizontal = true) {
            }
        }
        val expectedClasses = "footer footer-horizontal"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Footer with 2 rows")
    }

    @Test
    fun centered_footer_with_logo_and_social_icons() {
        val html = createHTML(prettyPrint = false).div {
            daisyFooter(horizontal = true, center = true) {
            }
        }
        val expectedClasses = "footer footer-center footer-horizontal"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Centered footer with logo and social icons")
    }

    @Test
    fun centered_footer_with_social_icons() {
        val html = createHTML(prettyPrint = false).div {
            daisyFooter(horizontal = true, center = true) {
            }
        }
        val expectedClasses = "footer footer-center footer-horizontal"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Centered footer with social icons")
    }

    @Test
    fun two_footer() {
        val html = createHTML(prettyPrint = false).div {
            daisyFooter(horizontal = true) {
            }
        }
        val expectedClasses = "footer footer-horizontal"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Two footer")
    }
}
