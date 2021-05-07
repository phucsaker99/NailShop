package com.example.nailshop.ui.detailstore

import com.example.nailshop.data.resource.repository.NailShopRepository

class DetailStorePresenter(
    private val view: DetailStoreContract.View,
    private val repository: NailShopRepository
) : DetailStoreContract.Presenter {
    override fun start() {

    }
}
