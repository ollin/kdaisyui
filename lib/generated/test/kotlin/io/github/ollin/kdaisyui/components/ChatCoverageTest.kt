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

    private fun assertCommonFlags(html: String, label: String, content: Boolean = true) {
        assertTrue(html.contains("id=\"x-cov-id\""), "$label id")
        assertTrue(html.contains("data-attrs=\"yes\""), "$label attrs")
        if (content) assertTrue(html.contains("data-content=\"yes\""), "$label content")
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
        assertCommonFlags(html, "Chat")
    }

    @Test
    fun chat_color_bubbleneutral() {
        val html = createHTML(prettyPrint = false).div {
            daisyChat(
                color = ChatColor.BubbleNeutral,
                content = { },
            )
        }
        assertRendered(html, "chat chat-bubble-neutral", "Chat color BubbleNeutral")
    }

    @Test
    fun chat_color_bubbleprimary() {
        val html = createHTML(prettyPrint = false).div {
            daisyChat(
                color = ChatColor.BubblePrimary,
                content = { },
            )
        }
        assertRendered(html, "chat chat-bubble-primary", "Chat color BubblePrimary")
    }

    @Test
    fun chat_color_bubblesecondary() {
        val html = createHTML(prettyPrint = false).div {
            daisyChat(
                color = ChatColor.BubbleSecondary,
                content = { },
            )
        }
        assertRendered(html, "chat chat-bubble-secondary", "Chat color BubbleSecondary")
    }

    @Test
    fun chat_color_bubbleaccent() {
        val html = createHTML(prettyPrint = false).div {
            daisyChat(
                color = ChatColor.BubbleAccent,
                content = { },
            )
        }
        assertRendered(html, "chat chat-bubble-accent", "Chat color BubbleAccent")
    }

    @Test
    fun chat_color_bubbleinfo() {
        val html = createHTML(prettyPrint = false).div {
            daisyChat(
                color = ChatColor.BubbleInfo,
                content = { },
            )
        }
        assertRendered(html, "chat chat-bubble-info", "Chat color BubbleInfo")
    }

    @Test
    fun chat_color_bubblesuccess() {
        val html = createHTML(prettyPrint = false).div {
            daisyChat(
                color = ChatColor.BubbleSuccess,
                content = { },
            )
        }
        assertRendered(html, "chat chat-bubble-success", "Chat color BubbleSuccess")
    }

    @Test
    fun chat_color_bubblewarning() {
        val html = createHTML(prettyPrint = false).div {
            daisyChat(
                color = ChatColor.BubbleWarning,
                content = { },
            )
        }
        assertRendered(html, "chat chat-bubble-warning", "Chat color BubbleWarning")
    }

    @Test
    fun chat_color_bubbleerror() {
        val html = createHTML(prettyPrint = false).div {
            daisyChat(
                color = ChatColor.BubbleError,
                content = { },
            )
        }
        assertRendered(html, "chat chat-bubble-error", "Chat color BubbleError")
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
        assertCommonFlags(html, "ChatImage")
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
        assertCommonFlags(html, "ChatHeader")
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
        assertCommonFlags(html, "ChatFooter")
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
        assertCommonFlags(html, "ChatBubble")
    }
}
