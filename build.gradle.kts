buildscript {
    dependencies {
        classpath(libs.secrets.gradle.plugin)
    }

}

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias (libs.plugins.android.library) apply false
    alias(libs.plugins.jetbrains.kotlin) apply false
    alias(libs.plugins.google.dagger.hilt.android) apply false
    alias(libs.plugins.google.devtools.ksp) apply false
    alias(libs.plugins.io.realm.kotlin) apply false
    //alias(libs.plugins.google.android.libraries.mapsplatform.secrets.gradle.plugin) apply false


}
