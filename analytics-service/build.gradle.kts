
plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.ktor)
}

group = "com.leobarreto"
version = "1.1.0"

application {
    mainClass = "io.ktor.server.netty.EngineMain"
}

repositories {
    mavenCentral()

}

dependencies {
    implementation(libs.ktor.server.content.negotiation)
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.serialization.jackson)
    implementation(libs.ktor.server.host.common)
    implementation(libs.ktor.server.status.pages)
    implementation(libs.ktor.server.netty)
    implementation(libs.logback.classic)
    implementation(libs.ktor.server.config.yaml)
    implementation("io.insert-koin:koin-ktor:3.5.6")
    implementation("io.insert-koin:koin-logger-slf4j:3.5.6")
    implementation(platform("software.amazon.awssdk:bom:2.20.28"))
    implementation("software.amazon.awssdk:sqs")
    implementation("software.amazon.awssdk:dynamodb")
    implementation("io.micrometer:micrometer-registry-prometheus:1.13.4")
    implementation("io.micrometer:micrometer-registry-prometheus-simpleclient:1.13.4")
    implementation("com.github.loki4j:loki-logback-appender:2.0.0")
    testImplementation(libs.ktor.server.test.host)
    testImplementation(libs.kotlin.test.junit)
}
