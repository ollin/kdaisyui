Feature: Profile
  The user's profile, where a theme is chosen with a preview of each

  Scenario: The profile page is served
    Given the user opens "/profile"
    Then the heading "Profile" is visible
