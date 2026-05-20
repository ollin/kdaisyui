package kdaisyui.e2e

import com.microsoft.playwright.Browser
import com.microsoft.playwright.BrowserContext
import com.microsoft.playwright.Page
import io.kotest.core.spec.style.FunSpec
import java.nio.file.Files
import java.nio.file.Path

abstract class PlaywrightSpec : FunSpec() {

    private var _context: BrowserContext? = null
    private var _page: Page? = null

    protected val context: BrowserContext get() = _context!!
    protected val page: Page get() = _page!!

    init {
        beforeTest {
            val browser = SharedInfrastructure.browser
            _context = browser.newContext(
                Browser.NewContextOptions().setBaseURL(BASE_URL)
            )
            _context!!.setDefaultTimeout(10_000.0)
            _page = _context!!.newPage()
        }

        afterTest { (testCase, _) ->
            _page?.let { p ->
                try {
                    val specName = testCase.spec::class.simpleName ?: "UnknownSpec"
                    val sanitized = testCase.name.name.replace(Regex("[^a-zA-Z0-9._-]"), "_")
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
            _context?.close()
            _context = null
            _page = null
        }
    }

    companion object {
        const val BASE_URL = SharedInfrastructure.BASE_URL
    }
}
