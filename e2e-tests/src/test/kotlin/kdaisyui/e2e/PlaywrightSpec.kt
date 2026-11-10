package kdaisyui.e2e

import com.microsoft.playwright.Browser
import com.microsoft.playwright.BrowserContext
import com.microsoft.playwright.Page
import com.microsoft.playwright.Tracing
import com.microsoft.playwright.options.ScreenshotAnimations
import io.kotest.core.spec.style.FunSpec
import java.nio.file.Files
import java.nio.file.Path

/**
 * Pass `javaScriptEnabled = false` to run every test of a spec with scripting off. That is the only
 * way to prove a component needs none: with scripting on, "no handler was wired" is an argument
 * about the source rather than an observation of the browser.
 */
abstract class PlaywrightSpec(private val javaScriptEnabled: Boolean = true) : FunSpec() {

    private var _context: BrowserContext? = null
    private var _page: Page? = null

    protected val context: BrowserContext get() = _context!!
    protected val page: Page get() = _page!!

    /** What this test's page logged at error level; a test that provokes it on purpose drains it. */
    protected lateinit var consoleErrors: ConsoleErrors

    init {
        beforeTest {
            val browser = SharedInfrastructure.browser
            _context = browser.newContext(
                Browser.NewContextOptions().setBaseURL(BASE_URL).setJavaScriptEnabled(javaScriptEnabled)
            )
            _context!!.setDefaultTimeout(10_000.0)
            _context!!.tracing().start(
                Tracing.StartOptions().setScreenshots(true).setSnapshots(true).setSources(true)
            )
            _page = _context!!.newPage()
            consoleErrors = ConsoleErrors(_page!!)
        }

        afterTest { (testCase, _) ->
            val specName = testCase.spec::class.simpleName ?: "UnknownSpec"
            val sanitized = testCase.name.name.replace(Regex("[^a-zA-Z0-9._-]"), "_")
            _page?.let { p ->
                try {
                    val dir = Path.of("build/screenshots/$specName")
                    Files.createDirectories(dir)
                    p.screenshot(
                        Page.ScreenshotOptions()
                            .setPath(dir.resolve("$sanitized.png"))
                            .setFullPage(true)
                    )
                } catch (e: Exception) {
                    System.err.println("Screenshot capture failed: ${e.message}")
                }
            }
            _context?.let { ctx ->
                try {
                    val traceDir = Path.of("build/reports/playwright/$specName")
                    Files.createDirectories(traceDir)
                    ctx.tracing().stop(
                        Tracing.StopOptions().setPath(traceDir.resolve("$sanitized.zip"))
                    )
                } catch (e: Exception) {
                    System.err.println("Trace capture failed: ${e.message}")
                }
            }
            // After the screenshot and the trace, so a page that logged an error still leaves both.
            try {
                val held = consoleErrors.drain()
                check(held.isEmpty()) { ConsoleErrors.failureMessage(held) }
            } finally {
                _context?.close()
                _context = null
                _page = null
            }
        }
    }

    /**
     * Writes the page to `build/reports/screenshots/<name>.png`, for a person to page through: a
     * rendering fault an assertion was never going to catch is visible there. Animations are
     * finished rather than waited out — DaisyUI transitions opacity, translate and scale over
     * 200ms, and a popover caught half-faded reads like a layout fault that is not there.
     */
    protected fun saveScreenshot(name: String) {
        Files.createDirectories(SCREENSHOT_DIR)
        val target = SCREENSHOT_DIR.resolve("$name.png")
        page.screenshot(
            Page.ScreenshotOptions()
                .setFullPage(true)
                .setAnimations(ScreenshotAnimations.DISABLED)
                .setPath(target)
        )
        println("Screenshot saved: ${target.toAbsolutePath()}")
    }

    companion object {
        val BASE_URL: String get() = SharedInfrastructure.BASE_URL
        private val SCREENSHOT_DIR: Path = Path.of("build/reports/screenshots")
    }
}
