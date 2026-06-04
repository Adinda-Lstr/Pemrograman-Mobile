package com.example.scrollablelistcompose.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieResponseDto(
    val results: List<MovieDto>
)

@Serializable
data class MovieDto(
    val id: Int,
    val title: String,
    @SerialName("poster_path")
    val posterPath: String? = null,
    val overview: String? = null
) {
    fun toEntity(): MovieEntity {
        return MovieEntity(
            id = id,
            title = title,
            posterPath = posterPath ?: "",
            overview = overview ?: ""
        )
    }
}