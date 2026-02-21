plugins {
    java
    id("io.papermc.paperweight.userdev").version("2.0.0-beta.19")
    id("xyz.jpenilla.run-paper").version("3.0.2")
    id("com.gradleup.shadow").version("9.3.1")
}

group = "com.jnngl"
version = "1.0.2"

java.toolchain.languageVersion.set(JavaLanguageVersion.of(21))

repositories {
    mavenCentral()
    maven {
        name = "papermc-repo"
        url = uri("https://repo.papermc.io/repository/maven-public/")
    }
    maven {
        name = "sonatype"
        url = uri("https://oss.sonatype.org/content/groups/public/")
    }
}

dependencies {
    paperweightDevelopmentBundle("io.papermc.paper:dev-bundle:1.21.11-R0.1-SNAPSHOT")
    implementation("net.elytrium:serializer:1.1.1")
    implementation("com.jnngl:mapcolor:1.0.1")
    compileOnly("io.papermc.paper:paper-api:1.21.11-R0.1-SNAPSHOT")
    annotationProcessor("org.projectlombok:lombok:1.18.42")
    compileOnly("org.projectlombok:lombok:1.18.42")
    implementation("com.j256.ormlite:ormlite-jdbc:6.1")
    implementation("org.xerial:sqlite-jdbc:3.51.2.0")
}

tasks {
    shadowJar {
        archiveClassifier.set("")
        relocate("net.elytrium.serializer", "com.jnngl.vanillaminimaps.serializer")
        exclude("org/slf4j/**")
        minimize()
    }

    compileJava {
        options.encoding = "UTF-8"
    }

    processResources {
        inputs.property("version", version)
        filteringCharset = "UTF-8"
        filesMatching("plugin.yml") {
            expand("version" to version)
        }
    }

    val prepareResourcePack by registering(Sync::class) {
        into(layout.buildDirectory.dir("resourcepack/staging"))

        from("src/resourcepack") {
            exclude("**/.DS_Store")
        }
    }

    val buildResourcePack by registering(Zip::class) {
        dependsOn(prepareResourcePack)
        from(prepareResourcePack)
        destinationDirectory.set(layout.buildDirectory.dir("resourcepack"))
        archiveFileName.set("resourcepack.zip")
    }

    build {
        dependsOn("shadowJar", buildResourcePack)
    }
}
