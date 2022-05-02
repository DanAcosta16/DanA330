package com.example.worddictionary.definition

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.worddictionary.R
import com.example.worddictionary.database.WordDatabase
import com.example.worddictionary.databinding.FragmentDefinitionBinding
import com.example.worddictionary.databinding.FragmentWordsBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


class DefinitionFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentDefinitionBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_definition, container, false)
        // Inflate the layout for this fragment
        val args: DefinitionFragmentArgs by navArgs()
        val argument = args.word!!
        val application = requireNotNull(this.activity).application
        val database = WordDatabase.getInstance(application).wordDao
        val viewModelFactory = DefinitionViewModelFactory(argument, database)
        val definitionViewModel = ViewModelProvider(this, viewModelFactory).get(
            DefinitionViewModel::class.java)
        binding.definitionViewModel = definitionViewModel
        binding.wordID.text = definitionViewModel.myWord.wordId
        binding.shortdef1.text = definitionViewModel.myWord.defOne
        binding.shortdef2.text = definitionViewModel.myWord.defTwo
        binding.shortdef3.text = definitionViewModel.myWord.defThree
        binding.addWordButton.setOnClickListener {
            lifecycleScope.launch {
                if(definitionViewModel.alreadyAdded()){
                    Snackbar.make(
                        it,
                        "Word already exists in database.",
                        Snackbar.LENGTH_LONG
                    ).show()
                }
                else{
                    definitionViewModel.addWordToDatabase()
                    Snackbar.make(
                        it,
                        "Word added to database.",
                        Snackbar.LENGTH_LONG
                    ).show()
                }

            }

        }
        binding.searchAgainButton.setOnClickListener {
            findNavController().navigate(R.id.action_definitionFragment_to_searchFragment)
        }

        return binding.root
    }


}