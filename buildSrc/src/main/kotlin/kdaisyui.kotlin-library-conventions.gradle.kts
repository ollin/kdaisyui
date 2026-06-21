import org.gradle.api.artifacts.VersionCatalogsExtension
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
