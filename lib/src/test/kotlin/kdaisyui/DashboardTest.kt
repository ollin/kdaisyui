package kdaisyui

import kdaisyui.components.*
import kotlinx.html.*
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertTrue

class DashboardTest {

    private fun generateDashboard(): String = createHTML(prettyPrint = true).html {
        lang = "en"
        head {
            meta { charset = "utf-8" }
            title { +"DevTrack Overview" }
            meta { name = "viewport"; content = "width=device-width, initial-scale=1" }
            link { rel = "stylesheet"; href = "output.css" }
        }
        body("drawer bg-base-200 lg:drawer-open min-h-screen") {
            input {
                id = "my-drawer"
                type = InputType.checkBox
                classes = setOf("drawer-toggle")
            }
            main("drawer-content") {
                div("grid grid-cols-12 grid-rows-[min-content] gap-y-12 p-4 lg:gap-x-12 lg:p-10") {
                    dashboardHeader()
                    statsSection()
                    pipelinesCard()
                    openIssuesCard()
                    repositoryActivityCard()
                    issuesByStatusCard()
                    teamActivityCard()
                    createRepositoryHeader()
                    createRepositoryForm()
                    recentEventsCard()
                    quickStatsCard()
                    settingsHeader()
                    teamMembersSection()
                    notificationSettingsSection()
                    repositorySettingsSection()
                    teamHeader()
                    inviteTeamMemberForm()
                    activeTeamMembersCard()
                    componentCoverageSection()
                }
            }
            aside("drawer-side z-10") {
                label {
                    htmlFor = "my-drawer"
                    classes = setOf("drawer-overlay")
                }
                sidebarNav()
            }
        }
    }

    private fun FlowContent.dashboardHeader() {
        header("col-span-12 flex items-center gap-2 lg:gap-4") {
            label {
                htmlFor = "my-drawer"
                classes = setOf("btn", "btn-square", "btn-ghost", "drawer-button", "lg:hidden")
            }
            div("grow") { h1("lg:text-2xl lg:font-light") { +"Overview" } }
            daisyInput(size = InputSize.Sm, placeholder = "Search", extraClasses = "rounded-full max-sm:w-24")
            daisySwap(rotate = true) {
                attributes["data-toggle-theme"] = "dark"
                attributes["data-act-class"] = "swap-active"
                span("swap-off") { +"☀\uFE0F" }
                span("swap-on") { +"🌙" }
            }
            daisyDropdown(end = true, extraClasses = "z-10") {
                div {
                    attributes["tabindex"] = "0"
                    classes = setOf("btn", "btn-circle", "btn-ghost")
                    daisyIndicator {
                        daisyBadge("3", variant = BadgeVariant.Warning, size = BadgeSize.Xs, extraClasses = "indicator-item")
                        +"🔔"
                    }
                }
                daisyDropdownContent(extraClasses = "rounded-box bg-base-100 mt-3 w-80 p-2 shadow-2xl") {
                    attributes["tabindex"] = "0"
                    ul {
                        li { a { +"Pipeline failed on frontend/feat/auth" } }
                        li { a { +"PR #1204 merged into main" } }
                    }
                }
            }
            daisyDropdown(extraClasses = "z-10") {
                div {
                    attributes["tabindex"] = "0"
                    classes = setOf("avatar", "btn", "btn-circle", "btn-ghost")
                    div("w-10 rounded-full") { img(src = "https://picsum.photos/80/80?100") }
                }
                daisyDropdownContent(extraClasses = "rounded-box bg-base-100 mt-3 w-52 p-2 shadow-2xl") {
                    attributes["tabindex"] = "0"
                    ul {
                        li { a { +"Profile" } }
                        li { a { +"Settings" } }
                    }
                }
            }
        }
    }

    private fun FlowContent.statsSection() {
        section("stats stats-vertical xl:stats-horizontal bg-base-100 col-span-12 w-full shadow-xs") {
            stat("Active Repositories", "142", "Across all organizations")
            stat("Open Issues", "1,847", "Live triage queue")
            stat("PRs Merged This Month", "389", "Healthy review velocity")
            stat("Pipeline Success Rate", "94.2%", "Last 30 days")
        }
    }

    private fun SECTION.stat(title: String, value: String, desc: String) {
        daisyStatStat {
            daisyStatStatTitle(title)
            daisyStatStatValue(value)
            daisyStatStatDesc(desc)
        }
    }

