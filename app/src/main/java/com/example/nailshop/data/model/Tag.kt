package com.example.nailshop.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Tag(
    val name: String,
    val image: String
) : Parcelable
