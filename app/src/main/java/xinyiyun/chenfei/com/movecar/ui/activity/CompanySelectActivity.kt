package xinyiyun.chenfei.com.movecar.ui.activity

/**
 * Created by Administrator on 2018/3/6.
 */


import android.text.Html
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import kotlinx.android.synthetic.main.activity_company_list.*
import org.jetbrains.anko.startActivity
import xinyiyun.chenfei.com.baselibrary.annotation.ContentView
import xinyiyun.chenfei.com.baselibrary.annotation.ToolbarView
import xinyiyun.chenfei.com.baselibrary.basic.BaseActivity
import xinyiyun.chenfei.com.baselibrary.common.vertical
import xinyiyun.chenfei.com.baselibrary.utils.SPreferencesUtils
import xinyiyun.chenfei.com.movecar.R
import xinyiyun.chenfei.com.movecar.base.MyApplication
import xinyiyun.chenfei.com.movecar.category.ClientInfo
import xinyiyun.chenfei.com.movecar.gen.VehicleInfoDao
import java.util.*

@ContentView(R.layout.activity_company_list)
@ToolbarView(title = "公司列表", showBack = true)
class CompanySelectActivity : BaseActivity() {

    private var mContentList = ArrayList<ClientInfo>()

    override fun onViewCreated() {
        InitDatas()
        rcl_companys.apply {
            vertical()
            adapter = MenuAdapter(mContentList).apply {
                setOnItemClickListener { _, _, position ->
                    startActivity<TeamSelectActivity>("companyid" to mContentList.get(position).companyid)
                }
            }
        }
    }

    private fun InitDatas() {
        var clientdao = MyApplication.mDaoSession.clientInfoDao
        var vehicledao = MyApplication.mDaoSession.vehicleInfoDao
        val format = getString(R.string.vehicle_info_text)
        val clientlist = clientdao.queryBuilder().list()
        val totol = SPreferencesUtils.getInt(this@CompanySelectActivity,"vehicletotal",0)
        val select = vehicledao.queryBuilder().where( VehicleInfoDao.Properties.IsSelected.eq(1)).count()
        val t = "<font color='#262f3c'>$totol</font>"
        val c = "<font color='#ff8e08'>$select</font>"
        myvehicle_txt_status.text = Html.fromHtml(String.format(format, t, c))
        if (clientlist.size > 0) {
            for (clientInfo in clientlist) {
                val vehNum = vehicledao.queryBuilder().where(VehicleInfoDao.Properties.Client.eq(clientInfo.companyid)).count()
                val selectNum = vehicledao.queryBuilder().where(VehicleInfoDao.Properties.Client.eq(clientInfo.companyid), VehicleInfoDao.Properties.IsSelected.eq(1)).count()
                if (selectNum > 0) {
                    clientInfo.state = 1
                } else {
                    clientInfo.state = 0
                }
                clientInfo.total = vehNum.toInt()
                clientInfo.des = "根据公司进行查询"
                mContentList.add(clientInfo)
            }
        }
    }
    inner class MenuAdapter(data: List<ClientInfo>?) : BaseQuickAdapter<ClientInfo, BaseViewHolder>(R.layout.item_main_client, data) {

        override fun convert(helper: BaseViewHolder, item: ClientInfo) {
            if (item.state ==0) {
                helper.setImageResource(R.id.item_main_client_ckb_choice, R.mipmap.btn_check_off_company)
            }
            else{
                helper.setImageResource(R.id.item_main_client_ckb_choice, R.mipmap.list_check_on)
            }
            helper.setText(R.id.item_main_client_txt_subtitle, item.des)
            val sb = StringBuilder()
            sb.append("&nbsp;&nbsp;<font color=\"#54BAC8\"><small>")
            sb.append("(").append(item.getTotal()).append(")")
            sb.append("</small></font>")
            helper.setText(R.id.item_main_client_txt_title, Html.fromHtml(item.khqc+sb.toString()))
        }
    }
}

