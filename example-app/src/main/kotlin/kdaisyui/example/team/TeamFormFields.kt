package kdaisyui.example.team

import io.github.ollin.kdaisyui.components.*
import kotlinx.html.*
import kdaisyui.example.Dashboard

fun FlowContent.teamEmailField() {
    daisyFieldset {
        daisyLabel("Email")
        daisyInput(
            id = Dashboard.Team.Invite.Form.Email(),
            placeholder = "new.member@devtrack.io",
            extraClasses = "w-full",
        )
    }
}

fun FlowContent.teamRoleSelect() {
    daisyFieldset {
        daisyLabel("Role")
        daisySelect(id = Dashboard.Team.Invite.Form.Role(), extraClasses = "w-full") {
            option { +"Owner" }
            option { +"Maintainer" }
            option { +"Developer" }
            option { +"Reporter" }
        }
    }
}

fun FlowContent.teamReposSelect() {
    daisyFieldset {
        daisyLabel("Repositories")
        daisySelect(id = Dashboard.Team.Invite.Form.Repos(), extraClasses = "w-full") {
            option { +"All" }
            option { +"Specific" }
        }
    }
}

fun FlowContent.teamInviteButton() {
    daisyFieldset {
        div("flex items-end py-4") {
            daisyButton(
                "Send invitation",
                id = Dashboard.Team.Invite.Form.Submit(),
                variant = ButtonVariant.Primary,
                extraClasses = "grow",
            )
        }
    }
}
