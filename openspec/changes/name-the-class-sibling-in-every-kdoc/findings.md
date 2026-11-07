# Findings

## F1 - The sibling was documented in two of three places, and the third is the one read

- **What happened:** at proposal, 2026-11-07. Checking where the class sibling is named found
  it on every reference page and in `llms-full.txt`, and absent from the generated KDoc. The
  Deploy-Radar's author used completion in the IDE, so the two documented places did not help.
- **Cause:** `generate-component-reference` and `llms-txt-index` wrote the sentence into the
  documents they generate; nobody wrote it into the Kotlin emitter, which has its own KDoc path.
- **Cost:** none here; it narrowed the change to one emitter line and one how-to section.
