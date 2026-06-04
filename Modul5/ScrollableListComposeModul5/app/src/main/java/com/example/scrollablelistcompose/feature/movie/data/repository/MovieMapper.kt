package com.example.scrollablelistcompose.feature.movie.data.repository

import com.example.scrollablelistcompose.feature.movie.data.local.MovieEntity
import com.example.scrollablelistcompose.feature.movie.data.remote.MovieDto

object MovieMapper {
    fun mapDtoToEntity(dto: MovieDto): MovieEntity {
        return MovieEntity(
            id = dto.id,
            title = dto.title,
            posterPath = dto.posterPath ?: "",
            overview = dto.overview ?: "Sinopsis tidak tersedia",
            releaseDate = dto.releaseDate ?: "Tanggal rilis tidak diketahui",
            voteAverage = dto.voteAverage ?: 0.0
        )
    }
}