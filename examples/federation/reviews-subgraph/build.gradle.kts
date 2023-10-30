description = "An example spring service for federation that extends the basic types with new fields"

plugins {
    id("com.expediagroup.graphql.examples.conventions")
    id("com.expediagroup.graphql")
    alias(libs.plugins.kotlin.spring)
    alias(libs.plugins.spring.boot)
}

dependencies {
    implementation("com.expediagroup", "graphql-kotlin-spring-server")
    testImplementation(libs.spring.boot.test)

    graphqlSDL("com.expediagroup", "graphql-kotlin-federated-hooks-provider")
}

// config below is to simplify dockerfile
tasks.named("build") {
    dependsOn("bootJar")
    dependsOn("graphqlGenerateSDL")
}

tasks.named<org.springframework.boot.gradle.tasks.bundling.BootJar>("bootJar") {
    archiveFileName.set("reviews-subgraph.jar")
}

graphql {
    schema {
        packages = listOf("com.expediagroup.graphql.examples.federation.reviews")
    }
}
