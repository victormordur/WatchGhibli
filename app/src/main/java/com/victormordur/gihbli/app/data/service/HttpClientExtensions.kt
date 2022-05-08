package com.victormordur.gihbli.app.data.service

import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import kotlinx.serialization.json.Json
import timber.log.Timber

val commonJsonSerializer = KotlinxSerializer(
    json = Json {
        isLenient = true
        ignoreUnknownKeys = true
    }
)

fun createHttpClient() = HttpClient {
    install(JsonFeature) {
        serializer = commonJsonSerializer
    }
    install(Logging) {
        logger = object : Logger {
            override fun log(message: String) {
                Timber.d("KTOR : $message")
            }
        }
        level = LogLevel.ALL
    }
}
