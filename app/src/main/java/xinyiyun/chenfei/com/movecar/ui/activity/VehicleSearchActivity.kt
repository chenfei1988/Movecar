package xinyiyun.chenfei.com.movecar.ui.activity

/**
 * Created by Administrator on 2018/3/6.
 */


import android.content.Intent
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import kotlinx.android.synthetic.main.activity_mohusearch.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.sdk25.coroutines.textChangedListener
import org.jetbrains.anko.startActivity
import xinyiyun.chenfei.com.baselibrary.annotation.ContentView
import xinyiyun.chenfei.com.baselibrary.annotation.ToolbarView
import xinyiyun.chenfei.com.baselibrary.basic.BaseActivity
import xinyiyun.chenfei.com.baselibrary.common.multiStateView
import xinyiyun.chenfei.com.baselibrary.common.setNewData
import xinyiyun.chenfei.com.baselibrary.common.vertical
import xinyiyun.chenfei.com.movecar.R
import xinyiyun.chenfei.com.movecar.base.MyApplication
import xinyiyun.chenfei.com.movecar.category.VehicleInfo
import xinyiyun.chenfei.com.movecar.gen.VehicleInfoDao

@ContentView(R.layout.activity_mohusearch)
@ToolbarView(title = "模糊查询车辆", showBack = true)

class VehicleSearchActivity : BaseActivity() {

    val queryvehiclelist = arrayListOf<VehicleInfo>()
    val historyvehiclelist = arrayListOf<VehicleInfo>()
    override fun onViewCreated() {
        var vehicledao = MyApplication.mDaoSession.vehicleInfoDao
         et_search_view.textChangedListener {
            afterTextChanged {
                et_search_view.text.toString().apply {
                    queryvehiclelist.clear()
                    if (isEmpty()) {
                        searchrv.adapter.notifyDataSetChanged()
                    } else {
                        queryvehiclelist.addAll(vehicledao.queryBuilder().where(VehicleInfoDao.Properties.Cph.like("")).build().list())
                        searchrv.adapter.notifyDataSetChanged()
                    }
                }
            }
        }
        historyvehiclelist.addAll(vehicledao.queryBuilder().where(VehicleInfoDao.Properties.IsSelected.eq(1)).build().list())
        historyrv.apply {
            vertical()
            adapter = RouteAdapter(historyvehiclelist).apply {
                setOnItemClickListener { adapter, view, position ->
                    startActivity<VehicleDetailActivity>("cph" to historyvehiclelist[position].cph)
                }
            }
        }
        searchrv.apply {
            vertical()
            adapter = RouteAdapter(queryvehiclelist).apply {
                setOnItemClickListener { adapter, view, position ->
                    var vehicle = queryvehiclelist[position]
                    vehicle.isSelected = 1
                    vehicledao.updateInTx(vehicle)
                    startActivity<VehicleDetailActivity>("cph" to vehicle.cph)
                }
            }
        }
        clear.onClick {
            historyvehiclelist.forEach {
                it.isSelected = 0
            }
            vehicledao.updateInTx(historyvehiclelist)
        }

    }

    inner class RouteAdapter(data: List<VehicleInfo>) :
            BaseQuickAdapter<VehicleInfo, BaseViewHolder>(R.layout.view_simple_list_item, data) {
        override fun convert(helper: BaseViewHolder, item: VehicleInfo) {
            helper.setText(R.id.tx_title, item.cph.trim())
        }
    }
}

