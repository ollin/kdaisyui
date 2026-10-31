package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.FlowContent
import kotlinx.html.Tag
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class AuraCoverageTest {

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
    fun aura_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyAura(
                content = { },
            )
        }
        assertRendered(html, Expected("Aura defaults", "aura", closes = "</div></div>"))
    }

    @Test
    fun aura_all_flags() {
        val html = allFlags(Expected("Aura all flags", "aura zz-extra", closes = "</div></div>")) { daisyAura(id = ID, extraClasses = EXTRA, attrs = ATTRS, content = CONTENT) }
    }

    @Test
    fun aura_size() = assertEachEntry("Aura size", mapOf(AuraSize.Xs to "aura aura-xs", AuraSize.Sm to "aura aura-sm", AuraSize.Md to "aura aura-md", AuraSize.Lg to "aura aura-lg", AuraSize.Xl to "aura aura-xl")) { daisyAura(size = it, content = { }) }

    @Test
    fun aura_style() = assertEachEntry("Aura style", mapOf(AuraStyle.Dual to "aura aura-dual", AuraStyle.Rainbow to "aura aura-rainbow", AuraStyle.Holo to "aura aura-holo", AuraStyle.Gold to "aura aura-gold", AuraStyle.Silver to "aura aura-silver", AuraStyle.Glow to "aura aura-glow")) { daisyAura(style = it, content = { }) }

    @Test
    fun auraClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyAuraClasses()
        }
        assertRendered(html, Expected("AuraClasses defaults", "aura"))
    }

    @Test
    fun auraClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyAuraClasses()
        }
        val expected = Expected("AuraClasses all flags", "aura", content = false)
        assertRendered(html, expected)
    }

    @Test
    fun auraClasses_size() = assertEachEntry("AuraClasses size", mapOf(AuraSize.Xs to "aura aura-xs", AuraSize.Sm to "aura aura-sm", AuraSize.Md to "aura aura-md", AuraSize.Lg to "aura aura-lg", AuraSize.Xl to "aura aura-xl")) { daisyAuraClasses(size = it) }

    @Test
    fun auraClasses_style() = assertEachEntry("AuraClasses style", mapOf(AuraStyle.Dual to "aura aura-dual", AuraStyle.Rainbow to "aura aura-rainbow", AuraStyle.Holo to "aura aura-holo", AuraStyle.Gold to "aura aura-gold", AuraStyle.Silver to "aura aura-silver", AuraStyle.Glow to "aura aura-glow")) { daisyAuraClasses(style = it) }
}
