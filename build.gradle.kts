import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.7.2"
    id("io.spring.dependency-management") version "1.0.12.RELEASE"
    kotlin("jvm") version "1.6.21"
    kotlin("plugin.spring") version "1.6.21"
    kotlin("plugin.jpa") version "1.6.21"
    kotlin ("plugin.allopen") version "1.6.21"
    kotlin ("plugin.noarg") version "1.6.21"
}

group = "com.seok2"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

noArg {
    annotation("javax.persistence.Entity") // 2
}

allOpen {
    annotation("javax.persistence.Entity") // 3
    annotation("javax.persistence.MappedSuperclass") // 4
    annotation("javax.persistence.Embeddable") // 5
}


repositories {
    mavenCentral()
}



dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-mail")

    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    // security
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("io.jsonwebtoken:jjwt-impl:0.11.5")
    implementation("io.jsonwebtoken:jjwt-jackson:0.11.5")

    runtimeOnly("com.h2database:h2")

    // test
    testImplementation("org.hamcrest:hamcrest:2.2")
    testImplementation("io.rest-assured:kotlin-extensions:5.1.1")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("com.icegreen:greenmail-junit5:1.6.0")

    //logging
    implementation("io.github.microutils:kotlin-logging-jvm:2.1.23")

    //configuration
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
}

tasks.test {
    useJUnitPlatform()
}


tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
