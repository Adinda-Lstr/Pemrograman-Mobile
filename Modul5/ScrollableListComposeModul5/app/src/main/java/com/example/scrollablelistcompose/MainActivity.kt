package com.example.scrollablelistcompose

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.scrollablelistcompose.core.network.ApiResult
import com.example.scrollablelistcompose.core.local.SharedPrefsHelper
import com.example.scrollablelistcompose.feature.movie.presentation.DetailScreen
import com.example.scrollablelistcompose.feature.movie.presentation.HomeScreen
import com.example.scrollablelistcompose.ui.theme.ScrollableListComposeTheme
import com.example.scrollablelistcompose.feature.movie.presentation.MovieViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sharedPrefs = SharedPrefsHelper(this)
        if (sharedPrefs.isFirstTimeOpen()) {
            Toast.makeText(this, "Selamat Datang di Aplikasi Movie!", Toast.LENGTH_LONG).show()
            sharedPrefs.setFirstTimeOpen(false)
        }

        setContent {
            ScrollableListComposeTheme {
                val navController = rememberNavController()
                val viewModel: MovieViewModel = viewModel()

                NavHost(navController = navController, startDestination = "home") {

                    composable("home") {
                        HomeScreen(
                            viewModel = viewModel,
                            onMovieClick = { movieId ->
                                navController.navigate("detail/$movieId")
                            }
                        )
                    }

                    composable("detail/{movieId}") { backStackEntry ->
                        val movieId = backStackEntry.arguments?.getString("movieId")?.toIntOrNull()
                        val state = viewModel.moviesState.collectAsState().value

                        if (state is ApiResult.Success) {
                            val movie = state.data.find { it.id == movieId }
                            if (movie != null) {
                                DetailScreen(
                                    movie = movie,
                                    onBackClick = { navController.popBackStack() }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}