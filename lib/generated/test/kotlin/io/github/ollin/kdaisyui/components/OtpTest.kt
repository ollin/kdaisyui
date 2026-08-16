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
    }

    @Test
    fun otp_with_6_digits() {
        val html = createHTML(prettyPrint = false).div {
            daisyOtp() {
            }
        }
        val expectedClasses = "otp"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for OTP with 6 digits")
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
    }

    @Test
    fun otp_with_different_sizes() {
        val html = createHTML(prettyPrint = false).div {
            daisyOtp() {
            }
        }
        val expectedClasses = "otp"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for OTP with different sizes")
    }

    @Test
    fun otp_with_different_colors() {
        val html = createHTML(prettyPrint = false).div {
            daisyOtp() {
            }
        }
        val expectedClasses = "otp"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for OTP with different colors")
    }
}
