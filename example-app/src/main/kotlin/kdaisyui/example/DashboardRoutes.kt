package kdaisyui.example

import io.ktor.http.*
import io.ktor.server.html.*
import io.ktor.server.routing.*
import kdaisyui.example.fragments.*

fun Route.dashboardRoutes() {
    get("/") {
        call.respondHtml(HttpStatusCode.OK) { dashboardShell() }
    }

    route("/fragments") {
        get("/stats") { call.respondHtmlFragment { statsFragment() } }
        get("/cards-row1") { call.respondHtmlFragment { cardsRow1Fragment() } }
        get("/cards-row2") { call.respondHtmlFragment { cardsRow2Fragment() } }
        get("/forms") { call.respondHtmlFragment { formsFragment() } }
        get("/form-sections") { call.respondHtmlFragment { formSectionsFragment() } }
        get("/team") { call.respondHtmlFragment { teamFragment() } }
    }
}
