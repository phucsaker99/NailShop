package com.example.nailshop.ui.homepage

import com.example.nailshop.base.BasePresenter
import com.example.nailshop.base.BaseView
import com.example.nailshop.data.model.Manicurist
import com.example.nailshop.data.model.Nail
import com.example.nailshop.data.model.Store
import com.example.nailshop.data.model.Tag

interface HomePageContract {
    interface View : BaseView {
        fun showNail(nails: List<Nail>)
        fun showManicurist(manicurists: List<Manicurist>)
    }

    interface Presenter : BasePresenter{
        fun getManicurists()
        fun getNails()
    }
}
