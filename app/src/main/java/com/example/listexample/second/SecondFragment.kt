package com.example.listexample.second

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.listexample.Movie
import com.example.listexample.MovieItem
import com.example.listexample.R
import com.example.listexample.databinding.FragmentSecondBinding
import com.example.listexample.first.ItemClicked

class SecondFragment : Fragment() {

    var binding: FragmentSecondBinding? = null
    private lateinit var secondAdapter: SecondAdapter
    val params: SecondFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRv()
    }

    fun initRv() {
        context?.let {
            secondAdapter = SecondAdapter(it, object : ItemClicked{
                override fun isItemClicked(currentItem: MovieItem) {

                }

            })
        }
        binding?.let {
            it.rv.layoutManager = LinearLayoutManager(context)
            it.rv.adapter = secondAdapter
        }
        submitToList()
    }

    fun submitToList() {
        val mockData = generateMockMovie()
        val newList = arrayListOf<MovieItem>()
        mockData.movieItem?.forEach {
            if (params.item?.id == it.id) {
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
        submitList(newList)
    }

    fun submitList(list: List<MovieItem>?) {
        secondAdapter.submitList(list)
    }
    fun generateMockMovie() = Movie(id = 0, movieItem = getMockList())

    fun getMockList() = listOf(
        MovieItem(id = 0, name = "a", "aa"),
        MovieItem(id = 1, name = "b", "bb"),
        MovieItem(id = 2, name = "c", "cc"),
        MovieItem(id = 3, name = "d", "dd"),
    )
}