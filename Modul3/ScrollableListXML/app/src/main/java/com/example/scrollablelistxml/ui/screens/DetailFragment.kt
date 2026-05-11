package com.example.scrollablelistxml.ui.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.scrollablelistxml.data.DestinationDataSource
import com.example.scrollablelistxml.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = arguments?.getInt("DEST_ID") ?: 0
        val data = DestinationDataSource.dummyDestinations.find { it.id == id }

        data?.let {
            binding.tvDetailName.text = it.name
            binding.tvDetailDesc.text = it.description
            binding.imgDetail.setImageResource(it.imageResId)

            val subtitle = "${it.location}, ${it.rating}"
            binding.tvDetailSubtitle.text = subtitle
        }

        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}