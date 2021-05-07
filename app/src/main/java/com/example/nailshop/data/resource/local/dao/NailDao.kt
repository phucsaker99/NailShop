package com.example.nailshop.data.resource.local.dao

import androidx.room.*
import com.example.nailshop.data.model.Nail

@Dao
interface NailDao {
    @Insert
    fun insert(nail: Nail)

    @Update
    fun update(nail: Nail)

    @Delete
    fun delete(nail: Nail)

    @Query("Delete from Nail")
    fun deleteAllNail()

    @Query("Select * from Nail where id =:id")
    fun getNail(id: Int): Nail?

    @Query("Select * from Nail")
    fun getAllNail(): List<Nail>?
}
