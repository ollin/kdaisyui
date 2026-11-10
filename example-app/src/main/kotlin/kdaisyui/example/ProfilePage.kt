package kdaisyui.example

import io.github.ollin.kdaisyui.components.BadgeFillStyle
import io.github.ollin.kdaisyui.components.BadgeSize
import io.github.ollin.kdaisyui.components.ButtonColor
import io.github.ollin.kdaisyui.components.RadioColor
import io.github.ollin.kdaisyui.components.RadioSize
import io.github.ollin.kdaisyui.components.daisyBadge
import io.github.ollin.kdaisyui.components.daisyButton
import io.github.ollin.kdaisyui.components.daisyCard
import io.github.ollin.kdaisyui.components.daisyCardActions
import io.github.ollin.kdaisyui.components.daisyCardBody
import io.github.ollin.kdaisyui.components.daisyCardTitle
import io.github.ollin.kdaisyui.components.daisyFieldset
import io.github.ollin.kdaisyui.components.daisyFieldsetLegend
import io.github.ollin.kdaisyui.components.daisyRadio
import io.github.ollin.kdaisyui.icons.HeroIconSize
import io.github.ollin.kdaisyui.icons.heroIconComputerDesktop
import io.github.ollin.kdaisyui.themes.DaisyTheme
import io.github.ollin.kdaisyui.themes.daisyTheme
import io.ktor.server.application.Application
import io.ktor.server.resources.href
import kotlinx.html.ButtonType
import kotlinx.html.FlowContent
import kotlinx.html.FormMethod
import kotlinx.html.HTML
import kotlinx.html.div
import kotlinx.html.form
import kotlinx.html.id
import kotlinx.html.label
import kotlinx.html.p
import kotlinx.html.section
import kotlinx.html.span

/** The user's profile, in the app's frame: an "Appearance" card where the theme is chosen. */
fun HTML.profilePage(application: Application, current: DaisyTheme?) {
    appShell(application, current, "Profile") {
        section("col-span-12") {
            daisyCard(extraClasses = "bg-base-100 shadow-sm") {
                daisyCardBody(extraClasses = "gap-6") { appearance(application.href(ProfilePage.Theme()), current) }
            }
        }
    }
}

private fun FlowContent.appearance(saveUrl: String, current: DaisyTheme?) {
    div {
        daisyCardTitle("Appearance")
        p("text-base-content/70 text-sm") { +"The theme DevTrack wears on every page. Each tile shows a theme in its own colours." }
    }
    form(action = saveUrl, method = FormMethod.post) {
        id = Profile.ThemeForm().id
        daisyFieldset {
            daisyFieldsetLegend { +"Theme" }
            div("grid grid-cols-2 gap-4 sm:grid-cols-3 lg:grid-cols-4 2xl:grid-cols-5") {
                tile("System default", "system", current == null) { systemMockup() }
                for (theme in DaisyTheme.entries) tile(theme.name, theme.value, theme == current) { themeMockup(theme) }
            }
        }
        daisyCardActions(extraClasses = "justify-end pt-6") {
            daisyButton("Save", color = ButtonColor.Primary, type = ButtonType.submit)
        }
    }
}

/**
 * One choice: a mock-up, then the radio and the name, which label it. The chosen tile is outlined
 * in the page's primary colour, and the stored one says "Current" in words, so neither selection
 * nor the saved state is shown by colour alone.
 */
private fun FlowContent.tile(name: String, value: String, isCurrent: Boolean, mockup: FlowContent.() -> Unit) {
    label("rounded-box hover:bg-base-200 has-checked:outline-primary flex cursor-pointer flex-col gap-2 p-2 outline-2 outline-offset-2 outline-transparent") {
        mockup()
        div("flex items-center gap-2 px-1") {
            daisyRadio(name = "theme", color = RadioColor.Primary, size = RadioSize.Sm, checked = isCurrent) { this.value = value }
            span("truncate text-sm font-medium") { +name }
            if (isCurrent) daisyBadge("Current", size = BadgeSize.Sm, fillStyle = BadgeFillStyle.Soft, extraClasses = "ms-auto")
        }
    }
}

/** Each chip's background and the content colour that sits on it, as the theme pairs them. */
private val CHIPS = listOf(
    "bg-primary text-primary-content",
    "bg-secondary text-secondary-content",
    "bg-accent text-accent-content",
    "bg-neutral text-neutral-content",
)

/** A small page in [theme]: a sidebar in base-200, a line of text, and the four colour chips. */
private fun FlowContent.themeMockup(theme: DaisyTheme) {
    div("rounded-box border-base-content/10 bg-base-100 text-base-content grid h-20 grid-cols-5 overflow-hidden border") {
        daisyTheme(theme)
        attributes["aria-hidden"] = "true"
        div("bg-base-200") {}
        div("col-span-4 flex flex-col justify-between p-2") {
            div("bg-base-content/20 h-2 w-3/4 rounded-full") {}
            div("flex gap-1") { for (chip in CHIPS) span("$chip rounded-field grid size-5 place-items-center text-xs font-bold") { +"A" } }
        }
    }
}

/** The system default has no colours of its own: it follows the device's light or dark setting. */
private fun FlowContent.systemMockup() {
    div("rounded-box border-base-content/10 bg-base-200 text-base-content/70 flex h-20 flex-col items-center justify-center gap-1 border text-xs") {
        attributes["aria-hidden"] = "true"
        heroIconComputerDesktop(size = HeroIconSize.Lg)
        span { +"Follows your device" }
    }
}
