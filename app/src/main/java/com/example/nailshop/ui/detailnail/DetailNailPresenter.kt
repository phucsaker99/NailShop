package com.example.nailshop.ui.detailnail

import com.example.nailshop.data.model.Bill
import com.example.nailshop.data.model.Manicurist
import com.example.nailshop.data.model.Nail
import com.example.nailshop.data.model.Store
import com.example.nailshop.data.resource.remote.api.OnDataCallback
import com.example.nailshop.data.resource.repository.NailShopRepository
import java.lang.Exception
import javax.security.auth.callback.Callback

class DetailNailPresenter(
    private val view: DetailNailContract.View,
    private val repository: NailShopRepository
) : DetailNailContract.Presenter {

    override fun getStoreByNail(id: Int) {
        repository.getStoreList(object : OnDataCallback<List<Store>> {
            override fun onSuccess(data: List<Store>) {
                val stores = mutableListOf<Store>()
                data.forEach {
                    val ds = it.listNail.split(",")
                    if (ds.contains(id.toString())) {
                        stores.add(it)
                    }
                }
                view.showStoreByNail(stores)
            }

            override fun onFail(exception: Exception) {

            }
        })
    }

    override fun getManicuristByStore(name: String) {
        repository.getManicuristList(object : OnDataCallback<List<Manicurist>> {
            override fun onSuccess(data: List<Manicurist>) {
                val manicurists = mutableListOf<Manicurist>()
                data.forEach {
                    val ds = it.store.split(",")
                    if (ds.contains(name)) {
                        manicurists.add(it)
                    }
                }
                view.showManicuristByStore(manicurists)
            }

            override fun onFail(exception: Exception) {

            }
        })
    }

    override fun saveBill(bill: Bill) {
        view.showLoading()
        repository.insertBill(bill, object : OnDataCallback<String> {
            override fun onSuccess(data: String) {
                view.showSuccessBill()
                view.hideLoading()
            }

            override fun onFail(exception: Exception) {
                view.showError(exception.message.toString())
                view.hideLoading()
            }
        })
    }

    override fun getTimeByBill(manicurists: String) {
        view.showLoading()
        repository.getTimeByBill(manicurists, object : OnDataCallback<List<Bill>> {
            override fun onSuccess(data: List<Bill>) {
                view.showTimeByBill(data)
                view.hideLoading()
            }

            override fun onFail(exception: Exception) {
                view.showError(exception.message.toString())
                view.hideLoading()
            }
        })
    }

    override fun getNailFavorite(nail: Nail) {
        view.showLoading()
        if (repository.getNail(nail.id) == null) {
            view.showNailFavorite(false)
            view.hideLoading()
        } else {
            view.showNailFavorite(true)
            view.hideLoading()
        }
    }

    override fun insertNailFavorite(nail: Nail) {
        if (repository.getAllNail()!!.contains(nail)) return
        repository.insert(nail)
    }

    override fun deleteNailFavorite(nail: Nail) {
        if (repository.getAllNail()!!.contains(nail)){
            repository.delete(nail)
        }
    }

    override fun start() {

    }
}