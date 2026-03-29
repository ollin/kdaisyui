package kdaisyui.example.fragments

import kdaisyui.components.*
import kdaisyui.core.addClassNames
import kotlinx.html.*

fun TagConsumer<*>.teamFragment() {
    teamHeader()
    inviteTeamMemberForm()
    activeTeamMembersTable()
}

private fun TagConsumer<*>.teamHeader() {
    header {
        addClassNames("col-span-12 flex items-center gap-2 lg:gap-4")
        div("grow") { h1("font-light lg:text-2xl") { +"Team management" } }
    }
}

private fun TagConsumer<*>.inviteTeamMemberForm() {
    section {
        addClassNames("card bg-base-100 col-span-12 xl:col-span-5")
        form(classes = "card-body") {
            action = ""
            daisyAlert(variant = AlertVariant.Success) {
                span { +"Invitation sent successfully" }
            }
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

private fun TagConsumer<*>.activeTeamMembersTable() {
    section {
        addClassNames("card bg-base-100 col-span-12 overflow-hidden shadow-xs xl:col-span-7")
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
                    teamMemberRow(1, "Alice Chen", "alice.chen@devtrack.io", "Owner", "All", "2 minutes ago")
                    teamMemberRow(2, "Bob Martinez", "bob.martinez@devtrack.io", "Maintainer", "12", "9 minutes ago")
                    teamMemberRow(3, "Carol White", "carol.white@devtrack.io", "Developer", "7", "24 minutes ago")
                    teamMemberRow(4, "Dave Kim", "dave.kim@devtrack.io", "Developer", "5", "1 hour ago")
                    teamMemberRow(5, "Eve Jackson", "eve.jackson@devtrack.io", "Reporter", "3", "2 hours ago")
                    teamMemberRow(6, "Frank Diaz", "frank.diaz@devtrack.io", "Maintainer", "10", "Today")
                }
            }
        }
    }
}

private fun TBODY.teamMemberRow(
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
