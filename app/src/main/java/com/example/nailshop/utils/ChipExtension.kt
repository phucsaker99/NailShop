package com.example.nailshop.utils

import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

fun ChipGroup.listColor(list: String, chipListener: (String) -> Unit) {
    val ds = list.split(",").map { it.trim() }
    for (i in ds) {
        val chip = Chip(context)
        chip.setOnClickListener {
            chipListener(i)
        }
        chip.text = i
        addView(chip)
    }
}

fun ChipGroup.listTag(list: String, chipListener: (String) -> Unit) {
    val ds = list.split(",").map { it.trim() }
    for (i in ds) {
        val chip = Chip(context)
        chip.setOnClickListener {
            chipListener(i)
        }
        chip.text = i
        addView(chip)
    }
}
