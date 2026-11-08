package kdaisyui.e2e

import com.microsoft.playwright.Browser
import com.microsoft.playwright.BrowserContext
import com.microsoft.playwright.Page
import com.microsoft.playwright.Tracing
import io.kotest.core.spec.style.FunSpec
import java.nio.file.Files
import java.nio.file.Path

abstract class PlaywrightSpec : FunSpec() {

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
                Browser.NewContextOptions().setBaseURL(BASE_URL)
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

    companion object {
        val BASE_URL: String get() = SharedInfrastructure.BASE_URL
    }
}
