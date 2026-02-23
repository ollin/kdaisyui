import java.util.Properties

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

val parentProps = file("../gradle.properties").inputStream().use {
    Properties().apply { load(it) }
}

fun getVersion(key: String): String =
    parentProps.getProperty(key) ?: error("Version '$key' not found in gradle.properties")

dependencies {
    implementation("org.jetbrains.kotlin.jvm:org.jetbrains.kotlin.jvm.gradle.plugin:${getVersion("versions.kotlin")}")
}
