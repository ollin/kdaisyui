package kdaisyui.example

import io.github.ollin.kdaisyui.components.*
import io.github.ollin.kdaisyui.core.addClassNames
import io.github.ollin.kdaisyui.core.HtmlId
import io.github.ollin.kdaisyui.themes.DaisyTheme
import io.github.ollin.kdaisyui.themes.daisyTheme
import io.ktor.server.application.Application
import io.ktor.server.resources.href
import kotlinx.html.*

fun HTML.dashboardShell(application: Application, theme: DaisyTheme?) {
    appShell(application, theme, "Overview") {
        htmxPlaceholder(application.href(Fragments.Stats()), "load", "col-span-12")
        htmxPlaceholder(application.href(Fragments.CardsRow1()), "load delay:100ms", "col-span-12 grid grid-cols-12 gap-y-12 lg:gap-x-12")
        htmxPlaceholder(application.href(Fragments.CardsRow2()), "revealed", "col-span-12 grid grid-cols-12 gap-y-12 lg:gap-x-12")
        htmxPlaceholder(application.href(Fragments.WhatsNew()), "revealed", "col-span-12 grid grid-cols-12 gap-y-12 lg:gap-x-12")
        htmxPlaceholder(application.href(Fragments.Forms()), "revealed", "col-span-12 grid grid-cols-12 gap-y-12 lg:gap-x-12")
        htmxPlaceholder(application.href(Fragments.FormSections()), "revealed", "col-span-12 grid grid-cols-12 gap-y-12 lg:gap-x-12")
        htmxPlaceholder(application.href(Fragments.Team()), "revealed", "col-span-12 grid grid-cols-12 gap-y-12 lg:gap-x-12")
    }
}

/**
 * The frame every page of the app shares: the drawer with its sidebar, and a header with the
 * page's [heading], the search field and the avatar menu. [pageContent] fills the twelve-column grid
 * below the header.
 */
fun HTML.appShell(application: Application, theme: DaisyTheme?, heading: String, pageContent: DIV.() -> Unit) {
    lang = "en"
    if (theme != null) daisyTheme(theme)
    head {
        meta { charset = "utf-8" }
        title { +"DevTrack $heading" }
        meta { name = "viewport"; content = "width=device-width, initial-scale=1" }
        pageAssets()
        script { src = "/webjars/htmx.org/dist/htmx.min.js" }
    }
    body("bg-base-200 lg:drawer-open min-h-screen") {
        daisyDrawerClasses()
        val drawerId: HtmlId = Dashboard.Drawer()
        input {
            id = drawerId.id
            type = InputType.checkBox
            daisyDrawerToggleClasses()
        }
        main {
            daisyDrawerContentClasses()
            id = Dashboard.Content().id
            div("grid grid-cols-12 grid-rows-[min-content] gap-y-12 p-4 lg:gap-x-12 lg:p-10") {
                shellHeader(application.href(ProfilePage()), heading)
                pageContent()
            }
        }
        aside("z-10") {
            daisyDrawerSideClasses()
            label {
                htmlFor = drawerId.id
                daisyDrawerOverlayClasses()
            }
            shellSidebar(application.href(DashboardPage()), overviewActive = heading == "Overview")
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
            daisyLoading(size = LoadingSize.Lg, style = LoadingStyle.Spinner)
        }
    }
}

private fun FlowContent.shellHeader(profileUrl: String, heading: String) {
    header("col-span-12 flex items-center gap-2 lg:gap-4") {
        id = Dashboard.Header().id
        label {
            htmlFor = Dashboard.Drawer().id
            classes = setOf("lg:hidden")
            daisyButtonClasses(style = ButtonStyle.Ghost, shape = ButtonShape.Square)
            daisyDrawerButtonClasses()
            +"☰"
        }
        div("grow") {
            h1("lg:text-2xl lg:font-light") { +heading }
        }
        div {
            daisyInput(
                id = Dashboard.Search(),
                size = InputSize.Sm,
                placeholder = "Search",
                extraClasses = "rounded-full max-sm:w-24",
            )
        }
        div("z-10") {
            daisyDropdownClasses(align = DropdownAlign.End)
            id = Dashboard.Header.Avatar().id
            daisyAvatar(placeholder = true) {
                daisyButtonClasses(style = ButtonStyle.Ghost, shape = ButtonShape.Circle)
                attributes["tabindex"] = "0"
                div("bg-neutral text-neutral-content w-10 rounded-full") { span { +"DT" } }
            }
            ul {
                attributes["tabindex"] = "0"
                classes = setOf("rounded-box", "bg-base-100", "mt-3", "w-52", "p-2", "shadow-2xl")
                daisyMenuClasses()
                daisyDropdownContentClasses()
                li { a(href = profileUrl) { +"Profile" } }
                li {
                    a {
                        +"Inbox"
                        daisyBadge("12", color = BadgeColor.Success)
                    }
                }
                li { a { +"Settings" } }
                li { a { +"Logout" } }
            }
        }
    }
}

private fun FlowContent.shellSidebar(dashboardUrl: String, overviewActive: Boolean) {
    nav("bg-base-100 flex min-h-screen w-72 flex-col gap-2 overflow-y-auto px-6 py-10") {
        id = Dashboard.Sidebar().id
        div("mx-4 flex items-center gap-2 font-black") { +"DevTrack" }
        daisyMenu(id = Dashboard.Sidebar.Menu(), extraClasses = "w-full") {
            // `classes` NAMED, because kotlinx.html's `a` is a(href, target, referrerPolicy,
            // classes): the positional form set href="menu-active", so this item linked to a
            // route that does not exist and never got the active styling either.
            li { a(href = dashboardUrl, classes = if (overviewActive) "menu-active" else null) { +"Overview" } }
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
