package com.example.nailshop.ui.homepage

import com.example.nailshop.R
import com.example.nailshop.base.BaseFragment
import com.example.nailshop.data.model.Manicurist
import com.example.nailshop.data.model.Nail
import com.example.nailshop.ui.adapter.ManicuristAdapter
import com.example.nailshop.ui.adapter.NailAdapter
import com.example.nailshop.ui.detailnail.DetailNailFragment
import com.example.nailshop.ui.dialog.LoadingDialog
import com.example.nailshop.ui.manicuristdetail.DetailManicuristFragment
import com.example.nailshop.ui.store.StoreFragment
import com.example.nailshop.utils.RepositoryUtils
import com.example.nailshop.utils.replaceFragment
import com.example.nailshop.utils.showToast
import kotlinx.android.synthetic.main.fragment_home.*

class HomePageFragment : BaseFragment(), HomePageContract.View {

    private var nailAdapter = NailAdapter(this::clickItemNail)
    private var manicuristAdapter = ManicuristAdapter(this::clickItemManicurist)

    private var presenter: HomePresenter? = null
    private var myDialog: LoadingDialog? = null

    override val layoutResource get() = R.layout.fragment_home

    override fun setupViews() {
        initAdapter()
        initDialog()
    }


    override fun initData() {
        val context = context ?: return
        val repository = RepositoryUtils.getRepository(context)
        presenter = HomePresenter(this, repository)
        presenter?.start()
    }

    override fun initActions() {
        textSearch.setOnClickListener {
            parentFragmentManager.replaceFragment(R.id.fragmentContainer, StoreFragment())
        }
    }

    override fun showNail(nails: List<Nail>) {
        nailAdapter.updateData(nails)
    }

    override fun showManicurist(manicurists: List<Manicurist>) {
        val sortedManicurist = manicurists.sortedByDescending { it.like }
        manicuristAdapter.updateData(sortedManicurist)
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

    private fun initDialog() {
        myDialog = context?.let { LoadingDialog(it) }
    }

    private fun initAdapter() {
        recyclerNail.apply {
            setHasFixedSize(true)
            adapter = nailAdapter
        }

        recyclerTopManicurist.apply {
            setHasFixedSize(true)
            adapter = manicuristAdapter
        }
    }

    private fun clickItemNail(nail: Nail) {
        parentFragmentManager.replaceFragment(
            R.id.fragmentContainer,
            DetailNailFragment.getInstance(nail)
        )
    }

    private fun clickItemManicurist(manicurist: Manicurist) {
        parentFragmentManager.replaceFragment(
            R.id.fragmentContainer,
            DetailManicuristFragment.getManicuristInstance(manicurist)
        )
    }
}
