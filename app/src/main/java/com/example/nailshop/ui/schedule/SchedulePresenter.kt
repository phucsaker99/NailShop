package com.example.nailshop.ui.schedule

import com.example.nailshop.data.model.Bill
import com.example.nailshop.data.resource.remote.api.OnDataCallback
import com.example.nailshop.data.resource.repository.NailShopRepository
import java.lang.Exception

class SchedulePresenter(
    private val view: ScheduleContract.View,
    private val repository: NailShopRepository
) : ScheduleContract.Presenter {

    override fun getListOrderById(id: Int) {
        view.showLoading()
        repository.getBillByIdUser(id, object : OnDataCallback<List<Bill>> {
            override fun onSuccess(data: List<Bill>) {
                view.showListOrderById(data)
                view.hideLoading()
            }

            override fun onFail(exception: Exception) {
                view.showError(exception.message.toString())
                view.hideLoading()
            }
        })
    }

    override fun deleteOrder(bill: Bill) {
        view.showLoading()
        repository.deleteBill(bill, object : OnDataCallback<String> {
            override fun onSuccess(data: String) {
                view.showDeleteOderSuccessfully(bill)
                view.hideLoading()
            }

            override fun onFail(exception: Exception) {
                view.showError(exception.message.toString())
                view.hideLoading()
            }
        })
    }

    override fun start() {

    }
}