package com.example.worddictionary.words

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.worddictionary.R
import com.example.worddictionary.database.Word
import com.example.worddictionary.database.WordDatabase
import com.example.worddictionary.databinding.FragmentWordsBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch



class WordsFragment : Fragment(R.layout.fragment_words) {

    lateinit var viewModel: WordsViewModel
    private lateinit var binding: FragmentWordsBinding
    private val adapter = WordsListAdapter()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        setHasOptionsMenu(true)
        binding = FragmentWordsBinding.inflate(inflater)
        val application = requireNotNull(this.activity).application
        val database = WordDatabase.getInstance(application).wordDao
        val viewModelFactory = WordsViewModelFactory(database)
        viewModel = ViewModelProvider(this, viewModelFactory).get(
            WordsViewModel::class.java)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel


        viewModel.dictWords.observe(this.viewLifecycleOwner) { list ->
            adapter.submitList(list)
        }
        binding.dictWords.adapter = adapter
        adapter.setOnItemClickListener(object : WordsListAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {
                val word = viewModel.dictWords.value?.get(position)
                if (word != null) {
                    if(word.active){
                        lifecycleScope.launch {
                            val newWord = Word(word.wordId, word.image, word.defOne, word.defTwo,
                            word.defThree, false, 0.5f)
                            viewModel.updateWord(newWord)
                            Snackbar.make(binding.dictWords, "The word ${word.wordId} has been" +
                                    " deactivated",
                                Snackbar.LENGTH_LONG).show()


                        }
                    }
                    else{
                        lifecycleScope.launch {
                            val newWord = Word(word.wordId, word.image, word.defOne, word.defTwo,
                                word.defThree, true, 0.5f)
                            viewModel.updateWord(newWord)
                            Snackbar.make(binding.dictWords, "The word ${word.wordId} has been" +
                                    " activated",
                                Snackbar.LENGTH_LONG).show()
                        }
                    }
                }

//
            }

        })


        viewModel.currentFilter.observe(this.viewLifecycleOwner) {
            viewModel.dictWords.removeObservers(this.viewLifecycleOwner)
            viewModel.dictWords.observe(this.viewLifecycleOwner) { list ->
                adapter.submitList(list)
            }
        }
        binding.clearButton.setOnClickListener {
            lifecycleScope.launch {
                viewModel.clearData()
            }
        }



        return binding.root
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.overflow_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.add_word) {
            findNavController()
                .navigate(WordsFragmentDirections.actionWordsFragmentToSearchFragment())
        } else {
            viewModel.changeFilter(
                when (item.itemId) {
                    R.id.active -> WordsFilter.SHOW_ACTIVE
                    R.id.inactive -> WordsFilter.SHOW_INACTIVE
                    else -> WordsFilter.SHOW_ALL
                }
            )
        }
        return true
    }




}

