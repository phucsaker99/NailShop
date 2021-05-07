package com.example.nailshop.data.resource.remote.api

import java.lang.Exception

interface OnDataCallback<T> {
    fun onSuccess(data: T)
    fun onFail(exception: Exception)
}
