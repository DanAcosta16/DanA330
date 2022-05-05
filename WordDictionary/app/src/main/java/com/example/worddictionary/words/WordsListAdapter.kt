package com.example.worddictionary.words


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.worddictionary.database.Word
import com.example.worddictionary.databinding.CardLayoutBinding

class WordsListAdapter : ListAdapter<Word,
        WordsListAdapter.WordViewHolder>(DiffCallback) {

    private lateinit var mListener : onItemClickListener

    interface onItemClickListener{

        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener){

        mListener = listener

    }
    class WordViewHolder(
        private var binding: CardLayoutBinding,
        listener: onItemClickListener
    ) : RecyclerView.ViewHolder(binding.root)  {

        init {
            binding.cardView.setOnClickListener {
                listener.onItemClick(bindingAdapterPosition)
            }
        }

        fun bind(word: Word) {
            binding.word = word
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        return WordViewHolder(CardLayoutBinding.inflate(LayoutInflater.from(parent.context)), mListener)
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

