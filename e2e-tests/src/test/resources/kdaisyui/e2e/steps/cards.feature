Feature: Dashboard Cards
  Card-based content sections loaded via htmx fragments

  Scenario: Pipeline table loads with project entries
    Given the user navigates to the dashboard
    Then the heading "Recent Pipeline Runs" is visible within 5 seconds
    And the table cell "api-gateway" is visible
    And the table cell "frontend" is visible

  Scenario: Open issues card loads with labels
    Given the user navigates to the dashboard
    Then the heading "Open Issues" is visible within 5 seconds
    And the text "Good First Issue" is visible
