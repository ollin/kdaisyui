package kdaisyui.e2e.steps

import io.cucumber.java.en.Then

/**
 * Steps that assert rendered CSS rather than class names.
 *
 * Everything else in this suite — and every generated component test — asserts that a class name
 * reaches the HTML. None of it can tell whether that class carries any style, so a component can
 * be generated correctly, pass its whole test suite, and render unstyled. These steps close that
 * gap for the cases where it matters.
 *
 * They compare two elements rather than assert a value, so nothing here hard-codes a pixel size
 * that a DaisyUI restyle would invalidate.
 */
class ComputedStyleSteps(private val world: PlaywrightWorld) {

    @Then("the elements {string} and {string} have the same {string}")
    fun sameComputedProperty(idA: String, idB: String, property: String) {
        val a = computed(idA, property)
        val b = computed(idB, property)
        check(a == b) { "Expected #$idA and #$idB to share $property, got $a and $b" }
    }

    @Then("the elements {string} and {string} differ in {string}")
    fun differentComputedProperty(idA: String, idB: String, property: String) {
        val a = computed(idA, property)
        val b = computed(idB, property)
        check(a != b) { "Expected #$idA and #$idB to differ in $property, both were $a" }
    }

    private fun computed(elementId: String, property: String): String =
        world.page.locator("#$elementId")
            .evaluate("(el, prop) => getComputedStyle(el).getPropertyValue(prop)", property)
            as String
}
