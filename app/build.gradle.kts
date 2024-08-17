plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
}

android {
    namespace = "com.example.toyprojectkakaoapi"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.toyprojectkakaoapi"
        minSdk = 26
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures{
        viewBinding = true
        buildConfig = true
    }
}

dependencies {
    //retrofit
    implementation(libs.retrofit)
    implementation(libs.converter)
    //hilt
    implementation(libs.hilt)
    ksp(libs.hilt.compiler)
    //nav
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    //coil
    implementation(libs.coil)
    //viewPager
    implementation (libs.androidx.viewpager2)
    //circleIndicator
    implementation (libs.circleindicator)
    //androidx-datastore-preferences
    implementation (libs.androidx.datastore.preferences)
    //gson
    implementation (libs.gson)
    //okhttp3
    implementation(libs.okhttp)
    implementation(libs.logging.interceptor)
    //lifecycle
    implementation (libs.androidx.lifecycle.viewmodel.ktx)
    implementation (libs.androidx.activity.ktx)
    implementation (libs.androidx.fragment.ktx)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}