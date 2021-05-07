package com.example.nailshop.ui.listnail

import com.example.nailshop.data.model.Nail
import com.example.nailshop.data.resource.remote.api.OnDataCallback
import com.example.nailshop.data.resource.repository.NailShopRepository
import java.lang.Exception

class ListNailPresenter(
    private val view: ListNailContract.View,
    private val repository: NailShopRepository
) : ListNailContract.Presenter {
    override fun start() {

    }

    override fun getNailListByColor(textColor: String) {
        view.showLoading()
        repository.getNailList(object : OnDataCallback<List<Nail>> {
            override fun onSuccess(data: List<Nail>) {
                val listNail = mutableListOf<Nail>()
                var listColor: MutableList<String>
                data.forEach {
                    listColor = it.color.split(",").map(String::trim).toMutableList()
                    if (listColor.contains(textColor)) {
                        listNail.add(it)
                    }
                }
                view.showNailListByColor(listNail)
                view.hideLoading()
            }

            override fun onFail(exception: Exception) {
                view.hideLoading()
            }
        })

        repository.getAllNail()
    }

    override fun getNailListByTag(textTag: String) {
        view.showLoading()
        repository.getNailList(object : OnDataCallback<List<Nail>> {
            override fun onSuccess(data: List<Nail>) {
                val listNail = mutableListOf<Nail>()
                var listTag: MutableList<String>
                data.forEach {
                    listTag = it.tag.split(",").map(String::trim).toMutableList()
                    if (listTag.contains(textTag)) {
                        listNail.add(it)
                    }
                }
                view.showNailListByTag(listNail)
                view.hideLoading()
            }

            override fun onFail(exception: Exception) {
                view.hideLoading()
            }
        })
    }
}
