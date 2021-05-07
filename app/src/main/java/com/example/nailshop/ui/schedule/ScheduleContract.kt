package com.example.nailshop.ui.schedule

import com.example.nailshop.base.BasePresenter
import com.example.nailshop.base.BaseView
import com.example.nailshop.data.model.Bill

interface ScheduleContract {
    interface View : BaseView {
        fun showListOrderById(bills: List<Bill>)
        fun showDeleteOderSuccessfully(bill: Bill)
    }

    interface Presenter : BasePresenter {
        fun getListOrderById(id: Int)
        fun deleteOrder(bill: Bill)
    }
}