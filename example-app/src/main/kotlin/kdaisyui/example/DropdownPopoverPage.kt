package kdaisyui.example

import io.github.ollin.kdaisyui.components.*
import io.github.ollin.kdaisyui.core.popoverTarget
import kotlinx.html.*

/**
 * DaisyUI's popover dropdown, on a page of its own and with no script.
 *
 * The trigger and the menu are a browser-native pair: `popovertarget` opens the menu, and the
 * anchor name both derive from the menu's id places it next to the trigger through CSS anchor
 * positioning. On the top layer it needs no z-index and no ancestor can clip it.
 */
fun HTML.dropdownPopoverPage() {
    lang = "en"
    head {
        meta { charset = "utf-8" }
        title { +"Popover Dropdown" }
        meta { name = "viewport"; content = "width=device-width, initial-scale=1" }
        daisyuiStylesheet()
    }
    body("bg-base-200 min-h-screen p-10") {
        val menu = Dashboard.DropdownPopover()
        daisyButton("Open menu", attrs = { popoverTarget(menu, anchored = true) })
        daisyDropdownPopover(id = menu, extraClasses = "bg-base-100 rounded-box w-52 shadow-sm", attrs = { daisyMenuClasses() }) {
            li { a { +"Item 1" } }
            li { a { +"Item 2" } }
        }
    }
}
