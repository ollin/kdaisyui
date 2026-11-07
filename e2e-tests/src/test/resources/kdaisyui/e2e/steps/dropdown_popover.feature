Feature: Popover Dropdown
  DaisyUI's popover dropdown, anchored to its trigger with no JavaScript

  Scenario: The served markup pairs the trigger and the menu
    Then the endpoint "/dropdown-popover" returns status 200
    And the response body contains "popovertarget=\"dashboard-dropdown-popover\""
    And the response body does not contain "data-anchor-name"
    And the response body contains "id=\"dashboard-dropdown-popover\""
    And the response body does not contain "data-position-anchor"
    And the response body does not contain "style="

  Scenario: The stylesheet turns the anchor attributes into anchor properties
    Then the endpoint "/static/app.css" returns status 200
    And the response body contains "anchor-name: attr(data-anchor-name type(<custom-ident>))"
    And the response body contains "position-anchor: attr(data-position-anchor type(<custom-ident>))"

  @nojs
  Scenario: The menu opens below its trigger with JavaScript disabled
    Given the user opens "/dropdown-popover"
    Then the popover "dashboard-dropdown-popover" is closed
    When the user clicks "Open menu"
    Then the popover "dashboard-dropdown-popover" is open
    And the popover "dashboard-dropdown-popover" sits below "Open menu"
