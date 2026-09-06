package kdaisyui.example

import kotlinx.html.HEAD
import kotlinx.html.link
import kotlinx.html.script

/**
 * The stylesheet every page in this application loads.
 *
 * One place on purpose: four pages carried the same three lines, and how this application
 * gets its CSS is precisely the thing `compile-tailwind-at-build-time` is changing. A
 * change of delivery should be a change in one function, not a search across page files.
 */
fun HEAD.daisyuiStylesheet() {
    link { rel = "stylesheet"; href = "/webjars/daisyui/daisyui.css" }
    link { rel = "stylesheet"; href = "/webjars/daisyui/themes.css" }
    script { src = "/webjars/tailwindcss__browser/dist/index.global.js" }
}
