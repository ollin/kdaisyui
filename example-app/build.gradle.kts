plugins {
    id("kdaisyui.kotlin-library-conventions")
    kotlin("plugin.serialization")
    application
    // This repository's own plugin: the stylesheet below covers only the components the app uses.
    id("io.github.ollin.kdaisyui")
}

application {
    mainClass.set("kdaisyui.example.ApplicationKt")
}

// The plugin compiles the stylesheet in a Node container, with the Tailwind and DaisyUI the components
// were generated against; its sources default to src/main/kotlin.
kdaisyui {
    stylesheet {
        entry.set(layout.projectDirectory.file("src/main/css/app.css"))
    }
    // The example is what consumers copy: a DaisyUI class written as a string fails its build.
    rawClasses {
        failBuild.set(true)
    }
}

// The compiled stylesheet ships as a classpath resource, so the running application
// serves it the same way in the IDE, from `just dev` and from a distribution.
tasks.named<ProcessResources>("processResources") {
    from(tasks.named("kdaisyuiStylesheet")) { into("static") }
}

dependencies {
    implementation(project(":lib"))
    implementation(project(":ktor-integration"))

    implementation(libs.bundles.ktor.server.app)

    // htmx is the only asset served from a webjar; the stylesheet is compiled above.
    implementation(libs.webjar.htmx)

    implementation(libs.logback.classic)
}
