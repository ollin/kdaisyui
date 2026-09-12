/**
 * The shared vocabulary for the two kinds of name this codegen keeps confusing.
 *
 * A TAG is what appears in HTML: `div`, `fieldset`, `textarea`. A kotlinx.html BUILDER is
 * the Kotlin function that emits one: `div`, `fieldSet`, `textArea`. They are equal for most
 * elements and differ for exactly the ones that catch you, so a mistake survives every test
 * that happens to use a `div`.
 *
 * This module exists rather than each file declaring its own because brands are NOMINAL:
 * two `TagName` declarations would be two incompatible types, which is worse than having
 * none at all. `llms-txt.ts` reads tag names out of DaisyUI's documentation and
 * `test-generator.ts` writes them into Kotlin — they must mean the same thing.
 *
 * It holds only types, so it disappears entirely under Node's type stripping.
 */

/** An HTML element name, e.g. `fieldset`. */
export type TagName = string & { readonly __brand: 'TagName' }

/** A kotlinx.html builder function name, e.g. `fieldSet`. */
export type BuilderName = string & { readonly __brand: 'BuilderName' }
