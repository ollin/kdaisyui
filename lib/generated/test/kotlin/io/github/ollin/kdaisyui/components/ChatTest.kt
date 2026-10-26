package io.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals

class ChatTest {

    private fun assertDocumented(html: String, tag: String, classes: String, case: String) {
        assertEquals(classes, html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" "), "Class mismatch for $case")
        assertEquals(tag, html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for $case")
    }

    @Test
    fun chat_start_and_chat_end() {
        val html = createHTML(prettyPrint = false).div {
            daisyChat(placement = ChatPlacement.Start) {
            }
        }
        assertDocumented(html, "div", "chat chat-start", "chat-start and chat-end")
    }

    @Test
    fun chat_start_and_chat_end_daisyChatBubble() {
        val html = createHTML(prettyPrint = false).div {
            daisyChatBubble() {
            }
        }
        assertDocumented(html, "div", "chat-bubble", "chat-start and chat-end")
    }

    @Test
    fun chat_start_and_chat_end_2() {
        val html = createHTML(prettyPrint = false).div {
            daisyChat(placement = ChatPlacement.End) {
            }
        }
        assertDocumented(html, "div", "chat chat-end", "chat-start and chat-end")
    }

    @Test
    fun chat_with_image_daisyChatImage() {
        val html = createHTML(prettyPrint = false).div {
            daisyChatImage(attrs = { daisyAvatarClasses() }) {
            }
        }
        assertDocumented(html, "div", "avatar chat-image", "Chat with image")
    }

    @Test
    fun chat_with_image_header_and_footer_daisyChatHeader() {
        val html = createHTML(prettyPrint = false).div {
            daisyChatHeader() {
            }
        }
        assertDocumented(html, "div", "chat-header", "Chat with image, header and footer")
    }

    @Test
    fun chat_with_image_header_and_footer_daisyChatFooter() {
        val html = createHTML(prettyPrint = false).div {
            daisyChatFooter() {
            }
        }
        assertDocumented(html, "div", "chat-footer", "Chat with image, header and footer")
    }

    @Test
    fun chat_bubble_with_colors_daisyChatBubble() {
        val html = createHTML(prettyPrint = false).div {
            daisyChatBubble(color = ChatBubbleColor.Primary) {
            }
        }
        assertDocumented(html, "div", "chat-bubble chat-bubble-primary", "Chat Bubble with colors")
    }

    @Test
    fun chat_bubble_with_colors_daisyChatBubble_2() {
        val html = createHTML(prettyPrint = false).div {
            daisyChatBubble(color = ChatBubbleColor.Secondary) {
            }
        }
        assertDocumented(html, "div", "chat-bubble chat-bubble-secondary", "Chat Bubble with colors")
    }

    @Test
    fun chat_bubble_with_colors_daisyChatBubble_3() {
        val html = createHTML(prettyPrint = false).div {
            daisyChatBubble(color = ChatBubbleColor.Accent) {
            }
        }
        assertDocumented(html, "div", "chat-bubble chat-bubble-accent", "Chat Bubble with colors")
    }

    @Test
    fun chat_bubble_with_colors_daisyChatBubble_4() {
        val html = createHTML(prettyPrint = false).div {
            daisyChatBubble(color = ChatBubbleColor.Neutral) {
            }
        }
        assertDocumented(html, "div", "chat-bubble chat-bubble-neutral", "Chat Bubble with colors")
    }

    @Test
    fun chat_bubble_with_colors_daisyChatBubble_5() {
        val html = createHTML(prettyPrint = false).div {
            daisyChatBubble(color = ChatBubbleColor.Info) {
            }
        }
        assertDocumented(html, "div", "chat-bubble chat-bubble-info", "Chat Bubble with colors")
    }

    @Test
    fun chat_bubble_with_colors_daisyChatBubble_6() {
        val html = createHTML(prettyPrint = false).div {
            daisyChatBubble(color = ChatBubbleColor.Success) {
            }
        }
        assertDocumented(html, "div", "chat-bubble chat-bubble-success", "Chat Bubble with colors")
    }

    @Test
    fun chat_bubble_with_colors_daisyChatBubble_7() {
        val html = createHTML(prettyPrint = false).div {
            daisyChatBubble(color = ChatBubbleColor.Warning) {
            }
        }
        assertDocumented(html, "div", "chat-bubble chat-bubble-warning", "Chat Bubble with colors")
    }

    @Test
    fun chat_bubble_with_colors_daisyChatBubble_8() {
        val html = createHTML(prettyPrint = false).div {
            daisyChatBubble(color = ChatBubbleColor.Error) {
            }
        }
        assertDocumented(html, "div", "chat-bubble chat-bubble-error", "Chat Bubble with colors")
    }
}
