package kdaisyui.example

import kotlinx.html.HEAD
import kotlinx.html.link

/**
 * The stylesheet every page in this application loads.
 *
 * Compiled by `:example-app:compileTailwind` from this application's Kotlin sources and the
 * library's generated ones, then shipped as a classpath resource.
 *
 * It replaced the prebuilt `daisyui.css` webjar plus Tailwind's browser build. That pairing
 * worked for the five variant prefixes DaisyUI happens to pre-generate — `sm:` `md:` `lg:`
 * `xl:` `hover:` — and silently produced nothing for every other one, so `max-sm:card-side`
 * or `dark:alert-info` were classes that sat in the HTML and did nothing. Compiling produces
 * whatever the sources actually use, and the result is ~23 KB instead of 1.1 MB.
 */
fun HEAD.daisyuiStylesheet() {
    link { rel = "stylesheet"; href = "/static/app.css" }
}
