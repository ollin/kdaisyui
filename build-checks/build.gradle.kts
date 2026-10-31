// Checks on the build itself, run by CI after `check`. Today one: no Gradle problem beyond those
// gradle/allowed-gradle-problems.txt accepts. Not published.
plugins {
    id("kdaisyui.kotlin-library-conventions")
    alias(libs.plugins.kover)
}
