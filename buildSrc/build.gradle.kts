plugins {
    `kotlin-dsl`
}
repositories {
    google()
    mavenCentral()
}

object Plugins {
    const val AGP = "7.0.2"
    const val KOTLIN = "1.5.21"
    const val SQLDELIGHT = "1.5.2"
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:${Plugins.KOTLIN}")
    implementation("com.android.tools.build:gradle:${Plugins.AGP}")
    implementation("org.jetbrains.kotlin:kotlin-serialization:${Plugins.KOTLIN}")
    implementation("com.squareup.sqldelight:gradle-plugin:${Plugins.SQLDELIGHT}")
}
