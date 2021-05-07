package com.example.nailshop.data.resource.repository

import com.example.nailshop.data.model.*
import com.example.nailshop.data.resource.remote.api.OnDataCallback

interface NailShopDataSource {
    interface Local {
        fun insert(account: Account)
        fun update(account: Account)
        fun delete(account: Account)
        fun deleteAllAccount()
        fun getAccount(id: Int): Account
        fun getAllAccount(): List<Account>

        fun insert(bill: Bill): Long
        fun update(bill: Bill)
        fun delete(bill: Bill)
        fun deleteAllBill()
        fun getBill(id: Int): Bill
        fun getAllBill(): List<Bill>

        fun insert(manicurist: Manicurist)
        fun update(manicurist: Manicurist)
        fun delete(manicurist: Manicurist)
        fun deleteAllManicurist()
        fun getManicurist(id: Int): Manicurist
        fun getAllManicurist(): List<Manicurist>

        fun insert(nail: Nail)
        fun update(nail: Nail)
        fun delete(nail: Nail)
        fun deleteAllNail()
        fun getNail(id: Int): Nail?
        fun getAllNail(): List<Nail>?

        fun insert(store: Store)
        fun update(store: Store)
        fun delete(store: Store)
        fun deleteAllStore()
        fun getStore(id: Int): Store
        fun getAllStore(): List<Store>
    }

    interface Remote {
        fun getNailList(callback: OnDataCallback<List<Nail>>)
        fun getManicuristList(callback: OnDataCallback<List<Manicurist>>)
        fun getStoreList(callback: OnDataCallback<List<Store>>)
        fun getBillByIdUser(id: Int, callback: OnDataCallback<List<Bill>>)
        fun getTimeByBill(manicuristList: String, callback: OnDataCallback<List<Bill>>)
        fun getAccountList(callback: OnDataCallback<List<Account>>)
        fun insertAccount(account: Account, callback: OnDataCallback<String>)
        fun insertBill(bill: Bill, callback: OnDataCallback<String>)
        fun deleteBill(bill: Bill, callback: OnDataCallback<String>)
        fun updateAccount(account: Account, callback: OnDataCallback<String>)
    }
}
