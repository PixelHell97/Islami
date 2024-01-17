package com.pixel.islami.ui.home.hadith

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pixel.islami.databinding.ItemHadithBinding
import com.pixel.islami.model.Hadith

class HadithRecycleAdapter(private val hadithList: List<Hadith>) :
    RecyclerView.Adapter<HadithRecycleAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemHadithBinding = ItemHadithBinding
            .inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val hadith = hadithList[position]
        holder.bind(hadith.title)
        onHadithClickListener?.let { onHadithClickListener ->
            holder.itemView.setOnClickListener {
                onHadithClickListener.onHadithClick(hadith, position)
            }
        }
    }

    override fun getItemCount(): Int = hadithList.size

    var onHadithClickListener: OnHadithClickListener? = null

    fun interface OnHadithClickListener {
        fun onHadithClick(item: Hadith, position: Int)
    }

    class ViewHolder(private val binding: ItemHadithBinding) : RecyclerView.ViewHolder(
        binding.root
    ) {
        fun bind(title: String) {
            binding.hadithName.text = title
        }
    }
}