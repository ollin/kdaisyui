package io.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals

class OtpTest {

    @Test
    fun otp() {
        val html = createHTML(prettyPrint = false).div {
            daisyOtp() {
            }
        }
        val expectedClasses = "otp"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for OTP")
        assertEquals("label", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for OTP")
    }

    @Test
    fun otp_joined() {
        val html = createHTML(prettyPrint = false).div {
            daisyOtp(joined = true) {
            }
        }
        val expectedClasses = "otp otp-joined"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for OTP joined")
        assertEquals("label", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for OTP joined")
    }

    @Test
    fun otp_with_different_sizes() {
        val html = createHTML(prettyPrint = false).div {
            daisyOtp(size = OtpSize.Xs) {
            }
        }
        val expectedClasses = "otp otp-xs"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for OTP with different sizes")
        assertEquals("label", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for OTP with different sizes")
    }

    @Test
    fun otp_with_different_sizes_2() {
        val html = createHTML(prettyPrint = false).div {
            daisyOtp(size = OtpSize.Sm) {
            }
        }
        val expectedClasses = "otp otp-sm"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for OTP with different sizes")
        assertEquals("label", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for OTP with different sizes")
    }

    @Test
    fun otp_with_different_sizes_3() {
        val html = createHTML(prettyPrint = false).div {
            daisyOtp(size = OtpSize.Md) {
            }
        }
        val expectedClasses = "otp otp-md"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for OTP with different sizes")
        assertEquals("label", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for OTP with different sizes")
    }

    @Test
    fun otp_with_different_sizes_4() {
        val html = createHTML(prettyPrint = false).div {
            daisyOtp(size = OtpSize.Lg) {
            }
        }
        val expectedClasses = "otp otp-lg"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for OTP with different sizes")
        assertEquals("label", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for OTP with different sizes")
    }

    @Test
    fun otp_with_different_sizes_5() {
        val html = createHTML(prettyPrint = false).div {
            daisyOtp(size = OtpSize.Xl) {
            }
        }
        val expectedClasses = "otp otp-xl"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for OTP with different sizes")
        assertEquals("label", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for OTP with different sizes")
    }

    @Test
    fun otp_with_different_colors() {
        val html = createHTML(prettyPrint = false).div {
            daisyOtp(color = OtpColor.Neutral) {
            }
        }
        val expectedClasses = "otp otp-neutral"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for OTP with different colors")
        assertEquals("label", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for OTP with different colors")
    }

    @Test
    fun otp_with_different_colors_2() {
        val html = createHTML(prettyPrint = false).div {
            daisyOtp(color = OtpColor.Primary) {
            }
        }
        val expectedClasses = "otp otp-primary"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for OTP with different colors")
        assertEquals("label", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for OTP with different colors")
    }

    @Test
    fun otp_with_different_colors_3() {
        val html = createHTML(prettyPrint = false).div {
            daisyOtp(color = OtpColor.Secondary) {
            }
        }
        val expectedClasses = "otp otp-secondary"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for OTP with different colors")
        assertEquals("label", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for OTP with different colors")
    }

    @Test
    fun otp_with_different_colors_4() {
        val html = createHTML(prettyPrint = false).div {
            daisyOtp(color = OtpColor.Accent) {
            }
        }
        val expectedClasses = "otp otp-accent"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for OTP with different colors")
        assertEquals("label", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for OTP with different colors")
    }

    @Test
    fun otp_with_different_colors_5() {
        val html = createHTML(prettyPrint = false).div {
            daisyOtp(color = OtpColor.Info) {
            }
        }
        val expectedClasses = "otp otp-info"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for OTP with different colors")
        assertEquals("label", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for OTP with different colors")
    }

    @Test
    fun otp_with_different_colors_6() {
        val html = createHTML(prettyPrint = false).div {
            daisyOtp(color = OtpColor.Success) {
            }
        }
        val expectedClasses = "otp otp-success"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for OTP with different colors")
        assertEquals("label", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for OTP with different colors")
    }

    @Test
    fun otp_with_different_colors_7() {
        val html = createHTML(prettyPrint = false).div {
            daisyOtp(color = OtpColor.Warning) {
            }
        }
        val expectedClasses = "otp otp-warning"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for OTP with different colors")
        assertEquals("label", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for OTP with different colors")
    }

    @Test
    fun otp_with_different_colors_8() {
        val html = createHTML(prettyPrint = false).div {
            daisyOtp(color = OtpColor.Error) {
            }
        }
        val expectedClasses = "otp otp-error"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for OTP with different colors")
        assertEquals("label", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for OTP with different colors")
    }
}
