package com.example.nailshop.ui.schedule

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.example.nailshop.R
import com.example.nailshop.base.BaseFragment
import com.example.nailshop.data.model.Bill
import com.example.nailshop.ui.adapter.ScheduleAdapter
import com.example.nailshop.ui.dialog.LoadingDialog
import com.example.nailshop.ui.login.Login
import com.example.nailshop.utils.RepositoryUtils
import com.example.nailshop.utils.showToast
import kotlinx.android.synthetic.main.fragment_schedule.*

class ScheduleFragment : BaseFragment(), ScheduleContract.View {
    private var myDialog: LoadingDialog? = null
    private val adapterOrder = ScheduleAdapter(this::setDeleteClicked)
    private var presenter: SchedulePresenter? = null
    private var schedules: MutableList<Bill>? = null

    override val layoutResource get() = R.layout.fragment_schedule

    override fun setupViews() {
        initToolbar()
        initDialog()
        initView()
        initAdapter()
    }

    private fun initAdapter() {
        recyclerOrder.adapter = adapterOrder
    }

    private fun initDialog() {
        context?.let { myDialog = LoadingDialog(it) }
    }

    private fun initView() {
        toolbarCart.title = ""
    }

    private fun initToolbar() {
        setHasOptionsMenu(true)
        (requireActivity() as AppCompatActivity).setSupportActionBar(toolbarCart)
    }

    override fun initData() {
        context?.let {
            val repository = RepositoryUtils.getRepository(it)
            presenter = SchedulePresenter(this, repository)
        }
        presenter?.start()
        val shared = activity?.getSharedPreferences(Login.SHARED_ACCOUNT, Context.MODE_PRIVATE)
        val id = shared!!.getInt(Login.SHARED_ID, -1)
        if (id != -1) {
            presenter?.getListOrderById(id)
        }
    }

    override fun initActions() {

    }

    override fun showListOrderById(bills: List<Bill>) {
        schedules = bills.toMutableList()
        adapterOrder.updateData(schedules!!)
        initTotal()
    }

    private fun initTotal() {
        textNailTotal.text = String.format("Total: %s nails", (schedules!!.size))
        textPriceTotal.text = String.format("Price: %.2f $", schedules!!.sumByDouble { it.money })
    }

    override fun showDeleteOderSuccessfully(bill: Bill) {
        schedules?.apply {
            remove(bill)
            adapterOrder.updateData(this)
        }
        initTotal()
        context?.showToast("Delete successfully")
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

    private fun setDeleteClicked(bill: Bill) {
        presenter?.deleteOrder(bill)
    }
}
