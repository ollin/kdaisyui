package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class JoinCoverageTest {

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
    fun join_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyJoin(
                content = { },
            )
        }
        assertRendered(html, "join", "Join defaults", closes = "</div></div>")
    }

    @Test
    fun join_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyJoin(
                id = htmlId("x-cov-id"),
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        assertRendered(html, "join zz-extra", "Join all flags", closes = "</div></div>")
        assertCommonFlags(html, "Join")
    }

    @Test
    fun join_direction_vertical() {
        val html = createHTML(prettyPrint = false).div {
            daisyJoin(
                direction = JoinDirection.Vertical,
                content = { },
            )
        }
        assertRendered(html, "join join-vertical", "Join direction Vertical")
    }

    @Test
    fun join_direction_horizontal() {
        val html = createHTML(prettyPrint = false).div {
            daisyJoin(
                direction = JoinDirection.Horizontal,
                content = { },
            )
        }
        assertRendered(html, "join join-horizontal", "Join direction Horizontal")
    }

    @Test
    fun scope_daisyButton() {
        val html = createHTML(prettyPrint = false).div {
            daisyJoin {
                daisyButton()
            }
        }
        assertTrue(html.contains("join-item"), "daisyButton in a daisyJoin")
    }

    @Test
    fun scope_daisyCard() {
        val html = createHTML(prettyPrint = false).div {
            daisyJoin {
                daisyCard(content = { })
            }
        }
        assertTrue(html.contains("join-item"), "daisyCard in a daisyJoin")
    }

    @Test
    fun scope_daisyCollapse() {
        val html = createHTML(prettyPrint = false).div {
            daisyJoin {
                daisyCollapse(content = { })
            }
        }
        assertTrue(html.contains("join-item"), "daisyCollapse in a daisyJoin")
    }

    @Test
    fun scope_daisyInput() {
        val html = createHTML(prettyPrint = false).div {
            daisyJoin {
                daisyInput()
            }
        }
        assertTrue(html.contains("join-item"), "daisyInput in a daisyJoin")
    }

    @Test
    fun scope_daisySelect() {
        val html = createHTML(prettyPrint = false).div {
            daisyJoin {
                daisySelect(content = { })
            }
        }
        assertTrue(html.contains("join-item"), "daisySelect in a daisyJoin")
    }

    @Test
    fun scope_daisyThemeController() {
        val html = createHTML(prettyPrint = false).div {
            daisyJoin {
                daisyThemeController()
            }
        }
        assertTrue(html.contains("join-item"), "daisyThemeController in a daisyJoin")
    }

    @Test
    fun scope_daisyValidator() {
        val html = createHTML(prettyPrint = false).div {
            daisyJoin {
                daisyValidator()
            }
        }
        assertTrue(html.contains("join-item"), "daisyValidator in a daisyJoin")
    }
}
