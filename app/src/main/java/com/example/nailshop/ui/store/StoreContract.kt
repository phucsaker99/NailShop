package com.example.nailshop.ui.store

import com.example.nailshop.base.BasePresenter
import com.example.nailshop.base.BaseView
import com.example.nailshop.data.model.Store

interface StoreContract {
    interface View : BaseView {
        fun showAllStores(stores: List<Store>)
    }

    interface Presenter : BasePresenter {
        fun getAllStores()
    }
}
