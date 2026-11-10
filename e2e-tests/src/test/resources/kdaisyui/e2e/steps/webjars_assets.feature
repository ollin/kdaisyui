Feature: Static Assets
  The stylesheet the build compiles, and the webjars the application still uses

  # Tailwind scans Kotlin sources as TEXT and cannot tell a comment from a call, so a composed
  # class written in a doc comment used to become a candidate and get a rule. Two comments were
  # doing it, and one of them kept lg:btn-lg alive in the stylesheet — which made the four
  # scenarios in tailwind_variant.feature pass whether or not extraClasses worked at all.
  #
  # dark:alert-info is the canary: no code in the example app can put it on a page, so a rule for
  # it can only have come from prose. The first assertion is the calibration — without it, the
  # second would pass just as happily against an empty response body.
  Scenario: The stylesheet carries no rule that only prose asked for
    Given the user navigates to the dashboard
    Then the endpoint "/static/app.css" returns status 200
    And the stylesheet defines a rule for the class "lg:btn-lg"
    And the stylesheet defines no rule for the class "dark:alert-info"
