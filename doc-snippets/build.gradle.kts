// Compiles the Kotlin snippets of the prose documentation (README.md, docs/), so a snippet that
// names a function, parameter or receiver the library no longer has fails the build. The main
// source set is the extractor that turns the Markdown into Kotlin files. Not published.
plugins {
    id("kdaisyui.kotlin-library-conventions")
    alias(libs.plugins.kover)
}

// Every document whose Kotlin snippets are compiled. A document that is not listed is not
// checked, so a new prose document belongs here.
val documents: List<String> = listOf("README.md", "docs/how-to.md", "docs/explanation.md") +
    rootProject.fileTree("docs/tutorials") { include("*.md") }.files.map { it.relativeTo(rootDir).invariantSeparatorsPath }.sorted()

val snippetsDir = layout.buildDirectory.dir("generated/snippets")

tasks.register<JavaExec>("extractSnippets") {
    description = "Writes one Kotlin file per snippet of the prose documentation."
    classpath = sourceSets.main.get().runtimeClasspath
    mainClass.set("io.github.ollin.kdaisyui.docsnippets.ExtractSnippetsKt")
    inputs.files(documents.map { rootProject.file(it) })
    outputs.dir(snippetsDir)
    args(listOf(rootDir.absolutePath, snippetsDir.get().asFile.absolutePath) + documents)
    // A fence that was removed must take its file with it.
    doFirst { snippetsDir.get().asFile.run { deleteRecursively(); mkdirs() } }
}

val snippets = sourceSets.create("snippets") {
    kotlin.srcDir(tasks.named("extractSnippets"))
}

dependencies {
    "snippetsImplementation"(project(":lib"))
    "snippetsImplementation"(project(":ktor-integration"))
    "snippetsImplementation"(libs.bundles.ktor.server.app)
}

// The snippets are documentation, not code the build relies on: Kover must not measure them,
// or the coverage gate would count every snippet as an untested line. `check` compiles them
// through compileSnippets instead.
kover {
    currentProject {
        sources {
            excludedSourceSets.add(snippets.name)
        }
    }
}

val compileSnippets = tasks.register("compileSnippets") {
    description = "Compiles every Kotlin snippet of the prose documentation."
    group = "verification"
    dependsOn(snippets.classesTaskName)
}

tasks.named("check") { dependsOn(compileSnippets) }
