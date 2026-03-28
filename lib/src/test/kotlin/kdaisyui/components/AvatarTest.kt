package kdaisyui.components

import kotlinx.html.div
import kotlinx.html.img
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals

class AvatarTest {
    @Test
    fun rendersAvatar() {
        val html = createHTML(prettyPrint = false).div {
            daisyAvatar {
                div("w-8 rounded-full") {
                    img(src = "https://example.com/pic.jpg")
                }
            }
        }
        assertEquals(
            expected = """<div><div class="avatar"><div class="w-8 rounded-full"><img src="https://example.com/pic.jpg"></div></div></div>""",
            actual = html.trim(),
        )
    }
}
