package com.example.scrollablelistcompose.feature.movie.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.scrollablelistcompose.core.local.MovieDatabase
import com.example.scrollablelistcompose.core.network.ApiResult
import com.example.scrollablelistcompose.core.network.RetrofitClient
import com.example.scrollablelistcompose.feature.movie.data.local.MovieEntity
import com.example.scrollablelistcompose.feature.movie.data.repository.MovieRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MovieViewModel(application: Application) : AndroidViewModel(application) {

    private val _moviesState = MutableStateFlow<ApiResult<List<MovieEntity>>>(ApiResult.Loading)
    val moviesState: StateFlow<ApiResult<List<MovieEntity>>> = _moviesState

    private val repository: MovieRepository

    init {
        val movieDao = MovieDatabase.getDatabase(application).movieDao()
        val apiService = RetrofitClient.apiService

        repository = MovieRepository(apiService, movieDao)

        fetchMovies()
    }

    private fun fetchMovies() {
        viewModelScope.launch {
            repository.getPopularMovies("6f2ee23d4795a0872eee04f343ae6c7d").collect { result ->
                _moviesState.value = result
            }
        }
    }
}