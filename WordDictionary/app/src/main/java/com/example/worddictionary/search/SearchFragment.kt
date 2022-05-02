package com.example.worddictionary.search

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.worddictionary.R
import com.example.worddictionary.databinding.FragmentSearchBinding
import com.example.worddictionary.network.DictionaryApi
import com.example.worddictionary.database.Word
import kotlinx.coroutines.launch
import org.json.JSONArray


/**
 * A simple [Fragment] subclass.
 * Use the [SearchFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SearchFragment : Fragment(R.layout.fragment_search) {

    private lateinit var binding: FragmentSearchBinding
    var imageURL = "https://www.merriam-webster.com/assets/mw/static/art/dict/"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSearchBinding.inflate(inflater)
        binding.searchButton.setOnClickListener { apiSearch() }
        return binding.root
    }

    private fun apiSearch(){
        val search: CharSequence = binding.searchView.query
        val searchString = search.toString()
        lifecycleScope.launch {
            val response = DictionaryApi.retrofitService.getWord(searchString)
            val jsonString = response.body()
            if (jsonString != null) {
                Log.d("SearchFragment", jsonString)
            }
            if (jsonString?.startsWith("[{\"meta\":") == true) {
                val word: Word = parseJsontoWord(searchString, jsonString)
                val directions = SearchFragmentDirections.actionSearchFragmentToDefinitionFragment(
                    word)
                findNavController().navigate(directions)
            }
        }

    }

    private fun parseJsontoWord(wordId: String, jsonString: String): Word {
        val json = JSONArray(jsonString)
        val entry = json.getJSONObject(0)
        val shortDef = entry.getJSONArray("shortdef")
        if(entry.has("art")) {
            val art = entry.getJSONObject("art")
            val artID = art.getString("artid")
            val artURL = "$imageURL$artID.gif"
            return constructWord(wordId, artURL, shortDef)
        }
        return constructWord(wordId, null, shortDef)

    }

    private fun constructWord(wordId: String, image: String?, json: JSONArray): Word {
        val word = when (json.length()) {
            0 -> Word(wordId, null, "No definition available")
            1 -> Word(wordId, image, json.getString(0))
            2 -> Word(wordId, image, json.getString(0), json.getString(1))
            else -> Word(
                wordId, image,
                json.getString(0),
                json.getString(1),
                json.getString(2)
            )
        }
        return word
    }

}