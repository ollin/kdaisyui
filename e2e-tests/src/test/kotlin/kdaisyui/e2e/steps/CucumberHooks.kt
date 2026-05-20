package kdaisyui.e2e.steps

import com.microsoft.playwright.Browser
import com.microsoft.playwright.Page
import io.cucumber.java.After
import io.cucumber.java.Before
import io.cucumber.java.Scenario
import kdaisyui.e2e.SharedInfrastructure

class CucumberHooks(private val world: PlaywrightWorld) {

    @Before
    fun setUp() {
        world.context = SharedInfrastructure.browser.newContext(
            Browser.NewContextOptions().setBaseURL(SharedInfrastructure.BASE_URL)
        )
        world.context.setDefaultTimeout(10_000.0)
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
        world.context.close()
    }
}
