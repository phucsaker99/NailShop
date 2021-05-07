package com.example.nailshop.ui.manicuristdetail

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import com.example.nailshop.R
import com.example.nailshop.base.BaseFragment
import com.example.nailshop.data.model.Manicurist
import com.example.nailshop.ui.dialog.LoadingDialog
import com.example.nailshop.utils.loadImage
import kotlinx.android.synthetic.main.fragment_manicurist_detail.*

class DetailManicuristFragment : BaseFragment() {
    private var loadingDialog: LoadingDialog? = null
    private var manicurist: Manicurist? = null

    override val layoutResource get() = R.layout.fragment_manicurist_detail

    override fun setupViews() {
        initToolbar()
        initDialog()
        initBundle()
    }

    private fun initToolbar() {
        setHasOptionsMenu(true)
        (requireActivity() as AppCompatActivity).setSupportActionBar(toolManicurist)
        toolManicurist.apply {
            title = "Manicurist"
            setTitleTextColor(Color.WHITE)
        }
    }

    override fun initData() {

    }

    override fun initActions() {
        toolManicurist.setNavigationOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    private fun initBundle() {
        manicurist = arguments?.getParcelable(BUNDLE_MANICURIST)
        manicurist?.let {
            textManicuristName.text = it.name
            textStore.text = String.format("Cửa hàng: %s", it.store)
            if (it.gender == 0) {
                textGender.text = "Giới tính: nam"
            } else {
                textGender.text = "Giới tính: nữ"
            }
            textLike.text = String.format("Yêu thích: %s", it.like.toString())
            imageManicurist.loadImage(it.image)
        }
    }

    private fun initDialog() {
        context?.let { loadingDialog = LoadingDialog(it) }
    }

    companion object {
        private const val BUNDLE_MANICURIST = "BUNDLE_MANICURIST"
        fun getManicuristInstance(manicurist: Manicurist) = DetailManicuristFragment().apply {
            arguments = bundleOf(BUNDLE_MANICURIST to manicurist)
        }
    }
}