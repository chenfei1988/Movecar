package xinyiyun.chenfei.com.movecar.ui.activity

import android.graphics.Color
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.widget.GridLayoutManager
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.flyco.dialog.listener.OnBtnClickL
import com.flyco.dialog.widget.NormalDialog
import com.jude.rollviewpager.RollPagerView
import com.jude.rollviewpager.adapter.LoopPagerAdapter
import com.jude.rollviewpager.hintview.ColorPointHintView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.nav_header_main.*
import org.jetbrains.anko.dip
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.toast
import xinyiyun.chenfei.com.baselibrary.basic.BaseActivity
import xinyiyun.chenfei.com.baselibrary.basic.BaseApplication
import xinyiyun.chenfei.com.baselibrary.common.api
import xinyiyun.chenfei.com.baselibrary.common.grid
import xinyiyun.chenfei.com.baselibrary.config.GlideOptions
import xinyiyun.chenfei.com.baselibrary.utils.PreferencesHelper
import xinyiyun.chenfei.com.baselibrary.utils.SPreferencesUtils
import xinyiyun.chenfei.com.movecar.R
import xinyiyun.chenfei.com.movecar.presenter.MovecarPresenter
import java.util.*

class MainActivity : BaseActivity() {

    lateinit var imageurls: Array<String>
    lateinit var searchimages: IntArray
    lateinit var searchmtitles: Array<String>
    var listData: MutableList<Menu> = mutableListOf()

    override fun onViewCreated() {
        setContentView(R.layout.activity_main)
        InitOnclick()
        InitPagerView()
        initSearchData()
    }

    private fun InitOnclick() {
        carInfoLayout.onClick {
            startActivity<CompanySelectActivity>()
        }
        vehiclestatisticsLayout.onClick {startActivity<VehicleStatisticsActivity>() }
        mycollectedvehicle.onClick { }
        loginout.onClick {
            LoginOUT()
        }

    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    fun OpenLeftMenu(view: View) {
        drawer_layout.openDrawer(Gravity.LEFT)
        drawer_layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED,
                Gravity.LEFT)
    }

    private fun initSearchData() {
        searchmtitles = arrayOf("历史记录", "报警查询", "短信调度", "照片信息", "企业巡岗", "企业在线", "操作记录"
              //  , "超速报警", "计划取消", "误趟统计", "车辆跑班"
        )
        searchimages = intArrayOf(R.mipmap.lishijilu, R.mipmap.baojingchaxun, R.mipmap.duanxindiaodu, R.mipmap.zhaopianxinxi, R.mipmap.qiyexungang, R.mipmap.qiyezaixian, R.mipmap.caozuojilu
              //  , R.mipmap.speedalarm,
             //   R.mipmap.plancancel, R.mipmap.wttj, R.mipmap.clpb
        )
        for (i in searchmtitles.indices) {
            listData.add(Menu(searchmtitles[i], searchimages[i]))
        }
        searchrv.apply {
            grid(3, dip(0.5f), R.color.gray_line)
            adapter = SearchAdapter(listData).apply {
                setOnItemClickListener { adapter, view, position ->
                    when (position) {
                    /*  0 -> startActivity<VehicleApplyActivity>()
                      1 -> startActivity<RepairApplyActivity>()
                      2 -> startActivity<DrivingObserveActivity>()
                      3 -> startActivity<VehicleReservationActivity>()
                      4 -> startActivity<MyVehManageActivity>()
                      5 -> startActivity<FirstSceneActivity>()
                      6 -> startActivity<RoadRiskActivity>()
                      7 -> startActivity<VehicleKnowledgeActivity>()*/
                    }
                }
            }
        }
    }

    private fun InitPagerView() {
        imageurls = arrayOf(this@MainActivity.getString(R.string.imagesulr_address) + "images/home1.png", this@MainActivity.getString(R.string.imagesulr_address) + "images/home2.png", this@MainActivity.getString(R.string.imagesulr_address) + "images/home3.png")
        roll_view_pager.setPlayDelay(3000)
        roll_view_pager.setAnimationDurtion(500)
        roll_view_pager.setAdapter(TestLoopAdapter(roll_view_pager))
        //   mRollViewPager.setHintView(new IconHintView(getActivity(), R.mipmap.point_focus, R.mipmap.point_normal));
        roll_view_pager.setHintView(ColorPointHintView(this, resources.getColor(R.color.themecolor), Color.WHITE))
    }

    fun LoginOUT(){
        val dialog = NormalDialog(this)
        dialog.content("你确定要切换账号吗？")//
                .style(NormalDialog.STYLE_TWO)//
                .titleTextSize(23f)//
                .show()
        dialog.setOnBtnClickL(
                OnBtnClickL {
                    dialog.dismiss()
                },
                OnBtnClickL {
                    PreferencesHelper.clearData()
                    dialog.dismiss()
                    startActivity<LoginActivity>()
                    finish()
                })
       }

    private fun UpdateAPP() {
    /*    api<MovecarPresenter> {
            versionupdate(MyApplication.appversion, {
                this.apply {
                    if (status) {
                        content.apply {
                            val dialog = NormalDialog(this@MainActivity)
                            dialog.content(tip)//
                                    .style(NormalDialog.STYLE_TWO)//
                                    .titleTextSize(23f)//
                                    .show()
                            dialog.setOnBtnClickL(
                                    OnBtnClickL {
                                        dialog.dismiss()
                                    },
                                    OnBtnClickL {
                                        UpdateUtils.downLoadApk(this@MainActivity, xinyiyun.chenfei.com.baselibrary.net.ApiService.BASE_URL.substring(0, xinyiyun.chenfei.com.baselibrary.net.ApiService.BASE_URL.length-1) + content.appUpdateUrl)
                                        dialog.dismiss()
                                    })
                        }
                    } else {
                        toast("已经是最新的版本")
                    }
                }
            })
        }*/
    }
    private inner class TestLoopAdapter(viewPager: RollPagerView) : LoopPagerAdapter(viewPager) {

        override fun getView(container: ViewGroup, position: Int): View {
            val view = ImageView(container.context)
            Glide.with(container.context).load(imageurls[position]).into(view)
            view.scaleType = ImageView.ScaleType.CENTER_CROP
            view.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
            return view
        }

        override fun getRealCount(): Int {
            return imageurls.size
        }
    }

    inner class SearchAdapter(data: List<Menu>?) : BaseQuickAdapter<Menu, BaseViewHolder>(R.layout.item_main_search, data) {

        override fun convert(helper: BaseViewHolder, item: Menu) {
            helper.setText(R.id.name, item.title)
            helper.setImageResource(R.id.image, item.iconId)
        }
    }

    inner class Menu(var title: String, var iconId: Int)
}
