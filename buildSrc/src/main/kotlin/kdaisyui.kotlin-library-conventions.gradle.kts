import org.gradle.api.artifacts.VersionCatalogsExtension

plugins {
    kotlin("jvm")
    `java-library`
}

// Precompiled script plugins cannot use the generated `libs.*` type-safe
// accessors, so resolve the catalog via VersionCatalogsExtension instead.
val kotlinVersion = extensions
    .getByType(VersionCatalogsExtension::class.java)
    .named("libs")
    .findVersion("kotlin").get().requiredVersion

tasks.withType<Jar>().configureEach {
    manifest {
        attributes(
            "Implementation-Title" to "${project.group}:${base.archivesName.get()}",
            "Implementation-Version" to project.version,
            "Implementation-Vendor" to "Oliver Nautsch",
            "Implementation-URL" to "https://github.com/ollin/kdaisyui",
            "Kotlin-Version" to kotlinVersion,
            "Created-By" to "Gradle ${gradle.gradleVersion}",
        )
    }
}

kotlin {
    jvmToolchain(21)
    compilerOptions {
        freeCompilerArgs.add("-Xreturn-value-checker=full")
    }
}

testing {
    suites {
        getByName<JvmTestSuite>("test") {
            useKotlinTest(kotlinVersion)
        }
    }
}
