package com.example.nailshop.ui.login

import com.example.nailshop.data.model.Account
import com.example.nailshop.data.resource.remote.api.OnDataCallback
import com.example.nailshop.data.resource.repository.NailShopRepository
import java.lang.Exception

class LoginPresenter(
    private val view: LoginContract.View,
    private val repository: NailShopRepository
) : LoginContract.Presenter {

    //check đăng nhập xem có thành công không
    override fun getLogin(account: Account) {
        repository.getAccountList(object : OnDataCallback<List<Account>> {
            override fun onSuccess(data: List<Account>) {
                for (ds in data) {
                    if (ds.username == account.username && ds.password == account.password) {
                        view.loginSuccess(ds)
                        return
                    }
                }
                view.loginFail()
            }

            override fun onFail(exception: Exception) {
                view.showError(exception.message.toString())
            }
        })
    }

    //check đăng ký xem có thành công không
    override fun getRegistration(account: Account) {
        repository.getAccountList(object : OnDataCallback<List<Account>> {
            override fun onSuccess(data: List<Account>) {
                data.forEach {
                    if (it.username == account.username) {
                        view.loginFail()
                    }
                }
            }

            override fun onFail(exception: Exception) {
                view.showError(exception.message.toString())
            }
        })
        repository.insertAccount(account, object : OnDataCallback<String> {
            override fun onSuccess(data: String) {
                view.registerSuccess()
            }

            override fun onFail(exception: Exception) {
                view.showError(exception.message.toString())
            }
        })
    }

    override fun start() {

    }
}