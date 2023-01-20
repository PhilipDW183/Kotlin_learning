package com.example.wordsapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wordsapp.databinding.FragmentLetterListBinding


class WordListFragment : Fragment() {

    // create a companion object associated with WordListFragment
    companion object {
        const val LETTER = "letter"
        const val SEARCH_PREFIX = "https://www.google.com/search?q="
    }

    // add a binding variable
    private var _binding: FragmentLetterListBinding? = null
    // get only variable
    private val binding get() = _binding!!

    private lateinit var letterId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // if the arguments are null then the lambda will not execute
        arguments?.let {
            letterId = it.getString(LETTER).toString()
        }
    }

    // inflate the layout onCreateView()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLetterListBinding.inflate(inflater, container, false)
        return binding.root
    }

    // bind the recycler view
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val letterId = letterId

        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = WordAdapter(letterId, requireContext())

        // Adds a [DividerItemDecoration] between items
        recyclerView.addItemDecoration(
            DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}