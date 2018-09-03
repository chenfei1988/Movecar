package xinyiyun.chenfei.com.movecar.ui.fragment

import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import kotlinx.android.synthetic.main.fragment_team_tj.*
import org.jetbrains.anko.dip
import xinyiyun.chenfei.com.baselibrary.annotation.ContentView
import xinyiyun.chenfei.com.baselibrary.annotation.StateView
import xinyiyun.chenfei.com.baselibrary.basic.BaseApplication
import xinyiyun.chenfei.com.baselibrary.basic.BaseFragment
import xinyiyun.chenfei.com.baselibrary.common.vertical
import xinyiyun.chenfei.com.movecar.R
import xinyiyun.chenfei.com.movecar.common.MATERIAL_COLORS
import xinyiyun.chenfei.com.movecar.common.api
import xinyiyun.chenfei.com.movecar.presenter.MovecarPresenter
import xinyiyun.chenfei.com.movecar.requestmodel.TonJiTeamNoInfo
import xinyiyun.chenfei.com.movecar.utils.MPAchart.PieChartManager
import java.util.*

/**
 * Created by Administrator on 2018/3/15.
 */
@ContentView(R.layout.fragment_team_tj)
@StateView(R.id.rcl_infos, isShowEmpty = true)
class TeamTJfragment : BaseFragment() {

    private var mContentList = ArrayList<TonJiTeamNoInfo>()
    override fun onViewCreated(view: View) {



        GetTonJiTeamNoInfoCount()
        rcl_infos.apply {
            vertical(dip(0.5f), R.color.gray_line)
            adapter = LineAdapter(mContentList).apply {
                setOnItemClickListener { _, _, position ->
                    when (position) {
                    }
                }
            }
        }
    }

    fun GetTonJiTeamNoInfoCount() {
        api<MovecarPresenter> {
            tjteaminfocount(BaseApplication.loginInfo.userid.toString(),{
                this.apply {
                    val count = this.count
                    if (count > 0) {
                        GetTonJiTeamNoInfo(count)
                    }
                }
            })
        }
    }

    fun GetTonJiTeamNoInfo(count: Int) {
        api<MovecarPresenter> {
            tjteaminfo(BaseApplication.loginInfo.userid.toString(), count, {
                this.apply {
                    mContentList.clear()
                    mContentList.addAll(this)
                    ShowBar()
                    (rcl_infos.adapter as LineAdapter).apply {
                        setNewData(mContentList)
                    }
                }
            })
        }
    }
    /**
     *
     * @param
     *
     * @param
     */
    private fun ShowBar() {
        val xValues = ArrayList<String>() // xVals用来表示每个饼块上的内容
        val yValues = ArrayList<Float>() // yVals用来表示封装每个饼块的实际数据
        // 饼图数据
        for(i in mContentList.indices){
            val tjinfo = mContentList.get(i)
            xValues.add(tjinfo.teamno)
            yValues.add(tjinfo.num.toFloat())
        }
        val pieChartManager = PieChartManager(mPieChart)
        pieChartManager.setPieChart(xValues, yValues, MATERIAL_COLORS.toList())
        pieChartManager.setCenterDescription("车队统计饼状图",R.color.black)
        pieChartManager.setDescription("")
        mPieChart.visibility = View.VISIBLE
    }
    inner class LineAdapter(data: List<TonJiTeamNoInfo>?) : BaseQuickAdapter<TonJiTeamNoInfo, BaseViewHolder>(R.layout.item_vehicletj, data) {

        override fun convert(helper: BaseViewHolder, item: TonJiTeamNoInfo) {
            helper.setText(R.id.title, item.teamno)
            helper.setText(R.id.value, item.num.toString())
            helper.setVisible(R.id.right_icon, false)
        }
    }
}