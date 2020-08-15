plugins {
    kotlin("jvm") version "1.3.72"
    id("org.jlleitschuh.gradle.ktlint") version "9.3.0"
    id("org.sonarqube") version "3.0"
    jacoco
}

group = "org.example"
version = "1.0-SNAPSHOT"

apply(plugin = "jacoco")

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    testImplementation(kotlin("test-junit"))
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
        kotlinOptions.freeCompilerArgs = listOf("-Xinline-classes")
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }

    jacocoTestReport {
        reports {
            xml.isEnabled = true
        }
    }
}

sonarqube {
    properties {
        property("sonar.projectKey", "lpicanco-chip-8-emulator")
        property("sonar.junit.reportPaths", "build/test-results/test")
        property("sonar.coverage.jacoco.xmlReportPaths", "build/reports/jacoco/test/jacocoTestReport.xml")
    }
}
