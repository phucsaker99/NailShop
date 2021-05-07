package com.example.nailshop.ui.detailstore.nail

import com.example.nailshop.R
import com.example.nailshop.base.BaseFragment
import com.example.nailshop.data.model.Nail
import com.example.nailshop.data.model.Store
import com.example.nailshop.ui.adapter.NailSortAdapter
import com.example.nailshop.ui.detailnail.DetailNailFragment
import com.example.nailshop.ui.detailstore.DetailStoreFragment
import com.example.nailshop.ui.dialog.LoadingDialog
import com.example.nailshop.utils.RepositoryUtils
import com.example.nailshop.utils.replaceFragment
import com.example.nailshop.utils.showToast
import kotlinx.android.synthetic.main.fragment_sort_manicurist_nail.*

class SortNailFragment : BaseFragment(), SortNailContract.View {
    private val adapter = NailSortAdapter(this::setListener)
    private var presenter: SortNailPresenter? = null
    private var loadingDialog: LoadingDialog? = null
    private var store: Store? = null

    override val layoutResource get() = R.layout.fragment_sort_manicurist_nail

    override fun setupViews() {
        initAdapter()
        initDialog()
        initBundle()
    }

    private fun initBundle() {
        store = parentFragment?.requireArguments()?.getParcelable(DetailStoreFragment.BUNDLE_STORE)
    }

    private fun initDialog() {
        context?.let { loadingDialog = LoadingDialog(it) }
    }

    private fun initAdapter() {
        recyclerSortManicurist.adapter = adapter
    }

    override fun initData() {
        context?.let {
            val repository = RepositoryUtils.getRepository(it)
            presenter = SortNailPresenter(this, repository)
        }
        presenter?.start()
        store?.let { presenter?.getNailsByStore(it.listNail) }
    }

    override fun initActions() {

    }

    override fun showNailsByStore(nails: List<Nail>) {
        adapter.updateData(nails)
    }

    override fun showError(message: String) {
        context?.showToast(message)
    }

    override fun showLoading() {
        loadingDialog?.show()
    }

    override fun hideLoading() {
        loadingDialog?.dismiss()
    }

    private fun setListener(nail: Nail) {
        parentFragment?.parentFragmentManager?.replaceFragment(R.id.fragmentContainer, DetailNailFragment.getInstance(nail))
    }
}