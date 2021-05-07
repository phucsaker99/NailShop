package com.example.nailshop.ui.favorite

import com.example.nailshop.base.BasePresenter
import com.example.nailshop.base.BaseView
import com.example.nailshop.data.model.Nail

interface AllMealContract {
    interface View : BaseView {
        fun showAllNailFavorite(nails: List<Nail>)
    }

    interface Presenter : BasePresenter {
        fun getAllNailFavorite()
    }
}
