Feature: Dashboard Shell
  The main dashboard layout with sidebar, header, and navigation

  Scenario: Dashboard loads with sidebar and header visible immediately
    Given the user navigates to the dashboard
    Then the text "DevTrack" is visible
    And the heading "Overview" is visible
    And the placeholder "Search" is visible

  Scenario: Sidebar navigation items are present
    Given the user navigates to the dashboard
    Then the sidebar contains the following navigation items:
      | label        |
      | Overview     |
      | Repositories |
      | Issues       |
      | Pipelines    |
      | Teams        |
      | Settings     |

  Scenario: Sidebar submenu expands on click
    Given the user navigates to the dashboard
    When the user clicks the sidebar summary "Repositories"
    Then the text "All Repos" is visible in the sidebar
    And the text "New Repository" is visible in the sidebar

  Scenario: CSS is loaded and DaisyUI classes are applied
    Given the user navigates to the dashboard
    Then the first nav element has a non-transparent background color
