package com.example.nailshop.ui.main


import android.content.Context
import android.content.Intent
import com.example.nailshop.R
import com.example.nailshop.base.BaseActivity
import com.example.nailshop.ui.homepage.HomePageFragment
import com.example.nailshop.ui.more.MoreFragment
import com.example.nailshop.ui.schedule.ScheduleFragment
import com.example.nailshop.ui.store.StoreFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    private val homePageFragment = HomePageFragment()
    private val storeFragment = StoreFragment()
    private val scheduleFragment = ScheduleFragment()
    private val moreFragment = MoreFragment()

    override val layoutResource: Int
        get() = R.layout.activity_main

    override fun initComponents() {
        navClient.setOnNavigationItemSelectedListener(onBottomNavigation)
        navClient.selectedItemId = R.id.menuHome
    }

    private val onBottomNavigation =
        BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menuHome -> openFragment(homePageFragment)
                R.id.menuStore -> openFragment(storeFragment)
                R.id.menuSchedule -> openFragment(scheduleFragment)
                R.id.menuMore -> openFragment(moreFragment)
            }
            true
        }

    companion object {
        fun getIntent(context: Context) = Intent(context, MainActivity::class.java)
    }
}
