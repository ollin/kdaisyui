# Findings

## F1 - `DaisyTheme` can name a theme the stylesheet does not carry

- **What happened:** at proposal, 2026-11-10. The enum lists all 35 built-in themes, while a
  compiled entry carries only `light` and `dark` unless it names more
  (`daisyui/packages/daisyui/functions/pluginOptionsHandler.js:9`). `daisyTheme(DaisyTheme.Nord)`
  against such a stylesheet renders the default theme, with no error anywhere - the same silent
  gap `css-delivery` describes for classes.
- **Cause:** themes are not classes, so the plugin's class list never sees them, and the entry's
  `@plugin` block is the consumer's text, not the plugin's.
- **Cost:** none here; the how-to states the limit (task 4.1). A plugin check, or a plugin option
  that writes the `themes` line, would close it.

## F2 - One question round at proposal

- **What happened:** four questions put to Oliver together on 2026-11-10: library scope,
  persistence, what "preview" means, which themes compile. All four answered as recommended.
- **Cause:** each changed scope or observable behaviour (the picker with or without a live
  preview, a library helper or none).
- **Cost:** one round, before the ledger run opened, so no wait is recorded.
