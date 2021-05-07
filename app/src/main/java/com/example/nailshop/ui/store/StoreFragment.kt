package com.example.nailshop.ui.store

import android.graphics.Color
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.EditText
import androidx.appcompat.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import com.example.nailshop.R
import com.example.nailshop.base.BaseFragment
import com.example.nailshop.data.model.Store
import com.example.nailshop.ui.adapter.StoreAdapter
import com.example.nailshop.ui.detailstore.DetailStoreFragment
import com.example.nailshop.ui.dialog.LoadingDialog
import com.example.nailshop.utils.RepositoryUtils
import com.example.nailshop.utils.replaceFragment
import com.example.nailshop.utils.showToast
import kotlinx.android.synthetic.main.fragment_store.*

class StoreFragment : BaseFragment(), StoreContract.View {
    private var presenter: StorePresenter? = null
    private var myDialog: LoadingDialog? = null
    private var storeAdapter = StoreAdapter(this::itemStoreClicked)

    override val layoutResource get() = R.layout.fragment_store

    override fun setupViews() {
        initToolbar()
        initAdapter()
        initDialog()
    }

    private fun initToolbar() {
        setHasOptionsMenu(true)
        (requireActivity() as AppCompatActivity).setSupportActionBar(toolbarStore)
    }

    override fun initData() {
        val context = context ?: return
        val repository = RepositoryUtils.getRepository(context)
        presenter = repository.let { StorePresenter(this, it) }
        presenter?.start()
    }

    override fun initActions() {

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_search_view, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_search -> {
                setSearchView(item)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setSearchView(itemSearch: MenuItem) {
        val searchView = itemSearch.actionView as SearchView
        val editSearch = searchView.findViewById<EditText>(androidx.appcompat.R.id.search_src_text)
        editSearch.apply {
            setTextColor(Color.WHITE)
            setHintTextColor(Color.GRAY)
        }
        searchView.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                storeAdapter.filter.filter(newText)
                return true
            }
        })
    }

    private fun initAdapter() {
        recyclerStore.adapter = storeAdapter
    }

    private fun initDialog() {
        myDialog = context?.let { LoadingDialog(it) }
    }

    private fun itemStoreClicked(store: Store) {
        parentFragmentManager.replaceFragment(
            R.id.fragmentContainer,
            DetailStoreFragment.getInstance(store)
        )
    }

    override fun showAllStores(stores: List<Store>) {
        storeAdapter.updateData(stores)
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
