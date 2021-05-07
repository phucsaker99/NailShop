package com.example.nailshop.ui.favorite

import com.example.nailshop.data.resource.repository.NailShopRepository

class AllMealPresenter(
    private val view: AllMealContract.View,
    private val repository: NailShopRepository
) : AllMealContract.Presenter {

    override fun getAllNailFavorite() {
        view.showLoading()
        val nails = repository.getAllNail()
        if (nails == null){
            view.showError("Bạn chưa yêu thích")
            view.hideLoading()
        }else{
            view.showAllNailFavorite(nails)
            view.hideLoading()
        }
    }

    override fun start() {
        getAllNailFavorite()
    }
}
