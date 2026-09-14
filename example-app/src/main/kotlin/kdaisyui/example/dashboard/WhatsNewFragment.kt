package kdaisyui.example.dashboard

import io.github.ollin.kdaisyui.components.*
import io.github.ollin.kdaisyui.core.addClassNames
import kdaisyui.example.Dashboard
import kotlinx.html.*

/**
 * The components DaisyUI 5.6/5.7 added: `aura`, `otp` and `megamenu`.
 *
 * This exists so each of them is actually rendered by a running application and asserted
 * end-to-end, not merely generated and unit-tested. A component that compiles and passes its
 * generated tests can still be unusable in a page — `dropdown` was, at 5.7.16.
 */
fun TagConsumer<*>.whatsNewFragment() {
    auraCard()
    otpCard()
    megamenuCard()
}

private fun TagConsumer<*>.auraCard() {
    section {
        id = Dashboard.WhatsNew().id
        addClassNames("card bg-base-100 col-span-12 shadow-xs xl:col-span-4")
        daisyCardBody {
            daisyCardTitle("Deploy Status")
            daisyAura(size = AuraSize.Lg, rainbow = true) {
                div("bg-base-200 rounded-box p-6 text-center") {
                    span("text-lg font-semibold") { +"v2.4.1 live" }
                }
            }
        }
    }
}

/** DaisyUI: "make sure the number of spans matches the maxlength and the pattern of the input". */
private const val OTP_DIGITS = 6

private fun TagConsumer<*>.otpCard() {
    section {
        addClassNames("card bg-base-100 col-span-12 shadow-xs xl:col-span-4")
        daisyCardBody {
            daisyCardTitle("Two-Factor Code")
            // Empty boxes and an input, which is DaisyUI's documented markup exactly — no
            // example of theirs pre-fills an OTP, because it is a field you type into and not
            // one that displays a value.
            //
            // This card exists to prove `daisyOtp` renders markup that WORKS, per the commit
            // that added it: "daisyDropdown passed every generated test while rendering markup
            // that could not open". Until 2026-09-14 the card wrote the digits into the spans
            // and omitted the input altogether — the very failure it was built to catch, and
            // the spans-only form is also why the characters sat high and right, since otp.css
            // paints them from the `> input` layer with the letter-spacing that centres one
            // character per box.
            daisyOtp(id = Dashboard.WhatsNew.Otp(), variant = OtpVariant.Primary, joined = true) {
                repeat(OTP_DIGITS) { span { } }
                input {
                    type = InputType.text
                    required = true
                    attributes["autocomplete"] = "one-time-code"
                    attributes["inputmode"] = "numeric"
                    attributes["maxLength"] = OTP_DIGITS.toString()
                    attributes["pattern"] = "[0-9]{$OTP_DIGITS}"
                }
            }
        }
    }
}

private fun TagConsumer<*>.megamenuCard() {
    section {
        addClassNames("card bg-base-100 col-span-12 shadow-xs xl:col-span-4")
        daisyCardBody {
            daisyCardTitle("Navigation Preview")
            daisyMegamenu(id = Dashboard.WhatsNew.Megamenu(), wide = true) {
                daisyMegamenuActive { }
                button { attributes["popovertarget"] = Dashboard.WhatsNew.Megamenu.Panel().id; +"Browse" }
                daisyMegamenuPanel(id = Dashboard.WhatsNew.Megamenu.Panel()) {
                    daisyMenu(extraClasses = "w-full") {
                        li { a { +"Repositories" } }
                        li { a { +"Pipelines" } }
                        li { a { +"Team" } }
                    }
                }
            }
        }
    }
}
