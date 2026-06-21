plugins {
    id("kdaisyui.kotlin-library-conventions")
    kotlin("plugin.serialization")
    application
}

application {
    mainClass.set("kdaisyui.example.ApplicationKt")
}

kotlin {
    compilerOptions {
        freeCompilerArgs.add("-Xcontext-parameters")
    }
}

dependencies {
    implementation(project(":lib"))
    implementation(project(":ktor-integration"))

    implementation(libs.bundles.ktor.server.app)

    // Webjar assets (daisyui, tailwind, htmx) come transitively from :ktor-integration.

    implementation(libs.logback.classic)
}
