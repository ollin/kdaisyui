package kdaisyui.e2e

import com.microsoft.playwright.Browser
import com.microsoft.playwright.BrowserType
import com.microsoft.playwright.Playwright
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import kdaisyui.example.configureRouting
import kotlinx.coroutines.runBlocking
import java.net.HttpURLConnection
import java.net.URI

/**
 * Singleton that manages the shared Ktor server and Chromium browser
 * for all E2E tests (both Kotest and Cucumber).
 *
 * Starts on first access, shuts down via JVM shutdown hook.
 */
internal object SharedInfrastructure {

    /**
     * Where the server answers, known only once it has started: it binds port 0 on the loopback
     * address, so the operating system picks a free port. A fixed 8080 failed the whole suite
     * with BindException whenever anything else on the machine held that port.
     */
    val BASE_URL: String

    private const val LOOPBACK = "127.0.0.1"
    private const val SERVER_START_TIMEOUT_MS = 30_000L
    private const val POLL_INTERVAL_MS = 200L

    val browser: Browser

    private val server: EmbeddedServer<NettyApplicationEngine, NettyApplicationEngine.Configuration>
    private val playwright: Playwright

    init {
        server = embeddedServer(Netty, port = 0, host = LOOPBACK) {
            configureRouting()
        }
        server.start(wait = false)
        val port = runBlocking { server.engine.resolvedConnectors().first().port }
        BASE_URL = "http://$LOOPBACK:$port"
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
                val connection = URI("$BASE_URL/").toURL()
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
