package com.demo.nerdeyesem.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.demo.nerdeyesem.R

class RestaurantDetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val args: RestaurantDetailsFragmentArgs by navArgs()
        return inflater.inflate(R.layout.fragment_restaurant_details, container, false)
    }
}