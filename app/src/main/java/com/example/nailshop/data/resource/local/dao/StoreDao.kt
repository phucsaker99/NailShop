package com.example.nailshop.data.resource.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.nailshop.data.model.Store

@Dao
interface StoreDao {
    @Insert
    fun insert(store: Store)

    @Update
    fun update(store: Store)

    @Delete
    fun delete(store: Store)

    @Query("Delete from Store")
    fun deleteAllStore()

    @Query("Select * from Store where id =:id")
    fun getStore(id: Int): Store

    @Query("Select * from Store")
    fun getAllStore(): List<Store>
}
