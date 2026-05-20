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

    val ktorVersion = "3.5.0"
    implementation("io.ktor:ktor-server-core:$ktorVersion")
    implementation("io.ktor:ktor-server-netty:$ktorVersion")
    implementation("io.ktor:ktor-server-html-builder:$ktorVersion")
    implementation("io.ktor:ktor-server-webjars:$ktorVersion")
    implementation("io.ktor:ktor-server-status-pages:$ktorVersion")
    implementation("io.ktor:ktor-server-resources:$ktorVersion")

    // Webjars — served at /webjars/{name}/{file}
    implementation("org.webjars.npm:daisyui:5.5.19")
    implementation("org.webjars.npm:tailwindcss__browser:4.2.1")
    implementation("org.webjars.npm:htmx.org:2.0.10")

    implementation("ch.qos.logback:logback-classic:1.5.32")
}
