pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven {
            authentication {
                create<BasicAuthentication>("basic")
            }
            url = uri("https://api.mapbox.com/downloads/v2/releases/maven")
            credentials {
                // Do not change the username below.
                // This should always be `mapbox` (not your username).
                username = "mapbox"
                // Use the secret token you stored in gradle.properties as the password
                password = "sk.eyJ1IjoiYW5kcmVzbG9sNjQiLCJhIjoiY2xrNTYxcHp2MG0wczNmczFlcXFmbGR1YiJ9.62hHBpKfc-aoThdggVmRLg"
            }
        }
    }
}

rootProject.name = "ExamenIIB"
include(":app")
