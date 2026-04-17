package kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals

class BreadcrumbsTest {

    @Test
    fun breadcrumbs() {
        val html = createHTML(prettyPrint = false).div {
            daisyBreadcrumbs() {
            }
        }
        val expectedClasses = "breadcrumbs"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Breadcrumbs")
    }

    @Test
    fun breadcrumbs_with_icons() {
        val html = createHTML(prettyPrint = false).div {
            daisyBreadcrumbs() {
            }
        }
        val expectedClasses = "breadcrumbs"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Breadcrumbs with icons")
    }

    @Test
    fun breadcrumbs_with_max_width() {
        val html = createHTML(prettyPrint = false).div {
            daisyBreadcrumbs() {
            }
        }
        val expectedClasses = "breadcrumbs"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Breadcrumbs with max-width")
    }
}
