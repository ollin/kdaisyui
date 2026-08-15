plugins {
    id("kdaisyui.kotlin-library-conventions")
    `maven-publish`
}

version = project.findProperty("version")?.toString() ?: "0.0.1-SNAPSHOT"
group = "io.github.ollin.kdaisyui"

base.archivesName.set("kdaisyui")

// --- DaisyUI submodule tag checkout ---

val daisyuiVersion = project.property("daisyui.version").toString()
val heroiconsVersion = project.property("heroicons.version").toString()

// --- Git hash for manifest (lazy, configuration-cache safe) ---

val gitHash: Provider<String> = providers.of(GitHashValueSource::class) {
    parameters.rootDir.set(rootProject.layout.projectDirectory)
}

abstract class GitHashValueSource : ValueSource<String, GitHashValueSource.Parameters> {
    interface Parameters : ValueSourceParameters {
        val rootDir: DirectoryProperty
    }

    override fun obtain(): String {
        val dir = parameters.rootDir.get().asFile
        val process = ProcessBuilder("git", "rev-parse", "HEAD")
            .directory(dir)
            .start()
        return process.inputStream.bufferedReader().readLine()?.trim() ?: "unknown"
    }
}

// --- JAR manifest attributes ---

tasks.withType<Jar> {
    manifest {
        attributes(
            "Implementation-Title" to "io.github.ollin.kdaisyui:kdaisyui",
            "Implementation-Version" to project.version,
            "Implementation-Vendor" to "ollin",
            "Implementation-URL" to "https://github.com/ollin/kdaisyui",
            "SCM-Revision" to gitHash,
            "DaisyUI-Version" to daisyuiVersion,
            "Kotlin-Version" to project.property("versions.kotlin").toString(),
            "Created-By" to "Gradle ${gradle.gradleVersion}",
        )
    }
}

repositories {
    mavenCentral()
}

abstract class CheckoutDaisyuiTag : DefaultTask() {
    @get:Inject
    abstract val execOperations: ExecOperations

    @get:Input
    abstract val targetTag: Property<String>

    @get:InputDirectory
    abstract val daisyuiDir: DirectoryProperty

    @TaskAction
    fun checkout() {
        val tag = targetTag.get()
        logger.lifecycle("Ensuring DaisyUI submodule is on tag $tag...")
        execOperations.exec {
            workingDir = daisyuiDir.get().asFile
            commandLine("git", "fetch", "--tags", "--quiet")
        }
        execOperations.exec {
            workingDir = daisyuiDir.get().asFile
            commandLine("git", "checkout", tag, "--quiet")
        }
        logger.lifecycle("DaisyUI submodule is on tag $tag")
    }
}

val checkoutDaisyuiTag = tasks.register<CheckoutDaisyuiTag>("checkoutDaisyuiTag") {
    group = "daisyui"
    description = "Checkout DaisyUI git submodule to tag v$daisyuiVersion"
    targetTag.set("v$daisyuiVersion")
    daisyuiDir.set(rootProject.layout.projectDirectory.dir("daisyui"))
}

abstract class CheckoutHeroiconsTag : DefaultTask() {
    @get:Inject
    abstract val execOperations: ExecOperations

    @get:Input
    abstract val targetTag: Property<String>

    @get:InputDirectory
    abstract val heroiconsDir: DirectoryProperty

    @TaskAction
    fun checkout() {
        val tag = targetTag.get()
        logger.lifecycle("Ensuring Heroicons submodule is on tag $tag...")
        execOperations.exec {
            workingDir = heroiconsDir.get().asFile
            commandLine("git", "fetch", "--tags", "--quiet")
        }
        execOperations.exec {
            workingDir = heroiconsDir.get().asFile
            commandLine("git", "checkout", tag, "--quiet")
        }
        logger.lifecycle("Heroicons submodule is on tag $tag")
    }
}

val checkoutHeroiconsTag = tasks.register<CheckoutHeroiconsTag>("checkoutHeroiconsTag") {
    group = "heroicons"
    description = "Checkout Heroicons git submodule to tag v$heroiconsVersion"
    targetTag.set("v$heroiconsVersion")
    heroiconsDir.set(rootProject.layout.projectDirectory.dir("heroicons"))
}

// --- Generated sources from DaisyUI codegen ---
// Committed, not build output: a sibling of src/ so generated and hand-written
// Kotlin never share a tree. Regeneration rewrites these directories; the diff
// then shows in git status. Never edit them by hand.

val generatedMainDir = layout.projectDirectory.dir("generated/main/kotlin")
val generatedTestDir = layout.projectDirectory.dir("generated/test/kotlin")

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
    dependsOn(checkoutDaisyuiTag)
    workingDir = rootProject.file("codegen")
    val outputDir = generatedMainDir.dir("io/github/ollin/kdaisyui/components")
    doFirst { outputDir.asFile.mkdirs() }
    commandLine("sh", "-c", "npm install --silent && node src/index-new.js --output-dir=\"${outputDir.asFile.absolutePath}\"")
    inputs.dir(rootProject.file("codegen/src"))
    inputs.dir(rootProject.file("daisyui/packages/docs"))
    inputs.file(rootProject.file("codegen/package.json"))
    inputs.file(rootProject.file("codegen/codegen-config.json"))
    outputs.dir(outputDir)
}

val generateComponentTests = tasks.register<Exec>("generateComponentTests") {
    group = "codegen"
    description = "Regenerate Kotlin component tests from DaisyUI source (git submodule)"
    dependsOn(checkoutDaisyuiTag)
    workingDir = rootProject.file("codegen")
    val outputDir = generatedTestDir.dir("io/github/ollin/kdaisyui/components")
    doFirst { outputDir.asFile.mkdirs() }
    commandLine("sh", "-c", "node src/test-generator.js all --output-dir=\"${outputDir.asFile.absolutePath}\"")
    dependsOn(generateComponents)
    inputs.dir(rootProject.file("codegen/src"))
    inputs.dir(rootProject.file("daisyui/packages/docs"))
    inputs.file(rootProject.file("codegen/package.json"))
    inputs.file(rootProject.file("codegen/codegen-config.json"))
    outputs.dir(outputDir)
}

val generateHeroicons = tasks.register<Exec>("generateHeroicons") {
    group = "codegen"
    description = "Regenerate Kotlin icon functions from Heroicons SVG source (git submodule)"
    dependsOn(checkoutHeroiconsTag)
    workingDir = rootProject.file("codegen")
    val outputDir = generatedMainDir.dir("io/github/ollin/kdaisyui/icons")
    doFirst { outputDir.asFile.mkdirs() }
    commandLine("sh", "-c", "npm install --silent && node src/index-heroicons.js --output-dir=\"${outputDir.asFile.absolutePath}\"")
    inputs.dir(rootProject.file("codegen/src"))
    inputs.dir(rootProject.file("heroicons/src"))
    inputs.file(rootProject.file("codegen/package.json"))
    outputs.dir(outputDir)
}

// Compilation deliberately does NOT depend on the generators. The generated
// sources are committed, so a clone builds and tests with no Node, no npm and
// no git submodules. Regeneration is explicit — `just generate` — and CI's
// drift check is what keeps the committed output honest.

// Sources JAR for Maven Central
val sourcesJar by tasks.registering(Jar::class) {
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
