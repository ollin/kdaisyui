Feature: Progressive Loading via htmx
  Fragments are loaded asynchronously after the initial page render

  Scenario: Page loads fragments progressively via htmx
    Given the user navigates to the dashboard
    When the user waits 3 seconds for fragments to load
    Then at least 2 fragment responses were received

  Scenario: All fragment endpoints return HTTP 200
    Given the user navigates to the dashboard
    Then the following fragment endpoints return status 200:
      | path                    |
      | /fragments/stats        |
      | /fragments/cards-row1   |
      | /fragments/cards-row2   |
      | /fragments/forms        |
      | /fragments/form-sections|
      | /fragments/team         |
