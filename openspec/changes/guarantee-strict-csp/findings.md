# Findings

## F1 - The example application breaks the policy it is about to guarantee

- **What happened:** at proposal, 2026-11-07. Reading `DashboardShell.kt` for the proposal
  found an inline `<style>` element, an avatar image from `https://picsum.photos`, and no icon
  link, so every page load 404s on `/favicon.ico`. None of the ten proposals from the
  Deploy-Radar named these; they are what a check nobody had would have found.
- **Cause:** the E2E suite sends no policy and listens to no console.
- **Cost:** three fix tasks (3.2, 3.3, 3.5) that would not exist for a library whose demo
  already loaded nothing from elsewhere; about 40 minutes in the estimate.
