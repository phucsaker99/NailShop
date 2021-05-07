package com.example.nailshop.data.resource.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.nailshop.data.model.Manicurist

@Dao
interface ManicuristDao {
    @Insert
    fun insert(manicurist: Manicurist)

    @Update
    fun update(manicurist: Manicurist)

    @Delete
    fun delete(manicurist: Manicurist)

    @Query("Delete from Manicurist")
    fun deleteAllManicurist()

    @Query("Select * from Manicurist where id =:id")
    fun getManicurist(id: Int): Manicurist

    @Query("Select * from Manicurist")
    fun getAllManicurist(): List<Manicurist>
}
