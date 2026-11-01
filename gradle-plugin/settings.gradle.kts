// The kdaisyui Gradle plugin, an included build of the repository: a plugin cannot be applied from a
// sibling subproject, and example-app applies this one. Released with the others by the root build.
dependencyResolutionManagement {
    repositories { mavenCentral() }
    versionCatalogs { create("libs") { from(files("../gradle/libs.versions.toml")) } }
}

rootProject.name = "kdaisyui-gradle-plugin"
