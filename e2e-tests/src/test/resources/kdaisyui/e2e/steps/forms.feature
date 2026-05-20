Feature: Repository Form
  The repository management form loaded via htmx fragment

  Scenario: Repository form inputs are present after scroll
    Given the user navigates to the dashboard
    When the user scrolls to the middle of the page
    Then the text "Repository management" is visible within 8 seconds
    And the placeholder "devtrack/api-gateway" is visible

  Scenario: Select dropdown has gitignore options
    Given the user navigates to the dashboard
    When the user scrolls to the middle of the page
    And the select dropdown is loaded within 8 seconds
    Then the second select dropdown contains the option "Node"
