package com.pixel.islami.ui.home

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.pixel.islami.R
import com.pixel.islami.databinding.ActivityMainBinding
import com.pixel.islami.ui.Constants
import com.pixel.islami.ui.home.hadith.HadithFragment
import com.pixel.islami.ui.home.quran.QuranFragment
import com.pixel.islami.ui.home.radio.RadioFragment
import com.pixel.islami.ui.home.sebha.SebhaFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // applyTheme(this, isDarkModeEnabled(this))
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        changeNightMode()
        initView()
    }

    private fun changeNightMode() {
        binding.nightModeSwitcher.setOnClickListener {
            toggleTheme(this)
        }
    }

    private fun isDarkModeEnabled(context: Context): Boolean {
        val prefs = getSharedPreferences(Constants.MY_PREF, Context.MODE_PRIVATE)
        return prefs.getBoolean(Constants.IS_DARK_MODE, false)
    }

    private fun toggleTheme(context: Context) {
        val currentTheme = isDarkModeEnabled(context)
        saveDarkModeState(context, !currentTheme)
        applyTheme(context, !currentTheme)
    }

    private fun applyTheme(context: Context, newTheme: Boolean) {
        when (newTheme) {
            false -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }

            true -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
        }
        (context as? Activity)?.recreate()
    }

    private fun saveDarkModeState(context: Context, newTheme: Boolean) {
        val editor = getSharedPreferences(Constants.MY_PREF, Context.MODE_PRIVATE).edit()
        editor.putBoolean(Constants.IS_DARK_MODE, newTheme)
        editor.apply()
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
