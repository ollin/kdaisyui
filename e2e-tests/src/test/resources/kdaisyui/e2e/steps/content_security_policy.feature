Feature: Content-Security-Policy
  The example application runs under the policy kdaisyui's markup is guaranteed to work under

  Scenario: The header is on every response, static ones included
    Then the endpoint "/" sends the header "Content-Security-Policy" as "default-src 'self'; img-src 'self' data:"
    And the endpoint "/static/app.css" sends the header "Content-Security-Policy" as "default-src 'self'; img-src 'self' data:"

  Scenario: The pages load no image from another host
    Then the endpoint "/" returns status 200
    And the response body does not contain "src=\"http"
    And the endpoint "/fragments/team" returns status 200
    And the response body does not contain "src=\"http"
    And the endpoint "/fragments/forms" returns status 200
    And the response body does not contain "src=\"http"
    And the endpoint "/fragments/form-sections" returns status 200
    And the response body does not contain "src=\"http"
    And the endpoint "/fragments/cards-row2" returns status 200
    And the response body does not contain "src=\"http"
