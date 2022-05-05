package com.example.worddictionary.search

//import android.content.Context
//import android.content.DialogInterface
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.ImageView
//import android.widget.ListAdapter
//import android.widget.SearchView
//import android.widget.TextView
//import androidx.recyclerview.widget.DiffUtil
//import androidx.recyclerview.widget.RecyclerView
//import com.example.worddictionary.R
//import com.example.worddictionary.database.Word
//import com.example.worddictionary.databinding.CardLayoutBinding
//import com.example.worddictionary.databinding.SearchItemBinding
//import com.example.worddictionary.words.WordsListAdapter
//import org.json.JSONArray
//import org.w3c.dom.Text

//class SearchWordAdapter (
//    private val suggestedWords: List<String>,
//    private val onSearchClickListener: OnSearchClickListener
//) : RecyclerView.Adapter<SearchWordAdapter.SearchViewHolder>() {
////    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
////        val textView: TextView = view.findViewById(R.id.item_title)
////        val imageView: ImageView = view.findViewById(R.id.item_image)
////    }
//    class SearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
//        val textView: TextView = itemView.findViewById(R.id.search_text)
//}
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
//        val adapterLayout = LayoutInflater.from(parent.context)
//            .inflate(R.layout.search_item, parent, false)
//
//        return SearchViewHolder(adapterLayout)
//    }
//
//    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
//        val item = suggestedWords[position]
//        holder.textView.text = item
//
//        holder.itemView.setOnClickListener {
//            onSearchClickListener.onSearchItemClicked(position)
//        }
//    }
//
//    override fun getItemCount(): Int {
//        return suggestedWords.size
//    }
//}
