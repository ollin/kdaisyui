package io.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals

class LoadingTest {

    @Test
    fun loading_spinner() {
        val html = createHTML(prettyPrint = false).div {
            daisyLoading(style = LoadingStyle.Spinner, size = LoadingSize.Xs) {
            }
        }
        val expectedClasses = "loading loading-spinner loading-xs"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Loading spinner")
        assertEquals("span", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Loading spinner")
    }

    @Test
    fun loading_spinner_2() {
        val html = createHTML(prettyPrint = false).div {
            daisyLoading(style = LoadingStyle.Spinner, size = LoadingSize.Sm) {
            }
        }
        val expectedClasses = "loading loading-sm loading-spinner"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Loading spinner")
        assertEquals("span", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Loading spinner")
    }

    @Test
    fun loading_spinner_3() {
        val html = createHTML(prettyPrint = false).div {
            daisyLoading(style = LoadingStyle.Spinner, size = LoadingSize.Md) {
            }
        }
        val expectedClasses = "loading loading-md loading-spinner"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Loading spinner")
        assertEquals("span", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Loading spinner")
    }

    @Test
    fun loading_spinner_4() {
        val html = createHTML(prettyPrint = false).div {
            daisyLoading(style = LoadingStyle.Spinner, size = LoadingSize.Lg) {
            }
        }
        val expectedClasses = "loading loading-lg loading-spinner"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Loading spinner")
        assertEquals("span", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Loading spinner")
    }

    @Test
    fun loading_spinner_5() {
        val html = createHTML(prettyPrint = false).div {
            daisyLoading(style = LoadingStyle.Spinner, size = LoadingSize.Xl) {
            }
        }
        val expectedClasses = "loading loading-spinner loading-xl"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Loading spinner")
        assertEquals("span", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Loading spinner")
    }

    @Test
    fun loading_dots() {
        val html = createHTML(prettyPrint = false).div {
            daisyLoading(style = LoadingStyle.Dots, size = LoadingSize.Xs) {
            }
        }
        val expectedClasses = "loading loading-dots loading-xs"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Loading dots")
        assertEquals("span", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Loading dots")
    }

    @Test
    fun loading_dots_2() {
        val html = createHTML(prettyPrint = false).div {
            daisyLoading(style = LoadingStyle.Dots, size = LoadingSize.Sm) {
            }
        }
        val expectedClasses = "loading loading-dots loading-sm"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Loading dots")
        assertEquals("span", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Loading dots")
    }

    @Test
    fun loading_dots_3() {
        val html = createHTML(prettyPrint = false).div {
            daisyLoading(style = LoadingStyle.Dots, size = LoadingSize.Md) {
            }
        }
        val expectedClasses = "loading loading-dots loading-md"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Loading dots")
        assertEquals("span", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Loading dots")
    }

    @Test
    fun loading_dots_4() {
        val html = createHTML(prettyPrint = false).div {
            daisyLoading(style = LoadingStyle.Dots, size = LoadingSize.Lg) {
            }
        }
        val expectedClasses = "loading loading-dots loading-lg"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Loading dots")
        assertEquals("span", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Loading dots")
    }

    @Test
    fun loading_dots_5() {
        val html = createHTML(prettyPrint = false).div {
            daisyLoading(style = LoadingStyle.Dots, size = LoadingSize.Xl) {
            }
        }
        val expectedClasses = "loading loading-dots loading-xl"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Loading dots")
        assertEquals("span", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Loading dots")
    }

    @Test
    fun loading_ring() {
        val html = createHTML(prettyPrint = false).div {
            daisyLoading(style = LoadingStyle.Ring, size = LoadingSize.Xs) {
            }
        }
        val expectedClasses = "loading loading-ring loading-xs"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Loading ring")
        assertEquals("span", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Loading ring")
    }

    @Test
    fun loading_ring_2() {
        val html = createHTML(prettyPrint = false).div {
            daisyLoading(style = LoadingStyle.Ring, size = LoadingSize.Sm) {
            }
        }
        val expectedClasses = "loading loading-ring loading-sm"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Loading ring")
        assertEquals("span", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Loading ring")
    }

    @Test
    fun loading_ring_3() {
        val html = createHTML(prettyPrint = false).div {
            daisyLoading(style = LoadingStyle.Ring, size = LoadingSize.Md) {
            }
        }
        val expectedClasses = "loading loading-md loading-ring"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Loading ring")
        assertEquals("span", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Loading ring")
    }

    @Test
    fun loading_ring_4() {
        val html = createHTML(prettyPrint = false).div {
            daisyLoading(style = LoadingStyle.Ring, size = LoadingSize.Lg) {
            }
        }
        val expectedClasses = "loading loading-lg loading-ring"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Loading ring")
        assertEquals("span", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Loading ring")
    }

    @Test
    fun loading_ring_5() {
        val html = createHTML(prettyPrint = false).div {
            daisyLoading(style = LoadingStyle.Ring, size = LoadingSize.Xl) {
            }
        }
        val expectedClasses = "loading loading-ring loading-xl"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Loading ring")
        assertEquals("span", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Loading ring")
    }

    @Test
    fun loading_ball() {
        val html = createHTML(prettyPrint = false).div {
            daisyLoading(style = LoadingStyle.Ball, size = LoadingSize.Xs) {
            }
        }
        val expectedClasses = "loading loading-ball loading-xs"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Loading ball")
        assertEquals("span", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Loading ball")
    }

    @Test
    fun loading_ball_2() {
        val html = createHTML(prettyPrint = false).div {
            daisyLoading(style = LoadingStyle.Ball, size = LoadingSize.Sm) {
            }
        }
        val expectedClasses = "loading loading-ball loading-sm"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Loading ball")
        assertEquals("span", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Loading ball")
    }

    @Test
    fun loading_ball_3() {
        val html = createHTML(prettyPrint = false).div {
            daisyLoading(style = LoadingStyle.Ball, size = LoadingSize.Md) {
            }
        }
        val expectedClasses = "loading loading-ball loading-md"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Loading ball")
        assertEquals("span", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Loading ball")
    }

    @Test
    fun loading_ball_4() {
        val html = createHTML(prettyPrint = false).div {
            daisyLoading(style = LoadingStyle.Ball, size = LoadingSize.Lg) {
            }
        }
        val expectedClasses = "loading loading-ball loading-lg"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Loading ball")
        assertEquals("span", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Loading ball")
    }

    @Test
    fun loading_ball_5() {
        val html = createHTML(prettyPrint = false).div {
            daisyLoading(style = LoadingStyle.Ball, size = LoadingSize.Xl) {
            }
        }
        val expectedClasses = "loading loading-ball loading-xl"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Loading ball")
        assertEquals("span", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Loading ball")
    }

    @Test
    fun loading_bars() {
        val html = createHTML(prettyPrint = false).div {
            daisyLoading(style = LoadingStyle.Bars, size = LoadingSize.Xs) {
            }
        }
        val expectedClasses = "loading loading-bars loading-xs"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Loading bars")
        assertEquals("span", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Loading bars")
    }

    @Test
    fun loading_bars_2() {
        val html = createHTML(prettyPrint = false).div {
            daisyLoading(style = LoadingStyle.Bars, size = LoadingSize.Sm) {
            }
        }
        val expectedClasses = "loading loading-bars loading-sm"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Loading bars")
        assertEquals("span", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Loading bars")
    }

    @Test
    fun loading_bars_3() {
        val html = createHTML(prettyPrint = false).div {
            daisyLoading(style = LoadingStyle.Bars, size = LoadingSize.Md) {
            }
        }
        val expectedClasses = "loading loading-bars loading-md"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Loading bars")
        assertEquals("span", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Loading bars")
    }

    @Test
    fun loading_bars_4() {
        val html = createHTML(prettyPrint = false).div {
            daisyLoading(style = LoadingStyle.Bars, size = LoadingSize.Lg) {
            }
        }
        val expectedClasses = "loading loading-bars loading-lg"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Loading bars")
        assertEquals("span", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Loading bars")
    }

    @Test
    fun loading_bars_5() {
        val html = createHTML(prettyPrint = false).div {
            daisyLoading(style = LoadingStyle.Bars, size = LoadingSize.Xl) {
            }
        }
        val expectedClasses = "loading loading-bars loading-xl"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Loading bars")
        assertEquals("span", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Loading bars")
    }

    @Test
    fun loading_infinity() {
        val html = createHTML(prettyPrint = false).div {
            daisyLoading(style = LoadingStyle.Infinity, size = LoadingSize.Xs) {
            }
        }
        val expectedClasses = "loading loading-infinity loading-xs"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Loading infinity")
        assertEquals("span", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Loading infinity")
    }

    @Test
    fun loading_infinity_2() {
        val html = createHTML(prettyPrint = false).div {
            daisyLoading(style = LoadingStyle.Infinity, size = LoadingSize.Sm) {
            }
        }
        val expectedClasses = "loading loading-infinity loading-sm"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Loading infinity")
        assertEquals("span", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Loading infinity")
    }

    @Test
    fun loading_infinity_3() {
        val html = createHTML(prettyPrint = false).div {
            daisyLoading(style = LoadingStyle.Infinity, size = LoadingSize.Md) {
            }
        }
        val expectedClasses = "loading loading-infinity loading-md"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Loading infinity")
        assertEquals("span", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Loading infinity")
    }

    @Test
    fun loading_infinity_4() {
        val html = createHTML(prettyPrint = false).div {
            daisyLoading(style = LoadingStyle.Infinity, size = LoadingSize.Lg) {
            }
        }
        val expectedClasses = "loading loading-infinity loading-lg"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Loading infinity")
        assertEquals("span", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Loading infinity")
    }

    @Test
    fun loading_infinity_5() {
        val html = createHTML(prettyPrint = false).div {
            daisyLoading(style = LoadingStyle.Infinity, size = LoadingSize.Xl) {
            }
        }
        val expectedClasses = "loading loading-infinity loading-xl"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Loading infinity")
        assertEquals("span", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Loading infinity")
    }

    @Test
    fun loading_with_colors() {
        val html = createHTML(prettyPrint = false).div {
            daisyLoading(style = LoadingStyle.Spinner) {
            }
        }
        val expectedClasses = "loading loading-spinner"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Loading with colors")
        assertEquals("span", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Loading with colors")
    }
}
