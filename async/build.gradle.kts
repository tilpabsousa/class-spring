plugins {
    // gera classes Java a partir dos schemas em src/main/avro/*.avsc
    id("com.github.davidmc24.gradle.plugin.avro") version "1.9.1"
}

repositories {
    // o serializer Avro da Confluent não está no Maven Central
    maven("https://packages.confluent.io/maven/")
}

dependencies {
    implementation(project(":core"))
    implementation("org.springframework.kafka:spring-kafka")
    implementation("io.confluent:kafka-avro-serializer:7.7.1") // traz org.apache.avro:avro como dependência transitiva

    testImplementation("org.springframework.kafka:spring-kafka-test")
}

tasks.bootJar {
    enabled = true
}

tasks.compileKotlin {
    dependsOn(tasks.generateAvroJava)
}
