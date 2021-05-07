package com.example.nailshop.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class Bill(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,
    @ColumnInfo(name = "listManicurist")
    val listManicurist: String,
    @ColumnInfo(name = "idUser")
    val idUser: Int,
    @ColumnInfo(name = "listNail")
    val listNail: String,
    @ColumnInfo(name = "imageNail")
    val imageNail: String,
    @ColumnInfo(name = "store")
    val store: String,
    @ColumnInfo(name = "address")
    val address: String,
    @ColumnInfo(name = "date")
    val date: String,
    @ColumnInfo(name = "money")
    val money: Double,
    @ColumnInfo(name = "status")
    val status: Boolean
) : Parcelable
