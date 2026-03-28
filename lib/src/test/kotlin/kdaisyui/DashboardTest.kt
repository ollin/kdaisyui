package kdaisyui

import kdaisyui.components.*
import kotlinx.html.*
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertTrue

/**
 * Integration test that generates a full DaisyUI admin dashboard page.
 *
 * Modeled after the html-dashboard@1.1.1 template. Uses the kdaisyUI DSL for
 * all DaisyUI components; raw kotlinx.html for layout utilities and non-DaisyUI
 * elements (SVGs, chart web-components).
 *
 * The generated HTML is deterministic and can be written to disk for visual
 * inspection when paired with a Tailwind/DaisyUI CSS build.
 */
class DashboardTest {

    /** Generates the complete dashboard HTML page. */
    private fun generateDashboard(): String = createHTML(prettyPrint = true).html {
        lang = "en"
        head {
            meta { charset = "utf-8" }
            title { +"Dashboard" }
            meta { name = "viewport"; content = "width=device-width, initial-scale=1" }
            link { rel = "stylesheet"; href = "output.css" }
        }
        body("drawer bg-base-200 lg:drawer-open min-h-screen") {
            // drawer toggle
            input {
                id = "my-drawer"
                type = InputType.checkBox
                classes = setOf("drawer-toggle")
            }
            // main content
            main("drawer-content") {
                div("grid grid-cols-12 grid-rows-[min-content] gap-y-12 p-4 lg:gap-x-12 lg:p-10") {
                    dashboardHeader()
                    statsSection()
                    transactionsCard()
                    revenueCard()
                    sourcesCard()
                    downloadsCard()
                    uniqueVisitorsCard()
                    formsHeader()
                    formInputsSection()
                    worldMapCard()
                    recentEventsCard()
                    formSectionsHeader()
                    productManagementSection()
                    productFormSection()
                    sizeAndRangeSection()
                    paymentHeader()
                    paymentFormCard()
                    userTransactionsCard()
                }
            }
            // sidebar
            aside("drawer-side z-10") {
                label {
                    htmlFor = "my-drawer"
                    classes = setOf("drawer-overlay")
                }
                sidebarNav()
            }
        }
    }

    // ──────────────────────────────────────────────
    // Header
    // ──────────────────────────────────────────────

    private fun FlowContent.dashboardHeader() {
        header("col-span-12 flex items-center gap-2 lg:gap-4") {
            label {
                htmlFor = "my-drawer"
                classes = setOf("btn", "btn-square", "btn-ghost", "drawer-button", "lg:hidden")
            }
            div("grow") {
                h1("lg:text-2xl lg:font-light") { +"Dashboard" }
            }
            div {
                daisyInput(
                    size = InputSize.Sm,
                    placeholder = "Search",
                    extraClasses = "rounded-full max-sm:w-24",
                )
            }
            // theme toggle (swap)
            daisySwap(rotate = true) {
                attributes["data-toggle-theme"] = "dark"
                attributes["data-act-class"] = "swap-active"
                span("swap-off") { +"☀\uFE0F" }
                span("swap-on") { +"🌙" }
            }
            notificationDropdown()
            userDropdown()
        }
    }

    private fun FlowContent.notificationDropdown() {
        daisyDropdown(end = true, extraClasses = "z-10") {
            div {
                attributes["tabindex"] = "0"
                classes = setOf("btn", "btn-circle", "btn-ghost")
                daisyIndicator {
                    daisyBadge(variant = BadgeVariant.Error, size = BadgeSize.Xs, extraClasses = "indicator-item")
                    +"🔔"
                }
            }
            daisyDropdownContent(extraClasses = "rounded-box bg-base-100 mt-3 w-80 p-2 shadow-2xl") {
                attributes["tabindex"] = "0"
                notificationItem("https://picsum.photos/80/80?1", "New message", "Alice: Hi, did you get my files?")
                notificationItem("https://picsum.photos/80/80?2", "Reminder", "Your meeting is at 10am")
                notificationItem("https://picsum.photos/80/80?3", "New payment", "Received \$2500 from John Doe")
                notificationItem("https://picsum.photos/80/80?4", "New payment", "Received \$1900 from Alice")
            }
        }
    }

    private fun UL.notificationItem(imgSrc: String, title: String, desc: String) {
        li {
            a("gap-4") {
                daisyAvatar {
                    div("w-8 rounded-full") { img(src = imgSrc) }
                }
                span {
                    b { +title }; br; +desc
                }
            }
        }
    }

