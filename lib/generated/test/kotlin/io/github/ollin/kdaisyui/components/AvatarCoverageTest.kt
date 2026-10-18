package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class AvatarCoverageTest {

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
    fun avatar_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyAvatar(
                content = { },
            )
        }
        assertRendered(html, "avatar", "Avatar defaults", closes = "</div></div>")
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
        assertRendered(html, "avatar avatar-placeholder zz-extra", "Avatar all flags", closes = "</div></div>")
        assertCommonFlags(html, "Avatar")
    }

    @Test
    fun avatar_presence_online() {
        val html = createHTML(prettyPrint = false).div {
            daisyAvatar(
                presence = AvatarPresence.Online,
                content = { },
            )
        }
        assertRendered(html, "avatar avatar-online", "Avatar presence Online")
    }

    @Test
    fun avatar_presence_offline() {
        val html = createHTML(prettyPrint = false).div {
            daisyAvatar(
                presence = AvatarPresence.Offline,
                content = { },
            )
        }
        assertRendered(html, "avatar avatar-offline", "Avatar presence Offline")
    }

    @Test
    fun avatarClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyAvatarClasses()
        }
        assertRendered(html, "avatar", "AvatarClasses defaults")
    }

    @Test
    fun avatarClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyAvatarClasses(
                placeholder = true,
            )
        }
        assertRendered(html, "avatar avatar-placeholder", "AvatarClasses all flags")
    }

    @Test
    fun avatarClasses_presence_online() {
        val html = createHTML(prettyPrint = false).div {
            daisyAvatarClasses(
                presence = AvatarPresence.Online,
            )
        }
        assertRendered(html, "avatar avatar-online", "AvatarClasses presence Online")
    }

    @Test
    fun avatarClasses_presence_offline() {
        val html = createHTML(prettyPrint = false).div {
            daisyAvatarClasses(
                presence = AvatarPresence.Offline,
            )
        }
        assertRendered(html, "avatar avatar-offline", "AvatarClasses presence Offline")
    }

    @Test
    fun avatarGroup_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyAvatarGroup(
                content = { },
            )
        }
        assertRendered(html, "avatar-group", "AvatarGroup defaults", closes = "</div></div>")
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
        assertRendered(html, "avatar-group zz-extra", "AvatarGroup all flags", closes = "</div></div>")
        assertCommonFlags(html, "AvatarGroup")
    }

    @Test
    fun avatarGroupClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyAvatarGroupClasses()
        }
        assertRendered(html, "avatar-group", "AvatarGroupClasses defaults")
    }

    @Test
    fun avatarGroupClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyAvatarGroupClasses()
        }
        assertRendered(html, "avatar-group", "AvatarGroupClasses all flags")
    }
}
