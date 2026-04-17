package kdaisyui.example.fragments

import io.github.ollin.kdaisyui.components.*
import io.github.ollin.kdaisyui.core.addClassNames
import kotlinx.html.*

fun TagConsumer<*>.formSectionsFragment() {
    formSectionsHeader()
    teamMembersSection()
    notificationSettingsSection()
    repositorySettingsSection()
}

private fun TagConsumer<*>.formSectionsHeader() {
    header {
        addClassNames("col-span-12 flex items-center gap-2 lg:gap-4")
        div("grow") { h1("font-light lg:text-2xl") { +"Team and repository settings" } }
    }
}

private fun TagConsumer<*>.teamMembersSection() {
    section {
        addClassNames("col-span-12 xl:col-span-4")
        daisyCard(extraClasses = "bg-base-100 shadow-xs") {
            daisyCardBody {
                daisyCardTitle("Team members")
                ul("space-y-4") {
                    teamMemberRow(1, "Alice Chen", "Owner")
                    teamMemberRow(2, "Bob Martinez", "Maintainer")
                    teamMemberRow(3, "Carol White", "Developer")
                }
            }
        }
    }
}

private fun UL.teamMemberRow(imgId: Int, name: String, role: String) {
    li("flex items-center gap-3") {
        daisyAvatar {
            div("w-10 rounded-full") {
                img(src = "https://picsum.photos/80/80?member-$imgId", alt = "$name avatar")
            }
        }
        div("grow") {
            div("font-medium") { +name }
            div("text-base-content/60 text-xs") { +role }
        }
        daisyButton("Remove", size = ButtonSize.Xs, variant = ButtonVariant.Error, outline = true)
    }
}

private fun TagConsumer<*>.notificationSettingsSection() {
    section {
        addClassNames("col-span-12 xl:col-span-4")
        daisyCard(extraClasses = "bg-base-100 shadow-xs") {
            daisyCardBody {
                daisyCardTitle("Notification settings")
                daisyFieldset {
                    notificationToggle("Pipeline failures", true)
                    notificationToggle("PR reviews requested", true)
                    notificationToggle("Issue assignments", true)
                    notificationToggle("Security alerts", true)
                    notificationToggle("Weekly digest", false)
                }
            }
        }
    }
}

private fun FIELDSET.notificationToggle(label: String, enabled: Boolean) {
    this.label("flex cursor-pointer justify-between py-2") {
        span("label") { +label }
        daisyToggle(size = ToggleSize.Sm, checked = enabled)
    }
}

private fun TagConsumer<*>.repositorySettingsSection() {
    section {
        addClassNames("col-span-12 xl:col-span-4")
        daisyCard(extraClasses = "bg-base-100 shadow-xs") {
            daisyCardBody {
                daisyCardTitle("Repository settings")
                daisyFieldset {
                    notificationToggle("Branch protection", true)
                    notificationToggle("Require PR reviews", true)
                    notificationToggle("Auto-delete branches", false)
                    notificationToggle("Allow force push", false)
                }
                daisyButton("Save settings", variant = ButtonVariant.Primary)
            }
        }
    }
}
