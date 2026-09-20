Feature: Disabled State Of A Button
  Whether the native attribute alone carries DaisyUI's disabled styling

  `daisyButton(disabled = true)` sets the native attribute, which is the accessible way to say
  a button is unavailable. Whether it also LOOKS disabled is a CSS question that no class
  assertion can reach: a button can carry every expected class and render unstyled.

  The enabled button is the positive control. Without it two unstyled buttons would agree and
  these scenarios would pass while proving nothing. Checked by deliberately breaking it: with
  the class removed from the third button, the first scenario fails and the second still passes.

  Equal styling is not equal meaning. DaisyUI documents the class technique as
  tabindex="-1" role="button" aria-disabled="true", because the class conveys nothing to a
  screen reader on its own; these scenarios measure the styling and say nothing about the
  semantics.

  Scenario: The native attribute and the class reach the same rule
    Given the user opens "/disabled-state"
    Then the elements "disabled-native" and "disabled-class" have the same "background-color"
    And the elements "disabled-native" and "disabled-class" have the same "color"

  Scenario: A disabled button does not look like an enabled one
    Given the user opens "/disabled-state"
    Then the elements "disabled-enabled" and "disabled-native" differ in "color"
