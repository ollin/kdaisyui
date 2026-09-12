# migrate-codegen-to-typescript

Port codegen/ from JavaScript to TypeScript 7, executed directly by Node's built-in type stripping. No build step, no runtime dependency, and no tsc type-check step: types serve the editor, while CI's existing gates — 19 codegen unit tests and byte-identical generated-sources-drift — remain the enforcement.
