package kdaisyui.example

import io.github.ollin.kdaisyui.components.daisyButton
import io.github.ollin.kdaisyui.components.daisyMegamenu
import io.github.ollin.kdaisyui.components.daisyMegamenuActive
import io.github.ollin.kdaisyui.components.daisyMegamenuPanel
import io.github.ollin.kdaisyui.core.HtmlId
import io.github.ollin.kdaisyui.core.popoverTarget
import kotlinx.html.*

/**
 * DaisyUI's documented megamenu, built from the generated wrappers.
 *
 * This page began as the same markup hand-written in raw `kotlinx.html`, because task 1.1 needed
 * it to answer a question about DaisyUI's markup without the DSL in the way. It now renders
 * through `daisyMegamenu` / `daisyMegamenuPanel` instead, and the scenarios and screenshots that
 * were written against the hand-written version are unchanged — which is what makes them proof
 * that the generated wrappers produce the documented construction rather than merely something
 * that renders.
 *
 * Source: `daisyui/skills/daisyui/components/megamenu.md:14-30`.
 */
fun HTML.megamenuReferencePage() {
    lang = "en"
    head {
        meta { charset = "utf-8" }
        title { +"Megamenu reference" }
        meta { name = "viewport"; content = "width=device-width, initial-scale=1" }
        daisyuiStylesheet()
    }
    body("bg-base-200 min-h-screen p-10") {
        h1("text-xl font-bold mb-6") { +"Megamenu reference" }
        p("mb-6 text-sm opacity-70") {
            +"DaisyUI's documented markup, from the generated wrappers. The trigger button below "
            +"is hidden at the sm breakpoint and up, where the megamenu renders as a horizontal bar."
        }

        daisyButton(
            text = "Menu",
            extraClasses = "sm:hidden",
            attrs = { popoverTarget(Megamenu.Reference()) },
        )

        daisyMegamenu(
            id = Megamenu.Reference(),
            extraClasses = "max-sm:megamenu-vertical p-2 border border-base-300",
        ) {
            daisyMegamenuActive { }

            panelTrigger("Components", Megamenu.PanelOne())
            daisyMegamenuPanel(id = Megamenu.PanelOne()) {
                ul("menu w-full") {
                    li { a { +"Buttons" } }
                    li { a { +"Cards" } }
                    li { a { +"Modals" } }
                }
            }

            panelTrigger("Docs", Megamenu.PanelTwo())
            daisyMegamenuPanel(id = Megamenu.PanelTwo()) {
                ul("menu w-full") {
                    li { a { +"Getting started" } }
                    li { a { +"Codegen" } }
                }
            }
        }

        p("mt-10 text-sm opacity-60") { +"end of page" }
    }
}

/**
 * A megamenu's own triggers carry no DaisyUI class — `megamenu.md:19` shows a bare `<button>` —
 * so this is plain `kotlinx.html` rather than `daisyButton`, which would add `btn`. Only the
 * outer trigger that opens the whole megamenu is a `btn`.
 */
private fun FlowContent.panelTrigger(label: String, panel: HtmlId) {
    button { popoverTarget(panel); +label }
}
