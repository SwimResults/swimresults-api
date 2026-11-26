// SwimResults API clients for Kotlin/Android (Retrofit 2 + Gson), generated from ../specs by generate.sh.

plugins {
    kotlin("jvm") version "2.2.21"
    id("com.vanniktech.maven.publish") version "0.36.0"
}

group = "de.swimresults"
version = providers.gradleProperty("VERSION_NAME").get()

repositories { mavenCentral() }

kotlin { jvmToolchain(17) }

dependencies {
    // exposed in the public API of the generated clients
    api("com.squareup.retrofit2:retrofit:2.11.0")
    api("com.squareup.okhttp3:okhttp:4.12.0")
    api("com.google.code.gson:gson:2.11.0")
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")
    implementation("com.squareup.retrofit2:converter-scalars:2.11.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")

    testImplementation(kotlin("test"))
}

tasks.test { useJUnitPlatform() }

mavenPublishing {
    publishToMavenCentral(automaticRelease = true) // Central Portal
    // the release workflow provides ORG_GRADLE_PROJECT_signingInMemoryKey(+Password); local publishes stay unsigned
    if (providers.gradleProperty("signingInMemoryKey").isPresent) signAllPublications()
    coordinates("de.swimresults", "swimresults-api", version.toString())
    pom {
        name.set("SwimResults API")
        description.set("Kotlin clients for the SwimResults APIs, generated from the OpenAPI specs.")
        url.set("https://github.com/SwimResults/swimresults-api")
        licenses {
            license {
                name.set("GNU General Public License v3.0")
                url.set("https://www.gnu.org/licenses/gpl-3.0.txt")
            }
        }
        developers {
            developer {
                id.set("swimresults")
                name.set("SwimResults")
                url.set("https://github.com/SwimResults")
            }
        }
        scm {
            url.set("https://github.com/SwimResults/swimresults-api")
            connection.set("scm:git:https://github.com/SwimResults/swimresults-api.git")
            developerConnection.set("scm:git:ssh://git@github.com/SwimResults/swimresults-api.git")
        }
    }
}
