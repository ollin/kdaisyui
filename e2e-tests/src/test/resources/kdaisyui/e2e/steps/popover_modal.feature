Feature: Popover Modal
  DaisyUI's popover modal method, the one that needs no JavaScript

  Scenario: The served markup carries the popover construction method
    Then the endpoint "/popover-modal" returns status 200
    And the response body contains "class=\"modal\""
    And the response body contains "popover=\"\""

  @nojs
  Scenario: The modal opens and closes with JavaScript disabled
    Given the user opens the popover modal page
    Then the popover "dashboard-popover-modal" is closed
    When the user clicks "Open modal"
    Then the popover "dashboard-popover-modal" is open
    When the user presses Escape
    Then the popover "dashboard-popover-modal" is closed
