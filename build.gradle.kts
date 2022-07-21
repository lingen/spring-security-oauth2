plugins {
    id("java")
}

group = "org.myddd.security"
version = "1.0-SNAPSHOT"

extra["spring-version"] = "5.3.22"
extra["spring-security-version"] = "5.7.2"
extra["jackson-version"] = "2.13.3"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework:spring-beans:${project.extra["spring-version"]}")
    implementation("org.springframework:spring-core:${project.extra["spring-version"]}")
    implementation("org.springframework:spring-context:${project.extra["spring-version"]}")
    implementation("org.springframework:spring-webmvc:${project.extra["spring-version"]}")
    implementation("org.springframework:spring-tx:${project.extra["spring-version"]}")
    implementation("org.springframework:spring-jdbc:${project.extra["spring-version"]}")

    implementation("org.springframework.security:spring-security-core:${project.extra["spring-security-version"]}")
    implementation("org.springframework.security:spring-security-config:${project.extra["spring-security-version"]}")
    implementation("org.springframework.security:spring-security-web:${project.extra["spring-security-version"]}")
    implementation("org.springframework.security:spring-security-jwt:1.1.1.RELEASE")

    implementation("org.springframework.data:spring-data-redis:2.7.2")
    implementation("redis.clients:jedis:4.2.3")

    implementation("javax.xml.bind:jaxb-api:2.3.0")
    implementation("com.sun.xml.bind:jaxb-impl:2.3.0")
    implementation("org.glassfish.jaxb:jaxb-runtime:2.3.0")

    implementation("com.fasterxml.jackson.core:jackson-annotations:2.13.3")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.13.3")
    implementation("commons-codec:commons-codec:1.15")

    implementation("javax.annotation:javax.annotation-api:1.3.2")
    compileOnly("javax.servlet:javax.servlet-api:4.0.1")

    testImplementation("org.springframework:spring-test:${project.extra["spring-version"]}")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.2")

}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}