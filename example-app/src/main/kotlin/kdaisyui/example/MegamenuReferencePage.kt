package kdaisyui.example

import kotlinx.html.*

/**
 * DaisyUI's documented megamenu markup, **hand-written on purpose**.
 *
 * This is the reference the generated wrappers are measured against. It is written with raw
 * `kotlinx.html` rather than `daisyMegamenu`, so that what it renders is DaisyUI's markup and
 * nothing of ours — which is the only way it can answer whether the generated version is right.
 *
 * `daisyMegamenu` today emits no `popover` attribute and there is no wrapper for the panels, so
 * the component as generated cannot open. When `support-popover-megamenu` task 5.1 rebuilds this
 * page from generated wrappers, the screenshots this page produces are what prove the rebuild
 * matches.
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
            +"DaisyUI's documented markup, hand-written. The trigger button below is hidden at "
            +"the sm breakpoint and up, where the megamenu is meant to render as a horizontal bar."
        }

        button(classes = "btn sm:hidden") {
            attributes["popovertarget"] = "megamenu-reference"
            +"Menu"
        }

        div("megamenu max-sm:megamenu-vertical p-2 border border-base-300") {
            id = "megamenu-reference"
            attributes["popover"] = ""

            span("megamenu-active") { }

            button { attributes["popovertarget"] = "megamenu-panel-one"; +"Components" }
            div {
                id = "megamenu-panel-one"
                attributes["popover"] = ""
                ul("menu w-full") {
                    li { a { +"Buttons" } }
                    li { a { +"Cards" } }
                    li { a { +"Modals" } }
                }
            }

            button { attributes["popovertarget"] = "megamenu-panel-two"; +"Docs" }
            div {
                id = "megamenu-panel-two"
                attributes["popover"] = ""
                ul("menu w-full") {
                    li { a { +"Getting started" } }
                    li { a { +"Codegen" } }
                }
            }
        }

        p("mt-10 text-sm opacity-60") { +"end of page" }
    }
}
