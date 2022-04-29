package com.example.worddictionary.definition

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.worddictionary.R
import com.example.worddictionary.databinding.FragmentDefinitionBinding
import com.example.worddictionary.databinding.FragmentWordsBinding


class DefinitionFragment : Fragment(R.layout.fragment_definition) {

    private lateinit var binding: FragmentDefinitionBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val args: DefinitionFragmentArgs by navArgs()
        binding = FragmentDefinitionBinding.inflate(inflater)
        binding.wordID.text = args.word?.wordId
        binding.shortdef1.text = args.word?.defOne
        binding.shortdef2.text = args.word?.defTwo
        binding.shortdef3.text = args.word?.defThree
        binding.image.setImageResource()
        return binding.root
    }


}