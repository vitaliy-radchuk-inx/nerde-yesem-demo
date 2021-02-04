package com.demo.nerdeyesem.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.demo.nerdeyesem.Injector
import com.demo.nerdeyesem.R
import com.demo.nerdeyesem.presentation.models.RestaurantModel
import com.demo.nerdeyesem.presentation.viewmodels.RestaurantDetailsViewModel
import com.demo.nerdeyesem.presentation.viewmodels.RestaurantDetailsViewModelFactory

class RestaurantDetailsFragment : Fragment() {

    private val viewModelFactory by lazy {
        RestaurantDetailsViewModelFactory(Injector.instance().restaurantsDetailsOrchestrator())
    }
    private val viewModel by viewModels<RestaurantDetailsViewModel> { viewModelFactory }
    private lateinit var restaurantName: TextView
    private lateinit var rating: RatingBar
    private lateinit var ratingText: TextView
    private lateinit var cuisines: TextView
    private lateinit var city: TextView
    private lateinit var address: TextView
    private lateinit var phones: TextView
    private lateinit var webSite: TextView
    private lateinit var timings: TextView
    private lateinit var highlights: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val args: RestaurantDetailsFragmentArgs by navArgs()
        viewModel.loadDetails(args.restaurantId)
        val view = inflater.inflate(R.layout.fragment_restaurant_details, container, false)
        initView(view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.details().observe(this.viewLifecycleOwner, { restaurant ->
            restaurant?.let { bindDetails(it) }
        })
    }

    private fun initView(view: View) {
        restaurantName = view.findViewById(R.id.restaurantName)
        rating = view.findViewById(R.id.rating)
        ratingText = view.findViewById(R.id.ratingText)
        cuisines = view.findViewById(R.id.cuisines)
        city = view.findViewById(R.id.city)
        address = view.findViewById(R.id.address)
        phones = view.findViewById(R.id.phones)
        webSite = view.findViewById(R.id.webSite)
        timings = view.findViewById(R.id.timings)
        highlights = view.findViewById(R.id.highlights)
    }

    private fun bindDetails(restaurant: RestaurantModel) {
        restaurantName.text = restaurant.name
        rating.rating = restaurant.rating
        ratingText.text = formatRating(restaurant.rating, restaurant.votes)
        cuisines.text = restaurant.cuisines
        city.text = restaurant.city
        address.text = restaurant.address
        phones.text = restaurant.phones
        webSite.text = restaurant.url
        timings.text = restaurant.timings
        highlights.text = restaurant.highlights
    }

    private fun formatRating(rating: Float, votes: Long): String {
        return getString(R.string.restaurant_rating_text, rating, votes)
    }
}