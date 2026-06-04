package com.example.scrollablelistxml.viewmodel

import androidx.lifecycle.ViewModel
import com.example.scrollablelistxml.data.Destination
import com.example.scrollablelistxml.data.DestinationDataSource
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
        Timber.d("XML LOG: Data masuk ke list kategori $category")
    }

    fun onDetailClicked(dest: Destination) {
        Timber.i("XML LOG: Tombol Detail ditekan. Item: ${dest.name} (ID: ${dest.id})")
    }

    fun onMapsClicked() {
        Timber.i("XML LOG: Tombol MAPS ditekan")
    }
}