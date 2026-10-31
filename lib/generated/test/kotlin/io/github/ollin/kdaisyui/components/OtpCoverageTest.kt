package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.FlowContent
import kotlinx.html.Tag
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class OtpCoverageTest {

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
    fun otp_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyOtp(
                content = { },
            )
        }
        assertRendered(html, Expected("Otp defaults", "otp", closes = "</label></div>"))
    }

    @Test
    fun otp_all_flags() {
        val html = allFlags(Expected("Otp all flags", "otp otp-joined zz-extra", closes = "</label></div>")) { daisyOtp(id = ID, joined = true, extraClasses = EXTRA, attrs = ATTRS, content = CONTENT) }
    }

    @Test
    fun otp_color() = assertEachEntry("Otp color", mapOf(OtpColor.Neutral to "otp otp-neutral", OtpColor.Primary to "otp otp-primary", OtpColor.Secondary to "otp otp-secondary", OtpColor.Accent to "otp otp-accent", OtpColor.Success to "otp otp-success", OtpColor.Info to "otp otp-info", OtpColor.Warning to "otp otp-warning", OtpColor.Error to "otp otp-error")) { daisyOtp(color = it, content = { }) }

    @Test
    fun otp_size() = assertEachEntry("Otp size", mapOf(OtpSize.Xs to "otp otp-xs", OtpSize.Sm to "otp otp-sm", OtpSize.Md to "otp otp-md", OtpSize.Lg to "otp otp-lg", OtpSize.Xl to "otp otp-xl")) { daisyOtp(size = it, content = { }) }

    @Test
    fun otpClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyOtpClasses()
        }
        assertRendered(html, Expected("OtpClasses defaults", "otp"))
    }

    @Test
    fun otpClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyOtpClasses(
                joined = true,
            )
        }
        val expected = Expected("OtpClasses all flags", "otp otp-joined", content = false)
        assertRendered(html, expected)
    }

    @Test
    fun otpClasses_color() = assertEachEntry("OtpClasses color", mapOf(OtpColor.Neutral to "otp otp-neutral", OtpColor.Primary to "otp otp-primary", OtpColor.Secondary to "otp otp-secondary", OtpColor.Accent to "otp otp-accent", OtpColor.Success to "otp otp-success", OtpColor.Info to "otp otp-info", OtpColor.Warning to "otp otp-warning", OtpColor.Error to "otp otp-error")) { daisyOtpClasses(color = it) }

    @Test
    fun otpClasses_size() = assertEachEntry("OtpClasses size", mapOf(OtpSize.Xs to "otp otp-xs", OtpSize.Sm to "otp otp-sm", OtpSize.Md to "otp otp-md", OtpSize.Lg to "otp otp-lg", OtpSize.Xl to "otp otp-xl")) { daisyOtpClasses(size = it) }
}
