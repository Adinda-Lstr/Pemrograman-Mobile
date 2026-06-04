package com.example.scrollablelistcompose.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.scrollablelistcompose.data.Destination
import com.example.scrollablelistcompose.data.DestinationDataSource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import timber.log.Timber

class DestinationViewModel(private val category: String) : ViewModel() {

    private val _destinations = MutableStateFlow<List<Destination>>(emptyList())
    val destinations: StateFlow<List<Destination>> = _destinations.asStateFlow()

    init {
        loadData()
    }

    private fun loadData() {
        _destinations.value = DestinationDataSource.dummyDestinations

        Timber.d("LOG: Data masuk ke list untuk kategori: $category")
    }

    fun onDetailClicked(dest: Destination) {
        Timber.i("LOG: Tombol Detail ditekan. Data terpilih: ${dest.name} (ID: ${dest.id})")
    }

    fun onMapsClicked() {
        Timber.i("LOG: Tombol Explicit Intent (Maps) ditekan")
    }
}