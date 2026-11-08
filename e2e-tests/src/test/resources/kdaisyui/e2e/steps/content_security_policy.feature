Feature: Content-Security-Policy
  The example application runs under the policy kdaisyui's markup is guaranteed to work under

  Scenario: The header is on every response, static ones included
    Then the endpoint "/" sends the header "Content-Security-Policy" as "default-src 'self'; img-src 'self' data:"
    And the endpoint "/static/app.css" sends the header "Content-Security-Policy" as "default-src 'self'; img-src 'self' data:"

  Scenario: The pages link an icon of their own origin
    Then the endpoint "/static/favicon.svg" returns status 200
    And the endpoint "/" returns status 200
    And the response body contains "rel=\"icon\" type=\"image/svg+xml\" href=\"/static/favicon.svg\""

  Scenario Outline: A page loads no image from another host
    Then the endpoint "<page>" returns status 200
    And the response body does not contain "src=\"http"

    Examples:
      | page                     |
      | /                        |
      | /fragments/team          |
      | /fragments/forms         |
      | /fragments/form-sections |
      | /fragments/cards-row2    |
