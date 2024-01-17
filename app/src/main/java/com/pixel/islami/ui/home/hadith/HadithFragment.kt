package com.pixel.islami.ui.home.hadith

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.pixel.islami.databinding.FragmentHadithBinding
import com.pixel.islami.model.Hadith
import com.pixel.islami.ui.Constants
import com.pixel.islami.ui.hadithDetails.HadithDetailsActivity

class HadithFragment : Fragment() {

    private lateinit var binding: FragmentHadithBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHadithBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        readHadithTitle()
    }

    private fun readHadithTitle() {
        val ahadeethList = mutableListOf<Hadith>()
        val inputStream = context?.assets?.open("ahadeeth.txt")
        val fileContent = inputStream?.bufferedReader()?.use { it.readText() }
        val ahadith = fileContent?.trim()?.split("#")
        ahadith?.forEach { hadith ->
            val line = hadith.trim().split("\n")
            val title = line[0]
            val newList = line.toMutableList().apply {
                removeAt(0)
            }
            val hadithContent = newList.joinToString("\n")
            val hadithDetails: Hadith = Hadith(title, hadithContent)
            ahadeethList.add(hadithDetails)
        }
        showHadithTitle(ahadeethList)
    }

    private lateinit var hadithAdaptor: HadithRecycleAdapter
    private fun showHadithTitle(ahadeethList: MutableList<Hadith>) {
        hadithAdaptor = HadithRecycleAdapter(ahadeethList)
        binding.hadithRecycleView.adapter = hadithAdaptor
        hadithAdaptor.onHadithClickListener = HadithRecycleAdapter.OnHadithClickListener{item, _ ->
            startHadithDetailsActivity(item)
        }

    }

    private fun startHadithDetailsActivity(hadith: Hadith) {
        val intent = Intent(context, HadithDetailsActivity::class.java)
        intent.putExtra(Constants.HADITH_KEY, hadith)
        startActivity(intent)
    }
}