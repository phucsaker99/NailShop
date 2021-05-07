package com.example.nailshop.ui.listnail

import com.example.nailshop.base.BasePresenter
import com.example.nailshop.base.BaseView
import com.example.nailshop.data.model.Nail
import com.example.nailshop.data.model.Store

interface ListNailContract {
    interface View : BaseView{
        fun showNailListByColor(nails : List<Nail>)
        fun showNailListByTag(nails: List<Nail>)
    }

    interface Presenter : BasePresenter {
        fun getNailListByColor(textColor: String)
        fun getNailListByTag(textTag: String)
    }
}
