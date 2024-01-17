package com.pixel.islami.ui.home.radio

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.pixel.islami.databinding.FragmentRadioBinding

class RadioFragment : Fragment() {

    private lateinit var binding: FragmentRadioBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRadioBinding.inflate(inflater, container, false)
        return binding.root
    }
}