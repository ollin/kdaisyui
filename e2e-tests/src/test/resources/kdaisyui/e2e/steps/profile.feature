Feature: Profile
  The user's profile, where a theme is chosen with a preview of each

  Scenario: The profile page is served
    Given the user opens "/profile"
    Then the heading "Profile" is visible

  Scenario: Every built-in theme is offered, and the system default
    Given the user opens "/profile"
    Then the theme form offers every theme and the system default

  Scenario: The stylesheet carries the themes beyond DaisyUI's default two
    Then the endpoint "/static/app.css" returns status 200
    And the response body contains "[data-theme=nord]"
    And the response body contains "[data-theme=abyss]"

  Scenario: Every theme is previewed in itself
    Given the user opens "/profile"
    Then every theme preview computes its own primary colour

  Scenario: Saving a theme keeps it in a cookie and returns to the profile
    Given the user opens "/profile"
    When the user chooses the theme "nord" and saves
    Then the heading "Profile" is visible
    And the browser keeps the theme "nord"
    And the page wears the theme "nord"
    And the theme choice "nord" is checked

  Scenario: A value that is not a theme is refused
    Then saving the theme "no-such-theme" is refused without a cookie

  Scenario: The saved theme is worn by the dashboard
    Given the user opens "/profile"
    When the user chooses the theme "nord" and saves
    And the user opens "/"
    Then the page wears the theme "nord"

  Scenario: The system default forgets the saved theme
    Given the user opens "/profile"
    When the user chooses the theme "nord" and saves
    And the user chooses the theme "system" and saves
    Then the page wears no theme of its own
    And the theme choice "system" is checked

  Scenario: The avatar menu leads to the profile
    Given the user navigates to the dashboard
    When the user opens the profile from the avatar menu
    Then the heading "Profile" is visible
