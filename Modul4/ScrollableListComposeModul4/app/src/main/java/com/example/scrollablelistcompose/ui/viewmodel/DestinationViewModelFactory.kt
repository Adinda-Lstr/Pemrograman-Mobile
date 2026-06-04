package com.example.scrollablelistcompose.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class DestinationViewModelFactory(private val category: String) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DestinationViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DestinationViewModel(category) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}