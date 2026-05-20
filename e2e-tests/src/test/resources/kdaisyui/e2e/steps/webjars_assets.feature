Feature: Webjar Assets
  Static assets served via webjars for DaisyUI, htmx, and Tailwind

  Scenario: DaisyUI CSS is served from webjars
    Given the user navigates to the dashboard
    Then the asset "/webjars/daisyui/daisyui.css" returns status 200 with content type containing "text/css"

  Scenario: htmx JS is served from webjars
    Given the user navigates to the dashboard
    Then the asset "/webjars/htmx.org/dist/htmx.min.js" returns status 200 with content type containing "javascript"

  Scenario: Tailwind browser JS is served from webjars
    Given the user navigates to the dashboard
    Then the asset "/webjars/tailwindcss__browser/dist/index.global.js" returns status 200 with content type containing "javascript"
