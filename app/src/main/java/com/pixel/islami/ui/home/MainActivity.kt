package com.pixel.islami.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.pixel.islami.R
import com.pixel.islami.databinding.ActivityMainBinding
import com.pixel.islami.ui.home.hadith.HadithFragment
import com.pixel.islami.ui.home.quran.QuranFragment
import com.pixel.islami.ui.home.radio.RadioFragment
import com.pixel.islami.ui.home.sebha.SebhaFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
    }

    private fun initView() {
        binding.content
            .bottomNavBar
            .setOnItemSelectedListener { item ->
                val fragment: Fragment = when (item.itemId) {
                    R.id.navigation_quran -> QuranFragment()

                    R.id.navigation_hadith -> HadithFragment()

                    R.id.navigation_sebha -> SebhaFragment()

                    R.id.navigation_radio -> RadioFragment()

                    else -> QuranFragment()
                }
                pushFragment(fragment)
                true
            }
        binding.content
            .bottomNavBar
            .selectedItemId = R.id.navigation_quran
    }

    private fun pushFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}