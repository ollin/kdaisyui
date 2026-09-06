Feature: Static Assets
  The stylesheet the build compiles, and the webjars the application still uses

  Scenario: DaisyUI CSS is served from webjars
    Given the user navigates to the dashboard
    Then the asset "/webjars/daisyui/daisyui.css" returns status 200 with content type containing "text/css"

  Scenario: htmx JS is served from webjars
    Given the user navigates to the dashboard
    Then the asset "/webjars/htmx.org/dist/htmx.min.js" returns status 200 with content type containing "javascript"

  # Replaced the Tailwind-browser-JS scenario. Nothing loads that script since the build
  # started compiling CSS, so asserting it was still downloadable tested a dependency the
  # application no longer has. This asserts the asset that took its place.
  Scenario: The compiled stylesheet is served
    Given the user navigates to the dashboard
    Then the asset "/static/app.css" returns status 200 with content type containing "text/css"
