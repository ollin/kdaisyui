Feature: Profile
  The user's profile, where a theme is chosen with a preview of each

  Scenario: The profile page is served
    Given the user opens "/profile"
    Then the heading "Profile" is visible

  Scenario: Every built-in theme is offered, and the system default
    Given the user opens "/profile"
    Then the theme form offers 36 choices
