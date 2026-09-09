package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class NavbarCoverageTest {

    @Test
    fun navbar_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyNavbar(
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("navbar", actualClasses, "Navbar defaults")
        assertTrue(html.endsWith("</div></div>"), "Navbar closes <div>")
    }

    @Test
    fun navbar_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyNavbar(
                id = htmlId("x-cov-id"),
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("navbar zz-extra", actualClasses, "Navbar all flags")
        assertTrue(html.contains("id=\"x-cov-id\""), "Navbar id")
        assertTrue(html.contains("data-attrs=\"yes\""), "Navbar attrs")
        assertTrue(html.contains("data-content=\"yes\""), "Navbar content")
        assertTrue(html.endsWith("</div></div>"), "Navbar closes <div>")
    }

    @Test
    fun navbarStart_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyNavbarStart(
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("navbar-start", actualClasses, "NavbarStart defaults")
        assertTrue(html.endsWith("</div></div>"), "NavbarStart closes <div>")
    }

    @Test
    fun navbarStart_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyNavbarStart(
                id = htmlId("x-cov-id"),
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("navbar-start zz-extra", actualClasses, "NavbarStart all flags")
        assertTrue(html.contains("id=\"x-cov-id\""), "NavbarStart id")
        assertTrue(html.contains("data-attrs=\"yes\""), "NavbarStart attrs")
        assertTrue(html.contains("data-content=\"yes\""), "NavbarStart content")
        assertTrue(html.endsWith("</div></div>"), "NavbarStart closes <div>")
    }

    @Test
    fun navbarCenter_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyNavbarCenter(
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("navbar-center", actualClasses, "NavbarCenter defaults")
        assertTrue(html.endsWith("</div></div>"), "NavbarCenter closes <div>")
    }

    @Test
    fun navbarCenter_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyNavbarCenter(
                id = htmlId("x-cov-id"),
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("navbar-center zz-extra", actualClasses, "NavbarCenter all flags")
        assertTrue(html.contains("id=\"x-cov-id\""), "NavbarCenter id")
        assertTrue(html.contains("data-attrs=\"yes\""), "NavbarCenter attrs")
        assertTrue(html.contains("data-content=\"yes\""), "NavbarCenter content")
        assertTrue(html.endsWith("</div></div>"), "NavbarCenter closes <div>")
    }

    @Test
    fun navbarEnd_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyNavbarEnd(
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("navbar-end", actualClasses, "NavbarEnd defaults")
        assertTrue(html.endsWith("</div></div>"), "NavbarEnd closes <div>")
    }

    @Test
    fun navbarEnd_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyNavbarEnd(
                id = htmlId("x-cov-id"),
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("navbar-end zz-extra", actualClasses, "NavbarEnd all flags")
        assertTrue(html.contains("id=\"x-cov-id\""), "NavbarEnd id")
        assertTrue(html.contains("data-attrs=\"yes\""), "NavbarEnd attrs")
        assertTrue(html.contains("data-content=\"yes\""), "NavbarEnd content")
        assertTrue(html.endsWith("</div></div>"), "NavbarEnd closes <div>")
    }
}
