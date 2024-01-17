package com.pixel.islami.ui.home.sebha

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.RotateAnimation
import androidx.fragment.app.Fragment
import com.pixel.islami.databinding.FragmentSebhaBinding

class SebhaFragment : Fragment() {

    private lateinit var binding: FragmentSebhaBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSebhaBinding.inflate(inflater, container, false)
        return binding.root
    }

    private var counter = 0
    private var seb7aCounter = 0
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.seb7aCounter.text = seb7aCounter.toString()
        binding.tasbe7Btn.text = "سبحان الله"

        binding.tasbe7Btn.setOnClickListener {
            binding.bodyOfSeb7a.rotation = binding.bodyOfSeb7a.rotation + 5F
            seb7aCounter++
            binding.seb7aCounter.text = seb7aCounter.toString()
            if (seb7aCounter == 33) {
                counter++
                seb7aCounter = 0
                binding.seb7aCounter.text = "0"
            }
            when (counter) {
                1 -> {
                    binding.tasbe7Btn.text = "الحمد لله"
                }
                2 -> {
                    binding.tasbe7Btn.text = "الله اكبر"
                }
                3 -> {
                    counter = 0
                    binding.tasbe7Btn.text = "سبحان الله"
                    seb7aCounter = 0
                }
            }
        }
    }


}