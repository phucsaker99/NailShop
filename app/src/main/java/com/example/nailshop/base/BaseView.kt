package com.example.nailshop.base

interface BaseView {
    fun showError(message: String)
    fun showLoading()
    fun hideLoading()
}
