plugins {
    id("java")
    idea
    `maven-publish`
}

group = "org.myddd.security"
version = "0.1.0-SNAPSHOT"

extra["jackson-version"] = "2.13.3"
extra["junit-version"] = "5.8.2"
extra["spring.boot"] = "2.7.1"

repositories {
    maven {
        setUrl("https://maven.aliyun.com/repository/public/")
    }
    maven {
        setUrl("https://maven.aliyun.com/repository/spring/")
    }
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web:${rootProject.extra["spring.boot"]}")
    implementation("org.springframework.boot:spring-boot-starter-security:${rootProject.extra["spring.boot"]}")
    implementation("org.springframework.boot:spring-boot-starter-oauth2-client:${rootProject.extra["spring.boot"]}")

    compileOnly("org.springframework.boot:spring-boot-starter-jdbc:${rootProject.extra["spring.boot"]}")
    compileOnly("org.springframework.boot:spring-boot-starter-data-redis:${rootProject.extra["spring.boot"]}")
    compileOnly("org.springframework.security:spring-security-jwt:1.1.1.RELEASE")

    implementation("javax.xml.bind:jaxb-api:2.3.0")
    implementation("com.sun.xml.bind:jaxb-impl:2.3.0")
    implementation("org.glassfish.jaxb:jaxb-runtime:2.3.0")
    implementation("javax.annotation:javax.annotation-api:1.3.2")

    implementation("com.fasterxml.jackson.core:jackson-annotations:${project.extra["jackson-version"]}")
    implementation("com.fasterxml.jackson.core:jackson-databind:${project.extra["jackson-version"]}")
    compileOnly("commons-codec:commons-codec:1.15")

    compileOnly("javax.servlet:javax.servlet-api:4.0.1")

    testImplementation("org.springframework.boot:spring-boot-starter-test:${rootProject.extra["spring.boot"]}")
    testImplementation("org.junit.jupiter:junit-jupiter-api:${project.extra["junit-version"]}")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:${project.extra["junit-version"]}")

}

tasks.withType<JavaCompile> {
    sourceCompatibility = "11"
    targetCompatibility = "11"
}

java {
    withSourcesJar()
}

publishing {
    publications {
        create<MavenPublication>("mavenJava"){
            groupId = "org.myddd.security"
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