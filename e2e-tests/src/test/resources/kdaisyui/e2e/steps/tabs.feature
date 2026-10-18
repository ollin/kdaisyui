Feature: Tabs
  daisyTabs renders the tablist and daisyTab the items, as DaisyUI documents them

  Until declare-the-component-model the container rendered a button and an active item emitted
  tabs-tab-active, a class DaisyUI does not have, so an active tab looked like any other. The
  inactive tab is the positive control: without it, two unstyled tabs would agree.

  Scenario: The served markup is a tablist with tab items
    Then the endpoint "/tabs" returns status 200
    And the response body contains "role=\"tablist\""
    And the response body contains "class=\"tab tab-active\""

  Scenario: An active tab looks different from an inactive one
    Given the user opens "/tabs"
    Then the elements "tab-active" and "tab-inactive" differ in "background-color"
