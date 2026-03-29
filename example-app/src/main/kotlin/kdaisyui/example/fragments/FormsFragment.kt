package kdaisyui.example.fragments

import kdaisyui.components.*
import kdaisyui.core.addClassNames
import kotlinx.html.*

fun TagConsumer<*>.formsFragment() {
    formsHeader()
    createRepositoryFormSection()
    recentEventsCard()
    quickStatsCard()
}

private fun TagConsumer<*>.formsHeader() {
    header {
        addClassNames("col-span-12 flex items-center gap-2 lg:gap-4")
        div("grow") { h1("font-light lg:text-2xl") { +"Repository management" } }
    }
}

private fun TagConsumer<*>.createRepositoryFormSection() {
    section {
        addClassNames("col-span-12 xl:col-span-4")
        daisyFieldset {
            daisyLabel("Repository name")
            daisyInput(placeholder = "devtrack/api-gateway", extraClasses = "w-full")
        }
        daisyFieldset {
            daisyLabel("Visibility")
            daisySelect(extraClasses = "w-full") {
                option { +"Public" }
                option { +"Private" }
                option { +"Internal" }
            }
        }
        daisyFieldset {
            daisyLabel("Description")
            daisyInput(placeholder = "Core API gateway service", extraClasses = "w-full")
        }
        daisyFieldset {
            daisyLabel("Default branch")
            daisyInput(extraClasses = "w-full", value = "main")
        }
        daisyFieldset {
            label("flex cursor-pointer justify-between py-2") {
                span("label") { +"Initialize with README" }
                daisyToggle(size = ToggleSize.Sm, checked = true)
            }
        }
        daisyFieldset {
            daisyLabel("Add .gitignore")
            daisySelect(extraClasses = "w-full") {
                option { +"None" }
                option { +"Kotlin" }
                option { +"Java" }
                option { +"Node" }
                option { +"Python" }
                option { +"Go" }
            }
        }
    }
}

private fun TagConsumer<*>.recentEventsCard() {
    section {
        addClassNames("card bg-base-100 col-span-12 shadow-xs xl:col-span-5")
        div("text-base-content/60 p-6 pb-0 text-center text-xs font-bold") { +"Recent events" }
        daisyMenu(extraClasses = "w-full") {
            for ((idx, event) in listOf(
                "Pipeline succeeded on api-gateway/main" to "2 minutes ago",
                "PR #1204 merged into frontend/main" to "11 minutes ago",
                "Issue #2891 assigned to Bob" to "31 minutes ago",
                "Release v2.4.1 deployed to production" to "1 hour ago",
                "Security alert triaged in worker-service" to "2 hours ago",
            ).withIndex()) {
                li {
                    a("gap-4") {
                        daisyAvatar {
                            div("w-6 rounded-full") {
                                img(src = "https://picsum.photos/80/80?${idx + 6}")
                            }
                        }
                        span("text-xs") { b { +event.first }; br; +event.second }
                    }
                }
            }
        }
    }
}

private fun TagConsumer<*>.quickStatsCard() {
    section {
        addClassNames("card bg-base-100 col-span-12 shadow-xs xl:col-span-3")
        daisyCardBody {
            daisyCardTitle("Quick stats")
            ul("space-y-2") {
                quickStatItem("Commits today", "47")
                quickStatItem("Reviews pending", "12")
                quickStatItem("Deployments", "3")
                quickStatItem("Alerts", "1")
            }
        }
    }
}

private fun UL.quickStatItem(label: String, value: String) {
    li("flex items-center justify-between") {
        span { +label }
        span("font-semibold") { +value }
    }
}
