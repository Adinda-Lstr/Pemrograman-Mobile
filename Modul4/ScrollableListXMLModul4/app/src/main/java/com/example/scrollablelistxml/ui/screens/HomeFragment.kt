package com.example.scrollablelistxml.ui.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.scrollablelistxml.R
import com.example.scrollablelistxml.adapter.DestinationAdapter
import com.example.scrollablelistxml.adapter.TrendingAdapter
import com.example.scrollablelistxml.databinding.FragmentHomeBinding
import com.example.scrollablelistxml.viewmodel.DestinationViewModel
import com.example.scrollablelistxml.viewmodel.DestinationViewModelFactory
import kotlinx.coroutines.launch

class HomeFragment : Fragment(R.layout.fragment_home) {

    private val viewModel: DestinationViewModel by viewModels {
        DestinationViewModelFactory("Wisata Kalimantan Selatan")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentHomeBinding.bind(view)

        val destinationAdapter = DestinationAdapter(
            list = emptyList(),
            onDetailClick = { dest ->
                viewModel.onDetailClicked(dest)
                val bundle = Bundle().apply {
                    putInt("DEST_ID", dest.id)
                }
                findNavController().navigate(R.id.action_HomeFragment_to_DetailFragment, bundle)
            },
            onMapsClick = {
                viewModel.onMapsClicked()
            }
        )
        binding.rvDestinations.adapter = destinationAdapter

        val trendingAdapter = TrendingAdapter(emptyList()) { dest ->
            viewModel.onDetailClicked(dest)
            val bundle = Bundle().apply {
                putInt("DEST_ID", dest.id)
            }
            findNavController().navigate(R.id.action_HomeFragment_to_DetailFragment, bundle)
        }
        binding.rvTrending.adapter = trendingAdapter


        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.destinations.collect { data ->
                    destinationAdapter.setData(data)
                    trendingAdapter.setData(data)
                }
            }
        }
    }
}