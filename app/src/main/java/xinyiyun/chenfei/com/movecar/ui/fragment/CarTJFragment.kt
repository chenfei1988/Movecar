package xinyiyun.chenfei.com.movecar.ui.fragment

import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import kotlinx.android.synthetic.main.fragment_car_tj.*
import org.jetbrains.anko.dip
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.sdk25.coroutines.textChangedListener
import xinyiyun.chenfei.com.baselibrary.annotation.ContentView
import xinyiyun.chenfei.com.baselibrary.annotation.StateView
import xinyiyun.chenfei.com.baselibrary.basic.BaseApplication
import xinyiyun.chenfei.com.baselibrary.basic.BaseFragment
import xinyiyun.chenfei.com.baselibrary.common.vertical
import xinyiyun.chenfei.com.baselibrary.utils.DateUtils
import xinyiyun.chenfei.com.baselibrary.utils.SPreferencesUtils
import xinyiyun.chenfei.com.movecar.R
import xinyiyun.chenfei.com.movecar.common.api
import xinyiyun.chenfei.com.movecar.common.timePickerYMD
import xinyiyun.chenfei.com.movecar.presenter.MovecarPresenter
import xinyiyun.chenfei.com.movecar.requestmodel.GetTonJiInfo
import xinyiyun.chenfei.com.movecar.utils.MPAchart.BarChartManager
import java.math.BigDecimal
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Administrator on 2018/3/12.
 */
@ContentView(R.layout.fragment_car_tj)
@StateView(R.id.rcl_infos, isShowEmpty = true)
class CarTJFragment : BaseFragment() {
    private var mStartTime = ""// 查询条件 -- 开始时间
    private var mEndTime = ""// 查询条件 -- 结束时间
    private var totalNum = 0// 车辆总数
    private var stopNum = 0// 停运车辆数
    private var noDataNum = 0// 无数据车辆数
    private var onlineNum = 0// 上线车辆数
    private var locNum = 0// 定位车辆数
    private var offlineNum = 0// 下线车辆数
    private var noLocNum = 0//不定位车辆
    private var mContentList = ArrayList<GetTonJiInfo>()
    private var mLineDataList = ArrayList<LineData>()
    override fun onViewCreated(view: View) {
        val calendar = Calendar.getInstance()
        val sTime = calendar.time.time
        calendar.add(Calendar.DATE, 1)
        val eTime = calendar.time.time
        mStartTime = DateUtils.formatDataToyyyMMdd(sTime, 6)
        statistics_txt_time.text = mStartTime
        mEndTime = DateUtils.formatDataToyyyMMdd(eTime, 6)
        GetTonJiInfoCount()
        totalNum = SPreferencesUtils.getInt(activity, "vehicletotal", 0)
        time_search.onClick {
            timePickerYMD(init = statistics_txt_time.text.toString(), block = { _, s ->
                statistics_txt_time.text = s
                mStartTime = s
            })
        }
        statistics_txt_time.textChangedListener {
            afterTextChanged {
                try {
                    var date = SimpleDateFormat("yyyy-MM-dd").parse(statistics_txt_time.text.toString())
                    var calendar = Calendar.getInstance()
                    calendar.time = date
                    calendar.add(Calendar.DATE, 1)
                    val eTime = calendar.time.time
                    mEndTime = DateUtils.formatDataToyyyMMdd(eTime, 6)
                    GetTonJiInfoCount()

                } catch (e: Exception) {

                }
            }

        }
        rcl_infos.apply {
            vertical(dip(0.5f), R.color.gray_line)
            adapter = LineAdapter(mLineDataList).apply {
                setOnItemClickListener { _, _, position ->
                    when (position) {
                    //  0 -> startActivity<WaitApproveActivity>("aftype" to "0") //车辆申请

                    }
                }
            }
        }
    }

    fun GetTonJiInfoCount() {
        api<MovecarPresenter> {
            GetTonJiInfoCount(BaseApplication.loginInfo.userid.toString(), mStartTime, mEndTime, {
                this.apply {
                    val count = this.count
                    if (count > 0) {
                        GetTonJiInfo(count)
                    }
                }
            })
        }
    }

