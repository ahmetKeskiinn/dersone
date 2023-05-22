package com.example.listexample.first

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.listexample.Movie
import com.example.listexample.MovieItem
import com.example.listexample.databinding.FragmentFirstBinding

class FirstFragment : Fragment() {
    var binding: FragmentFirstBinding? = null

    var selectedItem: MovieItem? = null

    private lateinit var firstAdapter: FirstAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRv()
        binding?.navigateButton?.setOnClickListener {
            val action = FirstFragmentDirections.actionFirstFragmentToSecondFragment(selectedItem)
            binding?.root?.let { it1 -> Navigation.findNavController(it1).navigate(action) }
        }
    }

    fun initRv() {
        context?.let {
            firstAdapter = FirstAdapter(it, object : ItemClicked {
                override fun isItemClicked(currentItem: MovieItem) {
                    val list = getMockList()
                    val newList = arrayListOf<MovieItem>()
                    list.forEach {
                        if (it.id == currentItem.id) {
                            selectedItem = currentItem
                            newList.add(
                                MovieItem(
                                    id = it.id,
                                    name = it.name,
                                    content = it.content,
                                    isSelected = true
                                )
                            )
                        } else {
                            newList.add(it)
                        }
                    }
                    firstAdapter.submitList(newList)
                }

            })
        }

        binding?.let {
            it.rvFirst.layoutManager = LinearLayoutManager(context)
            it.rvFirst.adapter = firstAdapter
        }
        submitToList()
    }

    fun submitToList() {
        val mockData = generateMockMovie()
        submitList(mockData.movieItem)
    }

    fun submitList(list: List<MovieItem>?) {
        firstAdapter.submitList(list)
    }

    fun generateMockMovie() = Movie(id = 0, movieItem = getMockList())

    fun getMockList() = listOf(
        MovieItem(id = 0, name = "a", "aa"),
        MovieItem(id = 1, name = "b", "bb"),
        MovieItem(id = 2, name = "c", "cc"),
    )

}