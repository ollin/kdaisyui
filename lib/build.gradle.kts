plugins {
    id("kdaisyui.kotlin-library-conventions")
    `maven-publish`
}

version = project.findProperty("version")?.toString() ?: "0.0.1-SNAPSHOT"
group = "io.github.ollin.kdaisyui"

base.archivesName.set("kdaisyui")

repositories {
    mavenCentral()
}

// --- Generated sources from DaisyUI codegen ---

val generatedMainDir = layout.buildDirectory.dir("generated/sources/kotlin/main")
val generatedTestDir = layout.buildDirectory.dir("generated/sources/kotlin/test")

sourceSets {
    main {
        kotlin.srcDir(generatedMainDir)
    }
    test {
        kotlin.srcDir(generatedTestDir)
    }
}

dependencies {
    api("org.jetbrains.kotlinx:kotlinx-html-jvm:${project.property("versions.kotlinx-html")}")
}

testing {
    suites {
        val test by getting(JvmTestSuite::class) {
            useKotlinTest(project.property("versions.kotlin").toString())
        }
    }
}

val generateComponents = tasks.register<Exec>("generateComponents") {
    group = "codegen"
    description = "Regenerate Kotlin components from DaisyUI source (git submodule)"
    workingDir = rootProject.file("codegen")
    val outputDir = generatedMainDir.map { it.dir("io/github/ollin/kdaisyui/components") }
    doFirst { outputDir.get().asFile.mkdirs() }
    commandLine("sh", "-c", "npm install --silent && node src/index-new.js --output-dir=\"${outputDir.get().asFile.absolutePath}\"")
    inputs.dir(rootProject.file("codegen/src"))
    inputs.dir(rootProject.file("daisyui/packages/docs"))
    inputs.file(rootProject.file("codegen/package.json"))
    inputs.file(rootProject.file("codegen/codegen-config.json"))
    outputs.dir(outputDir)
}

val generateComponentTests = tasks.register<Exec>("generateComponentTests") {
    group = "codegen"
    description = "Regenerate Kotlin component tests from DaisyUI source (git submodule)"
    workingDir = rootProject.file("codegen")
    val outputDir = generatedTestDir.map { it.dir("io/github/ollin/kdaisyui/components") }
    doFirst { outputDir.get().asFile.mkdirs() }
    commandLine("sh", "-c", "node src/test-generator.js all --output-dir=\"${outputDir.get().asFile.absolutePath}\"")
    dependsOn(generateComponents)
    inputs.dir(rootProject.file("codegen/src"))
    inputs.dir(rootProject.file("daisyui/packages/docs"))
    inputs.file(rootProject.file("codegen/package.json"))
    inputs.file(rootProject.file("codegen/codegen-config.json"))
    outputs.dir(outputDir)
}

tasks.named("compileKotlin") {
    dependsOn(generateComponents)
}

tasks.named("compileTestKotlin") {
    dependsOn(generateComponentTests)
}

// Sources JAR for Maven Central
val sourcesJar by tasks.registering(Jar::class) {
    dependsOn(tasks.named("generateComponents"))
    archiveClassifier.set("sources")
    from(sourceSets.main.get().allSource)
}

// Javadoc JAR for Maven Central (empty for Kotlin, but required)
val javadocJar by tasks.registering(Jar::class) {
    archiveClassifier.set("javadoc")
    dependsOn(tasks.javadoc)
    from(tasks.javadoc.map { it.outputs.files })
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
            artifactId = "kdaisyui"
            
            // Attach sources and javadoc JARs
            artifact(sourcesJar.get())
            artifact(javadocJar.get())
            
            // POM metadata required for Maven Central
            pom {
                name.set("kdaisyui")
                description.set("Type-safe DaisyUI component DSL for Kotlin server-rendered HTML")
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
