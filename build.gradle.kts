// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    id("com.google.dagger.hilt.android") version "2.48" apply false

}
buildscript{
    dependencies{

            classpath("com.android.tools.build:gradle:7.3.1")
            classpath("com.google.gms:google-services:4.4.2") // Ensure this line is present

    }
}