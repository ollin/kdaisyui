package io.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals

class LoadingTest {

    private fun assertDocumented(html: String, tag: String, classes: String, case: String) {
        assertEquals(classes, html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" "), "Class mismatch for $case")
        assertEquals(tag, html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for $case")
    }

    @Test
    fun loading_spinner() {
        val html = createHTML(prettyPrint = false).div {
            daisyLoading(style = LoadingStyle.Spinner, size = LoadingSize.Xs) {
            }
        }
        assertDocumented(html, "span", "loading loading-spinner loading-xs", "Loading spinner")
    }

    @Test
    fun loading_spinner_2() {
        val html = createHTML(prettyPrint = false).div {
            daisyLoading(style = LoadingStyle.Spinner, size = LoadingSize.Sm) {
            }
        }
        assertDocumented(html, "span", "loading loading-sm loading-spinner", "Loading spinner")
    }

    @Test
    fun loading_spinner_3() {
        val html = createHTML(prettyPrint = false).div {
            daisyLoading(style = LoadingStyle.Spinner, size = LoadingSize.Md) {
            }
        }
        assertDocumented(html, "span", "loading loading-md loading-spinner", "Loading spinner")
    }

    @Test
    fun loading_spinner_4() {
        val html = createHTML(prettyPrint = false).div {
            daisyLoading(style = LoadingStyle.Spinner, size = LoadingSize.Lg) {
            }
        }
        assertDocumented(html, "span", "loading loading-lg loading-spinner", "Loading spinner")
    }

    @Test
    fun loading_spinner_5() {
        val html = createHTML(prettyPrint = false).div {
            daisyLoading(style = LoadingStyle.Spinner, size = LoadingSize.Xl) {
            }
        }
        assertDocumented(html, "span", "loading loading-spinner loading-xl", "Loading spinner")
    }

    @Test
    fun loading_dots() {
        val html = createHTML(prettyPrint = false).div {
            daisyLoading(style = LoadingStyle.Dots, size = LoadingSize.Xs) {
            }
        }
        assertDocumented(html, "span", "loading loading-dots loading-xs", "Loading dots")
    }

    @Test
    fun loading_dots_2() {
        val html = createHTML(prettyPrint = false).div {
            daisyLoading(style = LoadingStyle.Dots, size = LoadingSize.Sm) {
            }
        }
        assertDocumented(html, "span", "loading loading-dots loading-sm", "Loading dots")
    }

    @Test
    fun loading_dots_3() {
        val html = createHTML(prettyPrint = false).div {
            daisyLoading(style = LoadingStyle.Dots, size = LoadingSize.Md) {
            }
        }
        assertDocumented(html, "span", "loading loading-dots loading-md", "Loading dots")
    }

    @Test
    fun loading_dots_4() {
        val html = createHTML(prettyPrint = false).div {
            daisyLoading(style = LoadingStyle.Dots, size = LoadingSize.Lg) {
            }
        }
        assertDocumented(html, "span", "loading loading-dots loading-lg", "Loading dots")
    }

    @Test
    fun loading_dots_5() {
        val html = createHTML(prettyPrint = false).div {
            daisyLoading(style = LoadingStyle.Dots, size = LoadingSize.Xl) {
            }
        }
        assertDocumented(html, "span", "loading loading-dots loading-xl", "Loading dots")
    }

    @Test
    fun loading_ring() {
        val html = createHTML(prettyPrint = false).div {
            daisyLoading(style = LoadingStyle.Ring, size = LoadingSize.Xs) {
            }
        }
        assertDocumented(html, "span", "loading loading-ring loading-xs", "Loading ring")
    }

    @Test
    fun loading_ring_2() {
        val html = createHTML(prettyPrint = false).div {
            daisyLoading(style = LoadingStyle.Ring, size = LoadingSize.Sm) {
            }
        }
        assertDocumented(html, "span", "loading loading-ring loading-sm", "Loading ring")
    }

    @Test
    fun loading_ring_3() {
        val html = createHTML(prettyPrint = false).div {
            daisyLoading(style = LoadingStyle.Ring, size = LoadingSize.Md) {
            }
        }
        assertDocumented(html, "span", "loading loading-md loading-ring", "Loading ring")
    }

    @Test
    fun loading_ring_4() {
        val html = createHTML(prettyPrint = false).div {
            daisyLoading(style = LoadingStyle.Ring, size = LoadingSize.Lg) {
            }
        }
        assertDocumented(html, "span", "loading loading-lg loading-ring", "Loading ring")
    }

    @Test
    fun loading_ring_5() {
        val html = createHTML(prettyPrint = false).div {
            daisyLoading(style = LoadingStyle.Ring, size = LoadingSize.Xl) {
            }
        }
        assertDocumented(html, "span", "loading loading-ring loading-xl", "Loading ring")
    }

