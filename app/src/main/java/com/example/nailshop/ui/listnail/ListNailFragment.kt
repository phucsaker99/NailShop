package com.example.nailshop.ui.listnail

import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import com.example.nailshop.R
import com.example.nailshop.base.BaseFragment
import com.example.nailshop.data.model.Nail
import com.example.nailshop.ui.detailnail.DetailNailFragment
import com.example.nailshop.ui.dialog.LoadingDialog
import com.example.nailshop.ui.adapter.NailAdapter
import com.example.nailshop.utils.RepositoryUtils
import com.example.nailshop.utils.replaceFragment
import com.example.nailshop.utils.showToast
import kotlinx.android.synthetic.main.fragment_list_nail.*

class ListNailFragment : BaseFragment(), ListNailContract.View {
    private val nailAdapter = NailAdapter(this::clickItem)
    private var presenter: ListNailPresenter? = null
    private var myDialog: LoadingDialog? = null

    override val layoutResource get() = R.layout.fragment_list_nail

    override fun setupViews() {
        initToolbar()
        initPresenter()
        initAdapter()
        initDialog()
    }

    override fun initData() {
        arguments?.let {
            if (it.getString(BUNDLE_COLOR).equals("color")) {
                it.getString(BUNDLE_TYPE)?.let { color ->
                    presenter?.getNailListByColor(color)
                    toolbarListNail.title = color
                }
            } else {
                it.getString(BUNDLE_TYPE)?.let { tag ->
                    presenter?.getNailListByTag(tag)
                    toolbarListNail.title = tag
                }
            }
        }
    }

    override fun initActions() {
        toolbarListNail.setNavigationOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    private fun initToolbar() {
        setHasOptionsMenu(true)
        (requireActivity() as AppCompatActivity).setSupportActionBar(toolbarListNail)
    }

    private fun initDialog() {
        myDialog = context?.let { LoadingDialog(it) }
    }

    private fun initAdapter() {
        recyclerNail.setHasFixedSize(true)
        recyclerNail.adapter = nailAdapter
    }

    private fun initPresenter() {
        val repository = context?.let { RepositoryUtils.getRepository(it) }
        presenter = repository?.let { ListNailPresenter(this, it) }
        presenter?.start()
    }

    private fun clickItem(nail: Nail) {
        parentFragmentManager.replaceFragment(
            R.id.fragmentContainer,
            DetailNailFragment.getInstance(nail)
        )
    }

    override fun showNailListByColor(nails: List<Nail>) {
        nailAdapter.updateData(nails)
    }

    override fun showNailListByTag(nails: List<Nail>) {
        nailAdapter.updateData(nails)
    }

    override fun showLoading() {
        myDialog?.show()
    }

    override fun hideLoading() {
        myDialog?.dismiss()
    }

    override fun showError(message: String) {
        context?.showToast(message)
    }

    companion object {
        private const val BUNDLE_COLOR = "color"
        private const val BUNDLE_TAG = "tag"
        private const val BUNDLE_TYPE = "type"

        fun getColorInstance(component: String) = ListNailFragment().apply {
            arguments = bundleOf(BUNDLE_COLOR to "color", BUNDLE_TYPE to component)
        }

        fun getTagInstance(component: String) = ListNailFragment().apply {
            arguments = bundleOf(BUNDLE_TAG to "tag", BUNDLE_TYPE to component)
        }
    }
}
