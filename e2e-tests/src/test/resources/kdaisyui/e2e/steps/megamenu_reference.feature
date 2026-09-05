Feature: Megamenu Reference
  DaisyUI's documented megamenu markup, hand-written, kept so it can be looked at

  The screenshots land in e2e-tests/build/reports/screenshots/. They exist to be reviewed by a
  human: a megamenu that renders wrongly is something an eye catches and an assertion does not.
  When task 5.1 rebuilds this page from generated wrappers, the same images prove the rebuild
  matches the reference.

  Scenario: The served markup carries the documented megamenu construction
    Then the endpoint "/megamenu-reference" returns status 200
    And the response body contains "class=\"megamenu"
    And the response body contains "popover=\"\""
    And the response body contains "megamenu-active"

  Scenario: Megamenu at a desktop viewport
    Given the viewport is 1280 by 800
    And the user opens "/megamenu-reference"
    Then a screenshot is saved as "megamenu-desktop-closed"
    When the user clicks the button "Components"
    Then the popover "megamenu-panel-one" is open
    And the popover "megamenu-panel-one" is visible
    And a screenshot is saved as "megamenu-desktop-panel-open"

  Scenario: Megamenu at a small viewport
    Given the viewport is 390 by 844
    And the user opens "/megamenu-reference"
    Then a screenshot is saved as "megamenu-small-closed"
    When the user clicks the button "Menu"
    Then the popover "megamenu-reference" is open
    And the popover "megamenu-reference" is visible
    And a screenshot is saved as "megamenu-small-opened"
