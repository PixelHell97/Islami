package com.pixel.islami.ui.hadithDetails

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pixel.islami.databinding.ActivityHadithDetailsBinding
import com.pixel.islami.model.Hadith
import com.pixel.islami.ui.Constants

class HadithDetailsActivity : AppCompatActivity() {

    private var hadith: Hadith? = null
    private lateinit var binding: ActivityHadithDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHadithDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.appBar)
        getHadith()
        initView()

    }

    private fun initView() {
        binding.hadithContent
            .hadithTitle.text = hadith?.title
        binding.hadithContent
            .hadith.text = hadith?.hadith
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    private fun getHadith() {
        hadith = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(Constants.HADITH_KEY, Hadith::class.java)
        } else {
            intent.getParcelableExtra(Constants.HADITH_KEY)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}