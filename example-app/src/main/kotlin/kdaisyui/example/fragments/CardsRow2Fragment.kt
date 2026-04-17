package kdaisyui.example.fragments

import io.github.ollin.kdaisyui.components.*
import io.github.ollin.kdaisyui.core.addClassNames
import kotlinx.html.*

fun TagConsumer<*>.cardsRow2Fragment() {
    repositoryActivityCard()
    issuesByStatusCard()
    teamActivityCard()
}

private fun TagConsumer<*>.repositoryActivityCard() {
    section {
        addClassNames("card bg-base-100 col-span-12 shadow-xs xl:col-span-4")
        daisyCardBody {
            daisyCardTitle("Repository Activity")
            ul("space-y-3") {
                for ((repo, commits) in listOf(
                    "api-gateway" to "47 commits",
                    "frontend" to "38 commits",
                    "worker-service" to "29 commits",
                    "mobile-app" to "21 commits",
                )) {
                    li("flex items-center justify-between") {
                        span("font-medium") { +repo }
                        span("text-base-content/70 text-sm") { +commits }
                    }
                }
            }
        }
    }
}

private fun TagConsumer<*>.issuesByStatusCard() {
    section {
        addClassNames("card bg-base-100 col-span-12 shadow-xs xl:col-span-4")
        daisyCardBody {
            daisyCardTitle("Issues by Status")
            ul("space-y-2") {
                issueStatusRow("Open", "1847")
                issueStatusRow("In Progress", "234")
                issueStatusRow("Closed This Month", "892")
                issueStatusRow("Won't Fix", "45")
            }
        }
    }
}

private fun TagConsumer<*>.teamActivityCard() {
    section {
        addClassNames("card bg-base-100 col-span-12 shadow-xs xl:col-span-4")
        daisyCardBody {
            daisyCardTitle("Team Activity")
            daisyMenu(extraClasses = "w-full") {
                for ((idx, event) in listOf(
                    "Alice merged PR #1204",
                    "Bob opened issue #2891",
                    "Carol deployed v2.4.1",
                    "Dave closed 12 issues",
                    "Eve created repository",
                ).withIndex()) {
                    li {
                        a("gap-3") {
                            daisyAvatar {
                                div("w-8 rounded-full") {
                                    img(src = "https://picsum.photos/80/80?team-${idx + 20}", alt = "Team avatar")
                                }
                            }
                            span("text-sm") { +event }
                        }
                    }
                }
            }
        }
    }
}

private fun UL.issueStatusRow(status: String, count: String) {
    li("flex items-center justify-between") {
        span { +status }
        span("font-mono") { +count }
    }
}
