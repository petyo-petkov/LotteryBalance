plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin)
    alias(libs.plugins.google.dagger.hilt.android)
    alias(libs.plugins.google.devtools.ksp)
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")

}



android {
    namespace = "com.example.lotterybalance"
    compileSdk = 34


    defaultConfig {
        applicationId = "com.example.lotterybalance"
        minSdk = 30
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }


    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        //kotlinCompilerExtensionVersion = "1.5.4-dev-k1.9.20-RC2-93f1625925e"
        kotlinCompilerExtensionVersion = "1.5.9-dev-k2.0.0-Beta3-7c5ec6895a0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.material3)
    implementation(libs.material3)
    implementation(libs.androidx.material.icons.extended)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.androidx.runtime.livedata)
    implementation(libs.androidx.foundation)

    //Gemini
    implementation(libs.generativeai)
    
    // Gson
    implementation (libs.gson)

    // compose
    implementation (libs.androidx.lifecycle.viewmodel.compose)
    implementation (libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))

    // google camera scanner
    implementation (libs.play.services.code.scanner)
    implementation (libs.play.services.base)
    implementation (libs.play.services.tflite.java)

    //viewmodel
    implementation (libs.androidx.lifecycle.viewmodel.ktx)
    implementation (libs.androidx.lifecycle.livedata.ktx)

    //dager hilt
    implementation (libs.hilt.android)
    implementation(libs.firebase.crashlytics.buildtools)
    ksp (libs.hilt.android.compiler)
    ksp (libs.androidx.hilt.compiler)
    implementation (libs.androidx.hilt.navigation.compose)

    // Room
    implementation (libs.androidx.room.runtime)
    annotationProcessor(libs.androidx.room.compiler)
    ksp(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)

    implementation (libs.kotlinx.metadata.jvm)
    ksp(libs.kotlinx.metadata.jvm)

    // LocalDate
    implementation (libs.kotlinx.datetime)

    testImplementation(libs.junit)
    testImplementation(libs.androidx.room.testing)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}