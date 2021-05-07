package com.example.nailshop.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.net.URL

@Entity
@Parcelize
data class Nail(
    @PrimaryKey
    @ColumnInfo
    val id: Int,
    @ColumnInfo
    val name: String,
    @ColumnInfo
    val tag: String,
    @ColumnInfo
    val image: String,
    @ColumnInfo
    val rate: Float,
    @ColumnInfo
    val like: Int,
    @ColumnInfo
    val color:String,
    @ColumnInfo
    val price: String,
    @ColumnInfo
    val description: String
) : Parcelable



