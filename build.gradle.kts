plugins {
    kotlin("multiplatform") version "1.4.10"
    id("org.jlleitschuh.gradle.ktlint") version "9.3.0"
    id("org.sonarqube") version "3.0"
    jacoco
}

group = "com.lpicanco.chip8"
version = "1.0-SNAPSHOT"

apply(plugin = "jacoco")

repositories {
    mavenCentral()
    jcenter()
}

kotlin {
    targets.all {
        compilations.all {
            kotlinOptions {
                freeCompilerArgs = listOf("-Xinline-classes", "-Xuse-experimental=kotlin.ExperimentalUnsignedTypes")
            }
        }
    }
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "1.8"
        }
        withJava()
    }
    js {
        browser()
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(kotlin("stdlib-common"))
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }

        val jvmMain by getting
        val jvmTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
            }
        }

        val jsMain by getting {
            dependencies {
                implementation(kotlin("stdlib-js"))
                implementation("org.jetbrains.kotlinx:kotlinx-html-js:0.7.2")
            }
        }

        val jsTest by getting {
            dependencies {
                implementation(kotlin("test-js"))
            }
        }
    }
}

tasks.withType<JacocoReport> {
    dependsOn("jvmTest")
    executionData.setFrom(files("$buildDir/jacoco/jvmTest.exec"))

    reports {
        xml.isEnabled = true
        xml.destination = File("$buildDir/reports/jacoco/test/jacocoTestReport.xml")
    }
}

tasks.register("jvmBuild") {
    dependsOn("jvmTest", "ktlintCheck", "jvmJar")
}

sonarqube {
    properties {
        property("sonar.junit.reportPaths", "build/test-results/jvmTest")
        property("sonar.coverage.jacoco.xmlReportPaths", "build/reports/jacoco/test/jacocoTestReport.xml")
    }
}
