package kdaisyui.example

import io.ktor.http.*
import io.ktor.server.html.*
import io.github.ollin.kdaisyui.themes.daisyThemeOf
import io.ktor.server.request.receiveParameters
import io.ktor.server.resources.get
import io.ktor.server.resources.href
import io.ktor.server.resources.post
import io.ktor.server.response.respond
import io.ktor.server.routing.*
import kdaisyui.example.dashboard.*
import kdaisyui.example.repo.*
import kdaisyui.example.team.*

fun Route.dashboardRoutes() {
    get<DashboardPage> {
        call.respondHtml(HttpStatusCode.OK) {
            dashboardShell(call.application, call.storedTheme())
        }
    }

    get<PopoverModalDemo> { call.respondHtml(HttpStatusCode.OK) { popoverModalPage() } }
    get<DropdownPopoverDemo> { call.respondHtml(HttpStatusCode.OK) { dropdownPopoverPage() } }

    get<MegamenuReference> { call.respondHtml(HttpStatusCode.OK) { megamenuReferencePage() } }

    get<TailwindVariantCheck> { call.respondHtml(HttpStatusCode.OK) { tailwindVariantPage() } }
    get<DisabledStateCheck> { call.respondHtml(HttpStatusCode.OK) { disabledStatePage() } }
    get<TabsDemo> { call.respondHtml(HttpStatusCode.OK) { tabsPage() } }
    get<ProfilePage> { call.respondHtml(HttpStatusCode.OK) { profilePage(call.application.href(ProfilePage.Theme())) } }
    post<ProfilePage.Theme> {
        val theme = daisyThemeOf(call.receiveParameters()["theme"].orEmpty())
            ?: return@post call.respond(HttpStatusCode.BadRequest)
        call.response.cookies.append(themeCookie(theme))
        call.response.headers.append(HttpHeaders.Location, call.application.href(ProfilePage()))
        call.respond(HttpStatusCode.SeeOther)
    }

    get<Fragments.Stats> { call.respondHtmlFragment { statsFragment() } }
    get<Fragments.CardsRow1> { call.respondHtmlFragment { cardsRow1Fragment() } }
    get<Fragments.CardsRow2> { call.respondHtmlFragment { cardsRow2Fragment() } }
    get<Fragments.WhatsNew> { call.respondHtmlFragment { whatsNewFragment() } }
    get<Fragments.Forms> { call.respondHtmlFragment { repoFragment() } }
    get<Fragments.FormSections> { call.respondHtmlFragment { formSectionsFragment() } }
    get<Fragments.Team> { call.respondHtmlFragment { teamFragment() } }
}
