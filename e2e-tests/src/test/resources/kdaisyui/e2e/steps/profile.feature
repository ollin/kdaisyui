Feature: Profile
  The user's profile, where a theme is chosen with a preview of each

  Scenario: The profile page is served
    Given the user opens "/profile"
    Then the heading "Profile" is visible

  Scenario: Every built-in theme is offered, and the system default
    Given the user opens "/profile"
    Then the theme form offers 36 choices

  Scenario: The stylesheet carries the themes beyond DaisyUI's default two
    Then the endpoint "/static/app.css" returns status 200
    And the response body contains "[data-theme=nord]"
    And the response body contains "[data-theme=abyss]"

  Scenario: Every theme is previewed in itself
    Given the user opens "/profile"
    Then the 35 theme previews each compute their own "--color-primary"

  Scenario: Saving a theme keeps it in a cookie and returns to the profile
    Given the user opens "/profile"
    When the user chooses the theme "nord" and saves
    Then the heading "Profile" is visible
    And the browser keeps the cookie "theme" as "nord"

  Scenario: A value that is not a theme is refused
    Then posting the theme "no-such-theme" to "/profile/theme" answers 400 and sets no cookie

  Scenario: The saved theme is worn by the dashboard
    Given the user opens "/profile"
    When the user chooses the theme "nord" and saves
    And the user opens "/"
    Then the page wears the theme "nord"
