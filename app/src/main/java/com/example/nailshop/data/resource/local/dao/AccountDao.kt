package com.example.nailshop.data.resource.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.nailshop.data.model.Account

@Dao
interface AccountDao {
    @Insert
    fun insert(account: Account)

    @Update
    fun update(account: Account)

    @Delete
    fun delete(account: Account)

    @Query("Delete from Account")
    fun deleteAllAccount()

    @Query("Select * from Account where id =:id")
    fun getAccount(id: Int): Account

    @Query("Select * from Account")
    fun getAllAccount(): List<Account>
}
