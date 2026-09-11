plugins {
    id("kdaisyui.kotlin-library-conventions")
    `maven-publish`
    alias(libs.plugins.kover)
    alias(libs.plugins.pitest)
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
        getByName<JvmTestSuite>("test") {
            useKotlinTest(libs.versions.kotlin.get())
        }
    }
}

// --- Mutation testing (PIT) ---
// Coverage says a line ran; mutation testing says an assertion would have noticed if it
// ran differently. Deliberately NOT bound to `check`: it forks a JVM per mutant and is far
// too slow for the inner loop — it runs as `:lib:pitest`, and in CI as its own job.
//
// The scope starts at one class on purpose. Mutating all 63 generated components would
// take hours and measure the generator rather than the code anyone wrote; it is widened
// deliberately in a later step. No `mutationThreshold` yet — the gate is sharpened only
// once the score is known to be 100%, so this stage cannot fail the build.
pitest {
    // A wildcard, not a list of names. Kotlin compiles top-level functions into a
    // `<File>Kt` class and `by lazy` into synthetic ones, so a hand-maintained list would
    // silently shrink whenever the compiler's output shape changed — and PIT reports a
    // filter that matches nothing as a clean run, never as an error.
    targetClasses.set(
        setOf(
            "io.github.ollin.kdaisyui.core.*",
            // Five of the 63 components, named individually rather than by wildcard:
            // `components.*` would mutate the whole generated surface for hours and would
            // mostly measure the generator, which emits one shape repeatedly. These five
            // carry the most conditionals, so they are where "the line ran" and "the right
            // class was emitted" are most likely to come apart.
            //
            // Verified against lib/build/classes — the `...Kt` suffix is the compiled name
            // for a file of top-level functions, and the sibling `ButtonVariant` /
            // `ButtonSize` enums are separate classes deliberately left out of scope.
            "io.github.ollin.kdaisyui.components.ButtonKt", // 18 conditionals, 2 enums, 10 flags
            "io.github.ollin.kdaisyui.components.ModalKt", // 23 across 7 functions
            "io.github.ollin.kdaisyui.components.DropdownKt", // 12, 10 positional flags
            "io.github.ollin.kdaisyui.components.TooltipKt", // 11, 8 positional flags
            "io.github.ollin.kdaisyui.components.RangeKt", // 10, and 4 nullable value params
        )
    )
    // PIT drives the suite through the JUnit Platform, which is what `useKotlinTest`
    // produces here; without this bridge it finds zero tests and reports every mutant
    // as surviving — an all-green-looking report that means nothing.
    // The whole suite, deliberately wider than `targetClasses`. PIT otherwise defaults this
    // to `targetClasses`, and any test outside that package then counts as non-existent:
    // the core helpers are exercised mostly by the component tests, so a `core.*` filter
    // here reported 8 mutants as uncovered in code Kover measures at 100%.
    targetTests.set(setOf("io.github.ollin.kdaisyui.*"))
    junit5PluginVersion.set(libs.versions.pitest.junit5.get())
    // Kotlin emits null-check calls into `kotlin.jvm.internal` on every parameter.
    // Mutating them yields mutants no test can meaningfully kill.
    avoidCallsTo.set(setOf("kotlin.jvm.internal"))
    // The same exclusion the Kover config carries, for the same reason and with the same
    // justification: `$DefaultImpls` holds only binary-compatibility bridge stubs for
    // interface defaults, reachable solely from consumers compiled against the legacy ABI.
    // No source-level test can call them — a `super<HtmlId>.target` super-call, which IS
    // tested, routes to the interface default directly.
    //
    // Safe as a CLASS exclusion because that class holds nothing else. The sibling bridges
    // that Kotlin emits into `AnnotatedIdBase` and `StringHtmlId` cannot be excluded the
    // same way: those classes also hold real, killed mutants, and `excludedMethods` matches
    // on name alone — "getTarget" would take out `HtmlId::getTarget`, the one place the
    // real logic lives, which IS killed.
    excludedClasses.set(setOf("*${'$'}DefaultImpls"))
    // XML alongside the HTML report: the surviving-mutant list is read mechanically in
    // the next step, and parsing HTML for it would be its own small disaster.
    outputFormats.set(setOf("HTML", "XML"))
    // TEST STRENGTH, not mutation score, and 100 is the real number rather than a
    // concession. Test strength is killed / (killed + survived): it ignores mutants no test
    // covers, which here is exactly four Kotlin-emitted interface-default bridges in
    // `AnnotatedIdBase` and `StringHtmlId` that NO source-level test can call.
    //
    // `mutationThreshold` cannot reach 100 on this scope because of those four, and PIT
    // offers no surgical way to drop them: `excludedMethods` matches by name with no class
    // qualifier, so excluding `getTarget` would also delete the mutant on
    // `HtmlId::getTarget` — the one place the real logic lives, and one that IS killed.
    // Removing the bridges outright means `-Xjvm-default=no-compatibility`, an ABI change
    // to a published artifact, already rejected in the Kover config for the same reason.
    //
    // Read this together with the Kover gate in the root build. The pair says something
    // precise and non-overlapping: everything reachable is EXECUTED (Kover, 100% line and
    // branch), and everything executed is ASSERTED (here, 100% test strength).
    testStrengthThreshold.set(100)
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

// Deliberately NOT wired into `check`, and deliberately in the `codegen` group rather than
// `verification`: `AGENTS.md` promises a clone builds and tests with no Node, no npm and no
// submodules. Only regeneration may need them, and this task is part of that world. CI runs
// it as its own job, next to `generated-sources-drift`.
//
// `node --test` needs no dependency — the runner ships with the Node pinned in
// `.tool-versions`, which keeps `codegen/package.json` free of dependencies.
tasks.register<Exec>("testCodegen") {
    group = "codegen"
    description = "Run the codegen unit tests (needs Node; not part of `check`)"
    workingDir = rootProject.file("codegen")
    // Delegates to the npm script rather than repeating `node --test test/`, so the
    // invocation is defined once. CI runs the same `npm test`, and a change to one cannot
    // leave the other behind.
    //
    // The `test/` argument in that script is load-bearing: the runner's default patterns
    // include `**/test-*.js`, which matches `src/test-generator.js` and
    // `src/test-generator-heroicons.js`, so a bare `node --test` EXECUTES both generators
    // as if they were test files.
    commandLine("sh", "-c", "npm test")
    inputs.dir(rootProject.file("codegen/src"))
    inputs.dir(rootProject.file("codegen/test"))
    inputs.file(rootProject.file("codegen/package.json"))
    // No declared output, so Gradle must never call this up-to-date and skip it.
    outputs.upToDateWhen { false }
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
val sourcesJar = tasks.register<Jar>("sourcesJar") {
    archiveClassifier.set("sources")
    from(sourceSets.main.get().allSource)
}

// Javadoc JAR for Maven Central (empty for Kotlin, but required)
val javadocJar = tasks.register<Jar>("javadocJar") {
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
