package xinyiyun.chenfei.com.movecar.ui.activity

import android.content.Context
import com.baidu.location.BDLocation
import com.baidu.mapapi.map.BitmapDescriptorFactory
import com.baidu.mapapi.map.MapStatus
import com.baidu.mapapi.map.MapStatusUpdateFactory
import com.baidu.mapapi.map.MarkerOptions
import com.baidu.mapapi.model.LatLng
import kotlinx.android.synthetic.main.activity_current_location.*
import org.jetbrains.anko.startActivity
import xinyiyun.chenfei.com.baselibrary.annotation.ContentView
import xinyiyun.chenfei.com.baselibrary.annotation.ToolbarView
import xinyiyun.chenfei.com.baselibrary.basic.BaseActivity
import xinyiyun.chenfei.com.movecar.R

@ContentView(R.layout.activity_current_location)
@ToolbarView("当前位置", true)
class CurrentLocationActivity : BaseActivity() {
    companion object {
        fun start(context: Context, loction: BDLocation) {
            context.startActivity<CurrentLocationActivity>("location" to loction)
        }
    }

    override fun onViewCreated() {

        map.removeViewAt(1)
        val mBaiduMap = map.map
        mBaiduMap.setMapStatus(MapStatusUpdateFactory.zoomTo(16.0f))
        intent.getParcelableExtra<BDLocation>("location")?.apply {
            tx_address.text = "详细地址:${addrStr}"
            val builder = MapStatus.Builder()
            val _latLng = LatLng(latitude, longitude)
            builder.target(_latLng).zoom(18.0f)
            mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()))
            mBaiduMap.addOverlay(MarkerOptions().position(_latLng)
                    .icon(BitmapDescriptorFactory
                            .fromResource(R.mipmap.dingwei)))
            mBaiduMap.setMapStatus(MapStatusUpdateFactory.newLatLng(_latLng))
        }

    }

    override fun onResume() {
        super.onResume()
        map.onResume()
    }

    override fun onPause() {
        super.onPause()
        map.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        map.onDestroy()
    }
}
