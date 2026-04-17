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
    commandLine("sh", "-c", "npm install --silent && node src/index-new.js")
    inputs.dir(rootProject.file("codegen/src"))
    inputs.dir(rootProject.file("daisyui/packages/docs"))
    inputs.file(rootProject.file("codegen/package.json"))
    inputs.file(rootProject.file("codegen/codegen-config.json"))
    outputs.dir(layout.projectDirectory.dir("src/main/kotlin/kdaisyui/components"))
}

tasks.named("compileKotlin") {
    dependsOn(generateComponents)
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
            artifactId = "kdaisyui"
        }
    }
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/ollin/kdaisyUI")
            credentials {
                username = System.getenv("GITHUB_ACTOR")
                password = System.getenv("GITHUB_TOKEN")
            }
        }
    }
}
