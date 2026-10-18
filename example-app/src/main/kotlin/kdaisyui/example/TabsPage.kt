package kdaisyui.example

import io.github.ollin.kdaisyui.components.TabsStyle
import io.github.ollin.kdaisyui.components.daisyTab
import io.github.ollin.kdaisyui.components.daisyTabs
import kotlinx.html.*

/**
 * A tab bar built from `daisyTabs` and `daisyTab`, on a page of its own.
 *
 * Until `declare-the-component-model` the container rendered a `<button>` and the active toggle
 * emitted `tabs-tab-active`, a class DaisyUI does not have, so an active tab looked like every
 * other. The second tab is the positive control: the active one must look different from it.
 */
fun HTML.tabsPage() {
    lang = "en"
    head {
        meta { charset = "utf-8" }
        title { +"Tabs" }
        meta { name = "viewport"; content = "width=device-width, initial-scale=1" }
        daisyuiStylesheet()
    }
    body("bg-base-200 min-h-screen p-10") {
        daisyTabs(style = TabsStyle.Lift) {
            daisyTab(active = true, attrs = { attributes["id"] = "tab-active" }) { +"Overview" }
            daisyTab(attrs = { attributes["id"] = "tab-inactive" }) { +"Portfolios" }
        }
    }
}
