Feature: Team Management
  Team management section loaded via htmx after scrolling to bottom

  Scenario: Team form loads after scrolling to bottom
    Given the user navigates to the dashboard
    When the user scrolls to the bottom of the page
    Then the text "Team management" is visible within 10 seconds
    And the text "Invitation sent successfully" is visible
    And the button "Send invitation" is visible

  Scenario: Team fragment endpoint returns HTML
    Given the user navigates to the dashboard
    Then the endpoint "/fragments/team" returns status 200
    And the response body contains "Send invitation"
    And the response body contains "alert-success"
    And the response body contains "Active team members"
