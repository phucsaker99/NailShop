package com.example.nailshop.ui.favorite

import com.example.nailshop.R
import com.example.nailshop.base.BaseFragment
import com.example.nailshop.data.model.Nail
import com.example.nailshop.ui.adapter.NailFavoriteAdapter
import com.example.nailshop.ui.detailnail.DetailNailFragment
import com.example.nailshop.ui.dialog.LoadingDialog
import com.example.nailshop.utils.RepositoryUtils
import com.example.nailshop.utils.addFragment
import com.example.nailshop.utils.replaceFragment
import com.example.nailshop.utils.showToast
import kotlinx.android.synthetic.main.fragment_favorite_all.*

class NailFavoriteFragment : BaseFragment(), AllMealContract.View {
    private val adapter = NailFavoriteAdapter(this::itemMealClicked)
    private var presenter: AllMealPresenter? = null
    private var loadingDialog: LoadingDialog? = null

    override val layoutResource get() = R.layout.fragment_favorite_all

    override fun setupViews() {
        initAdapter()
        initDialog()
    }

    override fun initData() {
        context?.let {
            val mealRepository = RepositoryUtils.getRepository(it)
            presenter = AllMealPresenter(
                this,
                mealRepository
            )
        }
        presenter?.start()
    }

    override fun initActions() {

    }

    override fun showAllNailFavorite(nails: List<Nail>) {
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

    private fun initDialog() {
        context?.let { loadingDialog = LoadingDialog(it) }
    }

    private fun initAdapter() {
        recyclerFavoriteAll.adapter = adapter
    }

    private fun itemMealClicked(nail: Nail) {
        parentFragmentManager.replaceFragment(
            R.id.fragmentContainer,
            DetailNailFragment.getInstance(nail)
        )
    }

    companion object {
        fun getInstance() = NailFavoriteFragment()
    }
}
