package com.example.nailshop.ui.login

import com.example.nailshop.base.BasePresenter
import com.example.nailshop.base.BaseView
import com.example.nailshop.data.model.Account

interface LoginContract {
    interface View : BaseView {
        fun loginSuccess(account: Account)
        fun loginFail()
        fun registerSuccess()
        fun registerFail()
    }

    interface Presenter : BasePresenter {
        fun getLogin(account: Account)
        fun getRegistration(account: Account)
    }
}