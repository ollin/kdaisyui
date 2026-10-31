package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class NavbarCoverageTest {

    /** What a case must render: its classes, sorted, the tail it closes with, whether it took content. */
    private class Expected(val label: String, val classes: String, val closes: String = "", val content: Boolean = true)

    private fun assertRendered(html: String, expected: Expected) {
        assertEquals(expected.classes, html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" "), expected.label)
        if (expected.closes.isNotEmpty()) assertTrue(html.endsWith(expected.closes), "${expected.label} closes")
    }

    private fun assertCommonFlags(html: String, expected: Expected) {
        assertTrue(html.contains("id=\"x-cov-id\""), "${expected.label} id")
        assertTrue(html.contains("data-attrs=\"yes\""), "${expected.label} attrs")
        if (expected.content) assertTrue(html.contains("data-content=\"yes\""), "${expected.label} content")
    }

    @Test
    fun navbar_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyNavbar(
                content = { },
            )
        }
        assertRendered(html, Expected("Navbar defaults", "navbar", closes = "</div></div>"))
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
        val expected = Expected("Navbar all flags", "navbar zz-extra", closes = "</div></div>")
        assertRendered(html, expected)
        assertCommonFlags(html, expected)
    }

    @Test
    fun navbarClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyNavbarClasses()
        }
        assertRendered(html, Expected("NavbarClasses defaults", "navbar"))
    }

    @Test
    fun navbarClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyNavbarClasses()
        }
        val expected = Expected("NavbarClasses all flags", "navbar", content = false)
        assertRendered(html, expected)
    }

    @Test
    fun navbarStart_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyNavbarStart(
                content = { },
            )
        }
        assertRendered(html, Expected("NavbarStart defaults", "navbar-start", closes = "</div></div>"))
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
        val expected = Expected("NavbarStart all flags", "navbar-start zz-extra", closes = "</div></div>")
        assertRendered(html, expected)
        assertCommonFlags(html, expected)
    }

    @Test
    fun navbarStartClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyNavbarStartClasses()
        }
        assertRendered(html, Expected("NavbarStartClasses defaults", "navbar-start"))
    }

    @Test
    fun navbarStartClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyNavbarStartClasses()
        }
        val expected = Expected("NavbarStartClasses all flags", "navbar-start", content = false)
        assertRendered(html, expected)
    }

    @Test
    fun navbarCenter_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyNavbarCenter(
                content = { },
            )
        }
        assertRendered(html, Expected("NavbarCenter defaults", "navbar-center", closes = "</div></div>"))
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
        val expected = Expected("NavbarCenter all flags", "navbar-center zz-extra", closes = "</div></div>")
        assertRendered(html, expected)
        assertCommonFlags(html, expected)
    }

    @Test
    fun navbarCenterClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyNavbarCenterClasses()
        }
        assertRendered(html, Expected("NavbarCenterClasses defaults", "navbar-center"))
    }

    @Test
    fun navbarCenterClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyNavbarCenterClasses()
        }
        val expected = Expected("NavbarCenterClasses all flags", "navbar-center", content = false)
        assertRendered(html, expected)
    }

    @Test
    fun navbarEnd_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyNavbarEnd(
                content = { },
            )
        }
        assertRendered(html, Expected("NavbarEnd defaults", "navbar-end", closes = "</div></div>"))
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
        val expected = Expected("NavbarEnd all flags", "navbar-end zz-extra", closes = "</div></div>")
        assertRendered(html, expected)
        assertCommonFlags(html, expected)
    }

    @Test
    fun navbarEndClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyNavbarEndClasses()
        }
        assertRendered(html, Expected("NavbarEndClasses defaults", "navbar-end"))
    }

    @Test
    fun navbarEndClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyNavbarEndClasses()
        }
        val expected = Expected("NavbarEndClasses all flags", "navbar-end", content = false)
        assertRendered(html, expected)
    }
}
