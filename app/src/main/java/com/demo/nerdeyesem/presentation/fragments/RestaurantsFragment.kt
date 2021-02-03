package com.demo.nerdeyesem.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.demo.nerdeyesem.R
import com.demo.nerdeyesem.presentation.adapters.RestaurantAdapter
import com.demo.nerdeyesem.presentation.viewmodels.RestaurantsViewModel


class RestaurantsFragment : Fragment() {
    private val viewModel: RestaurantsViewModel by viewModels()
    private val adapter by lazy { RestaurantAdapter { id -> onRestaurantClick(id) } }
    private lateinit var restaurants: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_restaurants, container, false)
        restaurants = view.findViewById(R.id.restaurants)
        restaurants.adapter = adapter
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.restaurants().observe(this.viewLifecycleOwner, { restaurants ->
            restaurants?.let { adapter.submitList(it) }
        })
    }

    private fun onRestaurantClick(id: String) {
        //TODO: Open details
    }
}