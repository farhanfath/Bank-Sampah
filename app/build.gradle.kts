plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)

    alias(libs.plugins.kotlin.serialization) // serialization
    alias(libs.plugins.kotlin.parcelize) // parcelize
}

android {
    namespace = "project.collab.banksampah"
    compileSdk = 36

    defaultConfig {
        applicationId = "project.collab.banksampah"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
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
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
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

    // coroutines core
    implementation(libs.kotlinx.coroutines.core)
    // paging
    implementation(libs.paging.runtime)

    // koin
    implementation(libs.koin.android)

    // retrofit
    implementation (libs.retrofit)
    implementation (libs.retrofit2.converter.gson)
    implementation(libs.logging.interceptor)

    // coroutines
    implementation (libs.kotlinx.coroutines.android)

    // viewmodel
    implementation(libs.androidx.lifecycle.viewmodel.compose)

    // coil
    implementation(libs.coil3.coil.compose)
    implementation(libs.coil.network.okhttp)

    // navigation
    implementation(libs.androidx.navigation.compose)

    // serialization
    implementation(libs.kotlinx.serialization.json)

    // icons
    implementation(libs.androidx.material.icons.extended)

    // shimmer
    implementation(libs.compose.shimmer)

    // paging compose
    implementation(libs.androidx.paging.compose)
}