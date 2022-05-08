package com.victormordur.gihbli.app.domain.model

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.junit.Assert
import org.junit.Test

class SerializationTest {

    companion object {
        val jsonParser = Json { encodeDefaults = true }
    }

    @Test
    fun testWatchFieldNotSerialized() {
        val watchedFilm =
            Film("id", "title", "description", "releaseDate", "director", "imageURL", true)
        val json = jsonParser.encodeToString(watchedFilm)
        val deserializedFilm = jsonParser.decodeFromString(Film.serializer(), json)
        Assert.assertTrue(json.contains("release_date"))
        Assert.assertFalse(json.contains("watched"))
        Assert.assertFalse(deserializedFilm.watched)
    }
}
