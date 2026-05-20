plugins {
    id("kdaisyui.kotlin-library-conventions")
    `maven-publish`
}

version = project.findProperty("version")?.toString() ?: "0.0.1-SNAPSHOT"
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

    val ktorVersion = project.property("versions.ktor").toString()
    api("io.ktor:ktor-server-resources:$ktorVersion")
}

testing {
    suites {
        val test by getting(JvmTestSuite::class) {
            useKotlinTest(project.property("versions.kotlin").toString())

            dependencies {
                val ktorVersion = project.property("versions.ktor").toString()
                implementation("io.ktor:ktor-server-test-host:$ktorVersion")
                implementation("io.ktor:ktor-server-core:$ktorVersion")
                implementation("io.ktor:ktor-server-resources:$ktorVersion")
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
                        name.set("ollin")
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
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/ollin/kdaisyui")
            credentials {
                username = System.getenv("GITHUB_ACTOR")
                password = System.getenv("GITHUB_TOKEN")
            }
        }
    }
}