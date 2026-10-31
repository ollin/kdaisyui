package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class MockupPhoneCoverageTest {

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
    fun mockupPhone_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyMockupPhone(
                content = { },
            )
        }
        assertRendered(html, Expected("MockupPhone defaults", "mockup-phone", closes = "</div></div>"))
    }

    @Test
    fun mockupPhone_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyMockupPhone(
                id = htmlId("x-cov-id"),
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        val expected = Expected("MockupPhone all flags", "mockup-phone zz-extra", closes = "</div></div>")
        assertRendered(html, expected)
        assertCommonFlags(html, expected)
    }

    @Test
    fun mockupPhoneClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyMockupPhoneClasses()
        }
        assertRendered(html, Expected("MockupPhoneClasses defaults", "mockup-phone"))
    }

    @Test
    fun mockupPhoneClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyMockupPhoneClasses()
        }
        val expected = Expected("MockupPhoneClasses all flags", "mockup-phone", content = false)
        assertRendered(html, expected)
    }

    @Test
    fun mockupPhoneCamera_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyMockupPhoneCamera(
                content = { },
            )
        }
        assertRendered(html, Expected("MockupPhoneCamera defaults", "mockup-phone-camera", closes = "</div></div>"))
    }

    @Test
    fun mockupPhoneCamera_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyMockupPhoneCamera(
                id = htmlId("x-cov-id"),
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        val expected = Expected("MockupPhoneCamera all flags", "mockup-phone-camera zz-extra", closes = "</div></div>")
        assertRendered(html, expected)
        assertCommonFlags(html, expected)
    }

    @Test
    fun mockupPhoneCameraClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyMockupPhoneCameraClasses()
        }
        assertRendered(html, Expected("MockupPhoneCameraClasses defaults", "mockup-phone-camera"))
    }

    @Test
    fun mockupPhoneCameraClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyMockupPhoneCameraClasses()
        }
        val expected = Expected("MockupPhoneCameraClasses all flags", "mockup-phone-camera", content = false)
        assertRendered(html, expected)
    }

    @Test
    fun mockupPhoneDisplay_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyMockupPhoneDisplay(
                content = { },
            )
        }
        assertRendered(html, Expected("MockupPhoneDisplay defaults", "mockup-phone-display", closes = "</div></div>"))
    }

    @Test
    fun mockupPhoneDisplay_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyMockupPhoneDisplay(
                id = htmlId("x-cov-id"),
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        val expected = Expected("MockupPhoneDisplay all flags", "mockup-phone-display zz-extra", closes = "</div></div>")
        assertRendered(html, expected)
        assertCommonFlags(html, expected)
    }

    @Test
    fun mockupPhoneDisplayClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyMockupPhoneDisplayClasses()
        }
        assertRendered(html, Expected("MockupPhoneDisplayClasses defaults", "mockup-phone-display"))
    }

    @Test
    fun mockupPhoneDisplayClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyMockupPhoneDisplayClasses()
        }
        val expected = Expected("MockupPhoneDisplayClasses all flags", "mockup-phone-display", content = false)
        assertRendered(html, expected)
    }
}
