package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.FlowContent
import kotlinx.html.Tag
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class MockupPhoneCoverageTest {

    /** What a case must render: its classes, sorted, and the tail it closes with. */
    private class Expected(val label: String, val classes: String, val closes: String = "")

    private fun assertRendered(html: String, expected: Expected) {
        assertEquals(expected.classes, html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" "), expected.label)
        if (expected.closes.isNotEmpty()) assertTrue(html.endsWith(expected.closes), "${expected.label} closes")
    }

    private val ID = htmlId("x-cov-id")
    private val EXTRA = "zz-extra"
    private val BLOCK: Tag.() -> Unit = { attributes["data-block"] = "yes" }

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
        assertTrue(html.contains("data-block=\"yes\""), "${expected.label} block")
    }

    @Test
    fun mockupPhone_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyMockupPhone()
        }
        assertRendered(html, Expected("MockupPhone defaults", "mockup-phone", closes = "</div></div>"))
    }

    @Test
    fun mockupPhone_all_flags() {
        val html = allFlags(Expected("MockupPhone all flags", "mockup-phone zz-extra", closes = "</div></div>")) { daisyMockupPhone(id = ID, extraClasses = EXTRA, block = BLOCK) }
    }

    @Test
    fun mockupPhoneClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyMockupPhoneClasses()
        }
        assertRendered(html, Expected("MockupPhoneClasses defaults", "mockup-phone"))
    }

    @Test
    fun mockupPhoneClasses_all_flags() = assertRenders(Expected("MockupPhoneClasses all flags", "mockup-phone")) { daisyMockupPhoneClasses() }

    @Test
    fun mockupPhoneCamera_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyMockupPhoneCamera()
        }
        assertRendered(html, Expected("MockupPhoneCamera defaults", "mockup-phone-camera", closes = "</div></div>"))
    }

    @Test
    fun mockupPhoneCamera_all_flags() {
        val html = allFlags(Expected("MockupPhoneCamera all flags", "mockup-phone-camera zz-extra", closes = "</div></div>")) { daisyMockupPhoneCamera(id = ID, extraClasses = EXTRA, block = BLOCK) }
    }

    @Test
    fun mockupPhoneCameraClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyMockupPhoneCameraClasses()
        }
        assertRendered(html, Expected("MockupPhoneCameraClasses defaults", "mockup-phone-camera"))
    }

    @Test
    fun mockupPhoneCameraClasses_all_flags() = assertRenders(Expected("MockupPhoneCameraClasses all flags", "mockup-phone-camera")) { daisyMockupPhoneCameraClasses() }

    @Test
    fun mockupPhoneDisplay_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyMockupPhoneDisplay()
        }
        assertRendered(html, Expected("MockupPhoneDisplay defaults", "mockup-phone-display", closes = "</div></div>"))
    }

    @Test
    fun mockupPhoneDisplay_all_flags() {
        val html = allFlags(Expected("MockupPhoneDisplay all flags", "mockup-phone-display zz-extra", closes = "</div></div>")) { daisyMockupPhoneDisplay(id = ID, extraClasses = EXTRA, block = BLOCK) }
    }

    @Test
    fun mockupPhoneDisplayClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyMockupPhoneDisplayClasses()
        }
        assertRendered(html, Expected("MockupPhoneDisplayClasses defaults", "mockup-phone-display"))
    }

    @Test
    fun mockupPhoneDisplayClasses_all_flags() = assertRenders(Expected("MockupPhoneDisplayClasses all flags", "mockup-phone-display")) { daisyMockupPhoneDisplayClasses() }
}
