// buildSrc is its own build with its own settings, so applying the foojay resolver in the
// root settings does not reach it. Without it, buildSrc's `jvmToolchain(21)` — requested
// while Gradle itself runs on the JDK 25 from .tool-versions — is satisfied from a
// previously auto-provisioned JDK with no toolchain repository behind it, which Gradle
// deprecated for removal in 10.
//
// Declared without a version on purpose: buildSrc inherits the root settings' plugin
// classpath, and naming a version there fails with "already on the classpath with an
// unknown version". The version lives in the root settings and only there.
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention")
}

rootProject.name = "kdaisyui-conventions"

// Import the main build's version catalog so buildSrc can use type-safe `libs.*`
// accessors. The catalog at gradle/libs.versions.toml is the single source of
// truth for all dependency, plugin, and tooling versions.
dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            from(files("../gradle/libs.versions.toml"))
        }
    }
}
