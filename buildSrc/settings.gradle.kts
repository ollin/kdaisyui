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
