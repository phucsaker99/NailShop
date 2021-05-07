package com.example.nailshop.ui.detailstore.nail

import com.example.nailshop.data.model.Nail
import com.example.nailshop.data.resource.remote.api.OnDataCallback
import com.example.nailshop.data.resource.repository.NailShopRepository
import java.lang.Exception

class SortNailPresenter(
    private val view: SortNailContract.View,
    private val repository: NailShopRepository
) : SortNailContract.Presenter {

    override fun getNailsByStore(list: String) {
        view.showLoading()
        val listTag = list.split(",").map(String::trim)
        val nails = mutableListOf<Nail>()
        repository.getNailList(object : OnDataCallback<List<Nail>> {
            override fun onSuccess(data: List<Nail>) {
                for (nail in data) {
                    if (listTag.contains(nail.id.toString())) {
                        nails.add(nail)
                    }
                }
                view.showNailsByStore(nails)
                view.hideLoading()
            }

            override fun onFail(exception: Exception) {
                view.showError(exception.message.toString())
                view.hideLoading()
            }
        })
    }

    override fun start() {
    }
}