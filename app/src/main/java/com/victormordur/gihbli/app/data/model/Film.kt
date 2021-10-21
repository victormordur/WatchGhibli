package com.victormordur.gihbli.app.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
data class Film(
    @SerialName("id")
    val id: String,
    @SerialName("title")
    val title: String,
    @SerialName("description")
    val description: String,
    @SerialName("release_date")
    val releaseDate: String,
    @SerialName("director")
    val director: String,
    @SerialName("image")
    val imageURL: String,
    @Transient
    val watched: Boolean = false
)
