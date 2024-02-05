plugins {
    kotlin("jvm") version "1.9.21"
}

group = "ca.helios5009"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0-RC2")
    implementation("org.java-websocket:Java-WebSocket:1.5.5")
    implementation("com.google.code.gson:gson:2.8.9")

}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(8)
}

