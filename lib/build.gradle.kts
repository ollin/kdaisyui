plugins {
    id("kdaisyui.kotlin-library-conventions")
    `maven-publish`
    alias(libs.plugins.kover)
}

group = "io.github.ollin.kdaisyui"

base.archivesName.set("kdaisyui")

// --- DaisyUI submodule tag checkout ---

val daisyuiVersion = libs.versions.daisyui.get()
val heroiconsVersion = libs.versions.heroicons.get()

// --- JAR manifest: shared attributes come from the convention plugin; only the
// DaisyUI version is module-specific to lib. ---

tasks.withType<Jar> {
    manifest {
        attributes("DaisyUI-Version" to daisyuiVersion)
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

// Generated resources, shipped in the jar. Currently the Tailwind class list, which is
// the only way a consumer's CSS build can learn the class names this library emits —
// they are assembled from enum values at runtime and the jar carries no Kotlin sources.
val generatedResourcesDir = layout.projectDirectory.dir("generated/main/resources")

sourceSets {
    main {
        kotlin.srcDir(generatedMainDir)
        resources.srcDir(generatedResourcesDir)
    }
    test {
        kotlin.srcDir(generatedTestDir)
    }
}

// --- Public API baseline ---
// The API is generated from DaisyUI, so nobody decides to remove an enum entry —
// a dependency bump carries it in, and releases are automated from a v* tag. This
// makes the surface a committed artefact: `lib/api/lib.api`, rewritten only by
// `updateKotlinAbi`. checkKotlinAbi joins `check` on its own and fails when the
// two diverge. Kotlin's own DSL, so no extra plugin; experimental, so opted in.
@OptIn(org.jetbrains.kotlin.gradle.dsl.abi.ExperimentalAbiValidation::class)
kotlin {
    abiValidation {
    }
}

dependencies {
    api(libs.kotlinx.html.jvm)
}

testing {
    suites {
        val test by getting(JvmTestSuite::class) {
            useKotlinTest(libs.versions.kotlin.get())
        }
    }
}

val generateComponents = tasks.register<Exec>("generateComponents") {
    group = "codegen"
    description = "Regenerate Kotlin components from DaisyUI source (git submodule)"
    dependsOn(checkoutDaisyuiTag)
    workingDir = rootProject.file("codegen")
    val outputDir = generatedMainDir.dir("io/github/ollin/kdaisyui/components")
    val classList = generatedResourcesDir.file("kdaisyui-classes.txt")
    doFirst { outputDir.asFile.mkdirs() }
    // No `npm install`: the codegen declares no dependencies, so it installed nothing and
    // only cost a network round-trip. Regeneration now works offline. Add it back here and
    // in the other two generator tasks if a dependency is ever introduced.
    commandLine(
        "sh", "-c",
        "node src/index-new.js --output-dir=\"${outputDir.asFile.absolutePath}\"" +
            " --class-list=\"${classList.asFile.absolutePath}\""
    )
    inputs.dir(rootProject.file("codegen/src"))
    inputs.dir(rootProject.file("daisyui/packages/docs"))
    inputs.file(rootProject.file("codegen/package.json"))
    inputs.file(rootProject.file("codegen/codegen-config.json"))
    outputs.dir(outputDir)
    outputs.file(classList)
}

val generateComponentTests = tasks.register<Exec>("generateComponentTests") {
    group = "codegen"
    description = "Regenerate Kotlin component tests from DaisyUI source (git submodule)"
    dependsOn(checkoutDaisyuiTag)
    workingDir = rootProject.file("codegen")
    val outputDir = generatedTestDir.dir("io/github/ollin/kdaisyui/components")
    // The coverage tests are produced by reading the generated components back, so
    // this task needs both paths. Passing the input path keeps this file the single
    // source of it — a second copy inside the generator once went stale unnoticed.
    val componentsDir = generatedMainDir.dir("io/github/ollin/kdaisyui/components")
    doFirst { outputDir.asFile.mkdirs() }
    commandLine("sh", "-c", "node src/test-generator.js all --output-dir=\"${outputDir.asFile.absolutePath}\" --components-dir=\"${componentsDir.asFile.absolutePath}\"")
    dependsOn(generateComponents)
    inputs.dir(componentsDir)
    inputs.dir(rootProject.file("codegen/src"))
    inputs.dir(rootProject.file("daisyui/packages/docs"))
    inputs.file(rootProject.file("codegen/package.json"))
    inputs.file(rootProject.file("codegen/codegen-config.json"))
    outputs.dir(outputDir)
}

val generateHeroiconTests = tasks.register<Exec>("generateHeroiconTests") {
    group = "codegen"
    description = "Regenerate exhaustive Kotlin icon render tests from Heroicons SVG source (git submodule)"
    dependsOn(checkoutHeroiconsTag)
    workingDir = rootProject.file("codegen")
    val outputDir = generatedTestDir.dir("io/github/ollin/kdaisyui/icons")
    doFirst { outputDir.asFile.mkdirs() }
    commandLine("sh", "-c", "node src/test-generator-heroicons.js --output-dir=\"${outputDir.asFile.absolutePath}\"")
    inputs.dir(rootProject.file("codegen/src"))
    inputs.dir(rootProject.file("heroicons/src"))
    inputs.file(rootProject.file("codegen/package.json"))
    outputs.dir(outputDir)
}

val generateHeroicons = tasks.register<Exec>("generateHeroicons") {
    group = "codegen"
    description = "Regenerate Kotlin icon functions from Heroicons SVG source (git submodule)"
    dependsOn(checkoutHeroiconsTag)
    workingDir = rootProject.file("codegen")
    val outputDir = generatedMainDir.dir("io/github/ollin/kdaisyui/icons")
    doFirst { outputDir.asFile.mkdirs() }
    commandLine("sh", "-c", "node src/index-heroicons.js --output-dir=\"${outputDir.asFile.absolutePath}\"")
    inputs.dir(rootProject.file("codegen/src"))
    inputs.dir(rootProject.file("heroicons/src"))
    inputs.file(rootProject.file("codegen/package.json"))
    outputs.dir(outputDir)
}

// Compilation deliberately does NOT depend on the generators. The generated
// sources are committed, so a clone builds and tests with no Node, no npm and
// no git submodules. Regeneration is explicit — `just generate` — and CI's
// generated-sources-drift job is what keeps the committed output honest.

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
