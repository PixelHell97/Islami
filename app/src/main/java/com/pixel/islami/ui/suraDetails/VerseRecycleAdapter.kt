package com.pixel.islami.ui.suraDetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pixel.islami.databinding.ItemVerseBinding

class VerseRecycleAdapter(private val verses: List<String>) :
    RecyclerView.Adapter<VerseRecycleAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemVerseBinding = ItemVerseBinding
            .inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val title = verses[position]
        holder.bind(title)
        onChapterClickListener?.let {onChapterClickListener ->
            holder.itemView.setOnClickListener {
                onChapterClickListener.onChapterClick(title, position)
            }
        }
    }

    override fun getItemCount(): Int = verses.size

    private var onChapterClickListener: OnChapterClickListener? = null

    fun interface OnChapterClickListener {
        fun onChapterClick(item: String, position: Int)
    }

    class ViewHolder(private val binding: ItemVerseBinding) : RecyclerView.ViewHolder(
        binding.root
    ) {
        fun bind(title: String) {
            binding.verseLine.text = title
        }
    }
}