package kdaisyui.example.fragments

import com.github.ollin.kdaisyui.components.*
import com.github.ollin.kdaisyui.core.addClassNames
import kotlinx.html.*

fun TagConsumer<*>.cardsRow1Fragment() {
    recentPipelineRunsCard()
    openIssuesCard()
}

private fun TagConsumer<*>.recentPipelineRunsCard() {
    section {
        addClassNames("card bg-base-100 col-span-12 overflow-hidden shadow-xs xl:col-span-6")
        daisyCardBody(extraClasses = "grow-0") {
            daisyCardTitle { daisyLink("Recent Pipeline Runs", hover = true) }
        }
        div("overflow-x-auto") {
            daisyTable(zebra = true) {
                thead {
                    tr {
                        th { +"Repository" }
                        th { +"Branch" }
                        th { +"Status" }
                        th { +"Duration" }
                        th { +"Triggered by" }
                    }
                }
                tbody {
                    pipelineRow("api-gateway", "main", "Success", "2m 14s", "push")
                    pipelineRow("frontend", "feat/auth", "Failed", "0m 47s", "push")
                    pipelineRow("worker-service", "main", "Success", "5m 02s", "schedule")
                    pipelineRow("docs", "main", "Success", "1m 33s", "push")
                    pipelineRow("mobile-app", "develop", "Running", "—", "push")
                }
            }
        }
    }
}

private fun TBODY.pipelineRow(repository: String, branch: String, status: String, duration: String, trigger: String) {
    tr {
        td { +repository }
        td { +branch }
        td {
            val (variant, text) = when (status) {
                "Success" -> BadgeVariant.Success to "Success"
                "Failed" -> BadgeVariant.Error to "Failed"
                else -> BadgeVariant.Info to "Running"
            }
            daisyBadge(text, variant = variant, size = BadgeSize.Sm)
        }
        td { +duration }
        td { +trigger }
    }
}

private fun TagConsumer<*>.openIssuesCard() {
    section {
        addClassNames("card bg-base-100 col-span-12 shadow-xs xl:col-span-6")
        daisyCardBody {
            daisyCardTitle("Open Issues")
            ul("space-y-2") {
                issueLabel("Bug", "234", BadgeVariant.Error)
                issueLabel("Enhancement", "891", BadgeVariant.Info)
                issueLabel("Documentation", "156", BadgeVariant.Success)
                issueLabel("Question", "89", BadgeVariant.Warning)
                issueLabel("Good First Issue", "477", BadgeVariant.Primary)
            }
        }
    }
}

private fun UL.issueLabel(label: String, count: String, variant: BadgeVariant) {
    li("flex items-center justify-between") {
        span { +label }
        daisyBadge(count, variant = variant)
    }
}
