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

    // coroutines
    implementation(libs.bundles.kotlinx.coroutine)

    // paging
    implementation(libs.bundles.paging)

    // koin
    implementation(libs.bundles.koin)

    // ktor
    implementation(libs.bundles.ktor)

    // paging
    implementation(libs.bundles.paging)

    // viewmodel
    implementation(libs.androidx.lifecycle.viewmodel.compose)

    // coil
    implementation(libs.bundles.coil)

    // navigation
    implementation(libs.bundles.navigation)

    // icons
    implementation(libs.androidx.material.icons.extended)

    // shimmer
    implementation(libs.compose.shimmer)

    // datastore
    implementation(libs.androidx.datastore.preferences)

    // accompanist
    implementation(libs.accompanist.permissions)
}