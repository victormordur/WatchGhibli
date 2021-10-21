plugins {
    id("com.android.application")
    kotlin("android")
    id("org.jetbrains.kotlin.plugin.serialization")
    id("com.squareup.sqldelight")
}

android {
    compileSdk = Sdk.COMPILE_SDK_VERSION

    defaultConfig {
        minSdk = Sdk.MIN_SDK_VERSION
        targetSdk = Sdk.TARGET_SDK_VERSION

        applicationId = AppConfig.APP_ID
        versionCode = AppConfig.APP_VERSION_CODE
        versionName = AppConfig.APP_VERSION_NAME
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildFeatures {
        viewBinding = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    lint {
        isWarningsAsErrors = true
        isAbortOnError = true
    }

    // Use this block to configure different flavors
//    flavorDimensions("version")
//    productFlavors {
//        create("full") {
//            dimension = "version"
//            applicationIdSuffix = ".full"
//        }
//        create("demo") {
//            dimension = "version"
//            applicationIdSuffix = ".demo"
//        }
//    }
}

dependencies {
    implementation(Kotlin.android)

    implementation(SupportLibs.ANDROIDX_APPCOMPAT)
    implementation(SupportLibs.ANDROIDX_CONSTRAINT_LAYOUT)
    implementation(SupportLibs.ANDROIDX_CORE_KTX)

    implementation(Logger.SLFJ4)
    implementation(Logger.TIMBER)

    implementation(Koin.CORE)
    implementation(Koin.ANDROID)
    implementation(Ktor.CORE)
    implementation(Ktor.ANDROID)
    implementation(Ktor.SERIALIZATION)
    implementation(Ktor.LOGGER)
    implementation(SqlDelight.ANDROID_DRIVER)

    testImplementation(TestingLib.JUNIT)
    testImplementation(TestingLib.KTOR_MOCK)
    testImplementation(TestingLib.SQLDELIGHT_JVM)
    testImplementation(AndroidTestingLib.ANDROIDX_TEST_CORE)
    testImplementation(AndroidTestingLib.ANDROIDX_TEST_CORE_KTX)
    testImplementation(AndroidTestingLib.ROBOELECTRIC)

    androidTestImplementation(AndroidTestingLib.ANDROIDX_TEST_EXT_JUNIT)
    androidTestImplementation(AndroidTestingLib.ANDROIDX_TEST_EXT_JUNIT_KTX)
    androidTestImplementation(AndroidTestingLib.ANDROIDX_TEST_RULES)
    androidTestImplementation(AndroidTestingLib.ESPRESSO_CORE)
}
