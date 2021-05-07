package com.example.nailshop.data.resource.repository

import com.example.nailshop.data.model.*
import com.example.nailshop.data.resource.remote.api.OnDataCallback

class NailShopRepository private constructor(
    private val local: NailShopDataSource.Local,
    private val remote: NailShopDataSource.Remote
) : NailShopDataSource.Local, NailShopDataSource.Remote {
    override fun insert(account: Account) {
        local.insert(account)
    }

    override fun insert(bill: Bill): Long = local.insert(bill)

    override fun insert(manicurist: Manicurist) {
        local.insert(manicurist)
    }

    override fun insert(nail: Nail) {
        local.insert(nail)
    }

    override fun insert(store: Store) {
        local.insert(store)
    }

    override fun update(account: Account) {
        local.update(account)
    }

    override fun update(bill: Bill) {
        local.update(bill)
    }

    override fun update(manicurist: Manicurist) {
        local.update(manicurist)
    }

    override fun update(nail: Nail) {
        local.update(nail)
    }

    override fun update(store: Store) {
        local.update(store)
    }

    override fun delete(account: Account) {
        local.delete(account)
    }

    override fun delete(bill: Bill) {
        local.delete(bill)
    }

    override fun delete(manicurist: Manicurist) {
        local.delete(manicurist)
    }

    override fun delete(nail: Nail) {
        local.delete(nail)
    }

    override fun delete(store: Store) {
        local.delete(store)
    }

    override fun deleteAllAccount() {
        local.deleteAllAccount()
    }

    override fun getAccount(id: Int) =
        local.getAccount(id)

    override fun getAllAccount() =
        local.getAllAccount()

    override fun deleteAllBill() {
        local.deleteAllBill()
    }

    override fun getBill(id: Int) =
        local.getBill(id)

    override fun getAllBill() =
        local.getAllBill()

    override fun deleteAllManicurist() {
        local.deleteAllManicurist()
    }

    override fun getManicurist(id: Int) =
        local.getManicurist(id)

    override fun getAllManicurist() =
        local.getAllManicurist()


    override fun deleteAllNail() {
        local.deleteAllNail()
    }

    override fun getNail(id: Int) =
        local.getNail(id)

    override fun getAllNail() =
        local.getAllNail()

    override fun deleteAllStore() {
        local.deleteAllStore()
    }

    override fun getStore(id: Int) =
        local.getStore(id)

    override fun getAllStore() =
        local.getAllStore()


    override fun getNailList(callback: OnDataCallback<List<Nail>>) {
        remote.getNailList(callback)
    }

    override fun getManicuristList(callback: OnDataCallback<List<Manicurist>>) =
        remote.getManicuristList(callback)


    override fun getStoreList(callback: OnDataCallback<List<Store>>) =
        remote.getStoreList(callback)


    override fun getBillByIdUser(id: Int, callback: OnDataCallback<List<Bill>>) =
        remote.getBillByIdUser(id, callback)

    override fun getTimeByBill(manicuristList: String, callback: OnDataCallback<List<Bill>>) {
        remote.getTimeByBill(manicuristList, callback)
    }


    override fun getAccountList(callback: OnDataCallback<List<Account>>) =
        remote.getAccountList(callback)

    override fun insertAccount(account: Account, callback: OnDataCallback<String>) {
        remote.insertAccount(account, callback)
    }

    override fun insertBill(bill: Bill, callback: OnDataCallback<String>) {
        remote.insertBill(bill, callback)
    }

    override fun deleteBill(bill: Bill, callback: OnDataCallback<String>) {
        remote.deleteBill(bill, callback)
    }

    override fun updateAccount(account: Account, callback: OnDataCallback<String>) {
        remote.updateAccount(account, callback)
    }


    companion object {
        private var instance: NailShopRepository? = null
        fun getInstance(local: NailShopDataSource.Local, remote: NailShopDataSource.Remote) =
            instance
                ?: NailShopRepository(
                    local, remote
                )
                    .also { instance = it }
    }
}
