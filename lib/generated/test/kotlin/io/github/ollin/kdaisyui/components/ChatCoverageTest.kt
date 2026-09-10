package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class ChatCoverageTest {

    private fun assertRendered(html: String, classes: String, label: String, closes: String = "") {
        assertEquals(
            classes,
            html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" "),
            label,
        )
        if (closes.isNotEmpty()) assertTrue(html.endsWith(closes), "$label closes")
    }

    @Test
    fun chat_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyChat(
                content = { },
            )
        }
        assertRendered(html, "chat", "Chat defaults", closes = "</div></div>")
    }

    @Test
    fun chat_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyChat(
                id = htmlId("x-cov-id"),
                end = true,
                start = true,
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        assertRendered(html, "chat chat-end chat-start zz-extra", "Chat all flags", closes = "</div></div>")
        assertTrue(html.contains("id=\"x-cov-id\""), "Chat id")
        assertTrue(html.contains("data-attrs=\"yes\""), "Chat attrs")
        assertTrue(html.contains("data-content=\"yes\""), "Chat content")
    }

    @Test
    fun chat_variant_bubbleneutral() {
        val html = createHTML(prettyPrint = false).div {
            daisyChat(
                variant = ChatVariant.BubbleNeutral,
                content = { },
            )
        }
        assertRendered(html, "chat chat-bubble-neutral", "Chat variant BubbleNeutral")
    }

    @Test
    fun chat_variant_bubbleprimary() {
        val html = createHTML(prettyPrint = false).div {
            daisyChat(
                variant = ChatVariant.BubblePrimary,
                content = { },
            )
        }
        assertRendered(html, "chat chat-bubble-primary", "Chat variant BubblePrimary")
    }

    @Test
    fun chat_variant_bubblesecondary() {
        val html = createHTML(prettyPrint = false).div {
            daisyChat(
                variant = ChatVariant.BubbleSecondary,
                content = { },
            )
        }
        assertRendered(html, "chat chat-bubble-secondary", "Chat variant BubbleSecondary")
    }

    @Test
    fun chat_variant_bubbleaccent() {
        val html = createHTML(prettyPrint = false).div {
            daisyChat(
                variant = ChatVariant.BubbleAccent,
                content = { },
            )
        }
        assertRendered(html, "chat chat-bubble-accent", "Chat variant BubbleAccent")
    }

    @Test
    fun chat_variant_bubbleinfo() {
        val html = createHTML(prettyPrint = false).div {
            daisyChat(
                variant = ChatVariant.BubbleInfo,
                content = { },
            )
        }
        assertRendered(html, "chat chat-bubble-info", "Chat variant BubbleInfo")
    }

    @Test
    fun chat_variant_bubblesuccess() {
        val html = createHTML(prettyPrint = false).div {
            daisyChat(
                variant = ChatVariant.BubbleSuccess,
                content = { },
            )
        }
        assertRendered(html, "chat chat-bubble-success", "Chat variant BubbleSuccess")
    }

    @Test
    fun chat_variant_bubblewarning() {
        val html = createHTML(prettyPrint = false).div {
            daisyChat(
                variant = ChatVariant.BubbleWarning,
                content = { },
            )
        }
        assertRendered(html, "chat chat-bubble-warning", "Chat variant BubbleWarning")
    }

    @Test
    fun chat_variant_bubbleerror() {
        val html = createHTML(prettyPrint = false).div {
            daisyChat(
                variant = ChatVariant.BubbleError,
                content = { },
            )
        }
        assertRendered(html, "chat chat-bubble-error", "Chat variant BubbleError")
    }

    @Test
    fun chatImage_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyChatImage(
                content = { },
            )
        }
        assertRendered(html, "chat-image", "ChatImage defaults", closes = "</div></div>")
    }

    @Test
    fun chatImage_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyChatImage(
                id = htmlId("x-cov-id"),
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        assertRendered(html, "chat-image zz-extra", "ChatImage all flags", closes = "</div></div>")
        assertTrue(html.contains("id=\"x-cov-id\""), "ChatImage id")
        assertTrue(html.contains("data-attrs=\"yes\""), "ChatImage attrs")
        assertTrue(html.contains("data-content=\"yes\""), "ChatImage content")
    }

    @Test
    fun chatHeader_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyChatHeader(
                content = { },
            )
        }
        assertRendered(html, "chat-header", "ChatHeader defaults", closes = "</div></div>")
    }

    @Test
    fun chatHeader_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyChatHeader(
                id = htmlId("x-cov-id"),
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        assertRendered(html, "chat-header zz-extra", "ChatHeader all flags", closes = "</div></div>")
        assertTrue(html.contains("id=\"x-cov-id\""), "ChatHeader id")
        assertTrue(html.contains("data-attrs=\"yes\""), "ChatHeader attrs")
        assertTrue(html.contains("data-content=\"yes\""), "ChatHeader content")
    }

    @Test
    fun chatFooter_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyChatFooter(
                content = { },
            )
        }
        assertRendered(html, "chat-footer", "ChatFooter defaults", closes = "</div></div>")
    }

    @Test
    fun chatFooter_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyChatFooter(
                id = htmlId("x-cov-id"),
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        assertRendered(html, "chat-footer zz-extra", "ChatFooter all flags", closes = "</div></div>")
        assertTrue(html.contains("id=\"x-cov-id\""), "ChatFooter id")
        assertTrue(html.contains("data-attrs=\"yes\""), "ChatFooter attrs")
        assertTrue(html.contains("data-content=\"yes\""), "ChatFooter content")
    }

    @Test
    fun chatBubble_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyChatBubble(
                content = { },
            )
        }
        assertRendered(html, "chat-bubble", "ChatBubble defaults", closes = "</div></div>")
    }

    @Test
    fun chatBubble_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyChatBubble(
                id = htmlId("x-cov-id"),
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        assertRendered(html, "chat-bubble zz-extra", "ChatBubble all flags", closes = "</div></div>")
        assertTrue(html.contains("id=\"x-cov-id\""), "ChatBubble id")
        assertTrue(html.contains("data-attrs=\"yes\""), "ChatBubble attrs")
        assertTrue(html.contains("data-content=\"yes\""), "ChatBubble content")
    }
}
