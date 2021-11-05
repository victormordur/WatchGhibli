object Sdk {
    const val MIN_SDK_VERSION = 21
    const val TARGET_SDK_VERSION = 31
    const val COMPILE_SDK_VERSION = 31
}

object Versions {
    const val KOTLIN = "1.5.30"
    const val COROUTINES = "1.5.2"
    const val APPCOMPAT = "1.3.1"
    const val CONSTRAINT_LAYOUT = "2.0.4"
    const val CORE_KTX = "1.6.0"
    const val ACTIVITY = "1.3.1"
    const val LIFECYCLE = "2.4.0"
    const val COMPOSE_CORE = "1.0.3"
    const val COMPOSE_ACCOMPANIST = "0.16.1"
    const val COMPOSE_NAVIGATION = "2.4.0-beta02"
    const val COMPOSE_COIL = "1.3.2"
    const val KOIN = "3.0.2"
    const val KTOR = "1.6.2"
    const val SQLDELIGHT = "1.5.2"
    const val TIMBER = "5.0.1"
    const val SLFJ4 = "1.7.5"
    const val KTLINT = "0.42.0"
    const val JUNIT = "4.13.2"
    const val ANDROIDX_TEST = "1.4.0"
    const val ANDROIDX_TEST_EXT = "1.1.3"
    const val ARCH_CORE = "2.1.0"
    const val ESPRESSO_CORE = "3.4.0"
    const val ROBOELECTRIC = "4.6"

    const val MOCKK = "1.12.0"
}

object BuildPluginsVersion {
    const val SQLDELIGHT = Versions.SQLDELIGHT
    const val DETEKT = "1.17.1"
    const val KTLINT = "10.1.0"
    const val VERSIONS_PLUGIN = "0.39.0"
}

object Kotlin {
    const val ANDROID = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.KOTLIN}"
}

object Coroutines {
    const val ANDROID = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.COROUTINES}"
}

object SupportLibs {
    const val ANDROIDX_APPCOMPAT = "androidx.appcompat:appcompat:${Versions.APPCOMPAT}"
    const val ANDROIDX_CONSTRAINT_LAYOUT = "androidx.constraintlayout:constraintlayout:${Versions.CONSTRAINT_LAYOUT}"
    const val ANDROIDX_CORE_KTX = "androidx.core:core-ktx:${Versions.CORE_KTX}"
    const val ANDROIDX_ACTIVITY_KTX = "androidx.activity:activity-ktx:${Versions.ACTIVITY}"
    const val ANDROIDX_LIFECYCLE_RUNTIME = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.LIFECYCLE}"
    const val ANDROIDX_LIFECYCLE_LIVEDATA = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.LIFECYCLE}"
    const val ANDROIDX_LIFECYCLE_VIEWMODEL = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.LIFECYCLE}"
}

object Compose {
    const val UI = "androidx.compose.ui:ui:${Versions.COMPOSE_CORE}"

    // Foundation (Border, Background, Box, Image, Scroll, shapes, animations, etc.)
    const val FOUNDATION = "androidx.compose.foundation:foundation:${Versions.COMPOSE_CORE}"

    // Material Design
    const val MATERIAL = "androidx.compose.material:material:${Versions.COMPOSE_CORE}"

    // Material design icons
    const val MATERIAL_ICONS =
        "androidx.compose.material:material-icons-core:${Versions.COMPOSE_CORE}"
    const val MATERIAL_ICONS_EXT =
        "androidx.compose.material:material-icons-extended:${Versions.COMPOSE_CORE}"

    // Tooling support (Previews, etc.)
    const val TOOLING = "androidx.compose.ui:ui-tooling:${Versions.COMPOSE_CORE}"

    const val ANDROIDX_ACTIVITY = "androidx.activity:activity-compose:${Versions.ACTIVITY}"

    // Integration with ViewModels
    const val ANDROIDX_VIEWMODEL =
        "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.LIFECYCLE}"

    // Support
    const val SWIPE_TO_REFRESH =
        "com.google.accompanist:accompanist-swiperefresh:${Versions.COMPOSE_ACCOMPANIST}"

    // Navigation bar
    const val NAVIGATION =
        "androidx.navigation:navigation-compose:${Versions.COMPOSE_NAVIGATION}"

    // Image Loader
    const val COIL = "io.coil-kt:coil-compose:${Versions.COMPOSE_COIL}"
}

object Logger {
    const val TIMBER = "com.jakewharton.timber:timber:${Versions.TIMBER}"
    const val SLFJ4 = "org.slf4j:slf4j-nop:${Versions.SLFJ4}"
}

object Koin {
    const val CORE = "io.insert-koin:koin-core:${Versions.KOIN}"
    const val ANDROID = "io.insert-koin:koin-android:${Versions.KOIN}"
}

object Ktor {
    const val CORE = "io.ktor:ktor-client-core:${Versions.KTOR}"
    const val ANDROID = "io.ktor:ktor-client-android:${Versions.KTOR}"
    const val SERIALIZATION = "io.ktor:ktor-client-serialization-jvm:${Versions.KTOR}"
    const val LOGGER = "io.ktor:ktor-client-logging:${Versions.KTOR}"
}

object SqlDelight {
    const val ANDROID_DRIVER = "com.squareup.sqldelight:android-driver:${Versions.SQLDELIGHT}"
    const val COROUTINES_EXT = "com.squareup.sqldelight:coroutines-extensions:${Versions.SQLDELIGHT}"
}

object TestingLib {
    const val JUNIT = "junit:junit:${Versions.JUNIT}"
    const val MOCKK = "io.mockk:mockk:${Versions.MOCKK}"
    const val KTOR_MOCK = "io.ktor:ktor-client-mock:${Versions.KTOR}"
    const val SQLDELIGHT_JVM = "com.squareup.sqldelight:sqlite-driver:${Versions.SQLDELIGHT}"
    const val COROUTINES = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.COROUTINES}"
}

object AndroidTestingLib {
    const val ANDROIDX_TEST_RULES = "androidx.test:rules:${Versions.ANDROIDX_TEST}"
    const val ANDROIDX_TEST_RUNNER = "androidx.test:runner:${Versions.ANDROIDX_TEST}"
    const val ANDROIDX_TEST_CORE = "androidx.test:core:${Versions.ANDROIDX_TEST}"
    const val ANDROIDX_TEST_CORE_KTX = "androidx.test:core-ktx:${Versions.ANDROIDX_TEST}"
    const val ANDROIDX_TEST_EXT_JUNIT = "androidx.test.ext:junit:${Versions.ANDROIDX_TEST_EXT}"
    const val ANDROIDX_TEST_EXT_JUNIT_KTX = "androidx.test.ext:junit-ktx:${Versions.ANDROIDX_TEST_EXT}"
    const val ANDROIDX_ARCH_CORE_TEST = "androidx.arch.core:core-testing:${Versions.ARCH_CORE}"
    const val ESPRESSO_CORE = "androidx.test.espresso:espresso-core:${Versions.ESPRESSO_CORE}"
    const val ROBOELECTRIC = "org.robolectric:robolectric:${Versions.ROBOELECTRIC}"
}
