package com.demo.nerdeyesem.presentation.fragments

import android.Manifest
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.demo.nerdeyesem.Injector
import com.demo.nerdeyesem.R
import com.demo.nerdeyesem.presentation.adapters.RestaurantAdapter
import com.demo.nerdeyesem.presentation.extensions.hideKeyboard
import com.demo.nerdeyesem.presentation.viewmodels.RestaurantsViewModel
import com.demo.nerdeyesem.presentation.viewmodels.RestaurantsViewModelFactory
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import pub.devrel.easypermissions.EasyPermissions
import pub.devrel.easypermissions.PermissionRequest


class RestaurantsFragment : Fragment(), EasyPermissions.PermissionCallbacks {
    private val permissions = arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION)
    private val viewModelFactory by lazy {
        RestaurantsViewModelFactory(Injector.instance().restaurantOrchestrator())
    }
    private val viewModel by viewModels<RestaurantsViewModel> { viewModelFactory }
    private val adapter by lazy { RestaurantAdapter { id -> onRestaurantClick(id) } }

    private lateinit var city: TextInputEditText
    private lateinit var restaurants: RecyclerView
    private lateinit var placeholder: TextView
    private lateinit var progress: ProgressBar
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
            restaurants?.let {
                placeholder.isVisible = it.isEmpty()
                this.restaurants.isVisible = it.isNotEmpty()
                adapter.submitList(it)
            }
        })
        viewModel.showPlaceholder().observe(this.viewLifecycleOwner, { showPlaceholder ->
            restaurants.isVisible = !showPlaceholder
            placeholder.isVisible = showPlaceholder ?: false
        })
        viewModel.showProgress().observe(this.viewLifecycleOwner, { showProgress ->
            progress.isVisible = showProgress ?: false
        })
        viewModel.showError().observe(this.viewLifecycleOwner, { message ->
            message?.let { showError(message) }
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
        showError(getString(R.string.error_permissions_denied))
    }

    private fun initView(view: View) {
        city = view.findViewById(R.id.city)
        city.setOnEditorActionListener { _, actionId, _ ->
            hideKeyboard()
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                viewModel.cityLocation(city.editableText.toString())
            }
            true
        }
        restaurants = view.findViewById(R.id.restaurants)
        restaurants.adapter = adapter
        placeholder = view.findViewById(R.id.placeholder)
        progress = view.findViewById(R.id.progress)
        fab = view.findViewById(R.id.fab)
        fab.setOnClickListener { checkLocationPermissions() }
    }

    private fun onRestaurantClick(id: String) {
        val action = RestaurantsFragmentDirections.actionRestaurantDetails(id)
        Navigation.findNavController(requireView()).navigate(action)
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
        city.text?.clear()
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

    private fun showError(message: String) {
        Snackbar.make(requireView(), message, Snackbar.LENGTH_SHORT)
            .setBackgroundTint(ContextCompat.getColor(requireContext(), R.color.red))
            .setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
            .show()
    }
}