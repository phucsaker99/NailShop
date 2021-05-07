package com.example.nailshop.ui.detailstore.manicurist

import com.example.nailshop.data.model.Manicurist
import com.example.nailshop.data.resource.remote.api.OnDataCallback
import com.example.nailshop.data.resource.repository.NailShopRepository
import java.lang.Exception

class SortManicuristPresenter(
    private val view: SortManicuristContract.View,
    private val repository: NailShopRepository
) : SortManicuristContract.Presenter {

    override fun getManicuristsByStore(list: String) {
        view.showLoading()
        val listTag = list.split(",").map(String::trim)
        val manicurists = mutableListOf<Manicurist>()
        repository.getManicuristList(object : OnDataCallback<List<Manicurist>> {
            override fun onSuccess(data: List<Manicurist>) {
                for (manicurist in data) {
                    if (listTag.contains(manicurist.id.toString())) {
                        manicurists.add(manicurist)
                    }
                }
                view.showManicuristsByStore(manicurists)
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