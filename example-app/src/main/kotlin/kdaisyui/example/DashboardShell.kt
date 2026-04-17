package kdaisyui.example

import com.github.ollin.kdaisyui.components.*
import com.github.ollin.kdaisyui.core.addClassNames
import kotlinx.html.*

fun HTML.dashboardShell() {
    lang = "en"
    head {
        meta { charset = "utf-8" }
        title { +"DevTrack Overview" }
        meta { name = "viewport"; content = "width=device-width, initial-scale=1" }
        link { rel = "stylesheet"; href = "/webjars/daisyui/daisyui.css" }
        link { rel = "stylesheet"; href = "/webjars/daisyui/themes.css" }
        script { src = "/webjars/tailwindcss__browser/dist/index.global.js" }
        script { src = "/webjars/htmx.org/dist/htmx.min.js" }
        style {
            unsafe {
                raw("""
                    .htmx-indicator { opacity: 0; transition: opacity 200ms ease-in; }
                    .htmx-request .htmx-indicator, .htmx-request.htmx-indicator { opacity: 1; }
                """.trimIndent())
            }
        }
    }
    body("drawer bg-base-200 lg:drawer-open min-h-screen") {
        input {
            id = "my-drawer"
            type = InputType.checkBox
            classes = setOf("drawer-toggle")
        }
        main("drawer-content") {
            div("grid grid-cols-12 grid-rows-[min-content] gap-y-12 p-4 lg:gap-x-12 lg:p-10") {
                shellHeader()
                htmxPlaceholder("/fragments/stats", "load", "col-span-12")
                htmxPlaceholder("/fragments/cards-row1", "load delay:100ms", "col-span-12 grid grid-cols-12 gap-y-12 lg:gap-x-12")
                htmxPlaceholder("/fragments/cards-row2", "revealed", "col-span-12 grid grid-cols-12 gap-y-12 lg:gap-x-12")
                htmxPlaceholder("/fragments/forms", "revealed", "col-span-12 grid grid-cols-12 gap-y-12 lg:gap-x-12")
                htmxPlaceholder("/fragments/form-sections", "revealed", "col-span-12 grid grid-cols-12 gap-y-12 lg:gap-x-12")
                htmxPlaceholder("/fragments/team", "revealed", "col-span-12 grid grid-cols-12 gap-y-12 lg:gap-x-12")
            }
        }
        aside("drawer-side z-10") {
            label {
                htmlFor = "my-drawer"
                classes = setOf("drawer-overlay")
            }
            shellSidebar()
        }
    }
}

private fun FlowContent.htmxPlaceholder(url: String, trigger: String, extraClasses: String) {
    div {
        addClassNames(extraClasses)
        hxGet(url)
        hxTrigger(trigger)
        hxSwap("outerHTML")
        div("flex justify-center py-12 col-span-12") {
            span("loading loading-spinner loading-lg") {}
        }
    }
}

private fun FlowContent.shellHeader() {
    header("col-span-12 flex items-center gap-2 lg:gap-4") {
        label {
            htmlFor = "my-drawer"
            classes = setOf("btn", "btn-square", "btn-ghost", "drawer-button", "lg:hidden")
            +"☰"
        }
        div("grow") {
            h1("lg:text-2xl lg:font-light") { +"Overview" }
        }
        div {
            daisyInput(
                size = InputSize.Sm,
                placeholder = "Search",
                extraClasses = "rounded-full max-sm:w-24",
            )
        }
        div("dropdown dropdown-end z-10") {
            div {
                attributes["tabindex"] = "0"
                classes = setOf("avatar", "btn", "btn-circle", "btn-ghost")
                div("w-10 rounded-full") {
                    img(src = "https://picsum.photos/80/80?5")
                }
            }
            ul {
                attributes["tabindex"] = "0"
                classes = setOf("menu", "dropdown-content", "rounded-box", "bg-base-100", "mt-3", "w-52", "p-2", "shadow-2xl")
                li { a { +"Profile" } }
                li {
                    a {
                        +"Inbox"
                        daisyBadge("12", variant = BadgeVariant.Success)
                    }
                }
                li { a { +"Settings" } }
                li { a { +"Logout" } }
            }
        }
    }
}

private fun FlowContent.shellSidebar() {
    nav("bg-base-100 flex min-h-screen w-72 flex-col gap-2 overflow-y-auto px-6 py-10") {
        div("mx-4 flex items-center gap-2 font-black") { +"DevTrack" }
        daisyMenu(extraClasses = "w-full") {
            li { a("menu-active") { +"Overview" } }
            sidebarSubmenu("Repositories", listOf("All Repos", "New Repository", "Archived"))
            li { a { +"Issues" } }
            li { a { +"Pull Requests" } }
            li { a { +"Pipelines" } }
            li { a { +"Teams" } }
            li { a { +"Settings" } }
        }
    }
}

private fun UL.sidebarSubmenu(title: String, items: List<String>) {
    li {
        details {
            summary { +title }
            ul {
                for (item in items) {
                    li { a { +item } }
                }
            }
        }
    }
}
