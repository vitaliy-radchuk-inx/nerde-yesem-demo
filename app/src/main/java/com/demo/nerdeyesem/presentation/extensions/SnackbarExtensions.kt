package com.demo.nerdeyesem.presentation.extensions

import android.view.View
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import com.demo.nerdeyesem.R
import com.google.android.material.snackbar.Snackbar

fun View.showError(@StringRes messageRes: Int, length: Int = Snackbar.LENGTH_SHORT) {
    Snackbar.make(this, messageRes, length).setUp().show()
}

fun View.showError(message: String, length: Int = Snackbar.LENGTH_SHORT) {
    Snackbar.make(this, message, length).setUp().show()
}

private fun Snackbar.setUp(): Snackbar {
    this.setBackgroundTint(ContextCompat.getColor(view.context, R.color.red))
        .setTextColor(ContextCompat.getColor(view.context, R.color.white))
        .also { return this }
}
