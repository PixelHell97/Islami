package com.pixel.islami.ui.suraDetails

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pixel.islami.databinding.ActivitySuraDetailsBinding
import com.pixel.islami.ui.Constants

class SuraDetailsActivity : AppCompatActivity() {

    private var chapterIndex: Int = 0
    private lateinit var chapterName: String
    private lateinit var binding: ActivitySuraDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySuraDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.appBar)
        chapterIndex = intent.getIntExtra(Constants.CHAPTER_INDEX, 0)
        chapterName = intent.getStringExtra(Constants.CHAPTER_TITLE) ?: ""
        initView()
        readSuraFile()
    }

    private fun readSuraFile() {
        val inputSteam = assets.open("$chapterIndex.txt")
        val fileContent = inputSteam.bufferedReader().use { it.readText() }
        val line = fileContent.trim().split("\n")
        initRecycler(line)
    }

    private lateinit var adapter: VerseRecycleAdapter
    private fun initRecycler(line: List<String>) {
        adapter = VerseRecycleAdapter(line)
        binding.suraContent
            .verseRecycler
            .adapter = adapter
    }

    private fun initView() {
        binding.suraContent.verseTitle.text = chapterName
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}