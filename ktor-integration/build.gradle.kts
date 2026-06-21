plugins {
    id("kdaisyui.kotlin-library-conventions")
    `maven-publish`
    alias(libs.plugins.kover)
    kotlin("plugin.serialization")
}

group = "io.github.ollin.kdaisyui"

base.archivesName.set("kdaisyui-ktor-integration")

repositories {
    mavenCentral()
}

kotlin {
    compilerOptions {
        freeCompilerArgs.add("-Xcontext-parameters")
    }
}

dependencies {
    api(project(":lib"))

    api(libs.ktor.server.resources)

    // DaisyUI CSS + Tailwind + htmx assets, served via Ktor's Webjars plugin.
    // Exposed transitively so Ktor consumers get the matching asset versions.
    api(libs.ktor.server.webjars)
    api(libs.bundles.webjars)
}

testing {
    suites {
        val test by getting(JvmTestSuite::class) {
            useKotlinTest(libs.versions.kotlin.get())

            dependencies {
                implementation(libs.ktor.server.test.host)
                implementation(libs.ktor.server.core)
                implementation(libs.ktor.server.resources)
            }
        }
    }
}

val sourcesJar by tasks.registering(Jar::class) {
    archiveClassifier.set("sources")
    from(sourceSets.main.get().allSource)
}

val javadocJar by tasks.registering(Jar::class) {
    archiveClassifier.set("javadoc")
    from(tasks.javadoc.map { it.outputs.files })
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
            artifactId = "kdaisyui-ktor-integration"

            artifact(sourcesJar.get())
            artifact(javadocJar.get())

            pom {
                name.set("kdaisyui-ktor-integration")
                description.set("Ktor Resources integration for kdaisyui HtmlId")
                url.set("https://github.com/ollin/kdaisyui")

                licenses {
                    license {
                        name.set("MIT License")
                        url.set("https://opensource.org/licenses/MIT")
                    }
                }

                developers {
                    developer {
                        id.set("ollin")
                        name.set("Oliver Nautsch")
                        email.set("ollin@users.noreply.github.com")
                    }
                }

                scm {
                    connection.set("scm:git:git://github.com/ollin/kdaisyui.git")
                    developerConnection.set("scm:git:ssh://github.com/ollin/kdaisyui.git")
                    url.set("https://github.com/ollin/kdaisyui")
                }
            }
        }
    }
    repositories {
        maven {
            name = "staging"
            url = rootProject.layout.buildDirectory.dir("staging-deploy").get().asFile.toURI()
        }
    }
}