    @Test
    fun loading_ball() {
        val html = createHTML(prettyPrint = false).div {
            daisyLoading(style = LoadingStyle.Ball, size = LoadingSize.Xs) {
            }
        }
        assertDocumented(html, "span", "loading loading-ball loading-xs", "Loading ball")
    }

    @Test
    fun loading_ball_2() {
        val html = createHTML(prettyPrint = false).div {
            daisyLoading(style = LoadingStyle.Ball, size = LoadingSize.Sm) {
            }
        }
        assertDocumented(html, "span", "loading loading-ball loading-sm", "Loading ball")
    }

    @Test
    fun loading_ball_3() {
        val html = createHTML(prettyPrint = false).div {
            daisyLoading(style = LoadingStyle.Ball, size = LoadingSize.Md) {
            }
        }
        assertDocumented(html, "span", "loading loading-ball loading-md", "Loading ball")
    }

    @Test
    fun loading_ball_4() {
        val html = createHTML(prettyPrint = false).div {
            daisyLoading(style = LoadingStyle.Ball, size = LoadingSize.Lg) {
            }
        }
        assertDocumented(html, "span", "loading loading-ball loading-lg", "Loading ball")
    }

    @Test
    fun loading_ball_5() {
        val html = createHTML(prettyPrint = false).div {
            daisyLoading(style = LoadingStyle.Ball, size = LoadingSize.Xl) {
            }
        }
        assertDocumented(html, "span", "loading loading-ball loading-xl", "Loading ball")
    }

    @Test
    fun loading_bars() {
        val html = createHTML(prettyPrint = false).div {
            daisyLoading(style = LoadingStyle.Bars, size = LoadingSize.Xs) {
            }
        }
        assertDocumented(html, "span", "loading loading-bars loading-xs", "Loading bars")
    }

    @Test
    fun loading_bars_2() {
        val html = createHTML(prettyPrint = false).div {
            daisyLoading(style = LoadingStyle.Bars, size = LoadingSize.Sm) {
            }
        }
        assertDocumented(html, "span", "loading loading-bars loading-sm", "Loading bars")
    }

    @Test
    fun loading_bars_3() {
        val html = createHTML(prettyPrint = false).div {
            daisyLoading(style = LoadingStyle.Bars, size = LoadingSize.Md) {
            }
        }
        assertDocumented(html, "span", "loading loading-bars loading-md", "Loading bars")
    }

    @Test
    fun loading_bars_4() {
        val html = createHTML(prettyPrint = false).div {
            daisyLoading(style = LoadingStyle.Bars, size = LoadingSize.Lg) {
            }
        }
        assertDocumented(html, "span", "loading loading-bars loading-lg", "Loading bars")
    }

    @Test
    fun loading_bars_5() {
        val html = createHTML(prettyPrint = false).div {
            daisyLoading(style = LoadingStyle.Bars, size = LoadingSize.Xl) {
            }
        }
        assertDocumented(html, "span", "loading loading-bars loading-xl", "Loading bars")
    }

    @Test
    fun loading_infinity() {
        val html = createHTML(prettyPrint = false).div {
            daisyLoading(style = LoadingStyle.Infinity, size = LoadingSize.Xs) {
            }
        }
        assertDocumented(html, "span", "loading loading-infinity loading-xs", "Loading infinity")
    }

    @Test
    fun loading_infinity_2() {
        val html = createHTML(prettyPrint = false).div {
            daisyLoading(style = LoadingStyle.Infinity, size = LoadingSize.Sm) {
            }
        }
        assertDocumented(html, "span", "loading loading-infinity loading-sm", "Loading infinity")
    }

    @Test
    fun loading_infinity_3() {
        val html = createHTML(prettyPrint = false).div {
            daisyLoading(style = LoadingStyle.Infinity, size = LoadingSize.Md) {
            }
        }
        assertDocumented(html, "span", "loading loading-infinity loading-md", "Loading infinity")
    }

    @Test
    fun loading_infinity_4() {
        val html = createHTML(prettyPrint = false).div {
            daisyLoading(style = LoadingStyle.Infinity, size = LoadingSize.Lg) {
            }
        }
        assertDocumented(html, "span", "loading loading-infinity loading-lg", "Loading infinity")
    }

    @Test
    fun loading_infinity_5() {
        val html = createHTML(prettyPrint = false).div {
            daisyLoading(style = LoadingStyle.Infinity, size = LoadingSize.Xl) {
            }
        }
        assertDocumented(html, "span", "loading loading-infinity loading-xl", "Loading infinity")
    }

    @Test
    fun loading_with_colors() {
        val html = createHTML(prettyPrint = false).div {
            daisyLoading(style = LoadingStyle.Spinner) {
            }
        }
        assertDocumented(html, "span", "loading loading-spinner", "Loading with colors")
    }
}
