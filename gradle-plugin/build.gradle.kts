// Narrows kdaisyui's class list to the components a build's compiled code uses. Published to Maven
// Central with its plugin marker; the root build stages both.
plugins {
    `kotlin-dsl`
    `maven-publish`
    alias(libs.plugins.kover)
    alias(libs.plugins.pitest)
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
    // The stylesheet compile runs Node in a container reached through the Docker daemon alone: no
    // docker CLI and no Node on the build host (compile-the-stylesheet-in-the-plugin).
    implementation(libs.testcontainers)
    testImplementation(kotlin("test"))
}

tasks.test { useJUnitPlatform() }

// The versions the stylesheet compile installs, from the repository's one place for each: the catalog
// and .tool-versions. Expanded into the jar, so a consumer pins nothing and gets what the components
// were generated against.
tasks.processResources {
    val versions = mapOf(
        "tailwind" to libs.versions.webjar.tailwindcss.browser.get(),
        "daisyui" to libs.versions.daisyui.get(),
        "node" to file("../.tool-versions").readLines().first { it.startsWith("nodejs ") }.substringAfter(" ").trim(),
        "kdaisyui" to version.toString(),
    )
    inputs.properties(versions)
    filesMatching("kdaisyui-versions.properties") { expand(versions) }
}

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

// Mutation testing at 100% test strength, as for every hand-written module; CI's mutation-tests job
// runs it. lib/build.gradle.kts holds why it is test strength and not mutation score.
pitest {
    targetClasses.set(setOf("io.github.ollin.kdaisyui.gradle.*"))
    targetTests.set(setOf("io.github.ollin.kdaisyui.gradle.*"))
    junit5PluginVersion.set(libs.versions.pitest.junit5.get())
    // Calls whose removal no test can observe: Kotlin's parameter null checks; closing the jar the
    // map is read from (nothing on Linux notices a leaked handle); and the fallback's warning, whose
    // reason the written file also states, which is what the tests assert.
    avoidCallsTo.set(setOf("kotlin.jvm.internal", "java.util.zip.ZipFile", "org.gradle.api.logging", "io.github.ollin.kdaisyui.gradle.NodeContainerRun"))
    // NodeContainerRun starts a Node container: each of its mutants, and each mutant removing the call
    // to it, would cost a container start and an npm install. It has no branch, and Kover keeps it in
    // the 100% gate through NodeContainerRunTest, which compiles a real stylesheet. Everything it is
    // handed is pure and mutated (compile-the-stylesheet-in-the-plugin, A11).
    excludedClasses.set(setOf("io.github.ollin.kdaisyui.gradle.NodeContainerRun"))
    outputFormats.set(setOf("HTML", "XML"))
    testStrengthThreshold.set(100)
    // ProjectBuilder injects classes into java.lang; Gradle's own test task opens it for the test
    // JVM, PIT's worker JVM needs it said.
    jvmArgs.set(listOf("--add-opens", "java.base/java.lang=ALL-UNNAMED"))
}
