import pl.allegro.tech.build.axion.release.domain.ChecksConfig

plugins {
    id("java")
    id("idea")
    id("eclipse")
    id("maven")
    id("maven-publish")
    id("pl.allegro.tech.build.axion-release") version "1.9.3"
    id("io.spring.dependency-management") version "1.0.6.RELEASE"
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}


scmVersion {

    versionIncrementer("incrementMajor")

    ignoreUncommittedChanges = false
    useHighestVersion = true

    checks(closureOf<ChecksConfig> {
        aheadOfRemote = false
        uncommittedChanges = false
    })
}

version = scmVersion.version
group = "com.mulecode"

repositories {
    mavenCentral()
    mavenLocal()
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.boot:spring-boot-parent:2.1.5.RELEASE")
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:Greenwich.RELEASE")
    }
}

dependencies {

    implementation("io.reactivex:rxjava:1.3.8")
    
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-web")

    implementation("com.nimbusds:nimbus-jose-jwt:7.1")
    implementation("org.mindrot:jbcrypt:0.4")
    
    implementation("com.fasterxml.jackson.module:jackson-module-parameter-names:2.9.7")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jdk8:2.9.7")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.9.7")

    implementation("org.apache.commons:commons-lang3:3.9")

    implementation("javax.xml.bind:jaxb-api:2.3.0")
    implementation("com.sun.xml.bind:jaxb-core:2.3.0")
    implementation("com.sun.xml.bind:jaxb-impl:2.3.0")
    implementation("javax.activation:activation:1.1.1")
    implementation("org.slf4j:slf4j-api")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.slf4j:slf4j-simple")
    testImplementation("junit:junit:4.12")
    testImplementation("org.hamcrest:hamcrest-all:1.3")
    testImplementation("com.jayway.jsonpath:json-path-assert:2.4.0")
}