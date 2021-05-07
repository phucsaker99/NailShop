package com.example.nailshop.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class Account(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo
    val id: Int = 0,
    @ColumnInfo
    var name: String = "",
    @ColumnInfo
    var username: String,
    @ColumnInfo
    var password: String,
    @ColumnInfo
    var sdt: Int = 0,
    @ColumnInfo
    var gender: Boolean = true
) :  Parcelable
