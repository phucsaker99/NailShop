package com.example.nailshop.utils

import android.widget.EditText

fun checkEmpty(vararg editText: EditText): Boolean {
    for (ele in editText) {
        if (ele.text.isEmpty()) {
            ele.error = "Không được để trống"
            return true
        } else if (ele.text.toString().length < 8) {
            ele.error = "Phải nhiều hơn hoặc bằng 8 ký tự"
            return true
        }
    }
    return false
}

fun checkPassword(password: EditText, rePassword: EditText): Boolean {
    if (password.text.toString() == rePassword.text.toString()) {
        return true
    }
    rePassword.error = "Mật khẩu không khớp"
    return false
}

fun formatInf(vararg editText: EditText) {
    for (ds in editText) {
        ds.setText("")
    }
}