    fun GetTonJiInfo(count: Int) {
        api<MovecarPresenter> {
            GetTonJiInfo(BaseApplication.loginInfo.userid.toString(), count, mStartTime, mEndTime, {
                this.apply {
                    mContentList.clear()
                    mContentList.addAll(this)
                    InitDatas()

                }
            })
        }
    }

    fun InitDatas() {
        stopNum = 0// 停运车辆数
        noDataNum = 0// 无数据车辆数
        onlineNum = 0// 上线车辆数
        locNum = 0// 定位车辆数
        offlineNum = 0// 下线车辆数
        noLocNum = 0//不定位车辆
        if (mContentList.size > 0) {
            for (i in mContentList.indices) {
                val entity = mContentList.get(i)
                stopNum += entity.stopV
                noDataNum += entity.noData
                onlineNum += entity.onLineV
                locNum += entity.locateV
            }
        }
        noLocNum = onlineNum - locNum
        offlineNum = totalNum - onlineNum
        ShowBar()
    }

    /**
     *
     * @param
     *
     * @param
     */
    private fun ShowBar() {
        val xValues = ArrayList<String>() // xVals用来表示每个饼块上的内容
        xValues.add("车辆总数")
        xValues.add("停运数")
        xValues.add("定位数")
        xValues.add("不定位数")
        xValues.add("无数据数")
        xValues.add("上线数")
        xValues.add("下线数")
        val yValues = ArrayList<Float>() // yVals用来表示封装每个饼块的实际数据
        // 饼图数据
        /**
         * 将一个饼形图分成四部分， 四部分的数值比例为14:14:34:38 所以 14代表的百分比就是14%
         */
        yValues.add(totalNum.toFloat())
        yValues.add(stopNum.toFloat())
        yValues.add(locNum.toFloat())
        yValues.add(noLocNum.toFloat())
        yValues.add(noDataNum.toFloat())
        yValues.add(onlineNum.toFloat())
        yValues.add(offlineNum.toFloat())
        val barChartManager1 = BarChartManager(mBarChart)
        barChartManager1.showBarChartString(xValues, yValues, "车辆统计报表")
        barChartManager1.setDescription("")
        mBarChart.visibility = View.VISIBLE
        InitLineDatas()
    }

    private fun InitLineDatas() {
        mLineDataList.clear()
        mLineDataList.add(LineData("车辆总数", totalNum.toString()))
        mLineDataList.add(LineData("停运车辆数", stopNum.toString()))
        mLineDataList.add(LineData("定位车辆数", locNum.toString()))
        mLineDataList.add(LineData("不定位车辆数", noLocNum.toString()))
        if (totalNum - stopNum > 0) {
            val b1 = BigDecimal(locNum * 100)
            val b2 = BigDecimal(totalNum - stopNum)
            val ret = b1.divide(b2, 2, BigDecimal.ROUND_HALF_UP)
                    .toDouble()
            mLineDataList.add(LineData("定位率", ret.toString() + "%"))
        } else {
            mLineDataList.add(LineData("定位率", "0.0%"))
        }
        mLineDataList.add(LineData("无数据车辆数", noDataNum.toString()))
        mLineDataList.add(LineData("上线车辆数", onlineNum.toString()))
        mLineDataList.add(LineData("下线车辆数", offlineNum.toString()))
        if (totalNum - stopNum > 0) {
            val b1 = BigDecimal(onlineNum * 100)
            val b2 = BigDecimal(totalNum - stopNum)
            val ret = b1.divide(b2, 2, BigDecimal.ROUND_HALF_UP)
                    .toDouble()
            mLineDataList.add(LineData("上线率", ret.toString() + "%"))
        } else {
            mLineDataList.add(LineData("上线率", "0.0%"))
        }
        (rcl_infos.adapter as LineAdapter).apply {
            setNewData(mLineDataList)
        }

    }

    inner class LineAdapter(data: List<LineData>?) : BaseQuickAdapter<LineData, BaseViewHolder>(R.layout.item_vehicletj, data) {

        override fun convert(helper: BaseViewHolder, item: LineData) {
            helper.setText(R.id.title, item.name)
            helper.setText(R.id.value, item.value)
            when (helper.adapterPosition) {
                0, 4, 8 -> helper.setVisible(R.id.right_icon, false)
                1, 2, 3, 5, 6, 7 -> helper.setVisible(R.id.right_icon, true)
            }
        }
    }

    data class LineData(var name: String, var value: String)
}