    private fun FlowContent.userDropdown() {
        daisyDropdown(end = true, extraClasses = "z-10") {
            div {
                attributes["tabindex"] = "0"
                classes = setOf("avatar", "btn", "btn-circle", "btn-ghost")
                div("w-10 rounded-full") {
                    img(src = "https://picsum.photos/80/80?5")
                }
            }
            daisyDropdownContent(extraClasses = "rounded-box bg-base-100 mt-3 w-52 p-2 shadow-2xl") {
                attributes["tabindex"] = "0"
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

    // ──────────────────────────────────────────────
    // Stats
    // ──────────────────────────────────────────────

    private fun FlowContent.statsSection() {
        section("stats stats-vertical xl:stats-horizontal bg-base-100 col-span-12 w-full shadow-xs") {
            repeat(4) {
                daisyStat {
                    daisyStatTitle("Total Page Views")
                    daisyStatValue("89,400")
                    daisyStatDesc("21% more than last month")
                }
            }
        }
    }

    // ──────────────────────────────────────────────
    // Cards
    // ──────────────────────────────────────────────

    private fun FlowContent.transactionsCard() {
        section("card bg-base-100 col-span-12 overflow-hidden shadow-xs xl:col-span-6") {
            daisyCardBody(extraClasses = "grow-0") {
                daisyCardTitle {
                    daisyLink("Transactions", hover = true)
                }
            }
            div("overflow-x-auto") {
                daisyTable(zebra = true) {
                    tbody {
                        transactionRow("Cy Ganderton", "Feb 2nd", "180 USD", true)
                        transactionRow("Hart Hagerty", "Sep 2nd", "250 USD", true)
                        transactionRow("Jim Hagerty", "Sep 2nd", "250 USD", true)
                        transactionRow("Hart Hagerty", "Sep 2nd", "250 USD", false)
                        transactionRow("Hart Hagerty", "Sep 2nd", "250 USD", false)
                        transactionRow("Brice Swyre", "Jul 2nd", "320 USD", true)
                    }
                }
            }
        }
    }

    private fun TBODY.transactionRow(name: String, date: String, amount: String, positive: Boolean) {
        tr {
            td { +name }
            td { +date }
            td {
                span(if (positive) "text-success" else "text-error") { +if (positive) "↑" else "↓" }
                +" $amount"
            }
        }
    }

    private fun FlowContent.revenueCard() {
        section("card bg-primary text-primary-content col-span-12 shadow-xs xl:col-span-6") {
            daisyCardBody(extraClasses = "pb-0") {
                daisyCardTitle("21,500 USD")
                daisyLink("Revenue report →", hover = true, extraClasses = "text-xs")
            }
            // chart placeholder
            div("h-full w-full p-4 pt-0") { +"[Revenue chart]" }
        }
    }

    private fun FlowContent.sourcesCard() {
        section("card bg-base-100 col-span-12 shadow-xs xl:col-span-4") {
            daisyCardBody {
                daisyCardTitle("Sources")
                div("flex items-center gap-10") {
                    div("grow") {
                        for ((color, label) in listOf(
                            "#19D6BF" to "Direct",
                            "#A838FF" to "Social",
                            "#3C37FF" to "Search",
                            "#FFBD3C" to "Email",
                        )) {
                            div("flex items-center gap-2") {
                                daisyBadge(size = BadgeSize.Xs, extraClasses = "bg-[$color]")
                                +label
                            }
                        }
                    }
                    // pie chart placeholder
                    div("h-32 w-32 shrink-0") { +"[Pie chart]" }
                }
            }
        }
    }

    private fun FlowContent.downloadsCard() {
        section("card bg-base-100 col-span-12 shadow-xs xl:col-span-4") {
            daisyCardBody(extraClasses = "pb-0") {
                daisyCardTitle("19,000")
                p { +"Downloads" }
            }
            div("rounded-box h-full w-full") { +"[Line chart]" }
        }
    }

    private fun FlowContent.uniqueVisitorsCard() {
        section("card bg-base-100 col-span-12 shadow-xs xl:col-span-4") {
            daisyCardBody(extraClasses = "pb-0") {
                daisyCardTitle("32,800")
                p { +"Unique visitors" }
            }
            div("rounded-box h-full w-full") { +"[Line chart]" }
        }
    }

    // ──────────────────────────────────────────────
    // Forms section
    // ──────────────────────────────────────────────

    private fun FlowContent.formsHeader() {
        header("col-span-12 flex items-center gap-2 lg:gap-4") {
            div("grow") {
                h1("font-light lg:text-2xl") { +"Forms and inputs" }
            }
        }
    }

    private fun FlowContent.formInputsSection() {
        section("col-span-12 xl:col-span-4") {
            daisyFieldset {
                daisyLabel("Product name")
                daisyInput(placeholder = "Type here", extraClasses = "w-full")
            }
            daisyFieldset {
                daisyLabel("Category")
                daisySelect(extraClasses = "w-full") {
                    option { disabled = true; selected = true; +"Pick" }
                    option { +"T-shirts" }
                    option { +"Dresses" }
                    option { +"Hats" }
                    option { +"Accessories" }
                }
            }
            daisyFieldset {
                daisyLabel("Size (cm)")
                div("flex items-center gap-2") {
                    daisyInput(placeholder = "Width", extraClasses = "w-1/2")
                    +"×"
                    daisyInput(placeholder = "Height", extraClasses = "w-1/2")
                }
            }
            hr("border-base-content/5 my-6 border-t-2") {}
            daisyFieldset {
                daisyLabel(extraClasses = "justify-start gap-2") {
                    span("label-text text-base-content/50 text-xs font-bold") {
                        +"Choose product visibility"
                    }
                }
            }
            daisyFieldset {
                label("flex cursor-pointer justify-between py-2") {
                    span("label") { +"Visible only for managers" }
                    daisyRadio(name = "visibility", size = RadioSize.Sm, checked = true)
                }
                label("flex cursor-pointer justify-between py-2") {
                    span("label") { +"Visible for all users" }
                    daisyRadio(name = "visibility", size = RadioSize.Sm, checked = true)
                }
            }
        }
    }

    private fun FlowContent.worldMapCard() {
        section("card bg-base-100 col-span-12 shadow-xs xl:col-span-5") {
            daisyCardBody(extraClasses = "pb-0") {
                daisyCardTitle("32,800")
                p { +"From 84 countries" }
            }
            div("fill-base-content/80 m-4") { +"[World map SVG]" }
        }
    }

    private fun FlowContent.recentEventsCard() {
        section("card bg-base-100 col-span-12 shadow-xs xl:col-span-3") {
            div("text-base-content/60 p-6 pb-0 text-center text-xs font-bold") {
                +"Recent events"
            }
            daisyMenu(extraClasses = "w-full") {
                for ((idx, event) in listOf(
                    "New User" to "2 minutes ago",
                    "New product added" to "1 hour ago",
                    "Database update" to "1 hour ago",
                    "Newsletter sent" to "2 hour ago",
                    "New User" to "2 hours ago",
                    "New product added" to "yesterday",
                    "New product added" to "yesterday",
                ).withIndex()) {
                    li {
                        a("gap-4") {
                            daisyAvatar {
                                div("w-6 rounded-full") {
                                    img(src = "https://picsum.photos/80/80?${idx + 6}")
                                }
                            }
                            span("text-xs") {
                                b { +event.first }; br; +event.second
                            }
                        }
                    }
                }
            }
        }
    }

    // ──────────────────────────────────────────────
    // Form sections
    // ──────────────────────────────────────────────

    private fun FlowContent.formSectionsHeader() {
        header("col-span-12 flex items-center gap-2 lg:gap-4") {
            div("grow") {
                h1("font-light lg:text-2xl") { +"Form sections" }
            }
        }
    }

    private fun FlowContent.productManagementSection() {
        section("col-span-12 xl:col-span-4") {
            daisyLabel {
                span("label-text") { +"Product management" }
            }
            ul("flex flex-col gap-4 p-1") {
                for ((id, name, color, price) in listOf(
                    ProductItem(1, "Portable fidget spinner", "Space Gray", "$299"),
                    ProductItem(2, "Wooden VR holder", "Casual Red", "$199"),
                    ProductItem(3, "Portable keychain", "Normal Yellow", "$299"),
                )) {
                    li("flex items-start gap-4") {
                        img(
                            classes = "rounded-field h-14 w-14 shrink-0",
                            src = "https://picsum.photos/80/80?id=$id",
                            alt = "Product",
                        ) {
                            width = "56"; height = "56"
                        }
                        div("flex grow flex-col gap-1") {
                            div("text-sm") { +name }
                            div("text-base-content/50 text-xs") { +color }
                            div("text-base-content/50 font-mono text-xs") { +price }
                        }
                        daisyJoin(extraClasses = "bg-base-100 justify-self-end") {
                            daisyButton("–", variant = ButtonVariant.Ghost, size = ButtonSize.Xs, extraClasses = "join-item")
                            daisyInput(ghost = true, size = InputSize.Xs, extraClasses = "join-item w-10 text-center") {
                                value = "1"
                            }
                            daisyButton("+", variant = ButtonVariant.Ghost, size = ButtonSize.Xs, extraClasses = "join-item")
                        }
                    }
                }
            }
        }
    }

    private fun FlowContent.productFormSection() {
        section("col-span-12 xl:col-span-4") {
            daisyFieldset {
                daisyLabel("Product name")
                daisyInput(placeholder = "Type here", extraClasses = "w-full")
            }
            daisyFieldset {
                daisyLabel("Category")
                daisySelect(extraClasses = "w-full") {
                    option { disabled = true; selected = true; +"Pick" }
                    option { +"T-shirts" }
                    option { +"Dresses" }
                    option { +"Hats" }
                    option { +"Accessories" }
                }
            }
            daisyFieldset {
                label("flex cursor-pointer justify-between py-2") {
                    span("label") { +"Public" }
                    daisyToggle(size = ToggleSize.Sm, checked = true)
                }
            }
            daisyFieldset {
                label("flex cursor-pointer justify-between py-2") {
                    span("label") { +"Featured product" }
                    daisyToggle(size = ToggleSize.Sm, checked = true)
                }
            }
        }
    }

    private fun FlowContent.sizeAndRangeSection() {
        section("col-span-12 xl:col-span-4") {
            daisyFieldset {
                daisyLabel("Size (cm)")
                div("flex items-center gap-2") {
                    daisyInput(placeholder = "Width", extraClasses = "w-1/2")
                    +"×"
                    daisyInput(placeholder = "Height", extraClasses = "w-1/2")
                }
            }
            daisyFieldset {
                div("text-base-content/70 py-4 text-xs") {
                    +"Set a audience range for this product."
                    br
                    +"This is optional"
                }
                daisyRange(size = RangeSize.Xs, min = "0", max = "100", value = "25", step = "25")
                div("flex w-full justify-between px-2 py-2 text-xs") {
                    for (v in listOf("0", "25", "50", "75", "100")) {
                        span { +v }
                    }
                }
            }
            daisyFieldset {
                div("flex gap-4 py-4") {
                    daisyButton("Save draft", outline = true)
                    daisyButton("Save and publish", variant = ButtonVariant.Primary, extraClasses = "grow")
                }
            }
        }
    }

    // ──────────────────────────────────────────────
    // Payment section
    // ──────────────────────────────────────────────

    private fun FlowContent.paymentHeader() {
        header("col-span-12 flex items-center gap-2 lg:gap-4") {
            div("grow") {
                h1("font-light lg:text-2xl") { +"Payment information" }
            }
        }
    }

    private fun FlowContent.paymentFormCard() {
        section("card bg-base-100 col-span-12 xl:col-span-5") {
            form(classes = "card-body") {
                action = ""
                daisyAlert(variant = AlertVariant.Success) {
                    span { +"Your payment was successful" }
                }
                daisyFieldset {
                    daisyLabel {
                        daisyLabelText("Card Number")
                    }
                    daisyInput(extraClasses = "w-full font-mono") {
                        attributes["pattern"] = """\d{16}"""
                        attributes["title"] = "16 digits card number"
                        minLength = "16"
                        maxLength = "16"
                        required = true
                    }
                }
                div("grid grid-cols-2 gap-2") {
                    daisyFieldset {
                        daisyLabel {
                            daisyLabelText("CVV")
                        }
                        daisyInput(placeholder = "XXX", extraClasses = "text-center font-mono") {
                            attributes["pattern"] = """\d{3,4}"""
                            attributes["title"] = "3 or 4 digits CVV number"
                            minLength = "3"
                            maxLength = "4"
                            required = true
                        }
                    }
                    daisyFieldset {
                        daisyLabel {
                            daisyLabelText("Expiration date")
                        }
                        div("input grid grid-cols-2 gap-2") {
                            input {
                                placeholder = "MM"
                                type = InputType.text
                                attributes["pattern"] = """\d{2}"""
                                classes = setOf("text-center", "font-mono")
                                minLength = "2"
                                maxLength = "2"
                                required = true
                            }
                            input {
                                placeholder = "YY"
                                type = InputType.text
                                attributes["pattern"] = """\d{2}"""
                                classes = setOf("text-center", "font-mono")
                                minLength = "2"
                                maxLength = "2"
                                required = true
                            }
                        }
                    }
                }
                daisyFieldset {
                    daisyLabel { daisyLabelText("Name") }
                    daisyInput(extraClasses = "w-full")
                }
                daisyFieldset {
                    label("flex cursor-pointer gap-4") {
                        daisyCheckbox(size = CheckboxSize.Sm, checked = true)
                        span("label-text") { +"Save my card information for future payments" }
                    }
                    label("flex cursor-pointer gap-4") {
                        daisyCheckbox(size = CheckboxSize.Sm, checked = true) {
                            required = true
                        }
                        span("label-text") { +"Accept terms of use and privacy policy" }
                    }
                }
                daisyFieldset {
                    div("flex items-end py-4") {
                        daisyButton("Confirm Payment", variant = ButtonVariant.Primary, extraClasses = "grow")
                    }
                }
            }
        }
    }

    private fun FlowContent.userTransactionsCard() {
        section("card bg-base-100 col-span-12 overflow-hidden shadow-xs xl:col-span-7") {
            daisyCardBody(extraClasses = "grow-0") {
                div("flex justify-between gap-2") {
                    daisyCardTitle(extraClasses = "grow") {
                        daisyLink("Recent user transactions", hover = true)
                    }
                    daisyButton("See all users", size = ButtonSize.Sm)
                    daisyButton("Settings", size = ButtonSize.Sm)
                }
            }
            div("overflow-x-auto") {
                daisyTable(zebra = true) {
                    tbody {
                        for ((imgId, name, country, date, amount, positive) in listOf(
                            UserTx(1, "Hart Hagerty", "United States", "Feb 2nd", "180 USD", true),
                            UserTx(2, "Brice Swyre", "China", "Sep 2nd", "250 USD", true),
                            UserTx(3, "Marjy Ferencz", "Russia", "Sep 2nd", "250 USD", true),
                            UserTx(4, "Yancy Tear", "Brazil", "Sep 2nd", "250 USD", false),
                            UserTx(5, "Marjy Ferencz", "Russia", "Sep 2nd", "250 USD", false),
                            UserTx(6, "Hart Hagerty", "United States", "Jul 2nd", "320 USD", true),
                            UserTx(1, "Hart Hagerty", "United States", "Feb 2nd", "180 USD", true),
                        )) {
                            tr {
                                td("w-0") {
                                    daisyCheckbox()
                                }
                                td {
                                    div("flex items-center gap-4") {
                                        daisyAvatar {
                                            div("mask mask-squircle h-10 w-10") {
                                                img(src = "https://picsum.photos/80/80?$imgId", alt = "Avatar")
                                            }
                                        }
                                        div {
                                            div("text-sm font-bold") { +name }
                                            div("text-xs opacity-50") { +country }
                                        }
                                    }
                                }
                                td { +date }
                                td {
                                    span(if (positive) "text-success" else "text-error") {
                                        +if (positive) "↑" else "↓"
                                    }
                                    +" $amount"
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    // ──────────────────────────────────────────────
    // Sidebar
    // ──────────────────────────────────────────────

    private fun FlowContent.sidebarNav() {
        nav("bg-base-100 flex min-h-screen w-72 flex-col gap-2 overflow-y-auto px-6 py-10") {
            div("mx-4 flex items-center gap-2 font-black") {
                +"Daisy Corp"
            }
            daisyMenu(extraClasses = "w-full") {
                li { a("menu-active") { +"Dashboard" } }
                li { a { +"Users" } }
                sidebarSubmenu("Products", listOf("All Products", "Add New", "Categories", "Tags", "Reports", "Archive"))
                sidebarSubmenu("Transactions", listOf("All Transactions", "Failed Transactions", "Successful Transactions"))
                li { a { +"Stats" } }
                li { a { +"Logs" } }
                li {
                    a {
                        +"Messages"
                        daisyBadge("12", variant = BadgeVariant.Info, size = BadgeSize.Sm)
                    }
                }
                sidebarSubmenu("Settings", listOf("General", "Themes", "Routes", "Comments", "Media", "Roles", "Merchants", "Databases", "Gateways", "Plugins", "API", "Support"))
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

    // ──────────────────────────────────────────────
    // Data classes for table rows
    // ──────────────────────────────────────────────

    private data class ProductItem(val id: Int, val name: String, val color: String, val price: String)
    private data class UserTx(val imgId: Int, val name: String, val country: String, val date: String, val amount: String, val positive: Boolean)

    // ──────────────────────────────────────────────
    // Test
    // ──────────────────────────────────────────────

    @Test
    fun generatesDashboardDeterministically() {
        val html = generateDashboard()

        // Structural assertions: page skeleton
        assertTrue(html.contains("""class="drawer"""), "should have drawer layout")
        assertTrue(html.contains("""class="drawer-toggle""""), "should have drawer toggle")
        assertTrue(html.contains("""class="drawer-content""""), "should have drawer content area")
        assertTrue(html.contains("""class="drawer-side"""), "should have drawer sidebar")
        assertTrue(html.contains("""class="drawer-overlay""""), "should have drawer overlay")

        // Stats section
        assertTrue(html.contains("""class="stat-title""""), "should have stat titles")
        assertTrue(html.contains("""class="stat-value""""), "should have stat values")
        assertTrue(html.contains("89,400"), "should have stat value content")

        // Cards
        assertTrue(html.contains("""class="card-body"""), "should have card bodies")
        assertTrue(html.contains("""class="card-title"""), "should have card titles")

        // Tables
        assertTrue(html.contains("""class="table table-zebra""""), "should have zebra tables")

        // Forms
        assertTrue(html.contains("""class="input"""), "should have inputs")
        assertTrue(html.contains("""class="select"""), "should have selects")
        assertTrue(html.contains("""class="fieldset""""), "should have fieldsets")
        assertTrue(html.contains("""class="checkbox"""), "should have checkboxes")
        assertTrue(html.contains("""class="toggle"""), "should have toggles")
        assertTrue(html.contains("""class="radio"""), "should have radios")
        assertTrue(html.contains("""class="range"""), "should have range inputs")

        // Navigation
        assertTrue(html.contains("""class="menu"""), "should have menu")
        assertTrue(html.contains("Dashboard"), "should have Dashboard menu item")
        assertTrue(html.contains("Products"), "should have Products submenu")

        // Buttons
        assertTrue(html.contains("""class="btn btn-primary"""), "should have primary buttons")
        assertTrue(html.contains("""class="btn btn-outline""""), "should have outline buttons")
        assertTrue(html.contains("Confirm Payment"), "should have payment button text")

        // Alerts
        assertTrue(html.contains("""class="alert alert-success""""), "should have success alert")

        // Badges
        assertTrue(html.contains("""class="badge"""), "should have badges")

        // Dropdown
        assertTrue(html.contains("""class="dropdown"""), "should have dropdowns")
        assertTrue(html.contains("""class="menu dropdown-content"""), "should have dropdown content")

        // Links
        assertTrue(html.contains("""class="link link-hover""""), "should have hover links")

        // Avatars
        assertTrue(html.contains("""class="avatar""""), "should have avatars")

        // Swap (theme toggle)
        assertTrue(html.contains("""class="swap"""), "should have swap component")

        // Join groups
        assertTrue(html.contains("""class="join"""), "should have join groups")

        // Determinism: two calls produce identical output
        val html2 = generateDashboard()
        assertTrue(html == html2, "dashboard generation must be deterministic")
    }

    @Test
    fun dashboardContainsAllSections() {
        val html = generateDashboard()

        // All major section headers present
        assertTrue(html.contains("Forms and inputs"), "section: Forms and inputs")
        assertTrue(html.contains("Form sections"), "section: Form sections")
        assertTrue(html.contains("Payment information"), "section: Payment information")
        assertTrue(html.contains("Recent user transactions"), "section: Recent user transactions")

        // Sidebar entries
        assertTrue(html.contains("Daisy Corp"), "sidebar branding")
        for (item in listOf("Dashboard", "Users", "Products", "Transactions", "Stats", "Logs", "Messages", "Settings")) {
            assertTrue(html.contains(item), "sidebar item: $item")
        }
    }
}
