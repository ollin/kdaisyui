plugins {
    id("kdaisyui.kotlin-library-conventions")
}

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
