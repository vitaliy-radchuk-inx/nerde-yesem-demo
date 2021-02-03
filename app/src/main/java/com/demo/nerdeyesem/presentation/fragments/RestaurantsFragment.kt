package com.demo.nerdeyesem.presentation.fragments

import android.Manifest
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.demo.nerdeyesem.R
import com.demo.nerdeyesem.presentation.adapters.RestaurantAdapter
import com.demo.nerdeyesem.presentation.viewmodels.RestaurantsViewModel
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.snackbar.Snackbar
import pub.devrel.easypermissions.EasyPermissions
import pub.devrel.easypermissions.PermissionRequest


class RestaurantsFragment : Fragment(), EasyPermissions.PermissionCallbacks {
    private val permissions = arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION)
    private val viewModel by viewModels<RestaurantsViewModel>()
    private val adapter by lazy { RestaurantAdapter { id -> onRestaurantClick(id) } }

    private lateinit var restaurants: RecyclerView
    private lateinit var placeholder: TextView
    private lateinit var fab: ExtendedFloatingActionButton

    companion object {
        internal const val RC_LOCATION_PERMISSION = 10001
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_restaurants, container, false)
        initView(view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.restaurants().observe(this.viewLifecycleOwner, { restaurants ->
            restaurants?.let { adapter.submitList(it) }
        })

        viewModel.showPlaceholder().observe(this.viewLifecycleOwner, { showPlaceholder ->
            placeholder.isVisible = showPlaceholder ?: false
        })
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
        requestLocation()
    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        Snackbar.make(requireView(), "Permissions were denied!", Snackbar.LENGTH_SHORT).show()
    }

    private fun initView(view: View) {
        restaurants = view.findViewById(R.id.restaurants)
        restaurants.adapter = adapter
        placeholder = view.findViewById(R.id.placeholder)
        fab = view.findViewById(R.id.fab)
        fab.setOnClickListener { checkLocationPermissions() }
    }

    private fun onRestaurantClick(id: String) {
        //TODO: Open details
    }

    private fun checkLocationPermissions() {
        if (EasyPermissions.hasPermissions(requireContext(), *permissions)) {
            requestLocation()
        } else {
            requestLocationPermissions()
        }
    }

    @SuppressLint("MissingPermission")
    private fun requestLocation() {
        viewModel.requestLocation(requireContext())
    }

    private fun requestLocationPermissions() {
        EasyPermissions.requestPermissions(
            PermissionRequest.Builder(this, RC_LOCATION_PERMISSION, *permissions)
                .setRationale(R.string.request_location_permissions_dialog_message)
                .setPositiveButtonText(R.string.dialog_ok_button_title)
                .setNegativeButtonText(R.string.dialog_cancel_button_title)
                .build()
        )
    }
}