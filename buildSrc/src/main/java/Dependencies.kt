object Sdk {
    const val MIN_SDK_VERSION = 21
    const val TARGET_SDK_VERSION = 31
    const val COMPILE_SDK_VERSION = 31
}

object Versions {
    const val KOTLIN = "1.5.21"
    const val ANDROIDX_TEST_EXT = "1.1.3"
    const val ANDROIDX_TEST = "1.4.0"
    const val APPCOMPAT = "1.3.0"
    const val CONSTRAINT_LAYOUT = "2.0.4"
    const val CORE_KTX = "1.6.0"
    const val ESPRESSO_CORE = "3.4.0"
    const val ROBOELECTRIC = "4.6"
    const val JUNIT = "4.13.2"
    const val KTLINT = "0.42.0"
    const val KOIN = "3.0.2"
    const val KTOR = "1.6.2"
    const val TIMBER = "5.0.1"
    const val SLFJ4 = "1.7.5"
    const val SQLDELIGHT = "1.5.2"
    const val MOCKK = "1.12.0"
}

object BuildPluginsVersion {
    const val SQLDELIGHT = Versions.SQLDELIGHT
    const val DETEKT = "1.17.1"
    const val KTLINT = "10.1.0"
    const val VERSIONS_PLUGIN = "0.39.0"
}

object Kotlin {
    const val android = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.KOTLIN}"
}

object SupportLibs {
    const val ANDROIDX_APPCOMPAT = "androidx.appcompat:appcompat:${Versions.APPCOMPAT}"
    const val ANDROIDX_CONSTRAINT_LAYOUT = "androidx.constraintlayout:constraintlayout:${Versions.CONSTRAINT_LAYOUT}"
    const val ANDROIDX_CORE_KTX = "androidx.core:core-ktx:${Versions.CORE_KTX}"
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
}

object TestingLib {
    const val JUNIT = "junit:junit:${Versions.JUNIT}"
    const val MOCKK = "io.mockk:mockk:${Versions.MOCKK}"
    const val KTOR_MOCK = "io.ktor:ktor-client-mock:${Versions.KTOR}"
    const val SQLDELIGHT_JVM = "com.squareup.sqldelight:sqlite-driver:${Versions.SQLDELIGHT}"
}

object AndroidTestingLib {
    const val ANDROIDX_TEST_RULES = "androidx.test:rules:${Versions.ANDROIDX_TEST}"
    const val ANDROIDX_TEST_RUNNER = "androidx.test:runner:${Versions.ANDROIDX_TEST}"
    const val ANDROIDX_TEST_CORE = "androidx.test:core:${Versions.ANDROIDX_TEST}"
    const val ANDROIDX_TEST_CORE_KTX = "androidx.test:core-ktx:${Versions.ANDROIDX_TEST}"
    const val ANDROIDX_TEST_EXT_JUNIT = "androidx.test.ext:junit:${Versions.ANDROIDX_TEST_EXT}"
    const val ANDROIDX_TEST_EXT_JUNIT_KTX = "androidx.test.ext:junit-ktx:${Versions.ANDROIDX_TEST_EXT}"
    const val ESPRESSO_CORE = "androidx.test.espresso:espresso-core:${Versions.ESPRESSO_CORE}"
    const val ROBOELECTRIC = "org.robolectric:robolectric:${Versions.ROBOELECTRIC}"
}
