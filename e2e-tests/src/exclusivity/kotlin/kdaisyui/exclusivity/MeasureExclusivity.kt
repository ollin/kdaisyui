package kdaisyui.exclusivity

import com.microsoft.playwright.BrowserType.LaunchOptions
import com.microsoft.playwright.Playwright
import java.nio.file.Files
import java.nio.file.Path

/**
 * Render the exclusivity probe and write `codegen/exclusivity.json`.
 *
 * The measurement decides which DaisyUI class groups become a Kotlin enum, and it can only be
 * taken in a browser: whether two classes conflict is a question about a cascade, a layout and
 * a resolved theme, none of which exist outside one.
 *
 * It lives in `e2e-tests` because that is the module that already owns browser automation —
 * `libs.playwright` is declared here and the system Chromium is already discovered here. It is
 * a source set of its OWN rather than part of `test`, because the test source set depends on
 * `:example-app`, which compiles its stylesheet in a Docker container. Measuring DaisyUI's
 * stylesheet has no business needing Docker.
 *
 * The page decides the verdicts and emits the complete file; this only carries the text out.
 * Keeping the decision in `codegen/src/exclusivity-verdicts.js` means the rule that shapes the
 * public API sits beside the generator that feeds it, in one language, reviewable in one diff.
 *
 * @param args the probe page to open, the file to write, and optionally the browser to use
 */
fun main(args: Array<String>) {
    require(args.size >= 2) { "usage: MeasureExclusivity <probe.html> <exclusivity.json> [chromium]" }
    val probe = Path.of(args[0])
    val output = Path.of(args[1])

    // Set explicitly rather than through PLAYWRIGHT_CHROMIUM_EXECUTABLE_PATH: that variable
    // does not redirect the bundled headless shell, so a machine with a perfectly good system
    // Chromium still fails asking to download one. Passing the path says what it means.
    val options = LaunchOptions()
    args.getOrNull(2)?.takeIf { it.isNotBlank() }?.let { options.setExecutablePath(Path.of(it)) }

    Playwright.create().use { playwright ->
        playwright.chromium().launch(options).use { browser ->
            val page = browser.newPage()
            page.navigate(probe.toUri().toString())

            // The page fills these on `load`, so navigation having returned is enough. Read the
            // summary first: it is the digest, and a run that produced no cases would show it.
            val summary = page.textContent("#kdaisyui-summary")
            val document = page.evaluate("() => window.kdaisyuiExclusivity().document") as String

            Files.writeString(output, document)
            println("$summary -> $output")
        }
    }
}
