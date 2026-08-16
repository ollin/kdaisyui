package io.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals

class ChatTest {

    @Test
    fun chat_start_and_chat_end() {
        val html = createHTML(prettyPrint = false).div {
            daisyChat(start = true, end = true) {
            }
        }
        val expectedClasses = "chat chat-end chat-start"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for chat-start and chat-end")
    }

    @Test
    fun chat_with_image() {
        val html = createHTML(prettyPrint = false).div {
            daisyChat(start = true) {
            }
        }
        val expectedClasses = "chat chat-start"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Chat with image")
    }

    @Test
    fun chat_with_image_header_and_footer() {
        val html = createHTML(prettyPrint = false).div {
            daisyChat(start = true, end = true) {
            }
        }
        val expectedClasses = "chat chat-end chat-start"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Chat with image, header and footer")
    }

    @Test
    fun chat_with_header_and_footer() {
        val html = createHTML(prettyPrint = false).div {
            daisyChat(start = true) {
            }
        }
        val expectedClasses = "chat chat-start"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Chat with header and footer")
    }

    @Test
    fun chat_bubble_with_colors() {
        val html = createHTML(prettyPrint = false).div {
            daisyChat(start = true, end = true) {
            }
        }
        val expectedClasses = "chat chat-end chat-start"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Chat Bubble with colors")
    }
}
