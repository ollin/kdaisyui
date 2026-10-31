package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.FlowContent
import kotlinx.html.Tag
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class ChatCoverageTest {

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
    fun chat_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyChat(
                content = { },
            )
        }
        assertRendered(html, Expected("Chat defaults", "chat", closes = "</div></div>"))
    }

    @Test
    fun chat_all_flags() {
        val html = allFlags(Expected("Chat all flags", "chat zz-extra", closes = "</div></div>")) { daisyChat(id = ID, extraClasses = EXTRA, attrs = ATTRS, content = CONTENT) }
    }

    @Test
    fun chat_placement() = assertEachEntry("Chat placement", mapOf(ChatPlacement.Start to "chat chat-start", ChatPlacement.End to "chat chat-end")) { daisyChat(placement = it, content = { }) }

    @Test
    fun chatClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyChatClasses()
        }
        assertRendered(html, Expected("ChatClasses defaults", "chat"))
    }

    @Test
    fun chatClasses_all_flags() = assertRenders(Expected("ChatClasses all flags", "chat", content = false)) { daisyChatClasses() }

    @Test
    fun chatClasses_placement() = assertEachEntry("ChatClasses placement", mapOf(ChatPlacement.Start to "chat chat-start", ChatPlacement.End to "chat chat-end")) { daisyChatClasses(placement = it) }

    @Test
    fun chatImage_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyChatImage(
                content = { },
            )
        }
        assertRendered(html, Expected("ChatImage defaults", "chat-image", closes = "</div></div>"))
    }

    @Test
    fun chatImage_all_flags() {
        val html = allFlags(Expected("ChatImage all flags", "chat-image zz-extra", closes = "</div></div>")) { daisyChatImage(id = ID, extraClasses = EXTRA, attrs = ATTRS, content = CONTENT) }
    }

    @Test
    fun chatImageClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyChatImageClasses()
        }
        assertRendered(html, Expected("ChatImageClasses defaults", "chat-image"))
    }

    @Test
    fun chatImageClasses_all_flags() = assertRenders(Expected("ChatImageClasses all flags", "chat-image", content = false)) { daisyChatImageClasses() }

    @Test
    fun chatHeader_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyChatHeader(
                content = { },
            )
        }
        assertRendered(html, Expected("ChatHeader defaults", "chat-header", closes = "</div></div>"))
    }

    @Test
    fun chatHeader_all_flags() {
        val html = allFlags(Expected("ChatHeader all flags", "chat-header zz-extra", closes = "</div></div>")) { daisyChatHeader(id = ID, extraClasses = EXTRA, attrs = ATTRS, content = CONTENT) }
    }

    @Test
    fun chatHeaderClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyChatHeaderClasses()
        }
        assertRendered(html, Expected("ChatHeaderClasses defaults", "chat-header"))
    }

    @Test
    fun chatHeaderClasses_all_flags() = assertRenders(Expected("ChatHeaderClasses all flags", "chat-header", content = false)) { daisyChatHeaderClasses() }

    @Test
    fun chatFooter_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyChatFooter(
                content = { },
            )
        }
        assertRendered(html, Expected("ChatFooter defaults", "chat-footer", closes = "</div></div>"))
    }

    @Test
    fun chatFooter_all_flags() {
        val html = allFlags(Expected("ChatFooter all flags", "chat-footer zz-extra", closes = "</div></div>")) { daisyChatFooter(id = ID, extraClasses = EXTRA, attrs = ATTRS, content = CONTENT) }
    }

    @Test
    fun chatFooterClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyChatFooterClasses()
        }
        assertRendered(html, Expected("ChatFooterClasses defaults", "chat-footer"))
    }

    @Test
    fun chatFooterClasses_all_flags() = assertRenders(Expected("ChatFooterClasses all flags", "chat-footer", content = false)) { daisyChatFooterClasses() }

    @Test
    fun chatBubble_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyChatBubble(
                content = { },
            )
        }
        assertRendered(html, Expected("ChatBubble defaults", "chat-bubble", closes = "</div></div>"))
    }

    @Test
    fun chatBubble_all_flags() {
        val html = allFlags(Expected("ChatBubble all flags", "chat-bubble zz-extra", closes = "</div></div>")) { daisyChatBubble(id = ID, extraClasses = EXTRA, attrs = ATTRS, content = CONTENT) }
    }

    @Test
    fun chatBubble_color() = assertEachEntry("ChatBubble color", mapOf(ChatBubbleColor.Neutral to "chat-bubble chat-bubble-neutral", ChatBubbleColor.Primary to "chat-bubble chat-bubble-primary", ChatBubbleColor.Secondary to "chat-bubble chat-bubble-secondary", ChatBubbleColor.Accent to "chat-bubble chat-bubble-accent", ChatBubbleColor.Info to "chat-bubble chat-bubble-info", ChatBubbleColor.Success to "chat-bubble chat-bubble-success", ChatBubbleColor.Warning to "chat-bubble chat-bubble-warning", ChatBubbleColor.Error to "chat-bubble chat-bubble-error")) { daisyChatBubble(color = it, content = { }) }

    @Test
    fun chatBubbleClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyChatBubbleClasses()
        }
        assertRendered(html, Expected("ChatBubbleClasses defaults", "chat-bubble"))
    }

    @Test
    fun chatBubbleClasses_all_flags() = assertRenders(Expected("ChatBubbleClasses all flags", "chat-bubble", content = false)) { daisyChatBubbleClasses() }

    @Test
    fun chatBubbleClasses_color() = assertEachEntry("ChatBubbleClasses color", mapOf(ChatBubbleColor.Neutral to "chat-bubble chat-bubble-neutral", ChatBubbleColor.Primary to "chat-bubble chat-bubble-primary", ChatBubbleColor.Secondary to "chat-bubble chat-bubble-secondary", ChatBubbleColor.Accent to "chat-bubble chat-bubble-accent", ChatBubbleColor.Info to "chat-bubble chat-bubble-info", ChatBubbleColor.Success to "chat-bubble chat-bubble-success", ChatBubbleColor.Warning to "chat-bubble chat-bubble-warning", ChatBubbleColor.Error to "chat-bubble chat-bubble-error")) { daisyChatBubbleClasses(color = it) }
}
