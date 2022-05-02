package com.example.worddictionary.words

import android.content.Context
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.worddictionary.R
import com.example.worddictionary.database.Word
import com.example.worddictionary.databinding.CardLayoutBinding

class WordsListAdapter : ListAdapter<Word, WordsListAdapter.WordViewHolder>(DiffCallback) {
    class WordViewHolder(
        private var binding: CardLayoutBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(word: Word) {
            binding.word = word
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        return WordViewHolder(CardLayoutBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val word = getItem(position)
        holder.bind(word)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Word>() {
        override fun areItemsTheSame(oldItem: Word, newItem: Word): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Word, newItem: Word): Boolean {
            return oldItem.wordId == newItem.wordId
        }
    }
}

