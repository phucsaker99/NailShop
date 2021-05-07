package com.example.nailshop.utils

import android.view.Gravity
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar

fun View.showSnackBar(message: String, length: Int = Snackbar.LENGTH_LONG) {
    Snackbar.make(this, message, length).show()
}

fun TextView.setLeftDrawable(drawable: Int) {
    setCompoundDrawablesWithIntrinsicBounds(drawable, 0, 0, 0)
    gravity = Gravity.CENTER_VERTICAL
    compoundDrawablePadding = 10
}
