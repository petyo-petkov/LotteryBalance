plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id ("dagger.hilt.android.plugin")
    //id ("org.jetbrains.kotlin.kapt")
    id ("com.google.devtools.ksp")
    id ("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
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

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation("androidx.compose.ui:ui:")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.navigation:navigation-compose:2.7.6")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.material3:material3:1.2.0-rc01")
    implementation("androidx.compose.material:material-icons-extended")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
    implementation("androidx.compose.runtime:runtime-livedata:1.6.0")
    implementation("androidx.compose.foundation:foundation:1.6.0")


    //Gemini
    implementation("com.google.ai.client.generativeai:generativeai:0.1.2")


    // Gson
    implementation ("com.google.code.gson:gson:2.10.1")

    // compose
    implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")
    implementation ("androidx.lifecycle:lifecycle-runtime-compose:2.7.0")
    implementation("androidx.activity:activity-compose:1.8.2")
    implementation(platform("androidx.compose:compose-bom:2024.01.00"))

    // google camera scanner
    implementation ("com.google.android.gms:play-services-code-scanner:16.1.0")
    implementation ("com.google.android.gms:play-services-base:18.3.0")
    implementation ("com.google.android.gms:play-services-tflite-java:16.1.0")

    //viewmodel
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.7.0")

    //dager hilt
    implementation ("com.google.dagger:hilt-android:2.48")
    implementation("com.google.firebase:firebase-crashlytics-buildtools:2.9.9")
    ksp ("com.google.dagger:hilt-android-compiler:2.48")
    ksp ("androidx.hilt:hilt-compiler:1.1.0")
    implementation ("androidx.hilt:hilt-navigation-compose:1.1.0")

    // Room
    implementation ("androidx.room:room-runtime:2.6.1")
    annotationProcessor("androidx.room:room-compiler:2.6.1")
    ksp("androidx.room:room-compiler:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")

    implementation ("org.jetbrains.kotlinx:kotlinx-metadata-jvm:0.7.0")
    ksp("org.jetbrains.kotlinx:kotlinx-metadata-jvm:0.7.0")

    // LocalDate
    implementation ("org.jetbrains.kotlinx:kotlinx-datetime:0.3.1")

    testImplementation("junit:junit:4.13.2")
    testImplementation("androidx.room:room-testing:2.6.1")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2024.01.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest:1.6.0")
}