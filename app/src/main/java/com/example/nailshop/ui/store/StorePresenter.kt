package com.example.nailshop.ui.store

import com.example.nailshop.data.model.Store
import com.example.nailshop.data.resource.remote.api.OnDataCallback
import com.example.nailshop.data.resource.repository.NailShopRepository
import java.lang.Exception

class StorePresenter(
    private val view: StoreContract.View,
    private val repository: NailShopRepository
) : StoreContract.Presenter {

    override fun getAllStores() {
        view.showLoading()
        repository.getStoreList(object : OnDataCallback<List<Store>> {
            override fun onSuccess(data: List<Store>) {
                view.showAllStores(data)
                view.hideLoading()
            }

            override fun onFail(exception: Exception) {
                view.showError(exception.message.toString())
                view.hideLoading()
            }
        })
    }

    override fun start() {
        getAllStores()
    }
}
