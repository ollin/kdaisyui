package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class ChatCoverageTest {

    @Test
    fun chat_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyChat(
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("chat", actualClasses, "Chat defaults")
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
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("chat chat-end chat-start zz-extra", actualClasses, "Chat all flags")
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
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("chat chat-bubble-neutral", actualClasses, "Chat variant BubbleNeutral")
    }

    @Test
    fun chat_variant_bubbleprimary() {
        val html = createHTML(prettyPrint = false).div {
            daisyChat(
                variant = ChatVariant.BubblePrimary,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("chat chat-bubble-primary", actualClasses, "Chat variant BubblePrimary")
    }

    @Test
    fun chat_variant_bubblesecondary() {
        val html = createHTML(prettyPrint = false).div {
            daisyChat(
                variant = ChatVariant.BubbleSecondary,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("chat chat-bubble-secondary", actualClasses, "Chat variant BubbleSecondary")
    }

    @Test
    fun chat_variant_bubbleaccent() {
        val html = createHTML(prettyPrint = false).div {
            daisyChat(
                variant = ChatVariant.BubbleAccent,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("chat chat-bubble-accent", actualClasses, "Chat variant BubbleAccent")
    }

    @Test
    fun chat_variant_bubbleinfo() {
        val html = createHTML(prettyPrint = false).div {
            daisyChat(
                variant = ChatVariant.BubbleInfo,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("chat chat-bubble-info", actualClasses, "Chat variant BubbleInfo")
    }

    @Test
    fun chat_variant_bubblesuccess() {
        val html = createHTML(prettyPrint = false).div {
            daisyChat(
                variant = ChatVariant.BubbleSuccess,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("chat chat-bubble-success", actualClasses, "Chat variant BubbleSuccess")
    }

    @Test
    fun chat_variant_bubblewarning() {
        val html = createHTML(prettyPrint = false).div {
            daisyChat(
                variant = ChatVariant.BubbleWarning,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("chat chat-bubble-warning", actualClasses, "Chat variant BubbleWarning")
    }

    @Test
    fun chat_variant_bubbleerror() {
        val html = createHTML(prettyPrint = false).div {
            daisyChat(
                variant = ChatVariant.BubbleError,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("chat chat-bubble-error", actualClasses, "Chat variant BubbleError")
    }

    @Test
    fun chatImage_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyChatImage(
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("chat-image", actualClasses, "ChatImage defaults")
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
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("chat-image zz-extra", actualClasses, "ChatImage all flags")
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
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("chat-header", actualClasses, "ChatHeader defaults")
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
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("chat-header zz-extra", actualClasses, "ChatHeader all flags")
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
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("chat-footer", actualClasses, "ChatFooter defaults")
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
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("chat-footer zz-extra", actualClasses, "ChatFooter all flags")
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
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("chat-bubble", actualClasses, "ChatBubble defaults")
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
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("chat-bubble zz-extra", actualClasses, "ChatBubble all flags")
        assertTrue(html.contains("id=\"x-cov-id\""), "ChatBubble id")
        assertTrue(html.contains("data-attrs=\"yes\""), "ChatBubble attrs")
        assertTrue(html.contains("data-content=\"yes\""), "ChatBubble content")
    }
}
