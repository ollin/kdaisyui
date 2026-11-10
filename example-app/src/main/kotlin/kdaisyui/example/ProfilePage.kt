package kdaisyui.example

import io.github.ollin.kdaisyui.components.ButtonColor
import io.github.ollin.kdaisyui.components.CardSize
import io.github.ollin.kdaisyui.components.daisyCard
import io.github.ollin.kdaisyui.components.daisyCardBody
import io.github.ollin.kdaisyui.components.daisyButton
import io.github.ollin.kdaisyui.components.daisyRadio
import io.github.ollin.kdaisyui.themes.DaisyTheme
import io.github.ollin.kdaisyui.themes.daisyTheme
import kotlinx.html.ButtonType
import kotlinx.html.FlowContent
import kotlinx.html.FormMethod
import kotlinx.html.HTML
import kotlinx.html.form
import kotlinx.html.div
import kotlinx.html.h1
import kotlinx.html.id
import kotlinx.html.label
import kotlinx.html.span

/** The user's profile, where a theme is chosen with a preview of each. */
fun HTML.profilePage(saveUrl: String, current: DaisyTheme?) {
    if (current != null) daisyTheme(current)
    demoPage("Profile") {
        h1("text-2xl font-light") { +"Profile" }
        form(action = saveUrl, method = FormMethod.post) {
            id = Profile.ThemeForm().id
            for (theme in DaisyTheme.entries) {
                label("flex items-center gap-3") {
                    daisyRadio(name = "theme", checked = theme == current) { value = theme.value }
                    themePreview(theme)
                }
            }
            daisyRadio(name = "theme", checked = current == null) { value = "system" }
            daisyButton("Save", color = ButtonColor.Primary, type = ButtonType.submit)
        }
    }
}

private val SWATCHES = listOf("bg-primary", "bg-secondary", "bg-accent", "bg-neutral")

/** A card in [theme], whatever the page wears: the theme's name on its base colour, and four swatches. */
private fun FlowContent.themePreview(theme: DaisyTheme) {
    daisyCard(size = CardSize.Sm, extraClasses = "bg-base-100 text-base-content w-48") {
        daisyTheme(theme)
        daisyCardBody {
            +theme.value
            div("flex gap-1") { for (swatch in SWATCHES) span("$swatch size-4 rounded") {} }
        }
    }
}
