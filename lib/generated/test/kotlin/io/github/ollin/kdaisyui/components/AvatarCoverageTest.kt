package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class AvatarCoverageTest {

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
        val html = createHTML(prettyPrint = false).div {
            daisyAvatar(
                id = htmlId("x-cov-id"),
                placeholder = true,
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        val expected = Expected("Avatar all flags", "avatar avatar-placeholder zz-extra", closes = "</div></div>")
        assertRendered(html, expected)
        assertCommonFlags(html, expected)
    }

    @Test
    fun avatar_presence_online() {
        val html = createHTML(prettyPrint = false).div {
            daisyAvatar(
                presence = AvatarPresence.Online,
                content = { },
            )
        }
        assertRendered(html, Expected("Avatar presence Online", "avatar avatar-online"))
    }

    @Test
    fun avatar_presence_offline() {
        val html = createHTML(prettyPrint = false).div {
            daisyAvatar(
                presence = AvatarPresence.Offline,
                content = { },
            )
        }
        assertRendered(html, Expected("Avatar presence Offline", "avatar avatar-offline"))
    }

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
    fun avatarClasses_presence_online() {
        val html = createHTML(prettyPrint = false).div {
            daisyAvatarClasses(
                presence = AvatarPresence.Online,
            )
        }
        assertRendered(html, Expected("AvatarClasses presence Online", "avatar avatar-online"))
    }

    @Test
    fun avatarClasses_presence_offline() {
        val html = createHTML(prettyPrint = false).div {
            daisyAvatarClasses(
                presence = AvatarPresence.Offline,
            )
        }
        assertRendered(html, Expected("AvatarClasses presence Offline", "avatar avatar-offline"))
    }

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
        val html = createHTML(prettyPrint = false).div {
            daisyAvatarGroup(
                id = htmlId("x-cov-id"),
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        val expected = Expected("AvatarGroup all flags", "avatar-group zz-extra", closes = "</div></div>")
        assertRendered(html, expected)
        assertCommonFlags(html, expected)
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
