package com.example.nailshop.ui.detailnail

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Context
import android.content.res.ColorStateList
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.DatePicker
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import com.example.nailshop.R
import com.example.nailshop.base.BaseFragment
import com.example.nailshop.data.model.Bill
import com.example.nailshop.data.model.Manicurist
import com.example.nailshop.data.model.Nail
import com.example.nailshop.data.model.Store
import com.example.nailshop.databinding.BottomSheetDetailNailBinding
import com.example.nailshop.databinding.DetailTimeListBinding
import com.example.nailshop.ui.dialog.LoadingDialog
import com.example.nailshop.ui.listnail.ListNailFragment
import com.example.nailshop.ui.login.Login
import com.example.nailshop.utils.*
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.chip.Chip
import kotlinx.android.synthetic.main.bottom_sheet_detail_nail.*
import kotlinx.android.synthetic.main.detail_nail.*
import kotlinx.android.synthetic.main.detail_nail.textPrice
import kotlinx.android.synthetic.main.detail_time_list.*

class DetailNailFragment : BaseFragment(), DetailNailContract.View, View.OnClickListener,
    DatePicker.OnDateChangedListener {
    private var viewBottomSheet: BottomSheetDetailNailBinding? = null
    private var presenter: DetailNailPresenter? = null
    private var nail: Nail? = null
    private var manicurist: Manicurist? = null
    private var store: Store? = null
    private var bottomSheet: BottomSheetDialog? = null
    private var myDialog: LoadingDialog? = null
    private var viewTime: DetailTimeListBinding? = null

    override val layoutResource: Int
        get() = R.layout.detail_nail

    override fun onStop() {
        super.onStop()
        when (buttonFavorite.visibility) {
            View.VISIBLE -> nail?.let { presenter?.insertNailFavorite(it) }
            else -> nail?.let { presenter?.deleteNailFavorite(it) }
        }
    }

    override fun setupViews() {
        initDialog()
        initPresenter()
    }

    override fun initData() {
        nail = arguments?.getParcelable<Nail>(BUNDLE_NAIL)?.apply {
            imageNail.loadImage(image)
            textNailName.text = name
            textNailDescription.text = description
            textPrice.text = String.format("%s $", price)
            chipNailColor.listColor(color, this@DetailNailFragment::onChipColorClicked)
            chipNailTag.listTag(tag, this@DetailNailFragment::onChipTagClicked)
        }
        nail?.let { presenter?.getNailFavorite(it) }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun initActions() {
        imageShoppingNail.setOnClickListener { nail?.let { clickOrder(it) } }

        buttonFavorite.setOnClickListener {
            it.visibility = View.GONE
            buttonUnFavorite.visibility = View.VISIBLE
        }

        buttonUnFavorite.setOnClickListener {
            it.visibility = View.GONE
            buttonFavorite.visibility = View.VISIBLE
        }
    }

    override fun showStoreByNail(store: List<Store>) {
        val ds = store.map { it.name }
        if (ds.isEmpty()) {
            viewBottomSheet?.apply {
                spinnerStore.isEnabled = false
                spinnerStore.adapter = context?.let {
                    ArrayAdapter<String>(
                        it,
                        android.R.layout.simple_spinner_dropdown_item,
                        listOf("Nope")
                    )
                }
            }
            return
        }
        val arrayAdapter = context?.let {
            ArrayAdapter<String>(
                it,
                android.R.layout.simple_spinner_dropdown_item,
                ds
            )
        }
        viewBottomSheet?.apply {
            spinnerStore.adapter = arrayAdapter
            spinnerStore.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onNothingSelected(parent: AdapterView<*>?) {
                    }

                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        this@DetailNailFragment.store = store[position]
                        textAddress.text = store[position].address
                        presenter?.getManicuristByStore(store[position].name)
                    }
                }
        }
    }

    override fun showManicuristByStore(manicurists: List<Manicurist>) {
        val ds = manicurists.map { it.name }
        if (manicurists.isEmpty()) {
            viewBottomSheet?.apply {
                spinnerManicurist.isEnabled = false
                spinnerManicurist.adapter = context?.let {
                    ArrayAdapter<String>(
                        it,
                        android.R.layout.simple_spinner_dropdown_item,
                        listOf("Nope")
                    )
                }
            }
            return
        }

        val arrayAdapter = context?.let {
            ArrayAdapter<String>(
                it,
                android.R.layout.simple_spinner_dropdown_item,
                ds
            )
        }

        viewBottomSheet?.apply {
            spinnerManicurist.apply {
                isEnabled = true
                visibility = View.VISIBLE
                adapter = arrayAdapter
                onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    override fun onNothingSelected(parent: AdapterView<*>?) {}

                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        manicurist = manicurists[position]
                    }
                }
            }
        }
    }

    override fun showSuccessBill() {
        bottomSheet?.dismiss()
        context?.showToast("Save bill successfully")
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

    override fun onDateChanged(view: DatePicker?, year: Int, monthOfYear: Int, dayOfMonth: Int) {
        viewBottomSheet?.textDate?.text =
            String.format("%s/%s/%s", dayOfMonth, monthOfYear + 1, year)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.buttonOrder -> {
                viewBottomSheet?.apply {
                    when {
                        !spinnerManicurist.isEnabled -> {
                            context?.showToast("No manicurist")
                            return
                        }
                        textDate.text.isEmpty() -> {
                            context?.showToast("Please! choose day")
                            return
                        }
                        textTimeChoose.text.isEmpty() -> {
                            showTimeList()
                            return
                        }
                    }
                    saveOrder()
                }
            }
            R.id.textTimeChoose -> showTimeList()
        }
    }

    private fun saveOrder() {
        val shared = activity?.getSharedPreferences(Login.SHARED_ACCOUNT, Context.MODE_PRIVATE)
        val id = shared!!.getInt(Login.SHARED_ID, -1)
        if (id != -1) {
            viewBottomSheet?.apply {
                manicurist?.let {
                    val bill = Bill(
                        listManicurist = it.name,
                        idUser = id,
                        listNail = nail!!.name,
                        store = store!!.name,
                        imageNail = nail!!.image,
                        address = store!!.address,
                        date = String.format(
                            "%s;%s",
                            textDate.text.toString(),
                            textTimeChoose.text.toString()
                        ),
                        money = nail!!.price.toDouble(),
                        status = false
                    )
                    presenter?.saveBill(bill)
                }
            }
        } else {
            context?.showToast("Do you need to login into system of store")
        }
    }

    private fun initDialog() {
        context?.let { myDialog = LoadingDialog(it) }
    }

    private fun initPresenter() {
        context?.let {
            val repository = RepositoryUtils.getRepository(it)
            presenter = DetailNailPresenter(this, repository)
        }
        presenter?.start()
    }

    private fun onChipColorClicked(color: String) {
        parentFragmentManager.replaceFragment(
            R.id.fragmentContainer,
            ListNailFragment.getColorInstance(color)
        )
    }

    private fun onChipTagClicked(color: String) {
        parentFragmentManager.replaceFragment(
            R.id.fragmentContainer,
            ListNailFragment.getTagInstance(color)
        )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun clickOrder(nail: Nail) {
        bottomSheet = context?.let { BottomSheetDialog(it) }
        viewBottomSheet = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.bottom_sheet_detail_nail,
            bottomContainer,
            false
        )

        viewBottomSheet?.apply {
            buttonOrder.setOnClickListener(this@DetailNailFragment)
            textTimeChoose.setOnClickListener(this@DetailNailFragment)
            textPrice.text = String.format("%s $", nail.price)
            textNameNail.text = nail.name
            imageDate.setOnClickListener {
                val date = context?.let { it1 -> DatePickerDialog(it1) }
                date?.apply {
                    datePicker.minDate = System.currentTimeMillis()
                    datePicker.setOnDateChangedListener(this@DetailNailFragment)
                    viewBottomSheet?.textDate?.text = String.format(
                        "%s/%s/%s",
                        datePicker.dayOfMonth,
                        datePicker.month + 1,
                        datePicker.year
                    )
                }
                date?.show()
            }
        }

        bottomSheet?.setContentView(viewBottomSheet!!.root)
        bottomSheet?.show()
        presenter?.getStoreByNail(nail.id)
    }

    private fun showTimeList() {
        viewTime = DataBindingUtil.inflate<DetailTimeListBinding>(
            LayoutInflater.from(context),
            R.layout.detail_time_list,
            timeListContainer,
            false
        )!!

        val dialog = AlertDialog.Builder(context).setView(viewTime?.root).show()
        presenter?.getTimeByBill(
            manicurist?.name.toString()
        )
        viewTime?.buttonCancelTime?.setOnClickListener { dialog.dismiss() }
        viewTime?.buttonChooseTime?.setOnClickListener {
            if (viewTime?.chipTimeGroup?.checkedChipId != View.NO_ID) {
                viewBottomSheet?.apply {
                    textTimeChoose.visibility = View.VISIBLE
                    textTimeChoose.text =
                        viewTime?.chipTimeGroup?.findViewById<Chip>(viewTime!!.chipTimeGroup.checkedChipId)?.text
                }
                dialog.dismiss()
            } else {
                viewTime?.buttonChooseTime!!.showSnackBar("Do you no choose the time?")
            }
        }
    }

    override fun showTimeByBill(bills: List<Bill>) {
        if (bills.isEmpty()) return
        viewTime?.apply {
            for (i in 0..chipTimeGroup.childCount) {
                (chipTimeGroup.getChildAt(i) as? Chip)?.apply {
                    bills.forEach {
                        if (it.date.contains("${viewBottomSheet!!.textDate.text};${this.text}")) {
                            chipBackgroundColor = ColorStateList.valueOf(
                                ContextCompat.getColor(
                                    context,
                                    R.color.red_berry
                                )
                            )
                            isEnabled = false
                            return@forEach
                        }
                    }
                }
            }
        }
    }

    override fun showNailFavorite(isFavorite: Boolean) {
        if (isFavorite) {
            buttonFavorite.visibility = View.VISIBLE
        } else {
            buttonUnFavorite.visibility = View.VISIBLE
        }
    }

    companion object {
        const val BUNDLE_NAIL = "BUNDLE_NAIL"

        fun getInstance(component: Any) = DetailNailFragment().apply {
            arguments = bundleOf(BUNDLE_NAIL to component)
        }
    }
}