    private fun FlowContent.pipelinesCard() {
        section("card bg-base-100 col-span-12 overflow-hidden shadow-xs xl:col-span-6") {
            daisyCardBody(extraClasses = "grow-0") { daisyCardTitle { daisyLink("Recent Pipeline Runs", hover = true) } }
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
                val variant = when (status) {
                    "Success" -> BadgeVariant.Success
                    "Failed" -> BadgeVariant.Error
                    else -> BadgeVariant.Info
                }
                daisyBadge(status, variant = variant, size = BadgeSize.Sm)
            }
            td { +duration }
            td { +trigger }
        }
    }

    private fun FlowContent.openIssuesCard() {
        section("card bg-base-100 col-span-12 shadow-xs xl:col-span-6") {
            daisyCardBody {
                daisyCardTitle("Open Issues")
                ul("space-y-2") {
                    issue("Bug", "234", BadgeVariant.Error)
                    issue("Enhancement", "891", BadgeVariant.Info)
                    issue("Documentation", "156", BadgeVariant.Success)
                    issue("Question", "89", BadgeVariant.Warning)
                    issue("Good First Issue", "477", BadgeVariant.Primary)
                }
            }
        }
    }

    private fun UL.issue(label: String, count: String, variant: BadgeVariant) {
        li("flex items-center justify-between") {
            span { +label }
            daisyBadge(count, variant = variant)
        }
    }

    private fun FlowContent.repositoryActivityCard() {
        section("card bg-base-100 col-span-12 shadow-xs xl:col-span-4") {
            daisyCardBody {
                daisyCardTitle("Repository Activity")
                ul("space-y-3") {
                    li("flex items-center justify-between") { span { +"api-gateway" }; span("text-sm") { +"47 commits" } }
                    li("flex items-center justify-between") { span { +"frontend" }; span("text-sm") { +"38 commits" } }
                    li("flex items-center justify-between") { span { +"worker-service" }; span("text-sm") { +"29 commits" } }
                    li("flex items-center justify-between") { span { +"mobile-app" }; span("text-sm") { +"21 commits" } }
                }
            }
        }
    }

    private fun FlowContent.issuesByStatusCard() {
        section("card bg-base-100 col-span-12 shadow-xs xl:col-span-4") {
            daisyCardBody {
                daisyCardTitle("Issues by Status")
                ul("space-y-2") {
                    li("flex items-center justify-between") { span { +"Open" }; span("font-mono") { +"1847" } }
                    li("flex items-center justify-between") { span { +"In Progress" }; span("font-mono") { +"234" } }
                    li("flex items-center justify-between") { span { +"Closed This Month" }; span("font-mono") { +"892" } }
                    li("flex items-center justify-between") { span { +"Won't Fix" }; span("font-mono") { +"45" } }
                }
            }
        }
    }

    private fun FlowContent.teamActivityCard() {
        section("card bg-base-100 col-span-12 shadow-xs xl:col-span-4") {
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

    private fun FlowContent.createRepositoryHeader() {
        header("col-span-12 flex items-center gap-2 lg:gap-4") {
            div("grow") { h1("font-light lg:text-2xl") { +"Repository management" } }
        }
    }

    private fun FlowContent.createRepositoryForm() {
        section("col-span-12 xl:col-span-4") {
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

    private fun FlowContent.recentEventsCard() {
        section("card bg-base-100 col-span-12 shadow-xs xl:col-span-5") {
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

    private fun FlowContent.quickStatsCard() {
        section("card bg-base-100 col-span-12 shadow-xs xl:col-span-3") {
            daisyCardBody {
                daisyCardTitle("Quick stats")
                ul("space-y-2") {
                    li("flex items-center justify-between") { span { +"Commits today" }; b { +"47" } }
                    li("flex items-center justify-between") { span { +"Reviews pending" }; b { +"12" } }
                    li("flex items-center justify-between") { span { +"Deployments" }; b { +"3" } }
                    li("flex items-center justify-between") { span { +"Alerts" }; b { +"1" } }
                }
            }
        }
    }

    private fun FlowContent.settingsHeader() {
        header("col-span-12 flex items-center gap-2 lg:gap-4") {
            div("grow") { h1("font-light lg:text-2xl") { +"Team and repository settings" } }
        }
    }

    private fun FlowContent.teamMembersSection() {
        section("col-span-12 xl:col-span-4") {
            daisyCard(extraClasses = "bg-base-100 shadow-xs") {
                daisyCardBody {
                    daisyCardTitle("Team members")
                    ul("space-y-4") {
                        member(1, "Alice Chen", "Owner")
                        member(2, "Bob Martinez", "Maintainer")
                        member(3, "Carol White", "Developer")
                    }
                }
            }
        }
    }

    private fun UL.member(imgId: Int, name: String, role: String) {
        li("flex items-center gap-3") {
            daisyAvatar {
                div("w-10 rounded-full") { img(src = "https://picsum.photos/80/80?member-$imgId", alt = "$name avatar") }
            }
            div("grow") {
                div("font-medium") { +name }
                div("text-base-content/60 text-xs") { +role }
            }
            daisyButton("Remove", size = ButtonSize.Xs, variant = ButtonVariant.Error, outline = true)
        }
    }

    private fun FlowContent.notificationSettingsSection() {
        section("col-span-12 xl:col-span-4") {
            daisyCard(extraClasses = "bg-base-100 shadow-xs") {
                daisyCardBody {
                    daisyCardTitle("Notification settings")
                    daisyFieldset {
                        settingToggle("Pipeline failures", true)
                        settingToggle("PR reviews requested", true)
                        settingToggle("Issue assignments", true)
                        settingToggle("Security alerts", true)
                        settingToggle("Weekly digest", false)
                    }
                }
            }
        }
    }

    private fun FIELDSET.settingToggle(name: String, enabled: Boolean) {
        this.label("flex cursor-pointer justify-between py-2") {
            span("label") { +name }
            daisyToggle(size = ToggleSize.Sm, checked = enabled)
        }
    }

    private fun FlowContent.repositorySettingsSection() {
        section("col-span-12 xl:col-span-4") {
            daisyCard(extraClasses = "bg-base-100 shadow-xs") {
                daisyCardBody {
                    daisyCardTitle("Repository settings")
                    daisyFieldset {
                        settingToggle("Branch protection", true)
                        settingToggle("Require PR reviews", true)
                        settingToggle("Auto-delete branches", false)
                        settingToggle("Allow force push", false)
                    }
                    daisyButton("Save settings", variant = ButtonVariant.Primary)
                }
            }
        }
    }

    private fun FlowContent.teamHeader() {
        header("col-span-12 flex items-center gap-2 lg:gap-4") {
            div("grow") { h1("font-light lg:text-2xl") { +"Team management" } }
        }
    }

    private fun FlowContent.inviteTeamMemberForm() {
        section("card bg-base-100 col-span-12 xl:col-span-5") {
            form(classes = "card-body") {
                action = ""
                daisyAlert(variant = AlertVariant.Success) { span { +"Invitation sent successfully" } }
                daisyFieldset {
                    daisyLabel("Email")
                    daisyInput(placeholder = "new.member@devtrack.io", extraClasses = "w-full")
                }
                daisyFieldset {
                    daisyLabel("Role")
                    daisySelect(extraClasses = "w-full") {
                        option { +"Owner" }
                        option { +"Maintainer" }
                        option { +"Developer" }
                        option { +"Reporter" }
                    }
                }
                daisyFieldset {
                    daisyLabel("Repositories")
                    daisySelect(extraClasses = "w-full") {
                        option { +"All" }
                        option { +"Specific" }
                    }
                }
                daisyFieldset {
                    div("flex items-end py-4") {
                        daisyButton("Send invitation", variant = ButtonVariant.Primary, extraClasses = "grow")
                    }
                }
            }
        }
    }

    private fun FlowContent.activeTeamMembersCard() {
        section("card bg-base-100 col-span-12 overflow-hidden shadow-xs xl:col-span-7") {
            daisyCardBody(extraClasses = "grow-0") {
                daisyCardTitle("Active team members")
            }
            div("overflow-x-auto") {
                daisyTable(zebra = true) {
                    thead {
                        tr {
                            th { +"Member" }
                            th { +"Role" }
                            th { +"Repositories" }
                            th { +"Last active" }
                        }
                    }
                    tbody {
                        memberRow(1, "Alice Chen", "alice.chen@devtrack.io", "Owner", "All", "2 minutes ago")
                        memberRow(2, "Bob Martinez", "bob.martinez@devtrack.io", "Maintainer", "12", "9 minutes ago")
                        memberRow(3, "Carol White", "carol.white@devtrack.io", "Developer", "7", "24 minutes ago")
                        memberRow(4, "Dave Kim", "dave.kim@devtrack.io", "Developer", "5", "1 hour ago")
                        memberRow(5, "Eve Jackson", "eve.jackson@devtrack.io", "Reporter", "3", "2 hours ago")
                        memberRow(6, "Frank Diaz", "frank.diaz@devtrack.io", "Maintainer", "10", "Today")
                    }
                }
            }
        }
    }

    private fun FlowContent.componentCoverageSection() {
        section("col-span-12") {
            daisyCard(extraClasses = "bg-base-100 shadow-xs") {
                daisyCardBody {
                    daisyCardTitle("Component coverage")
                    daisyFieldset {
                        label("flex cursor-pointer gap-3") {
                            daisyCheckbox(size = CheckboxSize.Sm, checked = true)
                            span("label") { +"Enable notifications" }
                        }
                        label("flex cursor-pointer gap-3") {
                            daisyRadio(name = "coverage-mode", size = RadioSize.Sm, checked = true)
                            span("label") { +"Standard mode" }
                        }
                        daisyRange(size = RangeSize.Xs, min = "0", max = "100", value = "50", step = "25")
                    }
                    daisyJoin {
                        daisyButton("Prev", size = ButtonSize.Sm, extraClasses = "join-item")
                        daisyInput(size = InputSize.Sm, extraClasses = "join-item w-24 text-center", value = "2")
                        daisyButton("Next", size = ButtonSize.Sm, extraClasses = "join-item")
                    }
                }
            }
        }
    }

    private fun TBODY.memberRow(
        imgId: Int,
        name: String,
        email: String,
        role: String,
        repositories: String,
        lastActive: String,
    ) {
        tr {
            td {
                div("flex items-center gap-4") {
                    daisyAvatar {
                        div("mask mask-squircle h-10 w-10") {
                            img(src = "https://picsum.photos/80/80?team-member-$imgId", alt = "Avatar")
                        }
                    }
                    div {
                        div("text-sm font-bold") { +name }
                        div("text-xs opacity-70") { +email }
                    }
                }
            }
            td {
                val variant = when (role) {
                    "Owner" -> BadgeVariant.Primary
                    "Maintainer" -> BadgeVariant.Info
                    "Developer" -> BadgeVariant.Success
                    else -> BadgeVariant.Warning
                }
                daisyBadge(role, variant = variant, size = BadgeSize.Sm)
            }
            td { +repositories }
            td { +lastActive }
        }
    }

    private fun FlowContent.sidebarNav() {
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

    @Test
    fun generatesDashboardDeterministically() {
        val html = generateDashboard()

        assertTrue(html.contains("DevTrack"), "should contain DevTrack branding")
        assertTrue(html.contains("Active Repositories"), "should include Active Repositories stat")
        assertTrue(html.contains("Open Issues"), "should include Open Issues stat")
        assertTrue(html.contains("PRs Merged This Month"), "should include PR merge stat")
        assertTrue(html.contains("Pipeline Success Rate"), "should include pipeline success stat")
        assertTrue(html.contains("Pipelines"), "should include Pipelines nav")
        assertTrue(html.contains("Issues"), "should include Issues nav")
        assertTrue(html.contains("Pull Requests"), "should include Pull Requests nav")
        assertTrue(html.contains("api-gateway"), "should include pipeline repository data")
        assertTrue(html.contains("frontend"), "should include pipeline run data")
        assertTrue(html.contains("Team members"), "should include team members section")

        assertTrue(html.contains("class=\"btn"), "button component")
        assertTrue(html.contains("class=\"badge"), "badge component")
        assertTrue(html.contains("class=\"card"), "card component")
        assertTrue(html.contains("class=\"stats"), "stats component")
        assertTrue(html.contains("class=\"table"), "table component")
        assertTrue(html.contains("class=\"alert"), "alert component")
        assertTrue(html.contains("class=\"join"), "join component")
        assertTrue(html.contains("class=\"input"), "input component")
        assertTrue(html.contains("class=\"select"), "select component")
        assertTrue(html.contains("class=\"checkbox"), "checkbox component")
        assertTrue(html.contains("class=\"radio"), "radio component")
        assertTrue(html.contains("class=\"toggle"), "toggle component")
        assertTrue(html.contains("class=\"range"), "range component")
        assertTrue(html.contains("class=\"avatar"), "avatar component")
        assertTrue(html.contains("class=\"indicator"), "indicator component")
        assertTrue(html.contains("class=\"label"), "label component")
        assertTrue(html.contains("class=\"link"), "link component")
        assertTrue(html.contains("class=\"menu"), "menu component")
        assertTrue(html.contains("class=\"dropdown"), "dropdown component")
        assertTrue(html.contains("class=\"swap"), "swap component")
        assertTrue(html.contains("class=\"fieldset"), "fieldset component")

        val html2 = generateDashboard()
        assertTrue(html == html2, "dashboard generation must be deterministic")
    }

    @Test
    fun dashboardContainsAllSections() {
        val html = generateDashboard()

        assertTrue(html.contains("Repository management"), "section: Repository management")
        assertTrue(html.contains("Team and repository settings"), "section: Team and repository settings")
        assertTrue(html.contains("Team management"), "section: Team management")
        assertTrue(html.contains("Active team members"), "section: Active team members")

        for (item in listOf("Overview", "Repositories", "Issues", "Pull Requests", "Pipelines", "Teams", "Settings")) {
            assertTrue(html.contains(item), "sidebar item: $item")
        }
    }
}
