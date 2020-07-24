/*
 * This file was generated by the Gradle 'init' task.
 *
 * This generated file contains a sample Kotlin application project to get you started.
 */

plugins {
    // Apply the Kotlin JVM plugin to add support for Kotlin.
    id("org.jetbrains.kotlin.jvm") version "1.3.41"

    // Apply the application plugin to add support for building a CLI application.
    application
}

repositories {
    // Use jcenter for resolving dependencies.
    // You can declare any Maven/Ivy/file repository here.
    jcenter()
}

dependencies {
    // Align versions of all Kotlin components
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
    implementation ("org.mongodb:mongo-java-driver:3.12.6")
    // https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-java
    implementation ("org.seleniumhq.selenium:selenium-java:3.141.59")
    implementation ("net.sourceforge.jexcelapi:jxl:2.6.12")
    // https://mvnrepository.com/artifact/com.github.salomonbrys.kotson/kotson
    implementation("com.github.salomonbrys.kotson:kotson:2.5.0")
    // Use the Kotlin JDK 8 standard library.
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    // Use the Kotlin test library.
    testImplementation("org.jetbrains.kotlin:kotlin-test")

    // Use the Kotlin JUnit integration.
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
}

application {
    // Define the main class for the application.
    mainClassName = "dms.auctionspider.AppKt"
}