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

dependencies {
    api(project(":lib"))

    // compileOnly: the consumer owns its Ktor version. As `api` this published a hard
    // requirement that Gradle's conflict resolution let win over the consumer's own - and the
    // public inline functions inline Ktor's `href` into the caller, so the caller needs
    // ktor-server-resources on its compile classpath regardless. A missing one fails there.
    //
    // No webjars: nothing here serves or references an asset. Exporting htmx, Tailwind and
    // DaisyUI upgraded a consumer pinned to htmx 2.x to 4.x without a compile error.
    compileOnly(libs.ktor.server.resources)
}

testing {
    suites {
        getByName<JvmTestSuite>("test") {
            useKotlinTest(libs.versions.kotlin.get())

            dependencies {
                implementation(libs.ktor.server.test.host)
                implementation(libs.ktor.server.core)
                implementation(libs.ktor.server.resources)
            }
        }
    }
}

val sourcesJar = tasks.register<Jar>("sourcesJar") {
    archiveClassifier.set("sources")
    from(sourceSets.main.get().allSource)
}

val javadocJar = tasks.register<Jar>("javadocJar") {
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