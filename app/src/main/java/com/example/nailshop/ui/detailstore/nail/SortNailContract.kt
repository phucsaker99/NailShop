package com.example.nailshop.ui.detailstore.nail

import com.example.nailshop.base.BasePresenter
import com.example.nailshop.base.BaseView
import com.example.nailshop.data.model.Manicurist
import com.example.nailshop.data.model.Nail

interface SortNailContract {
    interface View : BaseView {
        fun showNailsByStore(nails: List<Nail>)
    }

    interface Presenter : BasePresenter {
        fun getNailsByStore(list: String)
    }
}