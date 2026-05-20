Feature: Stats Fragment
  Statistics cards loaded via htmx from the stats fragment endpoint

  Scenario: Stats load via htmx after page load
    Given the user navigates to the dashboard
    Then the text "Active Repositories" is visible within 5 seconds
    And the text "142" is visible
    And the text "Open Issues" is visible

  Scenario: Stats fragment endpoint returns HTML directly
    Given the user navigates to the dashboard
    Then the endpoint "/fragments/stats" returns status 200 with content type "text/html"
    And the response body contains "stat-title"
    And the response body contains "Active Repositories"
