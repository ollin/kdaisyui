package kdaisyui.example.team

import io.github.ollin.kdaisyui.components.*
import io.github.ollin.kdaisyui.core.addClassNames
import kdaisyui.example.Dashboard
import kotlinx.html.*

fun TagConsumer<*>.teamFragment() {
    teamHeader()
    inviteTeamMemberForm()
    activeTeamMembersTable()
}

private fun TagConsumer<*>.teamHeader() {
    header {
        id = Dashboard.Team().id
        addClassNames("col-span-12 flex items-center gap-2 lg:gap-4")
        div("grow") { h1("font-light lg:text-2xl") { +"Team management" } }
    }
}

private fun TagConsumer<*>.inviteTeamMemberForm() {
    section {
        daisyCardClasses()
        addClassNames("bg-base-100 col-span-12 xl:col-span-5")
        form {
            daisyCardBodyClasses()
            id = Dashboard.Team.Invite.Form().id
            action = ""
            daisyAlert(color = AlertColor.Success) {
                span { +"Invitation sent successfully" }
            }
            teamEmailField()
            teamRoleSelect()
            teamReposSelect()
            teamInviteButton()
        }
    }
}

private fun TagConsumer<*>.activeTeamMembersTable() {
    section {
        id = Dashboard.Team.Table().id
        daisyCardClasses()
        addClassNames("bg-base-100 col-span-12 overflow-hidden shadow-xs xl:col-span-7")
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
                    teamMemberRow("Alice Chen", "alice.chen@devtrack.io", "Owner", "All", "2 minutes ago")
                    teamMemberRow("Bob Martinez", "bob.martinez@devtrack.io", "Maintainer", "12", "9 minutes ago")
                    teamMemberRow("Carol White", "carol.white@devtrack.io", "Developer", "7", "24 minutes ago")
                    teamMemberRow("Dave Kim", "dave.kim@devtrack.io", "Developer", "5", "1 hour ago")
                    teamMemberRow("Eve Jackson", "eve.jackson@devtrack.io", "Reporter", "3", "2 hours ago")
                    teamMemberRow("Frank Diaz", "frank.diaz@devtrack.io", "Maintainer", "10", "Today")
                }
            }
        }
    }
}

private fun TBODY.teamMemberRow(
    name: String,
    email: String,
    role: String,
    repositories: String,
    lastActive: String,
) {
    tr {
        td {
            div("flex items-center gap-4") {
                daisyAvatar(placeholder = true) {
                    div("bg-neutral text-neutral-content h-10 w-10") {
                        daisyMaskClasses(style = MaskStyle.Squircle)
                        span { +name.split(" ").joinToString("") { it.take(1) } }
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
                "Owner" -> BadgeColor.Primary
                "Maintainer" -> BadgeColor.Info
                "Developer" -> BadgeColor.Success
                else -> BadgeColor.Warning
            }
            daisyBadge(role, color = variant, size = BadgeSize.Sm)
        }
        td { +repositories }
        td { +lastActive }
    }
}
