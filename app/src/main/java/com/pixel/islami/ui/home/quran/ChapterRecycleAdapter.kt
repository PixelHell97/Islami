package com.pixel.islami.ui.home.quran

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pixel.islami.databinding.ItemChapterBinding

class ChapterRecycleAdapter(private val chaptersList: List<String>) :
    RecyclerView.Adapter<ChapterRecycleAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemChapterBinding = ItemChapterBinding
            .inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val title = chaptersList[position]
        holder.bind(title)
        onChapterClickListener?.let {onChapterClickListener ->
            holder.itemView.setOnClickListener {
                onChapterClickListener.onChapterClick(title, position)
            }
        }
    }

    override fun getItemCount(): Int = chaptersList.size

    var onChapterClickListener: OnChapterClickListener? = null

    fun interface OnChapterClickListener {
        fun onChapterClick(item: String, position: Int)
    }

    class ViewHolder(private val binding: ItemChapterBinding) : RecyclerView.ViewHolder(
        binding.root
    ) {
        fun bind(title: String) {
            binding.chapterName.text = title
        }
    }
}