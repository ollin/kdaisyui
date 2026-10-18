package io.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals

class ChatTest {

    @Test
    fun chat_start_and_chat_end() {
        val html = createHTML(prettyPrint = false).div {
            daisyChat(placement = ChatPlacement.Start) {
            }
        }
        val expectedClasses = "chat chat-start"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for chat-start and chat-end")
        assertEquals("div", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for chat-start and chat-end")
    }

    @Test
    fun chat_start_and_chat_end_daisyChatBubble() {
        val html = createHTML(prettyPrint = false).div {
            daisyChatBubble() {
            }
        }
        val expectedClasses = "chat-bubble"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for chat-start and chat-end")
        assertEquals("div", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for chat-start and chat-end")
    }

    @Test
    fun chat_start_and_chat_end_2() {
        val html = createHTML(prettyPrint = false).div {
            daisyChat(placement = ChatPlacement.End) {
            }
        }
        val expectedClasses = "chat chat-end"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for chat-start and chat-end")
        assertEquals("div", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for chat-start and chat-end")
    }

    @Test
    fun chat_with_image_daisyChatImage() {
        val html = createHTML(prettyPrint = false).div {
            daisyChatImage(attrs = { daisyAvatarClasses() }) {
            }
        }
        val expectedClasses = "avatar chat-image"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Chat with image")
        assertEquals("div", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Chat with image")
    }

    @Test
    fun chat_with_image_header_and_footer_daisyChatHeader() {
        val html = createHTML(prettyPrint = false).div {
            daisyChatHeader() {
            }
        }
        val expectedClasses = "chat-header"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Chat with image, header and footer")
        assertEquals("div", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Chat with image, header and footer")
    }

    @Test
    fun chat_with_image_header_and_footer_daisyChatFooter() {
        val html = createHTML(prettyPrint = false).div {
            daisyChatFooter() {
            }
        }
        val expectedClasses = "chat-footer"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Chat with image, header and footer")
        assertEquals("div", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Chat with image, header and footer")
    }

    @Test
    fun chat_bubble_with_colors_daisyChatBubble() {
        val html = createHTML(prettyPrint = false).div {
            daisyChatBubble(color = ChatBubbleColor.Primary) {
            }
        }
        val expectedClasses = "chat-bubble chat-bubble-primary"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Chat Bubble with colors")
        assertEquals("div", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Chat Bubble with colors")
    }

    @Test
    fun chat_bubble_with_colors_daisyChatBubble_2() {
        val html = createHTML(prettyPrint = false).div {
            daisyChatBubble(color = ChatBubbleColor.Secondary) {
            }
        }
        val expectedClasses = "chat-bubble chat-bubble-secondary"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Chat Bubble with colors")
        assertEquals("div", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Chat Bubble with colors")
    }

    @Test
    fun chat_bubble_with_colors_daisyChatBubble_3() {
        val html = createHTML(prettyPrint = false).div {
            daisyChatBubble(color = ChatBubbleColor.Accent) {
            }
        }
        val expectedClasses = "chat-bubble chat-bubble-accent"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Chat Bubble with colors")
        assertEquals("div", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Chat Bubble with colors")
    }

    @Test
    fun chat_bubble_with_colors_daisyChatBubble_4() {
        val html = createHTML(prettyPrint = false).div {
            daisyChatBubble(color = ChatBubbleColor.Neutral) {
            }
        }
        val expectedClasses = "chat-bubble chat-bubble-neutral"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Chat Bubble with colors")
        assertEquals("div", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Chat Bubble with colors")
    }

    @Test
    fun chat_bubble_with_colors_daisyChatBubble_5() {
        val html = createHTML(prettyPrint = false).div {
            daisyChatBubble(color = ChatBubbleColor.Info) {
            }
        }
        val expectedClasses = "chat-bubble chat-bubble-info"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Chat Bubble with colors")
        assertEquals("div", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Chat Bubble with colors")
    }

    @Test
    fun chat_bubble_with_colors_daisyChatBubble_6() {
        val html = createHTML(prettyPrint = false).div {
            daisyChatBubble(color = ChatBubbleColor.Success) {
            }
        }
        val expectedClasses = "chat-bubble chat-bubble-success"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Chat Bubble with colors")
        assertEquals("div", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Chat Bubble with colors")
    }

    @Test
    fun chat_bubble_with_colors_daisyChatBubble_7() {
        val html = createHTML(prettyPrint = false).div {
            daisyChatBubble(color = ChatBubbleColor.Warning) {
            }
        }
        val expectedClasses = "chat-bubble chat-bubble-warning"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Chat Bubble with colors")
        assertEquals("div", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Chat Bubble with colors")
    }

    @Test
    fun chat_bubble_with_colors_daisyChatBubble_8() {
        val html = createHTML(prettyPrint = false).div {
            daisyChatBubble(color = ChatBubbleColor.Error) {
            }
        }
        val expectedClasses = "chat-bubble chat-bubble-error"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Chat Bubble with colors")
        assertEquals("div", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Chat Bubble with colors")
    }
}
