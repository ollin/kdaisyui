plugins {
    `java-platform`
    `maven-publish`
}

version = project.findProperty("version")?.toString() ?: "0.0.1-SNAPSHOT"
group = "io.github.ollin.kdaisyui"

dependencies {
    constraints {
        api(project(":lib"))
        api(project(":ktor-integration"))
    }
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["javaPlatform"])
            artifactId = "kdaisyui-bom"

            pom {
                name.set("kdaisyui-bom")
                description.set("Bill of Materials for kdaisyui: aligns kdaisyui and kdaisyui-ktor-integration versions")
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
