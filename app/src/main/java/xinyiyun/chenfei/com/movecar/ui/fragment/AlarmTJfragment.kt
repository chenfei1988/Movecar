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
import xinyiyun.chenfei.com.movecar.requestmodel.TonJiAlarmInfo
import xinyiyun.chenfei.com.movecar.requestmodel.TonJiAlarmTypeInfo
import xinyiyun.chenfei.com.movecar.utils.MPAchart.BarChartManager
import java.math.BigDecimal
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Administrator on 2018/3/12.
 */
@ContentView(R.layout.fragment_car_tj)
@StateView(R.id.rcl_infos, isShowEmpty = true)
class AlarmTJfragment : BaseFragment() {
    private var mStartTime = ""// 查询条件 -- 开始时间
    private var mEndTime = ""// 查询条件 -- 结束时间
    private var totalalarmNum = 0// 报警总数
    private var resultNum = 0// 处理数
    private var noresultNum = 0// 未处理数
    private var chaosuNum = 0// 超速报警数
    private var pilaoNum = 0// 疲劳驾驶报警数
    private var yuexianNum = 0// 越线驾驶报警
    private var yejianchaosuNum = 0// 夜间超速报警数
    private var yingjiNum = 0//应急报警数
    private var xingshiyichangNum = 0//行驶异常报警数
    private var mContentList = ArrayList<TonJiAlarmInfo>()
    private var nContentList = ArrayList<TonJiAlarmTypeInfo>()
    private var mLineDataList = ArrayList<LineData>()
    override fun onViewCreated(view: View) {
        val calendar = Calendar.getInstance()
        val sTime = calendar.time.time
        calendar.add(Calendar.DATE, 1)
        val eTime = calendar.time.time
        mStartTime = DateUtils.formatDataToyyyMMdd(sTime, 6)
        statistics_txt_time.text = mStartTime
        mEndTime = DateUtils.formatDataToyyyMMdd(eTime, 6)
        GetTonJiAlarmInfoCount()
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
                    GetTonJiAlarmInfoCount()

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

    fun GetTonJiAlarmInfoCount() {
        api<MovecarPresenter> {
            GetTonJiAlarmInfoCount(BaseApplication.loginInfo.userid.toString(), mStartTime, mEndTime, {
                this.apply {
                    val count = this.count
                    if (count > 0) {
                        GetTonJiAlarmInfo(count)
                    }
                }
            })
        }
    }

    fun GetTonJiAlarmInfo(count: Int) {
        api<MovecarPresenter> {
            GetTonJiAlarmInfo(BaseApplication.loginInfo.userid.toString(), count, mStartTime, mEndTime, {
                this.apply {
                    mContentList.clear()
                    mContentList.addAll(this)
                    GetTonJiAlarmTypeInfoCount()
                }
            })
        }
    }
    fun GetTonJiAlarmTypeInfoCount() {
        api<MovecarPresenter> {
            GetTonJiAlarmTypeInfoCount(BaseApplication.loginInfo.userid.toString(), mStartTime, mEndTime, {
                this.apply {
                    val count = this.count
                    if (count > 0) {
                        GetTonJiAlarmTypeInfo(count)
                    }
                }
            })
        }
    }
    fun GetTonJiAlarmTypeInfo(count: Int) {
        api<MovecarPresenter> {
            GetTonJiAlarmTypeInfo(BaseApplication.loginInfo.userid.toString(), count, mStartTime, mEndTime, {
                this.apply {
                    nContentList.clear()
                    nContentList.addAll(this)
                    InitDatas()

                }
            })
        }
    }

    fun InitDatas() {
        for (i in mContentList.indices){
            val mTonJiAlarmInfo = mContentList.get(i)
            totalalarmNum += mTonJiAlarmInfo.alarm
            resultNum += mTonJiAlarmInfo.alarmResult
        }
        noresultNum = totalalarmNum -resultNum
        for (i in nContentList.indices){
            val nTonJiAlarmTypeInfo = nContentList.get(i)
            when(nTonJiAlarmTypeInfo.note){
                "超速报警"->chaosuNum+=nTonJiAlarmTypeInfo.num
                "疲劳驾驶报警"->pilaoNum+=nTonJiAlarmTypeInfo.num
                "越线驾驶报警"->yuexianNum+=nTonJiAlarmTypeInfo.num
                "夜间超速报警"->yejianchaosuNum+=nTonJiAlarmTypeInfo.num
                "应急报警"->yingjiNum+=nTonJiAlarmTypeInfo.num
                "行驶异常报警"->xingshiyichangNum+=nTonJiAlarmTypeInfo.num
            }
        }
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
        xValues.add("总数")
        xValues.add("未处理数")
        xValues.add("超速")
        xValues.add("疲劳驾驶")
        xValues.add("越线驾驶")
        xValues.add("夜间超速")
        xValues.add("应急报警")
        xValues.add("行驶异常")
        val yValues = ArrayList<Float>() // yVals用来表示封装每个饼块的实际数据
        // 饼图数据
        /**
         * 将一个饼形图分成四部分， 四部分的数值比例为14:14:34:38 所以 14代表的百分比就是14%
         */
        yValues.add(totalalarmNum.toFloat())
        yValues.add(noresultNum.toFloat())
        yValues.add(chaosuNum.toFloat())
        yValues.add(pilaoNum.toFloat())
        yValues.add(yuexianNum.toFloat())
        yValues.add(yejianchaosuNum.toFloat())
        yValues.add(yingjiNum.toFloat())
        yValues.add(xingshiyichangNum.toFloat())
        val barChartManager1 = BarChartManager(mBarChart)
        barChartManager1.showBarChartString(xValues, yValues, "车辆统计报表")
        barChartManager1.setDescription("")
        mBarChart.visibility = View.VISIBLE
        InitLineDatas()
    }

    private fun InitLineDatas() {
        mLineDataList.clear()
        mLineDataList.add(LineData("报警总数", totalalarmNum.toString()))
        mLineDataList.add(LineData("未处警条数", noresultNum.toString()))
        if (resultNum > 0) {
            val b1 = BigDecimal(resultNum * 100)
            val b2 = BigDecimal(totalalarmNum)
            val ret = b1.divide(b2, 2, BigDecimal.ROUND_HALF_UP)
                    .toDouble()
            mLineDataList.add(LineData("当前处警率", ret.toString() + "%"))
        } else {
            mLineDataList.add(LineData("当前处警率", "0.0%"))
        }
        mLineDataList.add(LineData("超速报警", chaosuNum.toString()))
        mLineDataList.add(LineData("疲劳驾驶", pilaoNum.toString()))
        mLineDataList.add(LineData("越线驾驶", yuexianNum.toString()))
        mLineDataList.add(LineData("夜间超速", yejianchaosuNum.toString()))
        mLineDataList.add(LineData("应急报警", yingjiNum.toString()))
        mLineDataList.add(LineData("行驶异常", yuexianNum.toString()))
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