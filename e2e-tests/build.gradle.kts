plugins {
    kotlin("jvm")
}

repositories {
    mavenCentral()
}

kotlin {
    jvmToolchain(21)
}

// The exclusivity measurement: a browser task, not a test.
//
// Its own source set rather than `test`, because `test` depends on `:example-app`, whose
// stylesheet is compiled in a Docker container. Measuring DaisyUI's own stylesheet must not
// need Docker, so the two share only the module and the Playwright dependency.
val exclusivity: SourceSet by sourceSets.creating

// The compiled DaisyUI stylesheet, taken from the webjar rather than built: it is the same
// artifact `:ktor-integration` serves, so the measurement sees the CSS the library ships
// against. `gradle/libs.versions.toml` remains the single source of the version.
val daisyuiWebjar: Configuration by configurations.creating

dependencies {
    "exclusivityImplementation"(libs.playwright)
    daisyuiWebjar(libs.webjar.daisyui)
    testImplementation(project(":lib"))
    testImplementation(project(":example-app"))
    testImplementation(libs.ktor.server.core)
    testImplementation(libs.ktor.server.netty)
    testImplementation(libs.ktor.server.test.host)

    testImplementation(libs.playwright)

    // Kotest (existing tests)
    testImplementation(libs.kotest.runner.junit5)
    testImplementation(libs.kotest.assertions.core)
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    // Cucumber BDD
    testImplementation(libs.cucumber.java)
    testImplementation(libs.cucumber.junit.platform.engine)
    testImplementation(libs.cucumber.picocontainer)

    testImplementation(libs.junit.platform.suite)
    testRuntimeOnly(libs.junit.platform.suite.engine)

    testImplementation(libs.logback.classic)
}

// The browser Playwright should drive: whatever this machine already has, so neither the tests
// nor the measurement downloads one. Defined once because two tasks now need it — duplicating
// the lookup would let them drift onto different browsers and make a disagreement unreadable.
val systemChromium: Provider<String> =
    providers.environmentVariable("PLAYWRIGHT_CHROMIUM_EXECUTABLE_PATH")
        .orElse(providers.provider {
            listOf("chromium", "chromium-browser", "google-chrome-stable", "google-chrome")
                .firstNotNullOfOrNull { name ->
                    ProcessBuilder("which", name).start()
                        .inputStream.bufferedReader().readLine()?.takeIf { it.isNotBlank() }
                }
        })

tasks.test {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
        showStandardStreams = true
    }
    dependsOn(":example-app:classes")

    // Cucumber naming strategy for disambiguating scenarios in Gradle output
    systemProperty("cucumber.junit-platform.naming-strategy", "long")

    environment("PLAYWRIGHT_SKIP_BROWSER_DOWNLOAD", "1")
    systemChromium.orNull?.let { environment("PLAYWRIGHT_CHROMIUM_EXECUTABLE_PATH", it) }

    doLast {
        listOf("playwright-report", "test-results").forEach { name ->
            val dir = projectDir.resolve(name)
            if (dir.isDirectory && dir.walkBottomUp().all { it.isDirectory }) {
                dir.deleteRecursively()
            }
        }
    }
}


tasks.register<JavaExec>("playwrightInstall") {
    classpath(sourceSets["test"].runtimeClasspath)
    mainClass.set("com.microsoft.playwright.CLI")
    args("install", "chromium", "--with-deps")
}

// ── The exclusivity measurement ──────────────────────────────────────────────
//
// Three steps, none of them in `check`: `codegen/exclusivity.json` is a committed artefact
// like `lib/generated/**`, and re-measuring it needs Node, the DaisyUI submodule and a
// browser. `just measure-exclusivity` runs the chain; `:lib:verifyExclusivity` is the cheap
// guard that fires in CI when the committed file stops describing the submodule.

val exclusivityDir: Provider<Directory> = layout.buildDirectory.dir("exclusivity")

val unpackDaisyuiCss = tasks.register<Copy>("unpackDaisyuiCss") {
    group = "codegen"
    description = "Unpack the compiled DaisyUI stylesheet from the webjar"
    // The whole bundle, every theme included. A hand-picked subset once omitted the theme
    // entirely, so every colour-dependent class computed to the same transparent black and
    // four groups were measured as indistinguishable.
    from(daisyuiWebjar.map { zipTree(it) }) {
        include("**/daisyui.css")
        eachFile { path = name }
        includeEmptyDirs = false
    }
    into(exclusivityDir)
}

val buildExclusivityProbe = tasks.register<Exec>("buildExclusivityProbe") {
    group = "codegen"
    description = "Generate the exclusivity probe page from DaisyUI's documented examples"
    dependsOn(":lib:checkoutDaisyuiTag", ":lib:installCodegenDeps", unpackDaisyuiCss)
    workingDir = rootProject.file("codegen")
    doFirst { exclusivityDir.get().asFile.mkdirs() }
    commandLine(
        "sh", "-c",
        "node src/index-exclusivity.ts --output-dir=\"${exclusivityDir.get().asFile.absolutePath}\""
    )
    inputs.dir(rootProject.file("codegen/src"))
    inputs.dir(rootProject.file("daisyui/packages/docs"))
    outputs.dir(exclusivityDir)
}

tasks.register<JavaExec>("measureExclusivity") {
    group = "codegen"
    description = "Re-measure class exclusivity in a browser and rewrite codegen/exclusivity.json"
    dependsOn(buildExclusivityProbe)
    classpath = exclusivity.runtimeClasspath
    mainClass.set("kdaisyui.exclusivity.MeasureExclusivityKt")
    environment("PLAYWRIGHT_SKIP_BROWSER_DOWNLOAD", "1")
    systemChromium.orNull?.let { environment("PLAYWRIGHT_CHROMIUM_EXECUTABLE_PATH", it) }
    argumentProviders.add(CommandLineArgumentProvider {
        listOfNotNull(
            exclusivityDir.get().file("probe.html").asFile.absolutePath,
            rootProject.file("codegen/exclusivity.json").absolutePath,
            systemChromium.orNull,
        )
    })
}
