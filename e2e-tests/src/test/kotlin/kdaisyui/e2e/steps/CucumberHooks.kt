package kdaisyui.e2e.steps

import com.microsoft.playwright.Browser
import com.microsoft.playwright.Page
import com.microsoft.playwright.Tracing
import io.cucumber.java.After
import io.cucumber.java.Before
import io.cucumber.java.Scenario
import kdaisyui.e2e.SharedInfrastructure
import java.nio.file.Files
import java.nio.file.Path

class CucumberHooks(private val world: PlaywrightWorld) {

    @Before
    fun setUp() {
        world.context = SharedInfrastructure.browser.newContext(
            Browser.NewContextOptions().setBaseURL(SharedInfrastructure.BASE_URL)
        )
        world.context.setDefaultTimeout(10_000.0)
        world.context.tracing().start(
            Tracing.StartOptions().setScreenshots(true).setSnapshots(true).setSources(true)
        )
        world.page = world.context.newPage()
    }

    @After
    fun tearDown(scenario: Scenario) {
        try {
            val screenshot = world.page.screenshot(
                Page.ScreenshotOptions().setFullPage(true)
            )
            scenario.attach(screenshot, "image/png", scenario.name)
        } catch (e: Exception) {
            System.err.println("Screenshot capture failed: ${e.message}")
        }
        try {
            val sanitized = scenario.name.replace(Regex("[^a-zA-Z0-9._-]"), "_")
            val traceDir = Path.of("build/reports/playwright/cucumber")
            Files.createDirectories(traceDir)
            world.context.tracing().stop(
                Tracing.StopOptions().setPath(traceDir.resolve("$sanitized.zip"))
            )
        } catch (e: Exception) {
            System.err.println("Trace capture failed: ${e.message}")
        }
        world.context.close()
    }
}
