package xinyiyun.chenfei.com.movecar.ui.activity

import com.baidu.location.Address
import com.baidu.location.BDLocation
import com.baidu.mapapi.model.LatLng
import com.baidu.mapapi.search.core.SearchResult
import com.baidu.mapapi.search.geocode.*
import kotlinx.android.synthetic.main.activity_vehicle_data.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.startActivity
import xinyiyun.chenfei.com.baselibrary.annotation.ContentView
import xinyiyun.chenfei.com.baselibrary.annotation.StateView
import xinyiyun.chenfei.com.baselibrary.annotation.ToolbarView
import xinyiyun.chenfei.com.baselibrary.basic.BaseActivity
import xinyiyun.chenfei.com.baselibrary.basic.BaseApplication
import xinyiyun.chenfei.com.movecar.R
import xinyiyun.chenfei.com.movecar.common.api
import xinyiyun.chenfei.com.movecar.presenter.MovecarPresenter

/**
 * Created by Administrator on 2018/3/8.
 */

@ContentView(R.layout.activity_vehicle_data)
@ToolbarView(title = "车辆详情", showBack = true)
@StateView(R.id.ll_vehicledetail)
class VehicleDetailActivity : BaseActivity(), OnGetGeoCoderResultListener {
    override fun onGetGeoCodeResult(p0: GeoCodeResult?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    override fun onGetReverseGeoCodeResult(result: ReverseGeoCodeResult?) {
        if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {;
            return;
        }
        location = BDLocation()
        var address= Address.Builder().country("").city("").street(result.address).build()
        location.setAddr(address);
        location.setAddrStr(result.address)
        location.setLatitude(result.location.latitude)
        location.setLongitude(result.location.longitude)
        tv_address.text = result.address
        tv_address.onClick {
            startActivity<CurrentLocationActivity>("location" to location)
        }
    }

    lateinit var location: BDLocation
    lateinit var mSearch: GeoCoder
    override fun onViewCreated() {
        val cph = intent.getStringExtra("cph")
        val vehId = intent.getStringExtra("vehId")
        mSearch = GeoCoder.newInstance()
        mSearch.setOnGetGeoCodeResultListener(this)
        api<MovecarPresenter> {
            GetTpVeDate(BaseApplication.loginInfo.userid.toString(), cph, 1, {
                this.apply {
                    vehicl_data_txt_title.text = cph
                    companyteam.text = khqc + teamNo
                    devicenum.text = deviceid
                    simnum.text = simNo
                    drivername.text = dname
                    driverphone.text = dphone
                    carcolor.text = platecolor
                    locationstatus.text = if (locate) {
                        "成功"
                    } else {
                        "失败"
                    }
                    carsudu.text = velocity.toString()
                    direction.text = GetDerectionstr(angle)
                    longlat.text = longitude.toString() + "," + latitude.toString()
                    tv_signal.text = signal.toString()
                    wxs.text = satelliteLen.toString()
                    updatetime.text = time

                    mSearch.reverseGeoCode(ReverseGeoCodeOption().location(LatLng(latitude,longitude)))
                }
            })
        }
    }

    private fun GetDerectionstr(angle: Int): String {
        var derectionstr = ""
        if (angle >= 0) {
            if (angle == 0) {
                derectionstr = "正东"
            } else if (angle < 90) {
                derectionstr = "东北"
            } else if (angle == 90) {
                derectionstr = "正北"
            } else if (angle < 180) {
                derectionstr = "西北"
            } else if (angle == 180) {
                derectionstr = "正西"
            } else if (angle < 270) {
                derectionstr = "西南"
            } else if (angle == 270) {
                derectionstr = "正南"
            } else if (angle < 360) {
                derectionstr = "东南"
            } else if (angle == 360) {
                derectionstr = "正东"
            } else {
                derectionstr = "获取方向失败"
            }
        } else {
            derectionstr = "获取方向失败"
        }
        return derectionstr
    }
}