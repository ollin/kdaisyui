package kdaisyui.example

import io.github.ollin.kdaisyui.components.*
import io.github.ollin.kdaisyui.core.addClassNames
import kotlinx.html.*

/**
 * DaisyUI's popover modal method, on a page of its own.
 *
 * Deliberately not a dashboard fragment. The dashboard loads its fragments over htmx, so a
 * fragment is unreachable with JavaScript disabled — and JavaScript being disabled is the
 * point of this page. Nothing here wires an event handler: the `popovertarget` button and the
 * `popover` attribute are a browser-native pair, and DaisyUI styles the open state through
 * `.modal:popover-open`.
 */
fun HTML.popoverModalPage() {
    lang = "en"
    head {
        meta { charset = "utf-8" }
        title { +"Popover Modal" }
        meta { name = "viewport"; content = "width=device-width, initial-scale=1" }
        link { rel = "stylesheet"; href = "/webjars/daisyui/daisyui.css" }
        link { rel = "stylesheet"; href = "/webjars/daisyui/themes.css" }
        script { src = "/webjars/tailwindcss__browser/dist/index.global.js" }
    }
    body("bg-base-200 min-h-screen p-10") {
        val modalId = Dashboard.PopoverModal()
        daisyButton(text = "Open modal", variant = ButtonVariant.Primary) {
            attributes["popovertarget"] = modalId.id
        }
        daisyModalPopover(id = modalId) {
            daisyModalBox {
                h3("text-lg font-bold") { +"Opened without JavaScript" }
                p("py-4") { +"The browser's popover API opened this. No script ran." }
                daisyModalAction {
                    daisyButton(text = "Close") {
                        attributes["popovertarget"] = modalId.id
                        attributes["popovertargetaction"] = "hide"
                    }
                }
            }
        }
    }
}
