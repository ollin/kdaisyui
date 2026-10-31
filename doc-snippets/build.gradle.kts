// Compiles the Kotlin snippets of the prose documentation (README.md, docs/), so a snippet that
// names a function, parameter or receiver the library no longer has fails the build. The main
// source set is the extractor that turns the Markdown into Kotlin files. Not published.
plugins {
    id("kdaisyui.kotlin-library-conventions")
    alias(libs.plugins.kover)
}
