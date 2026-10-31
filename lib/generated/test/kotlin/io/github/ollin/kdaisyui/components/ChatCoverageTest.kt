package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class ChatCoverageTest {

    /** What a case must render: its classes, sorted, the tail it closes with, whether it took content. */
    private class Expected(val label: String, val classes: String, val closes: String = "", val content: Boolean = true)

    private fun assertRendered(html: String, expected: Expected) {
        assertEquals(expected.classes, html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" "), expected.label)
        if (expected.closes.isNotEmpty()) assertTrue(html.endsWith(expected.closes), "${expected.label} closes")
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
        val html = createHTML(prettyPrint = false).div {
            daisyChat(
                id = htmlId("x-cov-id"),
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        val expected = Expected("Chat all flags", "chat zz-extra", closes = "</div></div>")
        assertRendered(html, expected)
        assertCommonFlags(html, expected)
    }

    @Test
    fun chat_placement_start() {
        val html = createHTML(prettyPrint = false).div {
            daisyChat(
                placement = ChatPlacement.Start,
                content = { },
            )
        }
        assertRendered(html, Expected("Chat placement Start", "chat chat-start"))
    }

    @Test
    fun chat_placement_end() {
        val html = createHTML(prettyPrint = false).div {
            daisyChat(
                placement = ChatPlacement.End,
                content = { },
            )
        }
        assertRendered(html, Expected("Chat placement End", "chat chat-end"))
    }

    @Test
    fun chatClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyChatClasses()
        }
        assertRendered(html, Expected("ChatClasses defaults", "chat"))
    }

    @Test
    fun chatClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyChatClasses()
        }
        val expected = Expected("ChatClasses all flags", "chat", content = false)
        assertRendered(html, expected)
    }

    @Test
    fun chatClasses_placement_start() {
        val html = createHTML(prettyPrint = false).div {
            daisyChatClasses(
                placement = ChatPlacement.Start,
            )
        }
        assertRendered(html, Expected("ChatClasses placement Start", "chat chat-start"))
    }

    @Test
    fun chatClasses_placement_end() {
        val html = createHTML(prettyPrint = false).div {
            daisyChatClasses(
                placement = ChatPlacement.End,
            )
        }
        assertRendered(html, Expected("ChatClasses placement End", "chat chat-end"))
    }

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
        val html = createHTML(prettyPrint = false).div {
            daisyChatImage(
                id = htmlId("x-cov-id"),
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        val expected = Expected("ChatImage all flags", "chat-image zz-extra", closes = "</div></div>")
        assertRendered(html, expected)
        assertCommonFlags(html, expected)
    }

    @Test
    fun chatImageClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyChatImageClasses()
        }
        assertRendered(html, Expected("ChatImageClasses defaults", "chat-image"))
    }

    @Test
    fun chatImageClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyChatImageClasses()
        }
        val expected = Expected("ChatImageClasses all flags", "chat-image", content = false)
        assertRendered(html, expected)
    }

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
        val html = createHTML(prettyPrint = false).div {
            daisyChatHeader(
                id = htmlId("x-cov-id"),
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        val expected = Expected("ChatHeader all flags", "chat-header zz-extra", closes = "</div></div>")
        assertRendered(html, expected)
        assertCommonFlags(html, expected)
    }

    @Test
    fun chatHeaderClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyChatHeaderClasses()
        }
        assertRendered(html, Expected("ChatHeaderClasses defaults", "chat-header"))
    }

    @Test
    fun chatHeaderClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyChatHeaderClasses()
        }
        val expected = Expected("ChatHeaderClasses all flags", "chat-header", content = false)
        assertRendered(html, expected)
    }

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
        val html = createHTML(prettyPrint = false).div {
            daisyChatFooter(
                id = htmlId("x-cov-id"),
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        val expected = Expected("ChatFooter all flags", "chat-footer zz-extra", closes = "</div></div>")
        assertRendered(html, expected)
        assertCommonFlags(html, expected)
    }

    @Test
    fun chatFooterClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyChatFooterClasses()
        }
        assertRendered(html, Expected("ChatFooterClasses defaults", "chat-footer"))
    }

    @Test
    fun chatFooterClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyChatFooterClasses()
        }
        val expected = Expected("ChatFooterClasses all flags", "chat-footer", content = false)
        assertRendered(html, expected)
    }

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
        val html = createHTML(prettyPrint = false).div {
            daisyChatBubble(
                id = htmlId("x-cov-id"),
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        val expected = Expected("ChatBubble all flags", "chat-bubble zz-extra", closes = "</div></div>")
        assertRendered(html, expected)
        assertCommonFlags(html, expected)
    }

    @Test
    fun chatBubble_color_neutral() {
        val html = createHTML(prettyPrint = false).div {
            daisyChatBubble(
                color = ChatBubbleColor.Neutral,
                content = { },
            )
        }
        assertRendered(html, Expected("ChatBubble color Neutral", "chat-bubble chat-bubble-neutral"))
    }

    @Test
    fun chatBubble_color_primary() {
        val html = createHTML(prettyPrint = false).div {
            daisyChatBubble(
                color = ChatBubbleColor.Primary,
                content = { },
            )
        }
        assertRendered(html, Expected("ChatBubble color Primary", "chat-bubble chat-bubble-primary"))
    }

    @Test
    fun chatBubble_color_secondary() {
        val html = createHTML(prettyPrint = false).div {
            daisyChatBubble(
                color = ChatBubbleColor.Secondary,
                content = { },
            )
        }
        assertRendered(html, Expected("ChatBubble color Secondary", "chat-bubble chat-bubble-secondary"))
    }

    @Test
    fun chatBubble_color_accent() {
        val html = createHTML(prettyPrint = false).div {
            daisyChatBubble(
                color = ChatBubbleColor.Accent,
                content = { },
            )
        }
        assertRendered(html, Expected("ChatBubble color Accent", "chat-bubble chat-bubble-accent"))
    }

    @Test
    fun chatBubble_color_info() {
        val html = createHTML(prettyPrint = false).div {
            daisyChatBubble(
                color = ChatBubbleColor.Info,
                content = { },
            )
        }
        assertRendered(html, Expected("ChatBubble color Info", "chat-bubble chat-bubble-info"))
    }

    @Test
    fun chatBubble_color_success() {
        val html = createHTML(prettyPrint = false).div {
            daisyChatBubble(
                color = ChatBubbleColor.Success,
                content = { },
            )
        }
        assertRendered(html, Expected("ChatBubble color Success", "chat-bubble chat-bubble-success"))
    }

    @Test
    fun chatBubble_color_warning() {
        val html = createHTML(prettyPrint = false).div {
            daisyChatBubble(
                color = ChatBubbleColor.Warning,
                content = { },
            )
        }
        assertRendered(html, Expected("ChatBubble color Warning", "chat-bubble chat-bubble-warning"))
    }

    @Test
    fun chatBubble_color_error() {
        val html = createHTML(prettyPrint = false).div {
            daisyChatBubble(
                color = ChatBubbleColor.Error,
                content = { },
            )
        }
        assertRendered(html, Expected("ChatBubble color Error", "chat-bubble chat-bubble-error"))
    }

    @Test
    fun chatBubbleClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyChatBubbleClasses()
        }
        assertRendered(html, Expected("ChatBubbleClasses defaults", "chat-bubble"))
    }

    @Test
    fun chatBubbleClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyChatBubbleClasses()
        }
        val expected = Expected("ChatBubbleClasses all flags", "chat-bubble", content = false)
        assertRendered(html, expected)
    }

    @Test
    fun chatBubbleClasses_color_neutral() {
        val html = createHTML(prettyPrint = false).div {
            daisyChatBubbleClasses(
                color = ChatBubbleColor.Neutral,
            )
        }
        assertRendered(html, Expected("ChatBubbleClasses color Neutral", "chat-bubble chat-bubble-neutral"))
    }

    @Test
    fun chatBubbleClasses_color_primary() {
        val html = createHTML(prettyPrint = false).div {
            daisyChatBubbleClasses(
                color = ChatBubbleColor.Primary,
            )
        }
        assertRendered(html, Expected("ChatBubbleClasses color Primary", "chat-bubble chat-bubble-primary"))
    }

    @Test
    fun chatBubbleClasses_color_secondary() {
        val html = createHTML(prettyPrint = false).div {
            daisyChatBubbleClasses(
                color = ChatBubbleColor.Secondary,
            )
        }
        assertRendered(html, Expected("ChatBubbleClasses color Secondary", "chat-bubble chat-bubble-secondary"))
    }

    @Test
    fun chatBubbleClasses_color_accent() {
        val html = createHTML(prettyPrint = false).div {
            daisyChatBubbleClasses(
                color = ChatBubbleColor.Accent,
            )
        }
        assertRendered(html, Expected("ChatBubbleClasses color Accent", "chat-bubble chat-bubble-accent"))
    }

    @Test
    fun chatBubbleClasses_color_info() {
        val html = createHTML(prettyPrint = false).div {
            daisyChatBubbleClasses(
                color = ChatBubbleColor.Info,
            )
        }
        assertRendered(html, Expected("ChatBubbleClasses color Info", "chat-bubble chat-bubble-info"))
    }

    @Test
    fun chatBubbleClasses_color_success() {
        val html = createHTML(prettyPrint = false).div {
            daisyChatBubbleClasses(
                color = ChatBubbleColor.Success,
            )
        }
        assertRendered(html, Expected("ChatBubbleClasses color Success", "chat-bubble chat-bubble-success"))
    }

    @Test
    fun chatBubbleClasses_color_warning() {
        val html = createHTML(prettyPrint = false).div {
            daisyChatBubbleClasses(
                color = ChatBubbleColor.Warning,
            )
        }
        assertRendered(html, Expected("ChatBubbleClasses color Warning", "chat-bubble chat-bubble-warning"))
    }

    @Test
    fun chatBubbleClasses_color_error() {
        val html = createHTML(prettyPrint = false).div {
            daisyChatBubbleClasses(
                color = ChatBubbleColor.Error,
            )
        }
        assertRendered(html, Expected("ChatBubbleClasses color Error", "chat-bubble chat-bubble-error"))
    }
}
