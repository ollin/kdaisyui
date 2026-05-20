package kdaisyui.example

import io.ktor.http.*
import io.ktor.server.html.*
import io.ktor.server.resources.get
import io.ktor.server.routing.*
import kdaisyui.example.fragments.*

fun Route.dashboardRoutes() {
    get<DashboardPage> {
        call.respondHtml(HttpStatusCode.OK) {
            dashboardShell(call.application)
        }
    }

    get<Fragments.Stats> { call.respondHtmlFragment { statsFragment() } }
    get<Fragments.CardsRow1> { call.respondHtmlFragment { cardsRow1Fragment() } }
    get<Fragments.CardsRow2> { call.respondHtmlFragment { cardsRow2Fragment() } }
    get<Fragments.Forms> { call.respondHtmlFragment { formsFragment() } }
    get<Fragments.FormSections> { call.respondHtmlFragment { formSectionsFragment() } }
    get<Fragments.Team> { call.respondHtmlFragment { teamFragment() } }
}
