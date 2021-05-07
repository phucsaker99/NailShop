package com.example.nailshop.ui.login

import android.content.Context
import android.content.Intent
import android.view.View
import com.example.nailshop.R
import com.example.nailshop.base.BaseActivity
import com.example.nailshop.data.model.Account
import com.example.nailshop.ui.dialog.LoadingDialog
import com.example.nailshop.ui.main.MainActivity
import com.example.nailshop.utils.*
import kotlinx.android.synthetic.main.activity_login.*

class Login : BaseActivity(), LoginContract.View {
    private var presenter: LoginPresenter? = null
    private var myDialog: LoadingDialog? = null

    override val layoutResource get() = R.layout.activity_login

    override fun initComponents() {
        initInf()
        checkAccount()
        initDialog()
        initPresenter()
        initAction()
    }

    private fun initInf() {
        val shared = getSharedPreferences(SHARED_ACCOUNT, Context.MODE_PRIVATE)
        editName.setText(shared.getString(SHARED_USERNAME, ""))
        editPassword.setText(shared.getString(SHARED_PASSWORD, ""))
    }

    private fun checkAccount() {
        val id = getSharedPreferences(SHARED_ACCOUNT, Context.MODE_PRIVATE).getInt(SHARED_ID, -1)
        if (id >= 0) {
            val intent = MainActivity.getIntent(this)
            startActivity(intent)
            finish()
        }
    }

    private fun initDialog() {
        myDialog = LoadingDialog(this)
    }

    private fun initPresenter() {
        val repository = RepositoryUtils.getRepository(this)
        presenter = LoginPresenter(this, repository)
        presenter?.start()
    }

    private fun initAction() {
        buttonLogin.setOnClickListener {
            if (editRePassword.visibility == View.VISIBLE) {
                editRePassword.visibility = View.GONE
                return@setOnClickListener
            }
            if (!checkEmpty(editName, editPassword)) {
                val account = Account(
                    username = editName.text.toString(),
                    password = editPassword.text.toString()
                )
                presenter?.getLogin(account)
            }
        }

        buttonSignUp.setOnClickListener {
            if (editRePassword.visibility == View.GONE) {
                editRePassword.visibility = View.VISIBLE
                return@setOnClickListener
            }
            if (!checkEmpty(editName, editPassword, editRePassword) && checkPassword(
                    editPassword,
                    editRePassword
                )
            ) {
                val account = Account(
                    username = editName.text.toString(),
                    password = editPassword.text.toString()
                )
                presenter?.getRegistration(account)
            }
        }
    }

    override fun loginSuccess(account: Account) {
        val sharedPreferences = getSharedPreferences(SHARED_ACCOUNT, Context.MODE_PRIVATE)
        val edit = sharedPreferences.edit()
        edit.putInt(SHARED_ID, account.id)
        if (chkCheck.isChecked) {
            edit.putString(SHARED_USERNAME, account.username)
            edit.putString(SHARED_PASSWORD, account.password)
        } else {
            edit.putString(SHARED_USERNAME, "")
            edit.putString(SHARED_PASSWORD, "")
        }
        edit.apply()
        val intent = MainActivity.getIntent(this)
        startActivity(intent)
        finish()
    }

    override fun loginFail() {
        this.showToast("Đăng nhập thất bại")
    }

    override fun registerSuccess() {
        formatInf(editName, editPassword, editRePassword)
        this.showToast("Đăng ký thành công")
    }

    override fun registerFail() {
        this.showToast("Đăng ký thất bại")
    }

    override fun showError(message: String) {
        showToast(message)
    }

    override fun showLoading() {
        myDialog?.show()
    }

    override fun hideLoading() {
        myDialog?.dismiss()
    }

    companion object {
        const val SHARED_ACCOUNT = "ACCOUNT"
        const val SHARED_ID = "ID"
        const val SHARED_USERNAME = "USERNAME"
        const val SHARED_PASSWORD = "PASSWORD"

        fun getIntent(context: Context) = Intent(context, Login::class.java)
    }
}
