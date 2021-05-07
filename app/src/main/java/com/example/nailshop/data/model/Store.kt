package com.example.nailshop.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class Store(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo
    val id: Int,
    @ColumnInfo
    val name: String,
    @ColumnInfo
    val address: String,
    @ColumnInfo
    val listManicurist: String,
    @ColumnInfo
    val timeDay: String,
    @ColumnInfo
    val listNail: String
) : Parcelable
