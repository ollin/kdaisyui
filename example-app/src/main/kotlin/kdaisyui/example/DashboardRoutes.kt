package kdaisyui.example

import io.ktor.http.*
import io.ktor.server.html.*
import io.ktor.server.resources.get
import io.ktor.server.routing.*
import kdaisyui.example.dashboard.*
import kdaisyui.example.repo.*
import kdaisyui.example.team.*

fun Route.dashboardRoutes() {
    get<DashboardPage> {
        call.respondHtml(HttpStatusCode.OK) {
            dashboardShell(call.application)
        }
    }

    get<Fragments.Stats> { call.respondHtmlFragment { statsFragment() } }
    get<Fragments.CardsRow1> { call.respondHtmlFragment { cardsRow1Fragment() } }
    get<Fragments.CardsRow2> { call.respondHtmlFragment { cardsRow2Fragment() } }
    get<Fragments.WhatsNew> { call.respondHtmlFragment { whatsNewFragment() } }
    get<Fragments.Forms> { call.respondHtmlFragment { repoFragment() } }
    get<Fragments.FormSections> { call.respondHtmlFragment { formSectionsFragment() } }
    get<Fragments.Team> { call.respondHtmlFragment { teamFragment() } }
}
