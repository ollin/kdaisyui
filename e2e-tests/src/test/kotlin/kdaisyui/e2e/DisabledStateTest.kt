package kdaisyui.e2e

import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

/**
 * Whether the native attribute alone carries DaisyUI's disabled styling.
 *
 * `daisyButton(disabled = true)` sets the native attribute, which is the accessible way to say a
 * button is unavailable. Whether it also LOOKS disabled is a CSS question that no class assertion
 * can reach: a button can carry every expected class and render unstyled.
 *
 * The enabled button is the positive control. Without it two unstyled buttons would agree and
 * these tests would pass while proving nothing. Checked by deliberately breaking it: with the
 * class removed from the third button, the first test fails and the second still passes.
 *
 * Equal styling is not equal meaning. DaisyUI documents the class technique as
 * `tabindex="-1" role="button" aria-disabled="true"`, because the class conveys nothing to a
 * screen reader on its own; these tests measure the styling and say nothing about the semantics.
 */
class DisabledStateTest : PlaywrightSpec() {

    init {
        test("The native attribute and the class reach the same rule") {
            page.navigate("/disabled-state")
            for (property in listOf("background-color", "color")) {
                page.computedStyle("disabled-native", property) shouldBe
                    page.computedStyle("disabled-class", property)
            }
        }

        test("A disabled button does not look like an enabled one") {
            page.navigate("/disabled-state")
            page.computedStyle("disabled-native", "color") shouldNotBe
                page.computedStyle("disabled-enabled", "color")
        }
    }
}
