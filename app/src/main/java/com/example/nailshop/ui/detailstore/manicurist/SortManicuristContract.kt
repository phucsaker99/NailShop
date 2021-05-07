package com.example.nailshop.ui.detailstore.manicurist

import com.example.nailshop.base.BasePresenter
import com.example.nailshop.base.BaseView
import com.example.nailshop.data.model.Manicurist

interface SortManicuristContract {
    interface View : BaseView {
        fun showManicuristsByStore(manicurists: List<Manicurist>)
    }

    interface Presenter : BasePresenter {
        fun getManicuristsByStore(list: String)
    }
}