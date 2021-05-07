package com.example.nailshop.ui.more

import com.example.nailshop.base.BasePresenter
import com.example.nailshop.base.BaseView
import com.example.nailshop.data.model.Account

interface MoreContract {
    interface View : BaseView {
        fun showInfor(account: Account)
    }

    interface Presenter : BasePresenter {
        fun getInfor(id: Int)
        fun getSaveAccount(account: Account)
    }
}