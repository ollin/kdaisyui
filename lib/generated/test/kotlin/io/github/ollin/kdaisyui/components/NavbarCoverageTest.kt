package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.FlowContent
import kotlinx.html.Tag
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class NavbarCoverageTest {

    /** What a case must render: its classes, sorted, the tail it closes with, whether it took content. */
    private class Expected(val label: String, val classes: String, val closes: String = "", val content: Boolean = true)

    private fun assertRendered(html: String, expected: Expected) {
        assertEquals(expected.classes, html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" "), expected.label)
        if (expected.closes.isNotEmpty()) assertTrue(html.endsWith(expected.closes), "${expected.label} closes")
    }

    private val ID = htmlId("x-cov-id")
    private val EXTRA = "zz-extra"
    private val ATTRS: Tag.() -> Unit = { attributes["data-attrs"] = "yes" }
    private val CONTENT: Tag.() -> Unit = { attributes["data-content"] = "yes" }

    /** Renders one call and asserts the classes it writes. */
    private fun assertRenders(expected: Expected, render: FlowContent.() -> Unit) {
        assertRendered(createHTML(prettyPrint = false).div { render() }, expected)
    }

    /** Renders a call that passes every escape hatch, and asserts its classes and the hatches. */
    private fun allFlags(expected: Expected, render: FlowContent.() -> Unit): String {
        val html = createHTML(prettyPrint = false).div { render() }
        assertRendered(html, expected)
        assertCommonFlags(html, expected)
        return html
    }

    /** Renders one call per entry of an axis and asserts the classes each must write. */
    private fun <E> assertEachEntry(label: String, classesOf: Map<E, String>, render: FlowContent.(E) -> Unit) {
        for ((entry, classes) in classesOf) {
            assertRendered(createHTML(prettyPrint = false).div { render(entry) }, Expected("$label $entry", classes))
        }
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
        val html = allFlags(Expected("Navbar all flags", "navbar zz-extra", closes = "</div></div>")) { daisyNavbar(id = ID, extraClasses = EXTRA, attrs = ATTRS, content = CONTENT) }
    }

    @Test
    fun navbarClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyNavbarClasses()
        }
        assertRendered(html, Expected("NavbarClasses defaults", "navbar"))
    }

    @Test
    fun navbarClasses_all_flags() = assertRenders(Expected("NavbarClasses all flags", "navbar", content = false)) { daisyNavbarClasses() }

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
        val html = allFlags(Expected("NavbarStart all flags", "navbar-start zz-extra", closes = "</div></div>")) { daisyNavbarStart(id = ID, extraClasses = EXTRA, attrs = ATTRS, content = CONTENT) }
    }

    @Test
    fun navbarStartClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyNavbarStartClasses()
        }
        assertRendered(html, Expected("NavbarStartClasses defaults", "navbar-start"))
    }

    @Test
    fun navbarStartClasses_all_flags() = assertRenders(Expected("NavbarStartClasses all flags", "navbar-start", content = false)) { daisyNavbarStartClasses() }

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
        val html = allFlags(Expected("NavbarCenter all flags", "navbar-center zz-extra", closes = "</div></div>")) { daisyNavbarCenter(id = ID, extraClasses = EXTRA, attrs = ATTRS, content = CONTENT) }
    }

    @Test
    fun navbarCenterClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyNavbarCenterClasses()
        }
        assertRendered(html, Expected("NavbarCenterClasses defaults", "navbar-center"))
    }

    @Test
    fun navbarCenterClasses_all_flags() = assertRenders(Expected("NavbarCenterClasses all flags", "navbar-center", content = false)) { daisyNavbarCenterClasses() }

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
        val html = allFlags(Expected("NavbarEnd all flags", "navbar-end zz-extra", closes = "</div></div>")) { daisyNavbarEnd(id = ID, extraClasses = EXTRA, attrs = ATTRS, content = CONTENT) }
    }

    @Test
    fun navbarEndClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyNavbarEndClasses()
        }
        assertRendered(html, Expected("NavbarEndClasses defaults", "navbar-end"))
    }

    @Test
    fun navbarEndClasses_all_flags() = assertRenders(Expected("NavbarEndClasses all flags", "navbar-end", content = false)) { daisyNavbarEndClasses() }
}
