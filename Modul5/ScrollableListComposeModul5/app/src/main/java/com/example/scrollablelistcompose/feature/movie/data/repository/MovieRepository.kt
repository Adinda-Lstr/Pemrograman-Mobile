package com.example.scrollablelistcompose.feature.movie.data.repository

import com.example.scrollablelistcompose.core.network.ApiResult
import com.example.scrollablelistcompose.core.network.safeApiCall
import com.example.scrollablelistcompose.feature.movie.data.local.MovieDao
import com.example.scrollablelistcompose.feature.movie.data.local.MovieEntity
import com.example.scrollablelistcompose.feature.movie.data.remote.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MovieRepository(
    private val apiService: ApiService,
    private val movieDao: MovieDao
) {
    fun getPopularMovies(apiKey: String): Flow<ApiResult<List<MovieEntity>>> = flow {
        emit(ApiResult.Loading)

        val localData = movieDao.getMoviesList()
        if (localData.isNotEmpty()) {
            emit(ApiResult.Success(localData))
        }

        val remoteResult = safeApiCall { apiService.getPopularMovies(apiKey) }

        when (remoteResult) {
            is ApiResult.Success -> {

                val entities = remoteResult.data.results.map { MovieMapper.mapDtoToEntity(it) }
                movieDao.clearAll()
                movieDao.insertAll(entities)

                emit(ApiResult.Success(movieDao.getMoviesList()))
            }
            is ApiResult.Error -> {
                if (localData.isEmpty()) emit(ApiResult.Error(remoteResult.errorMessage))
            }
            else -> {}
        }
    }
}