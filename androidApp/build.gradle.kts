import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    kotlin("multiplatform")
    id("com.android.application")
    id("org.jetbrains.compose")
}

kotlin {
    androidTarget()
    sourceSets {
        val androidMain by getting {
            dependencies {
                implementation(project(":shared"))
            }
        }
    }
}

//读取local.properties
val localProperties = gradleLocalProperties(rootDir)

android {
    compileSdk = (findProperty("android.compileSdk") as String).toInt()
    namespace = "com.chinahrt.app.shitu"

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")

    defaultConfig {
        applicationId = "com.chinahrt.app.shitu"
        minSdk = (findProperty("android.minSdk") as String).toInt()
        targetSdk = (findProperty("android.targetSdk") as String).toInt()
        versionCode = 1
        versionName = "1.0"
    }

    signingConfigs {
        create("release"){
            keyAlias = localProperties.getProperty("key.alias") as String
            keyPassword = localProperties.getProperty("key.password") as String
            storeFile = file(localProperties.getProperty("key.path") as String)
            storePassword = localProperties.getProperty("key.password") as String
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlin {
        jvmToolchain(11)
    }
}
