plugins {
    id("java")
    idea
    `maven-publish`
}

group = "org.myddd.security"
version = "0.1.0-SNAPSHOT"

extra["spring-version"] = "5.3.22"
extra["spring-security-version"] = "5.7.2"
extra["jackson-version"] = "2.13.3"
extra["junit-version"] = "5.8.2"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework:spring-beans:${project.extra["spring-version"]}")
    implementation("org.springframework:spring-core:${project.extra["spring-version"]}")
    implementation("org.springframework:spring-context:${project.extra["spring-version"]}")
    implementation("org.springframework:spring-webmvc:${project.extra["spring-version"]}")
    implementation("org.springframework:spring-tx:${project.extra["spring-version"]}")
    compileOnly("org.springframework:spring-jdbc:${project.extra["spring-version"]}")

    implementation("org.springframework.security:spring-security-core:${project.extra["spring-security-version"]}")
    implementation("org.springframework.security:spring-security-config:${project.extra["spring-security-version"]}")
    implementation("org.springframework.security:spring-security-web:${project.extra["spring-security-version"]}")

    compileOnly("org.springframework.security:spring-security-jwt:1.1.1.RELEASE")
    compileOnly("org.springframework.data:spring-data-redis:2.7.2")
    compileOnly("redis.clients:jedis:4.2.3")

    implementation("javax.xml.bind:jaxb-api:2.3.0")
    implementation("com.sun.xml.bind:jaxb-impl:2.3.0")
    implementation("org.glassfish.jaxb:jaxb-runtime:2.3.0")

    implementation("com.fasterxml.jackson.core:jackson-annotations:${project.extra["jackson-version"]}")
    implementation("com.fasterxml.jackson.core:jackson-databind:${project.extra["jackson-version"]}")
    implementation("commons-codec:commons-codec:1.15")

    implementation("javax.annotation:javax.annotation-api:1.3.2")
    compileOnly("javax.servlet:javax.servlet-api:4.0.1")

    testImplementation("org.springframework:spring-test:${project.extra["spring-version"]}")

    testImplementation("org.junit.jupiter:junit-jupiter-api:${project.extra["junit-version"]}")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:${project.extra["junit-version"]}")

}

publishing {
    publications {
        create<MavenPublication>("mavenJava"){
            groupId = "org.myddd"
            afterEvaluate {
                artifactId = tasks.jar.get().archiveBaseName.get()
            }
            from(components["java"])
        }

        repositories {
            maven {

                val releasesRepoUrl = "sftp://ssh.myddd.org:10010/repositories/releases"
                val snapshotsRepoUrl = "sftp://ssh.myddd.org:10010/repositories/snapshots"
                url = uri(if (version.toString().endsWith("SNAPSHOT")) snapshotsRepoUrl else releasesRepoUrl)

                credentials {
                    username = if(project.hasProperty("username")) project.property("username") as String? else ""
                    password = if(project.hasProperty("password")) project.property("password") as String? else ""
                }

            }
        }
    }
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}