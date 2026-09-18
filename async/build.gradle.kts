dependencies {
    implementation(project(":core"))
    implementation("org.springframework.kafka:spring-kafka")

    testImplementation("org.springframework.kafka:spring-kafka-test")
}

tasks.bootJar {
    enabled = true
}
