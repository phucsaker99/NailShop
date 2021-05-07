package com.example.nailshop.ui.detailnail

import com.example.nailshop.base.BasePresenter
import com.example.nailshop.base.BaseView
import com.example.nailshop.data.model.Bill
import com.example.nailshop.data.model.Manicurist
import com.example.nailshop.data.model.Nail
import com.example.nailshop.data.model.Store

interface DetailNailContract {
    interface View : BaseView {
        fun showStoreByNail(store: List<Store>)
        fun showManicuristByStore(manicurists: List<Manicurist>)
        fun showSuccessBill()
        fun showTimeByBill(bills: List<Bill>)
        fun showNailFavorite(isFavorite: Boolean)
    }

    interface Presenter : BasePresenter {
        fun getStoreByNail(id: Int)
        fun getManicuristByStore(name: String)
        fun saveBill(bill: Bill)
        fun getTimeByBill(manicurists: String)
        fun getNailFavorite(nail: Nail)
        fun insertNailFavorite(nail: Nail)
        fun deleteNailFavorite(nail: Nail)
    }
}