package kdaisyui.example

import kotlinx.html.HEAD
import kotlinx.html.link

/**
 * What every page in this application links in its head: the stylesheet, and an icon of its own.
 *
 * The stylesheet is compiled by the kdaisyui plugin's `kdaisyuiStylesheet` task from this
 * application's Kotlin sources and the classes of the kdaisyui components it uses, then shipped as
 * a classpath resource.
 *
 * It replaced the prebuilt `daisyui.css` webjar plus Tailwind's browser build. That pairing
 * worked for the five variant prefixes DaisyUI happens to pre-generate — `sm:` `md:` `lg:`
 * `xl:` `hover:` — and silently produced nothing for every other one, so a `card-side` behind a
 * `max-sm` breakpoint, or an `alert-info` behind `dark`, sat in the HTML and did nothing.
 * Compiling produces whatever the sources actually use, and the result is ~23 KB instead of 1.1 MB.
 *
 * Those two examples used to be written here as composed classes, which is the mistake this
 * paragraph describes, committed in the describing of it: Tailwind scans Kotlin sources as TEXT,
 * so the comment itself was a candidate and the compiled stylesheet carried six rules that no
 * code in this application could ever put on a page. Name the variant and the class separately.
 */
fun HEAD.pageAssets() {
    link { rel = "stylesheet"; href = "/static/app.css" }
    // An icon of its own origin: without a link the browser asks for /favicon.ico and logs a 404.
    link { rel = "icon"; type = "image/svg+xml"; href = "/static/favicon.svg" }
}
