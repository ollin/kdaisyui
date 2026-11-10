package kdaisyui.example

import io.github.ollin.kdaisyui.components.ButtonColor
import io.github.ollin.kdaisyui.components.daisyButton
import io.github.ollin.kdaisyui.components.daisyRadio
import io.github.ollin.kdaisyui.themes.DaisyTheme
import kotlinx.html.ButtonType
import kotlinx.html.FormMethod
import kotlinx.html.HTML
import kotlinx.html.form
import kotlinx.html.h1
import kotlinx.html.id

/** The user's profile, where a theme is chosen with a preview of each. */
fun HTML.profilePage() {
    demoPage("Profile") {
        h1("text-2xl font-light") { +"Profile" }
        form(method = FormMethod.post) {
            id = Profile.ThemeForm().id
            for (theme in DaisyTheme.entries) daisyRadio(name = "theme") { value = theme.value }
            daisyRadio(name = "theme") { value = "system" }
            daisyButton("Save", color = ButtonColor.Primary, type = ButtonType.submit)
        }
    }
}
