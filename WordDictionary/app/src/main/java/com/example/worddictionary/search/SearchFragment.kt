package com.example.worddictionary.search

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.worddictionary.databinding.FragmentSearchBinding
import com.example.worddictionary.network.DictionaryApi
import com.example.worddictionary.database.Word
import kotlinx.coroutines.launch
import org.json.JSONArray



class SearchFragment : Fragment(){

    private val viewModel: SearchViewModel by lazy {
        ViewModelProvider(this).get(SearchViewModel::class.java)
    }
    private lateinit var binding: FragmentSearchBinding
//    private val suggestions = viewModel.suggestedWords.value


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
//        val application = requireNotNull(activity).application
        binding = FragmentSearchBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
//        viewModel.suggestedWords.observe(viewLifecycleOwner, Observer { suggestedWords ->
//            if(null != suggestedWords){
//                val searchList = viewModel.suggestedWords.value!!
//                var searchAdapter = SearchWordAdapter(searchList, this)
//                binding.searchRecycler.layoutManager = LinearLayoutManager(application)
//                binding.searchRecycler.adapter = searchAdapter
//                searchAdapter.notifyDataSetChanged()
//                binding.searchRecycler.visibility = View.VISIBLE
//            }
//        })
        binding.searchButton.setOnClickListener { apiSearch(binding.searchView.query.toString()) }
        return binding.root
    }

    private fun apiSearch(search: String){
        lifecycleScope.launch {
            val response = DictionaryApi.retrofitService.getWord(search)
            val jsonString = response.body()
            if (jsonString != null) {
                Log.d("SearchFragment", jsonString)
            }
            if (jsonString?.startsWith("[{\"meta\":") == true) {
                val word: Word = viewModel.parseJsontoWord(search, jsonString)
                val directions = SearchFragmentDirections.actionSearchFragmentToDefinitionFragment(
                    word)
                findNavController().navigate(directions)
            }
            else{
                val json = JSONArray(jsonString)
                val list: MutableList<String> = arrayListOf()
                for (i in 0 until json.length()) {
                    val word = json.getJSONObject(i).toString()
                    list.add(word)
                }
                viewModel.setSuggestedWords(list)

            }
        }

    }

//    override fun onSearchItemClicked(position: Int) {
//        val clickedItem = suggestions?.get(position)
//        if (clickedItem != null) {
//            val newSearch = clickedItem.toString()
//            apiSearch(newSearch)
//        }
//    }


}