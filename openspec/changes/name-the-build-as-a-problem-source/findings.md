# Findings

## F1 - A public getter nothing read, shown by PIT as an uncovered mutant

- **What happened:** gate, 2026-11-10. `Source.Plugin(val id: String)` generated a public `getId`
  that no code called, since `toString()` reads the field; PIT reported its mutant as
  `NO_COVERAGE`, which test strength ignores and Kover did not flag. `private val id` removed the
  getter (`^ r`), and PIT then killed 24 of 24.
- **Cause:** a property declared public by habit, for a value only its printed form exposes.
- **Cost:** one TCR run and one PIT run, about 3 minutes.

## F2 - CodeScene could not be run: its access token expired

- **What happened:** gate, 2026-11-10. `code_health_score` and `analyze_change_set` answered
  "Access token is invalid or expired". The rest of the gate is green.
- **Cause:** an expired token in the CodeScene MCP server's configuration; nothing in this change.
- **Cost:** the CodeScene row of the gate stays open until Oliver renews the token.
