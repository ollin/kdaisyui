package kdaisyui.example

import kotlinx.html.HTML
import kotlinx.html.h1

/** The user's profile, where a theme is chosen with a preview of each. */
fun HTML.profilePage() {
    demoPage("Profile") {
        h1("text-2xl font-light") { +"Profile" }
    }
}
