package com.example.worddictionary.words

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.worddictionary.R
import com.example.worddictionary.database.WordDatabase
import com.example.worddictionary.databinding.FragmentWordsBinding
import com.example.worddictionary.definition.DefinitionViewModelFactory


/**
 * A simple [Fragment] subclass.
 * Use the [WordsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class WordsFragment : Fragment(R.layout.fragment_words) {

    private lateinit var binding: FragmentWordsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        binding = FragmentWordsBinding.inflate(inflater)
        val application = requireNotNull(this.activity).application
        val database = WordDatabase.getInstance(application).wordDao
        val viewModelFactory = WordsViewModelFactory(database)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(
            WordsViewModel::class.java)
//        if(viewModel.dbExists()){
//            binding.addWordMessage.visibility = View.GONE
//        }
        binding.dictWords.adapter = WordsListAdapter()

        return binding.root
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.overflow_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.add_word -> {
                view?.findNavController()?.navigate(R.id.action_wordsFragment_to_searchFragment)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


}