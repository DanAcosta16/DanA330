package com.example.worddictionary.definition

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.worddictionary.R
import com.example.worddictionary.databinding.FragmentDefinitionBinding
import com.example.worddictionary.databinding.FragmentWordsBinding


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
        val viewModelFactory = DefinitionViewModelFactory(argument)
        val definitionViewModel = ViewModelProvider(this, viewModelFactory).get(
            DefinitionViewModel::class.java)
        binding.definitionViewModel = definitionViewModel
        binding.wordID.text = definitionViewModel.myWord.wordId
        binding.shortdef1.text = definitionViewModel.myWord.defOne
        binding.shortdef2.text = definitionViewModel.myWord.defTwo
        binding.shortdef3.text = definitionViewModel.myWord.defThree
        return binding.root
    }


}