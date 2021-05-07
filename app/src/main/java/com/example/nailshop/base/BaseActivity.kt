package com.example.nailshop.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.nailshop.R
import com.example.nailshop.ui.detailnail.DetailNailFragment
import com.example.nailshop.utils.clearBackStack

abstract class BaseActivity : AppCompatActivity() {
    @get:LayoutRes
    protected abstract val layoutResource: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutResource)
        initComponents()
    }

    protected fun openFragment(fragment: Fragment) =
        supportFragmentManager.clearBackStack()
            .beginTransaction().setCustomAnimations(
                R.anim.slide_in,
                R.anim.fade_out,
                R.anim.fade_in,
                R.anim.slide_out
            )
            .replace(R.id.fragmentContainer, fragment).commit()

    protected abstract fun initComponents()
}
