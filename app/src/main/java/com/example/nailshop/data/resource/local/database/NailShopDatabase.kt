package com.example.nailshop.data.resource.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.nailshop.data.model.*
import com.example.nailshop.data.resource.local.dao.*

@Database(
    entities = [Account::class, Bill::class, Manicurist::class, Nail::class, Store::class],
    version = 8
)
abstract class NailShopDatabase : RoomDatabase() {
    companion object {
        var instance: NailShopDatabase? = null

        fun getInstance(context: Context): NailShopDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    NailShopDatabase::class.java,
                    "NailShop"
                ).fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
            }
            return instance as NailShopDatabase
        }
    }

    abstract fun getAccountDao(): AccountDao
    abstract fun getBillDao(): BillDao
    abstract fun getManicuristDao(): ManicuristDao
    abstract fun getNailDao(): NailDao
    abstract fun getStoreDao(): StoreDao
}
