import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.provider.ValueSource
import org.gradle.api.provider.ValueSourceParameters
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

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

abstract class GitHashValueSource : ValueSource<String, GitHashValueSource.Parameters> {
    interface Parameters : ValueSourceParameters {
        val rootDir: DirectoryProperty
    }

    override fun obtain(): String {
        val process = ProcessBuilder("git", "rev-parse", "HEAD")
            .directory(parameters.rootDir.get().asFile)
            .start()
        return process.inputStream.bufferedReader().readLine()?.trim() ?: "unknown"
    }
}

val gitHash: Provider<String> = providers.of(GitHashValueSource::class.java) {
    parameters.rootDir.set(rootProject.layout.projectDirectory)
}

tasks.withType<Jar>().configureEach {
    manifest {
        attributes(
            "Implementation-Title" to "${project.group}:${base.archivesName.get()}",
            "Implementation-Version" to project.version,
            "Implementation-Vendor" to "ollin",
            "Implementation-URL" to "https://github.com/ollin/kdaisyui",
            "SCM-Revision" to gitHash.get(),
            "Kotlin-Version" to kotlinVersion,
            "Created-By" to "Gradle ${gradle.gradleVersion}",
        )
    }
}

repositories {
    mavenCentral()
}

kotlin {
    jvmToolchain(21)
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_21)

        freeCompilerArgs.add("-Xreturn-value-checker=full")
    }
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

testing {
    suites {
        val test by getting(JvmTestSuite::class) {
            useKotlinTest(kotlinVersion)
        }
    }
}
