# Findings

## F1 - A broken `openspec/config.yaml` is ignored with a warning, and was committed

- **What happened:** task 2.3, 2026-11-08. The rule's text "not from its behaviour: a changed
  line" put `: ` into a plain YAML scalar, so `config.yaml` no longer parsed. The commit went in
  unchecked; the grep for the rule printed 0, and parsing the file showed the error, so the
  unpushed commit was amended. Probed afterwards: with that file, `openspec instructions tasks`
  prints `Warning: could not parse … ignoring it` on stderr and serves **no** rules at all, and
  `openspec validate --all --strict` does not fail because of it.
- **Cause:** a rule edited as prose in a YAML file, with no check that the file parses; the CLI
  degrades silently to "no project rules".
- **Cost:** five minutes; nothing reached `main`.
