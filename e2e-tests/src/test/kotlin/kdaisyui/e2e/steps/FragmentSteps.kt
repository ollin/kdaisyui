package kdaisyui.e2e.steps

import com.microsoft.playwright.Page.GetByRoleOptions
import com.microsoft.playwright.assertions.LocatorAssertions.IsVisibleOptions
import com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat
import com.microsoft.playwright.options.AriaRole
import io.cucumber.datatable.DataTable
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import kdaisyui.e2e.SharedInfrastructure
import java.util.concurrent.atomic.AtomicInteger

class FragmentSteps(private val world: PlaywrightWorld) {

    private val fragmentResponseCount = AtomicInteger(0)
    private var lastResponseBody: String = ""

    @Then("the text {string} is visible within {int} seconds")
    fun textIsVisibleWithTimeout(text: String, seconds: Int) {
        assertThat(world.page.getByText(text).first())
            .isVisible(IsVisibleOptions().setTimeout((seconds * 1000).toDouble()))
    }

    @Then("the heading {string} is visible within {int} seconds")
    fun headingIsVisibleWithTimeout(name: String, seconds: Int) {
        assertThat(world.page.getByRole(AriaRole.HEADING, GetByRoleOptions().setName(name)))
            .isVisible(IsVisibleOptions().setTimeout((seconds * 1000).toDouble()))
    }

    @Then("the table cell {string} is visible")
    fun tableCellIsVisible(text: String) {
        assertThat(world.page.getByRole(AriaRole.CELL, GetByRoleOptions().setName(text))).isVisible()
    }

    @When("the user scrolls to the middle of the page")
    fun scrollToMiddle() {
        world.page.evaluate("() => window.scrollTo(0, document.body.scrollHeight / 2)")
    }

    @When("the user scrolls to the bottom of the page")
    fun scrollToBottom() {
        world.page.evaluate("() => window.scrollTo(0, document.body.scrollHeight)")
    }

    @When("the select dropdown is loaded within {int} seconds")
    fun selectDropdownLoaded(seconds: Int) {
        world.page.waitForSelector(
            "select.select",
            com.microsoft.playwright.Page.WaitForSelectorOptions().setTimeout((seconds * 1000).toDouble())
        )
    }

    @Then("the second select dropdown contains the option {string}")
    fun secondSelectContainsOption(optionText: String) {
        val select = world.page.locator("select.select").nth(1)
        assertThat(select).isVisible()
        val options = select.locator("option").allTextContents()
        check(options.contains(optionText)) { "Expected option '$optionText' in $options" }
    }

    @When("the user waits {int} seconds for fragments to load")
    fun waitForFragments(seconds: Int) {
        fragmentResponseCount.set(0)
        world.page.onResponse { response ->
            if (response.url().contains("/fragments/")) {
                fragmentResponseCount.incrementAndGet()
            }
        }
        world.page.navigate("/")
        world.page.waitForTimeout((seconds * 1000).toDouble())
    }

    @Then("at least {int} fragment responses were received")
    fun atLeastNFragmentResponses(expected: Int) {
        val actual = fragmentResponseCount.get()
        check(actual >= expected) { "Expected at least $expected fragment responses, got $actual" }
    }

    @Then("the following fragment endpoints return status {int}:")
    fun fragmentEndpointsReturnStatus(expectedStatus: Int, dataTable: DataTable) {
        for (row in dataTable.asMaps()) {
            val path = row["path"]!!
            val response = world.page.request().get("${SharedInfrastructure.BASE_URL}$path")
            check(response.status() == expectedStatus) {
                "Expected status $expectedStatus for $path, got ${response.status()}"
            }
        }
    }

    @Then("the endpoint {string} returns status {int} with content type {string}")
    fun endpointReturnsStatusWithContentType(path: String, expectedStatus: Int, expectedContentType: String) {
        val response = world.page.request().get("${SharedInfrastructure.BASE_URL}$path")
        check(response.status() == expectedStatus) {
            "Expected status $expectedStatus for $path, got ${response.status()}"
        }
        val contentType = response.headers()["content-type"]!!
        check(contentType.contains(expectedContentType)) {
            "Expected content type containing '$expectedContentType', got '$contentType'"
        }
        lastResponseBody = response.text()
    }

    @Then("the endpoint {string} returns status {int}")
    fun endpointReturnsStatus(path: String, expectedStatus: Int) {
        val response = world.page.request().get("${SharedInfrastructure.BASE_URL}$path")
        check(response.status() == expectedStatus) {
            "Expected status $expectedStatus for $path, got ${response.status()}"
        }
        lastResponseBody = response.text()
    }

    @Then("the response body contains {string}")
    fun responseBodyContains(text: String) {
        check(lastResponseBody.contains(text)) {
            "Expected response body to contain '$text'"
        }
    }

    @Then("the asset {string} returns status {int} with content type containing {string}")
    fun assetReturnsStatusWithContentType(path: String, expectedStatus: Int, contentTypeFragment: String) {
        val response = world.page.request().get("${SharedInfrastructure.BASE_URL}$path")
        check(response.status() == expectedStatus) {
            "Expected status $expectedStatus for $path, got ${response.status()}"
        }
        val contentType = response.headers()["content-type"]!!
        check(contentType.contains(contentTypeFragment)) {
            "Expected content type containing '$contentTypeFragment', got '$contentType'"
        }
    }
}
