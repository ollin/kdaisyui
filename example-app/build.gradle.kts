// Explicit: inside the Kotlin DSL, `java` resolves to the JavaPluginExtension accessor,
// so a fully qualified `java.nio.file.Files` does not compile.
import java.nio.file.Files

plugins {
    id("kdaisyui.kotlin-library-conventions")
    kotlin("plugin.serialization")
    application
}

application {
    mainClass.set("kdaisyui.example.ApplicationKt")
}

kotlin {
    compilerOptions {
        freeCompilerArgs.add("-Xcontext-parameters")
    }
}

// --- Tailwind CSS, compiled at build time in a container ---
//
// The prebuilt daisyui webjar ships only five variant prefixes (sm, md, lg, xl, hover),
// so max-*, dark: and focus: variants of DaisyUI classes silently do nothing. Compiling
// produces every variant the sources actually use — and a 23 KB stylesheet instead of
// 1.1 MB, because a compile emits only what is reachable.
//
// Docker, not Node: this keeps the host requirement off :lib and :ktor-integration.

val nodeVersion = providers.provider {
    file("${rootDir}/.tool-versions").readLines()
        .first { it.startsWith("nodejs ") }
        .substringAfter("nodejs ")
        .trim()
}

val tailwindImageTag = "kdaisyui-tailwind:${libs.versions.webjar.tailwindcss.browser.get()}"

val buildTailwindImage = tasks.register<Exec>("buildTailwindImage") {
    description = "Builds the container image that compiles the example app's CSS."
    inputs.file(layout.projectDirectory.file("tailwind/Dockerfile"))
    inputs.property("node", nodeVersion)
    inputs.property("tailwind", libs.versions.webjar.tailwindcss.browser.get())
    inputs.property("daisyui", libs.versions.daisyui.get())
    // Docker's own layer cache makes a rebuild cheap; this marker keeps Gradle honest.
    outputs.file(layout.buildDirectory.file("tailwind/image-id.txt"))

    commandLine(
        "docker", "build",
        "--build-arg", "NODE_VERSION=${nodeVersion.get()}",
        "--build-arg", "TAILWIND_VERSION=${libs.versions.webjar.tailwindcss.browser.get()}",
        "--build-arg", "DAISYUI_VERSION=${libs.versions.daisyui.get()}",
        "--iidfile", layout.buildDirectory.file("tailwind/image-id.txt").get().asFile.absolutePath,
        "-t", tailwindImageTag,
        layout.projectDirectory.dir("tailwind").asFile.absolutePath,
    )
    doFirst { layout.buildDirectory.dir("tailwind").get().asFile.mkdirs() }
}

val compileTailwind = tasks.register<Exec>("compileTailwind") {
    description = "Compiles the example app's stylesheet from its Kotlin sources."
    dependsOn(buildTailwindImage)

    val appKotlin = layout.projectDirectory.dir("src/main/kotlin")
    val libKotlin = rootProject.layout.projectDirectory.dir("lib/generated/main/kotlin")
    val cssDir = layout.projectDirectory.dir("src/main/css")
    val outDir = layout.buildDirectory.dir("tailwind/out")

    inputs.dir(appKotlin)
    inputs.dir(libKotlin)
    inputs.dir(cssDir)
    outputs.file(outDir.map { it.file("app.css") })

    // Mount points rather than repository paths, so app.css can name them flatly and
    // survives a directory move.
    commandLine(
        listOf("docker", "run", "--rm") +
            callingUserArgs() +
            listOf(
                "-v", "${cssDir.asFile.absolutePath}:/deps/project/css:ro",
                "-v", "${appKotlin.asFile.absolutePath}:/deps/project/app-kotlin:ro",
                "-v", "${libKotlin.asFile.absolutePath}:/deps/project/lib-kotlin:ro",
                "-v", "${outDir.get().asFile.absolutePath}:/deps/project/out",
                tailwindImageTag,
                "--input", "css/app.css",
                "--output", "out/app.css",
            )
    )
    doFirst { outDir.get().asFile.mkdirs() }
}

/**
 * `--user` on Unix only, and read from the filesystem rather than by running `id`.
 *
 * Unix needs it: a default `docker run` writes output as root, which leaves root-owned
 * files in `build/` and breaks a later `./gradlew clean`. The failure is delayed and
 * confusing — the first run succeeds and the *next* one cannot overwrite what it left.
 *
 * Windows must not get it: there is no `id` command, and Docker Desktop already writes
 * as the calling user, so the flag would be both unavailable and pointless.
 */
// The compiled stylesheet ships as a classpath resource, so the running application
// serves it the same way in the IDE, from `just dev` and from a distribution.
tasks.named<ProcessResources>("processResources") {
    from(compileTailwind) { into("static") }
}

fun callingUserArgs(): List<String> {
    if (System.getProperty("os.name").startsWith("Windows", ignoreCase = true)) return emptyList()
    val path = projectDir.toPath()
    val uid = Files.getAttribute(path, "unix:uid")
    val gid = Files.getAttribute(path, "unix:gid")
    return listOf("-u", "$uid:$gid")
}

dependencies {
    implementation(project(":lib"))
    implementation(project(":ktor-integration"))

    implementation(libs.bundles.ktor.server.app)

    // Webjar assets (daisyui, tailwind, htmx) come transitively from :ktor-integration.

    implementation(libs.logback.classic)
}
