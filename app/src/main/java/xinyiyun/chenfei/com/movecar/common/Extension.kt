package xinyiyun.chenfei.com.movecar.common

import android.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.github.mikephil.charting.utils.ColorTemplate.rgb
import com.jzxiang.pickerview.TimePickerDialog
import com.jzxiang.pickerview.data.Type
import com.trello.rxlifecycle2.LifecycleProvider
import xinyiyun.chenfei.com.baselibrary.R
import xinyiyun.chenfei.com.baselibrary.basic.BaseFragment
import xinyiyun.chenfei.com.movecar.base.BasePresenter
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by YangQiang on 2017/8/31.
 */
val MATERIAL_COLORS = intArrayOf(rgb("#2ecc71"), rgb("#f1c40f"), rgb("#e74c3c"), rgb("#3498db"))

inline fun <reified T : BasePresenter> LifecycleProvider<*>.api(block: T.() -> Unit) {
    block(T::class.java.getConstructor(LifecycleProvider::class.java).newInstance(this))
}
inline fun BaseFragment.timePickerYMD(init: String = "", format: String = "yyyy-MM-dd", crossinline block: (TimePickerDialog, String) -> Unit) {
    TimePickerDialog.Builder().setCallBack { v1, v2 ->
        block.invoke(v1, SimpleDateFormat(format).format(Date(v2)))
    }.setTitleStringId("请选择时间")
            .setCyclic(true)
            .setMaxMillseconds(System.currentTimeMillis())
            .setCurrentMillseconds(if (init.isEmpty()) System.currentTimeMillis() else SimpleDateFormat(format).parse(init).time)
            .setThemeColor(resources.getColor(R.color.timepicker_dialog_bg))
            .setType(Type.YEAR_MONTH_DAY)
            .setWheelItemTextNormalColor(resources.getColor(R.color.timetimepicker_default_text_color))
            .setWheelItemTextSelectorColor(resources.getColor(R.color.orange))
            .setWheelItemTextSize(13)
            .build().show(fragmentManager, "")
}
