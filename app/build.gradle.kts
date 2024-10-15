plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.google.dagger.hilt.android)
    alias(libs.plugins.google.gms.google.services) apply false //TODO remove false after getting the google service json plist
    alias(libs.plugins.google.firebase.crashlytics)
    alias(libs.plugins.google.devtools.ksp)
    kotlin("kapt")
    id("kotlin-kapt")
}

android {
    namespace = "com.rickaban.android.getrandomusers"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.rickaban.android.getrandomusers"
        minSdk = 29
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        debug {
            applicationVariants.all {
                outputs
                    .map { it as com.android.build.gradle.internal.api.BaseVariantOutputImpl }
                    .forEach { output ->
                        val outputFileName = "${applicationId.split(".").last()}_debug_v${versionName}.apk"
                        output.outputFileName = outputFileName
                    }
            }
            isDebuggable = true
        }
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    flavorDimensionList.add("env")

    productFlavors {
        create("dev") {
            dimension = "env"
            manifestPlaceholders["appLabel"] = "$displayName-DEV"
            buildConfigField("String", "DEFAULT_BASE_URL", "\"https://randomuser.me/api/\"")
            buildConfigField("String", "DISPLAY_NAME", "\"${manifestPlaceholders["appLabel"]}\"")
        }
        create("prod") {
            dimension = "env"
            manifestPlaceholders["appLabel"] = displayName
            buildConfigField("String", "DEFAULT_BASE_URL", "\"https://randomuser.me/api/\"")
            buildConfigField("String", "DISPLAY_NAME", "\"${manifestPlaceholders["appLabel"]}\"")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            excludes += "META-INF/LICENSE.md"
            excludes += "META-INF/LICENSE-notice.md"
        }
    }
    applicationVariants.all {
        addJavaSourceFoldersToModel(
            File(layout.buildDirectory.get().asFile, "generated/ksp/$name/kotlin")
        )
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    debugImplementation(libs.androidx.arch.core.testing)
    debugImplementation(libs.kotlinx.coroutine.test)
    debugImplementation(libs.mockk)
    //di
    implementation(libs.google.dagger.hilt.android)
    implementation(libs.androidx.hilt.navigation.compose)
    kapt(libs.google.dagger.hilt.android.compiler)
    //data
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.gson)
    implementation(libs.okhttp3.logging.interceptor)
    implementation(libs.androidx.pref)
    implementation(libs.room.ktx)
    ksp(libs.room.compiler)
    //ui
    implementation(libs.ramcosta.compose.destinations.animation.core)
    ksp(libs.ramcosta.compose.destinations.ksp)
    implementation(libs.google.accompanist.swiperefresh)
    implementation(libs.google.accompanist.drawable.painter)
    implementation(libs.coil.kt.compose)
    implementation(libs.androidx.compose.material.icons.extended)
    //utils
    implementation(libs.timber)
    implementation(libs.google.accompanist.permissions)
    implementation(platform(libs.google.firebase.bom))
    implementation(libs.google.firebase.analytics)
    implementation(libs.google.firebase.crashlytics)
    implementation(libs.androidx.biometric.ktx)
    implementation(libs.androidx.work.runtime.ktx)
    implementation(libs.faker)
}

kapt {
    correctErrorTypes = true
}