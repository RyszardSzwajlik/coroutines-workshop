import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.22"
    application
    id("org.springframework.boot") version "3.0.1"
    id("io.spring.dependency-management") version "1.1.0"
}

group = "org.example"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    // kotlin coroutines essential
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")

    // kotlin coroutines extras
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")

    // spring related
    implementation("org.springframework.boot:spring-boot-starter-data-r2dbc")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    // h2 db
    runtimeOnly("com.h2database:h2")
    runtimeOnly("io.r2dbc:r2dbc-h2")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "17"
}
