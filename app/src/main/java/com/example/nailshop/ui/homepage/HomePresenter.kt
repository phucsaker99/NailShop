package com.example.nailshop.ui.homepage

import com.example.nailshop.data.model.Manicurist
import com.example.nailshop.data.model.Nail
import com.example.nailshop.data.resource.remote.api.OnDataCallback
import com.example.nailshop.data.resource.repository.NailShopRepository
import java.lang.Exception

class HomePresenter(
    private val view: HomePageContract.View,
    private val repository: NailShopRepository
) : HomePageContract.Presenter {

    override fun start() {
        getNails()
        getManicurists()
    }

    //hiển thị danh sách nail
    override fun getNails() {
        view.showLoading()
        repository.getNailList(object : OnDataCallback<List<Nail>> {
            override fun onSuccess(data: List<Nail>) {
                view.showNail(data)
                view.hideLoading()
            }

            override fun onFail(exception: Exception) {
                view.showError(exception.message.toString())
                view.hideLoading()
            }
        })
    }

    //hiển thị danh sách manicurist
    override fun getManicurists() {
        repository.getManicuristList(object : OnDataCallback<List<Manicurist>> {
            override fun onSuccess(data: List<Manicurist>) {
                view.showManicurist(data)
                view.hideLoading()
            }

            override fun onFail(exception: Exception) {
                view.showError(exception.message.toString())
                view.hideLoading()
            }
        })
    }

}
