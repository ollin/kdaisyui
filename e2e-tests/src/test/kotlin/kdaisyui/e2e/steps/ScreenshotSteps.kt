package kdaisyui.e2e.steps

import com.microsoft.playwright.Page
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import java.nio.file.Files
import java.nio.file.Path

/** Where saved screenshots land, relative to `e2e-tests/`. Printed on every save. */
private val SCREENSHOT_DIR: Path = Path.of("build/reports/screenshots")

/**
 * Steps for looking at a page rather than asserting about it.
 *
 * The existing `@After` hook attaches a screenshot to the Cucumber report, which is fine for
 * diagnosing a failure and useless for browsing: the image is embedded in the report and there is
 * no file to open. These steps write named PNGs to `build/reports/screenshots/` instead, so a
 * human can page through them and spot the rendering faults an assertion was never going to catch.
 *
 * Both steps are deliberately component-agnostic.
 */
class ScreenshotSteps(private val world: PlaywrightWorld) {

    @Given("the viewport is {int} by {int}")
    fun setViewport(width: Int, height: Int) {
        world.page.setViewportSize(width, height)
    }

    @Then("a screenshot is saved as {string}")
    fun saveScreenshot(name: String) {
        Files.createDirectories(SCREENSHOT_DIR)
        val target = SCREENSHOT_DIR.resolve("$name.png")
        world.page.screenshot(
            Page.ScreenshotOptions().setFullPage(true).setPath(target)
        )
        println("Screenshot saved: ${target.toAbsolutePath()}")
    }
}
