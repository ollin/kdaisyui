package kdaisyui.e2e

import com.microsoft.playwright.Browser
import com.microsoft.playwright.BrowserContext
import com.microsoft.playwright.BrowserType
import com.microsoft.playwright.Page
import com.microsoft.playwright.Playwright
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import kdaisyui.example.configureRouting
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import java.net.HttpURLConnection
import java.net.URI

/**
 * Base class for Playwright E2E tests.
 *
 * Shares a single Ktor server and Chromium browser across ALL test classes
 * via [SharedInfrastructure]. Each test gets a fresh [BrowserContext] and [Page].
 */
abstract class PlaywrightTestBase {

    companion object {
        const val BASE_URL = "http://localhost:8080"
    }

    protected lateinit var context: BrowserContext
    protected lateinit var page: Page

    @BeforeEach
    fun createContextAndPage() {
        val browser = SharedInfrastructure.browser
        context = browser.newContext(
            Browser.NewContextOptions().setBaseURL(BASE_URL)
        )
        context.setDefaultTimeout(10_000.0)
        page = context.newPage()
    }

    @AfterEach
    fun closeContext() {
        if (::context.isInitialized) context.close()
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
