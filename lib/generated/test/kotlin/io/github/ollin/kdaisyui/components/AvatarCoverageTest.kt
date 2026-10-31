package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.FlowContent
import kotlinx.html.Tag
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class AvatarCoverageTest {

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
    fun avatar_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyAvatar(
                content = { },
            )
        }
        assertRendered(html, Expected("Avatar defaults", "avatar", closes = "</div></div>"))
    }

    @Test
    fun avatar_all_flags() {
        val html = allFlags(Expected("Avatar all flags", "avatar avatar-placeholder zz-extra", closes = "</div></div>")) { daisyAvatar(id = ID, placeholder = true, extraClasses = EXTRA, attrs = ATTRS, content = CONTENT) }
    }

    @Test
    fun avatar_presence() = assertEachEntry("Avatar presence", mapOf(AvatarPresence.Online to "avatar avatar-online", AvatarPresence.Offline to "avatar avatar-offline")) { daisyAvatar(presence = it, content = { }) }

    @Test
    fun avatarClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyAvatarClasses()
        }
        assertRendered(html, Expected("AvatarClasses defaults", "avatar"))
    }

    @Test
    fun avatarClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyAvatarClasses(
                placeholder = true,
            )
        }
        val expected = Expected("AvatarClasses all flags", "avatar avatar-placeholder", content = false)
        assertRendered(html, expected)
    }

    @Test
    fun avatarClasses_presence() = assertEachEntry("AvatarClasses presence", mapOf(AvatarPresence.Online to "avatar avatar-online", AvatarPresence.Offline to "avatar avatar-offline")) { daisyAvatarClasses(presence = it) }

    @Test
    fun avatarGroup_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyAvatarGroup(
                content = { },
            )
        }
        assertRendered(html, Expected("AvatarGroup defaults", "avatar-group", closes = "</div></div>"))
    }

    @Test
    fun avatarGroup_all_flags() {
        val html = allFlags(Expected("AvatarGroup all flags", "avatar-group zz-extra", closes = "</div></div>")) { daisyAvatarGroup(id = ID, extraClasses = EXTRA, attrs = ATTRS, content = CONTENT) }
    }

    @Test
    fun avatarGroupClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyAvatarGroupClasses()
        }
        assertRendered(html, Expected("AvatarGroupClasses defaults", "avatar-group"))
    }

    @Test
    fun avatarGroupClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyAvatarGroupClasses()
        }
        val expected = Expected("AvatarGroupClasses all flags", "avatar-group", content = false)
        assertRendered(html, expected)
    }
}
