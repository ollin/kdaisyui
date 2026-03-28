package kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals

class IndicatorTest {
    @Test
    fun rendersIndicator() {
        val html = createHTML(prettyPrint = false).div {
            daisyIndicator {
                daisyBadge(variant = BadgeVariant.Error, size = BadgeSize.Xs, extraClasses = "indicator-item")
            }
        }
        assertEquals(
            expected = """<div><div class="indicator"><span class="badge badge-error badge-xs indicator-item"></span></div></div>""",
            actual = html.trim(),
        )
    }
}
