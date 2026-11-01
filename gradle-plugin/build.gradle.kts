// Narrows kdaisyui's class list to the components a build's compiled code uses. Published to Maven
// Central with its plugin marker; the root build stages both.
plugins {
    `kotlin-dsl`
    `maven-publish`
    alias(libs.plugins.kover)
}

group = "io.github.ollin.kdaisyui"
// The release passes -Pversion, which reaches included builds too; locally the root's
// gradle.properties says which version this is, as it does for the other artifacts.
version = providers.gradleProperty("version").orNull?.takeUnless { it == "unspecified" }
    ?: file("../gradle.properties").readLines().first { it.startsWith("version=") }.substringAfter("=")

java {
    withSourcesJar()
    withJavadocJar()
}

// Both publications, the plugin and its marker, need the POM Maven Central checks; JReleaser's dry
// run rejects either without it.
publishing {
    publications.withType<MavenPublication>().configureEach {
        pom {
            name.set("kdaisyui-gradle-plugin")
            description.set("Narrows kdaisyui's class list to the components a build uses")
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
    repositories {
        maven {
            name = "staging"
            // The root build's staging directory, where JReleaser picks every artifact up.
            url = layout.projectDirectory.dir("../build/staging-deploy").asFile.toURI()
        }
    }
}

gradlePlugin {
    plugins {
        create("kdaisyui") {
            id = "io.github.ollin.kdaisyui"
            implementationClass = "io.github.ollin.kdaisyui.gradle.KdaisyuiPlugin"
        }
    }
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test { useJUnitPlatform() }

// Its own 100% line and branch gate: the root build's aggregated one cannot reach an included build.
kover {
    reports {
        verify {
            rule("100% line coverage") { bound { minValue = 100; coverageUnits = kotlinx.kover.gradle.plugin.dsl.CoverageUnit.LINE } }
            rule("100% branch coverage") { bound { minValue = 100; coverageUnits = kotlinx.kover.gradle.plugin.dsl.CoverageUnit.BRANCH } }
        }
    }
}

tasks.check { dependsOn(tasks.named("koverVerify")) }
