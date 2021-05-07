package com.example.nailshop.ui.more

import android.content.Context
import com.example.nailshop.R
import com.example.nailshop.base.BaseFragment
import com.example.nailshop.data.model.Account
import com.example.nailshop.ui.detailnail.DetailNailFragment
import com.example.nailshop.ui.dialog.LoadingDialog
import com.example.nailshop.ui.favorite.NailFavoriteFragment
import com.example.nailshop.ui.login.Login
import com.example.nailshop.ui.main.MainActivity
import com.example.nailshop.utils.RepositoryUtils
import com.example.nailshop.utils.addFragment
import com.example.nailshop.utils.replaceFragment
import com.example.nailshop.utils.showToast
import kotlinx.android.synthetic.main.fragment_more.*

class MoreFragment : BaseFragment(), MoreContract.View {
    private var presenter: MorePresenter? = null
    private var myDialog: LoadingDialog? = null
    private var account: Account? = null

    override val layoutResource get() = R.layout.fragment_more

    override fun setupViews() {
        initDialog()
        initPresenter()
        initInfo()
    }

    private fun initDialog() {
        context?.let { myDialog = LoadingDialog(it) }
    }

    private fun initPresenter() {
        context?.let {
            val repository = RepositoryUtils.getRepository(it)
            presenter = MorePresenter(this, repository)
        }
        presenter?.start()
    }

    private fun initInfo() {
        val share = context?.getSharedPreferences(Login.SHARED_ACCOUNT, Context.MODE_PRIVATE)
        share?.getInt(Login.SHARED_ID, -1)?.let { presenter?.getInfor(it) }
    }

    override fun initData() {

    }

    override fun initActions() {
        buttonEdit.setOnClickListener {
            if (!editName.isEnabled) {
                settingInfo()
            } else {
                saveInfo()
                settingInfo()
            }
        }

        buttonLogout.setOnClickListener {
            val share = context?.getSharedPreferences(Login.SHARED_ACCOUNT, Context.MODE_PRIVATE)
            share?.edit()?.putInt(Login.SHARED_ID, -1)?.apply()
            val intent = (activity as MainActivity).let { Login.getIntent(it) }
            startActivity(intent)
        }

        buttonFavorite.setOnClickListener {
            parentFragmentManager.addFragment(
                R.id.fragmentContainer,
                NailFavoriteFragment.getInstance()
            )
        }
    }

    private fun saveInfo() {
        account?.let {
            it.sdt = editPhoneNumber.text.toString().toInt()
            it.name = editName.text.toString()
            it.gender = !radioButtonMale.isChecked
        }
        account?.let { presenter?.getSaveAccount(it) }
    }

    private fun settingInfo() {
        editName.isEnabled = !editName.isEnabled
        editPhoneNumber.isEnabled = !editPhoneNumber.isEnabled
        radioButtonMale.isEnabled = !radioButtonMale.isEnabled
        radioButtonFemale.isEnabled = !radioButtonFemale.isEnabled
    }

    override fun showInfor(account: Account) {
        this.account = account
        this.account?.apply {
            editName.setText(name)
            sdt.toString().let { editPhoneNumber.setText(it) }
            if (gender) {
                radioButtonFemale.isChecked = true
            } else {
                radioButtonMale.isChecked = true
            }
        }
    }

    override fun showError(message: String) {
        context?.showToast(message)
    }

    override fun showLoading() {
        myDialog?.show()
    }

    override fun hideLoading() {
        myDialog?.dismiss()
    }
}
