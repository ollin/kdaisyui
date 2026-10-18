## REMOVED Requirements

### Requirement: Configuration that is never read is an error

**Reason:** `codegen/codegen-config.json` is deleted. Everything it held moves into the component
model, which is keyed by DaisyUI directory name throughout. The guard existed because a mis-keyed
entry was indistinguishable from an absent one; one key convention and a completeness check remove
that failure instead of detecting it.

**Migration:** see "The model accounts for every class DaisyUI declares" in `component-model`.
Generation fails when a model file names no DaisyUI directory, or when a DaisyUI directory has
neither a model file nor a skip entry.
