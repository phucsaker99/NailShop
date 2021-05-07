package com.example.nailshop.ui.more

import com.example.nailshop.data.model.Account
import com.example.nailshop.data.resource.remote.api.OnDataCallback
import com.example.nailshop.data.resource.repository.NailShopRepository
import java.lang.Exception

class MorePresenter(
    private val view: MoreContract.View,
    private val repository: NailShopRepository
) : MoreContract.Presenter {

    override fun getInfor(id: Int) {
        view.showLoading()
        repository.getAccountList(object : OnDataCallback<List<Account>> {
            override fun onSuccess(data: List<Account>) {
                for (ds in data) {
                    if (ds.id == id) {
                        view.showInfor(ds)
                    }
                }
                view.hideLoading()
            }

            override fun onFail(exception: Exception) {
                view.showError(exception.message.toString())
                view.hideLoading()
            }
        })
    }

    override fun getSaveAccount(account: Account) {
        view.showLoading()
        repository.updateAccount(account, object : OnDataCallback<String> {
            override fun onSuccess(data: String) {
                view.showInfor(account)
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