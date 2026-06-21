repositories {
    gradlePluginPortal()
    mavenCentral()
}

plugins {
    `kotlin-dsl`
}

kotlin {
    jvmToolchain(21)
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

dependencies {
    val kotlinVersion = libs.versions.kotlin.get()
    implementation("org.jetbrains.kotlin.jvm:org.jetbrains.kotlin.jvm.gradle.plugin:$kotlinVersion")
    implementation("org.jetbrains.kotlin:kotlin-serialization:$kotlinVersion")
}
