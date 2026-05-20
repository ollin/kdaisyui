package kdaisyui.e2e

import com.microsoft.playwright.Browser
import com.microsoft.playwright.BrowserContext
import com.microsoft.playwright.BrowserType
import com.microsoft.playwright.Page
import com.microsoft.playwright.Playwright
import io.kotest.core.spec.style.FunSpec
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import kdaisyui.example.configureRouting
import java.net.HttpURLConnection
import java.net.URI
import java.nio.file.Files
import java.nio.file.Path

/**
 * Base spec for Playwright E2E tests.
 *
 * Shares a single Ktor server and Chromium browser across ALL specs
 * via [SharedInfrastructure]. Each test gets a fresh [BrowserContext] and [Page].
 * Screenshots are captured after every test to build/screenshots/{specName}/{testName}.png.
 */
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
                    val sanitized = testCase.name.testName.replace(Regex("[^a-zA-Z0-9._-]"), "_")
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
        const val BASE_URL = "http://localhost:8080"
    }
}

private object SharedInfrastructure {

    private const val SERVER_START_TIMEOUT_MS = 30_000L
    private const val POLL_INTERVAL_MS = 200L

    val browser: Browser

    private val server: EmbeddedServer<NettyApplicationEngine, NettyApplicationEngine.Configuration>
    private val playwright: Playwright

    init {
        server = embeddedServer(Netty, port = 8080) {
            configureRouting()
        }
        server.start(wait = false)
        waitForServer()

        playwright = Playwright.create()
        val launchOptions = BrowserType.LaunchOptions().setHeadless(true)
        System.getenv("PLAYWRIGHT_CHROMIUM_EXECUTABLE_PATH")?.let {
            launchOptions.setExecutablePath(java.nio.file.Path.of(it))
        }
        browser = playwright.chromium().launch(launchOptions)

        Runtime.getRuntime().addShutdownHook(Thread {
            browser.close()
            playwright.close()
            server.stop(500, 1000)
        })
    }

    private fun waitForServer() {
        val deadline = System.currentTimeMillis() + SERVER_START_TIMEOUT_MS
        while (System.currentTimeMillis() < deadline) {
            try {
                val connection = URI("http://localhost:8080/").toURL()
                    .openConnection() as HttpURLConnection
                connection.connectTimeout = 1000
                connection.readTimeout = 1000
                connection.requestMethod = "GET"
                if (connection.responseCode == 200) return
            } catch (_: Exception) { }
            Thread.sleep(POLL_INTERVAL_MS)
        }
        error("Ktor server did not start within ${SERVER_START_TIMEOUT_MS}ms")
    }
}
