package com.example.nailshop.ui.detailstore.manicurist

import com.example.nailshop.R
import com.example.nailshop.base.BaseFragment
import com.example.nailshop.data.model.Manicurist
import com.example.nailshop.data.model.Store
import com.example.nailshop.ui.adapter.ManicuristSortAdapter
import com.example.nailshop.ui.detailstore.DetailStoreFragment
import com.example.nailshop.ui.dialog.LoadingDialog
import com.example.nailshop.ui.manicuristdetail.DetailManicuristFragment
import com.example.nailshop.utils.RepositoryUtils
import com.example.nailshop.utils.replaceFragment
import com.example.nailshop.utils.showToast
import kotlinx.android.synthetic.main.fragment_sort_manicurist_nail.*

class SortManicuristFragment : BaseFragment(), SortManicuristContract.View {
    private val adapter = ManicuristSortAdapter(this::setListener)
    private var presenter: SortManicuristPresenter? = null
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
            presenter = SortManicuristPresenter(this, repository)
        }
        presenter?.start()
        store?.let { presenter?.getManicuristsByStore(it.listManicurist) }
    }

    override fun initActions() {

    }

    override fun showManicuristsByStore(manicurists: List<Manicurist>) {
        adapter.updateData(manicurists)
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

    private fun setListener(manicurist: Manicurist) {
        parentFragment?.parentFragmentManager?.replaceFragment(
            R.id.fragmentContainer,
            DetailManicuristFragment.getManicuristInstance(manicurist)
        )
    }
}