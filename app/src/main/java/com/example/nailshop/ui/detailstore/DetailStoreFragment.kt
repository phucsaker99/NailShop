package com.example.nailshop.ui.detailstore

import androidx.core.os.bundleOf
import com.example.nailshop.R
import com.example.nailshop.base.BaseFragment
import com.example.nailshop.data.model.Store
import com.example.nailshop.ui.adapter.NailViewPagerAdapter
import com.example.nailshop.ui.detailstore.manicurist.SortManicuristFragment
import com.example.nailshop.ui.detailstore.nail.SortNailFragment
import com.example.nailshop.ui.dialog.LoadingDialog
import com.example.nailshop.utils.RepositoryUtils
import com.example.nailshop.utils.showToast
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_store_detail.*

class DetailStoreFragment : BaseFragment(), DetailStoreContract.View {
    private var presenter: DetailStorePresenter? = null
    private var myDialog: LoadingDialog? = null
    private var store: Store? = null
    private val sortManicuristFragment = SortManicuristFragment()
    private val sortNailFragment = SortNailFragment()

    override val layoutResource get() = R.layout.fragment_store_detail

    override fun setupViews() {
        initInf()
        initTabLayout()
        setTabsWithViewPager()
        initDialog()
        initAdapter()
    }

    override fun initData() {
        context?.let {
            val repository = RepositoryUtils.getRepository(it)
            presenter = DetailStorePresenter(this, repository)
        }
        presenter?.start()
    }

    override fun initActions() {

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

    private fun initTabLayout() {
        NailViewPagerAdapter(childFragmentManager).apply {
            addFragment(sortNailFragment, getString(R.string.title_nail))
            addFragment(sortManicuristFragment, getString(R.string.title_manicurist))
            viewPagerStore.adapter = this
        }
    }

    private fun setTabsWithViewPager() {
        tabStore.apply {
            addTab(this.newTab())
            addTab(this.newTab())
            tabGravity = TabLayout.GRAVITY_FILL
            setupWithViewPager(viewPagerStore)
        }
    }

    private fun initInf() {
        store = arguments?.getParcelable<Store>(BUNDLE_STORE)?.apply {
            textStoreAddress.text = address
            textStoreName.text = name
            textTime.text = timeDay
        }
    }

    private fun initAdapter() {

    }

    private fun initDialog() {
        context?.let { myDialog = LoadingDialog(it) }
    }

    companion object {
        const val BUNDLE_STORE = "BUNDLE_STORE"
        fun getInstance(store: Store) = DetailStoreFragment().apply {
            arguments = bundleOf(BUNDLE_STORE to store)
        }
    }
}
