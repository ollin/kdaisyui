import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    kotlin("jvm")
    `java-library`
}

repositories {
    mavenCentral()
}

kotlin {
    jvmToolchain(25)
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_25)
    }
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(25))
    }
}

testing {
    suites {
        val test by getting(JvmTestSuite::class) {
            useKotlinTest(project.property("versions.kotlin").toString())
        }
    }
}
