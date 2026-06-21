plugins {
    `java-platform`
    `maven-publish`
}

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
