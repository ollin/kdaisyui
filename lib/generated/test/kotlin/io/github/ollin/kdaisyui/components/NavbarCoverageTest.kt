package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class NavbarCoverageTest {

    private fun assertRendered(html: String, classes: String, label: String, closes: String = "") {
        assertEquals(
            classes,
            html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" "),
            label,
        )
        if (closes.isNotEmpty()) assertTrue(html.endsWith(closes), "$label closes")
    }

    private fun assertCommonFlags(html: String, label: String, content: Boolean = true) {
        assertTrue(html.contains("id=\"x-cov-id\""), "$label id")
        assertTrue(html.contains("data-attrs=\"yes\""), "$label attrs")
        if (content) assertTrue(html.contains("data-content=\"yes\""), "$label content")
    }

    @Test
    fun navbar_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyNavbar(
                content = { },
            )
        }
        assertRendered(html, "navbar", "Navbar defaults", closes = "</div></div>")
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
        assertRendered(html, "navbar zz-extra", "Navbar all flags", closes = "</div></div>")
        assertCommonFlags(html, "Navbar")
    }

    @Test
    fun navbarStart_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyNavbarStart(
                content = { },
            )
        }
        assertRendered(html, "navbar-start", "NavbarStart defaults", closes = "</div></div>")
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
        assertRendered(html, "navbar-start zz-extra", "NavbarStart all flags", closes = "</div></div>")
        assertCommonFlags(html, "NavbarStart")
    }

    @Test
    fun navbarCenter_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyNavbarCenter(
                content = { },
            )
        }
        assertRendered(html, "navbar-center", "NavbarCenter defaults", closes = "</div></div>")
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
        assertRendered(html, "navbar-center zz-extra", "NavbarCenter all flags", closes = "</div></div>")
        assertCommonFlags(html, "NavbarCenter")
    }

    @Test
    fun navbarEnd_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyNavbarEnd(
                content = { },
            )
        }
        assertRendered(html, "navbar-end", "NavbarEnd defaults", closes = "</div></div>")
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
        assertRendered(html, "navbar-end zz-extra", "NavbarEnd all flags", closes = "</div></div>")
        assertCommonFlags(html, "NavbarEnd")
    }
}
