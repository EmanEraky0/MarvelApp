plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("org.jetbrains.kotlin.plugin.parcelize")

}


android {
    namespace = "com.example.marvelapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.marvelapp"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        debug {
            buildConfigField("String", "Public_Key", "\"65eaba09aecaf35bf20af74b1ba38392\"")
            buildConfigField("String", "Private_Key", "\"a25071b57c1c68903c37ed7347df1621441fe80b\"")
            buildConfigField("String", "BASE_URL", "\"https://gateway.marvel.com:443/v1/public/\"")

        }
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    buildFeatures {
        buildConfig = true
        viewBinding = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    //View Binding
    implementation(libs.androidx.viewbinding)

    //retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.logging.interceptor)

    //koin
    implementation(libs.koin.android)

    // Jetpack Navigation components
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation( libs.androidx.navigation.ui.ktx)

    //glide
    implementation(libs.glide.v4132)
    annotationProcessor(libs.compiler)

}