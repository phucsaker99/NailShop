package com.example.nailshop.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class Manicurist(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo
    val id: Int,
    @ColumnInfo
    val name: String,
    @ColumnInfo
    val image: String,
    @ColumnInfo
    val gender: Int,
    @ColumnInfo
    val store: String,
    @ColumnInfo
    val rate: Float,
    @ColumnInfo
    val like: Int
) : Parcelable
