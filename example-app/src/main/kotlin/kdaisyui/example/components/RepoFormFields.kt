package kdaisyui.example.components

import io.github.ollin.kdaisyui.components.*
import kotlinx.html.*
import kdaisyui.example.Dashboard

fun FlowContent.repoNameField() {
    daisyFieldset {
        daisyLabel("Repository name")
        daisyInput(
            id = Dashboard.Repo.Form.Name(),
            placeholder = "devtrack/api-gateway",
            extraClasses = "w-full",
        )
    }
}

fun FlowContent.repoVisibilitySelect() {
    daisyFieldset {
        daisyLabel("Visibility")
        daisySelect(id = Dashboard.Repo.Form.Visibility(), extraClasses = "w-full") {
            option { +"Public" }
            option { +"Private" }
            option { +"Internal" }
        }
    }
}

fun FlowContent.repoDescriptionField() {
    daisyFieldset {
        daisyLabel("Description")
        daisyInput(
            id = Dashboard.Repo.Form.Description(),
            placeholder = "Core API gateway service",
            extraClasses = "w-full",
        )
    }
}

fun FlowContent.repoBranchField() {
    daisyFieldset {
        daisyLabel("Default branch")
        daisyInput(
            id = Dashboard.Repo.Form.Branch(),
            extraClasses = "w-full",
            value = "main",
        )
    }
}

fun FlowContent.repoReadmeToggle() {
    daisyFieldset {
        label("flex cursor-pointer justify-between py-2") {
            span("label") { +"Initialize with README" }
            daisyToggle(id = Dashboard.Repo.Form.Readme(), size = ToggleSize.Sm, checked = true)
        }
    }
}

fun FlowContent.repoGitignoreSelect() {
    daisyFieldset {
        daisyLabel("Add .gitignore")
        daisySelect(id = Dashboard.Repo.Form.Gitignore(), extraClasses = "w-full") {
            option { +"None" }
            option { +"Kotlin" }
            option { +"Java" }
            option { +"Node" }
            option { +"Python" }
            option { +"Go" }
        }
    }
}
