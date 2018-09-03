package xinyiyun.chenfei.com.movecar.ui.activity

import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import kotlinx.android.synthetic.main.activity_vehicle_statistics.*
import xinyiyun.chenfei.com.baselibrary.annotation.ContentView
import xinyiyun.chenfei.com.baselibrary.annotation.ToolbarView
import xinyiyun.chenfei.com.baselibrary.basic.BaseActivity
import xinyiyun.chenfei.com.movecar.R
import xinyiyun.chenfei.com.movecar.ui.fragment.AlarmTJfragment
import xinyiyun.chenfei.com.movecar.ui.fragment.CarTJFragment
import xinyiyun.chenfei.com.movecar.ui.fragment.TeamTJfragment

/**
 * Created by Administrator on 2018/3/8.
 */
@ContentView(R.layout.activity_vehicle_statistics)
@ToolbarView("信息统计", true)
class VehicleStatisticsActivity : BaseActivity() {
    override fun onViewCreated() {
        tab_tabs.apply {
            (1..4).forEach {
                addTab(newTab())
            }
            tabMode = TabLayout.MODE_FIXED
            setupWithViewPager(vp_things.apply {
                offscreenPageLimit = 4
                adapter = TabAdapter(supportFragmentManager)
            })
        }
    }

    inner class TabAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
        override fun getItem(position: Int) = listOf<Fragment>(CarTJFragment(), TeamTJfragment(), AlarmTJfragment(), CarTJFragment())[position]
        override fun getCount() = 4
        override fun getPageTitle(position: Int) = listOf("车辆", "车队","报警", "里程")[position]
    }
}
