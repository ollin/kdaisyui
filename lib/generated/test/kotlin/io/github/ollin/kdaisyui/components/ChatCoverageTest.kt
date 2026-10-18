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

    @Test
    fun chatBubble_color_neutral() {
        val html = createHTML(prettyPrint = false).div {
            daisyChatBubble(
                color = ChatBubbleColor.Neutral,
                content = { },
            )
        }
        assertRendered(html, "chat-bubble chat-bubble-neutral", "ChatBubble color Neutral")
    }

    @Test
    fun chatBubble_color_primary() {
        val html = createHTML(prettyPrint = false).div {
            daisyChatBubble(
                color = ChatBubbleColor.Primary,
                content = { },
            )
        }
        assertRendered(html, "chat-bubble chat-bubble-primary", "ChatBubble color Primary")
    }

    @Test
    fun chatBubble_color_secondary() {
        val html = createHTML(prettyPrint = false).div {
            daisyChatBubble(
                color = ChatBubbleColor.Secondary,
                content = { },
            )
        }
        assertRendered(html, "chat-bubble chat-bubble-secondary", "ChatBubble color Secondary")
    }

    @Test
    fun chatBubble_color_accent() {
        val html = createHTML(prettyPrint = false).div {
            daisyChatBubble(
                color = ChatBubbleColor.Accent,
                content = { },
            )
        }
        assertRendered(html, "chat-bubble chat-bubble-accent", "ChatBubble color Accent")
    }

    @Test
    fun chatBubble_color_info() {
        val html = createHTML(prettyPrint = false).div {
            daisyChatBubble(
                color = ChatBubbleColor.Info,
                content = { },
            )
        }
        assertRendered(html, "chat-bubble chat-bubble-info", "ChatBubble color Info")
    }

    @Test
    fun chatBubble_color_success() {
        val html = createHTML(prettyPrint = false).div {
            daisyChatBubble(
                color = ChatBubbleColor.Success,
                content = { },
            )
        }
        assertRendered(html, "chat-bubble chat-bubble-success", "ChatBubble color Success")
    }

    @Test
    fun chatBubble_color_warning() {
        val html = createHTML(prettyPrint = false).div {
            daisyChatBubble(
                color = ChatBubbleColor.Warning,
                content = { },
            )
        }
        assertRendered(html, "chat-bubble chat-bubble-warning", "ChatBubble color Warning")
    }

    @Test
    fun chatBubble_color_error() {
        val html = createHTML(prettyPrint = false).div {
            daisyChatBubble(
                color = ChatBubbleColor.Error,
                content = { },
            )
        }
        assertRendered(html, "chat-bubble chat-bubble-error", "ChatBubble color Error")
    }
}
