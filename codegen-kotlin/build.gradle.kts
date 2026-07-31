// PROBE — openspec/changes/probe-kotlin-emitter. Parallel Kotlin generator, running
// beside codegen/src/*.js. Nothing in the build depends on this; it is discardable by
// deleting this directory and the include line in settings.gradle.kts.
//
// Deliberately does NOT use kdaisyui.kotlin-library-conventions: a probe should not
// couple itself to buildSrc, and -Xreturn-value-checker=full is not a constraint we
// want to fight while finding out whether the idea works at all.

import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    kotlin("jvm")
    kotlin("plugin.serialization")
    application
}

repositories {
    mavenCentral()
}

kotlin {
    jvmToolchain(21)
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_21)
    }
}

dependencies {
    implementation("com.squareup:kotlinpoet:2.3.0")
    implementation("com.charleskorn.kaml:kaml:0.104.0")
    testImplementation(kotlin("test", project.property("versions.kotlin").toString()))
}

application {
    mainClass.set("io.github.ollin.kdaisyui.codegen.MainKt")
}

tasks.test {
    useJUnitPlatform()
}

// Paths in Main.kt are repo-relative, like the JS generator's.
tasks.named<JavaExec>("run") {
    workingDir = rootDir
}
