package com.example.nailshop.data.resource.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.nailshop.data.model.Bill

@Dao
interface BillDao {
    @Insert
    fun insert(bill: Bill) : Long

    @Update
    fun update(bill: Bill)

    @Delete
    fun delete(bill: Bill)

    @Query("Delete from Bill")
    fun deleteAllBill()

    @Query("Select * from Bill where id =:id")
    fun getBill(id: Int): Bill

    @Query("Select * from Bill")
    fun getAllBill(): List<Bill>
}
