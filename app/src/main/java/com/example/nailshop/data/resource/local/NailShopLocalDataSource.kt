package com.example.nailshop.data.resource.local

import com.example.nailshop.data.model.*
import com.example.nailshop.data.resource.repository.NailShopDataSource
import com.example.nailshop.data.resource.local.database.NailShopDatabase

class NailShopLocalDataSource(private val nailShopDatabase: NailShopDatabase) :
    NailShopDataSource.Local {
    override fun insert(account: Account) {
        nailShopDatabase.getAccountDao().insert(account)
    }

    override fun insert(bill: Bill) =
        nailShopDatabase.getBillDao().insert(bill)


    override fun insert(manicurist: Manicurist) {
        nailShopDatabase.getManicuristDao().insert(manicurist)
    }

    override fun insert(nail: Nail) {
        nailShopDatabase.getNailDao().insert(nail)
    }

    override fun insert(store: Store) {
        nailShopDatabase.getStoreDao().insert(store)
    }

    override fun update(account: Account) {
        nailShopDatabase.getAccountDao().update(account)
    }

    override fun update(bill: Bill) {
        nailShopDatabase.getBillDao().update(bill)
    }

    override fun update(manicurist: Manicurist) {
        nailShopDatabase.getManicuristDao().update(manicurist)
    }

    override fun update(nail: Nail) {
        nailShopDatabase.getNailDao().update(nail)
    }

    override fun update(store: Store) {
        nailShopDatabase.getStoreDao().update(store)
    }

    override fun delete(account: Account) {
        nailShopDatabase.getAccountDao().delete(account)
    }

    override fun delete(bill: Bill) {
        nailShopDatabase.getBillDao().delete(bill)
    }

    override fun delete(manicurist: Manicurist) {
        nailShopDatabase.getManicuristDao().delete(manicurist)
    }

    override fun delete(nail: Nail) {
        nailShopDatabase.getNailDao().delete(nail)
    }

    override fun delete(store: Store) {
        nailShopDatabase.getStoreDao().delete(store)
    }

    override fun deleteAllAccount() {
        nailShopDatabase.getAccountDao().deleteAllAccount()
    }

    override fun getAccount(id: Int) =
        nailShopDatabase.getAccountDao().getAccount(id)

    override fun getAllAccount()=
        nailShopDatabase.getAccountDao().getAllAccount()


    override fun deleteAllBill() {
        nailShopDatabase.getBillDao().deleteAllBill()
    }

    override fun getBill(id: Int) =
        nailShopDatabase.getBillDao().getBill(id)


    override fun getAllBill() =
        nailShopDatabase.getBillDao().getAllBill()


    override fun deleteAllManicurist() {
        nailShopDatabase.getManicuristDao().deleteAllManicurist()
    }

    override fun getManicurist(id: Int) =
        nailShopDatabase.getManicuristDao().getManicurist(id)


    override fun getAllManicurist() =
        nailShopDatabase.getManicuristDao().getAllManicurist()


    override fun deleteAllNail() {
        nailShopDatabase.getNailDao().deleteAllNail()
    }

    override fun getNail(id: Int): Nail? =
        nailShopDatabase.getNailDao().getNail(id)


    override fun getAllNail()=
        nailShopDatabase.getNailDao().getAllNail()


    override fun deleteAllStore() {
        nailShopDatabase.getStoreDao().deleteAllStore()
    }

    override fun getStore(id: Int)=
        nailShopDatabase.getStoreDao().getStore(id)


    override fun getAllStore() =
        nailShopDatabase.getStoreDao().getAllStore()

    companion object {
        private var instance: NailShopLocalDataSource? = null
        fun getInstance(nailShopDatabase: NailShopDatabase) =
            instance ?: synchronized(this) {
                instance ?: NailShopLocalDataSource(nailShopDatabase).also {
                    instance = it
                }
            }
    }
}
