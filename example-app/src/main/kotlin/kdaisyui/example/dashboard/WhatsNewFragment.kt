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

private fun TagConsumer<*>.otpCard() {
    section {
        addClassNames("card bg-base-100 col-span-12 shadow-xs xl:col-span-4")
        daisyCardBody {
            daisyCardTitle("Two-Factor Code")
            daisyOtp(id = Dashboard.WhatsNew.Otp(), variant = OtpVariant.Primary, joined = true) {
                for (digit in listOf("4", "8", "1", "5", "2", "3")) {
                    span { +digit }
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
                daisyMegamenuActive {
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
