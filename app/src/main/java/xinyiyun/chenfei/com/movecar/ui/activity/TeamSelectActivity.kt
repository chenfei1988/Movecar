package xinyiyun.chenfei.com.movecar.ui.activity

import android.text.Html
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import kotlinx.android.synthetic.main.activity_team_list.*
import org.jetbrains.anko.startActivity
import xinyiyun.chenfei.com.baselibrary.annotation.ContentView
import xinyiyun.chenfei.com.baselibrary.annotation.ToolbarView
import xinyiyun.chenfei.com.baselibrary.basic.BaseActivity
import xinyiyun.chenfei.com.baselibrary.common.vertical
import xinyiyun.chenfei.com.movecar.R
import xinyiyun.chenfei.com.movecar.base.MyApplication
import xinyiyun.chenfei.com.movecar.category.TeamInfo
import xinyiyun.chenfei.com.movecar.gen.TeamInfoDao
import xinyiyun.chenfei.com.movecar.gen.VehicleInfoDao
import java.util.*

@ContentView(R.layout.activity_team_list)
@ToolbarView(title = "车队列表", showBack = true)
class TeamSelectActivity : BaseActivity() {

    private var mContentList = ArrayList<TeamInfo>()
    private var companyid = 0;
    override fun onViewCreated() {
        InitDatas()
        rcl_companys.apply {
            vertical()
            adapter = MenuAdapter(mContentList).apply {
                setOnItemClickListener { _, _, position ->
                    startActivity<VehicleSelectActivity>("teamno" to mContentList.get(position).teamNo,"companyid" to companyid)
                }
            }
        }
    }

    private fun InitDatas() {
         companyid =intent.getIntExtra("companyid",0)
        if (companyid>0) {
            var teamdao = MyApplication.mDaoSession.teamInfoDao
            var vehicledao = MyApplication.mDaoSession.vehicleInfoDao
            val teamlist = teamdao.queryBuilder().where(TeamInfoDao.Properties.Companyid.eq(companyid)).list()
            val select = vehicledao.queryBuilder().where(VehicleInfoDao.Properties.Client.eq(companyid), VehicleInfoDao.Properties.IsSelected.eq(1)).count()
            car_choicenum.text = select.toString()
            if (teamlist.size > 0) {
                for (TeamInfo in teamlist) {
                    val vehNum = vehicledao.queryBuilder().where(VehicleInfoDao.Properties.Client.eq(companyid), VehicleInfoDao.Properties.TeamNo.eq(TeamInfo.teamNo)).count()
                    val selectNum = vehicledao.queryBuilder().where(VehicleInfoDao.Properties.Client.eq(companyid), VehicleInfoDao.Properties.TeamNo.eq(TeamInfo.teamNo), VehicleInfoDao.Properties.IsSelected.eq(1)).count()
                    if (selectNum > 0) {
                        TeamInfo.state = 1
                    } else {
                        TeamInfo.state = 0
                    }
                    TeamInfo.total = vehNum.toInt()
                    TeamInfo.des = "根据车队进行查询"
                    mContentList.add(TeamInfo)
                }
            }
        }
    }
    inner class MenuAdapter(data: List<TeamInfo>?) : BaseQuickAdapter<TeamInfo, BaseViewHolder>(R.layout.item_main_client, data) {

        override fun convert(helper: BaseViewHolder, item: TeamInfo) {
              if (item.state ==0) {
                helper.setImageResource(R.id.item_main_client_ckb_choice, R.mipmap.btn_check_off_car_team)
            }
            else{
                helper.setImageResource(R.id.item_main_client_ckb_choice, R.mipmap.list_check_on)
            }
            helper.setText(R.id.item_main_client_txt_subtitle, item.des)
            val sb = StringBuilder()
            sb.append("&nbsp;&nbsp;<font color=\"#54BAC8\"><small>")
            sb.append("(").append(item.getTotal()).append(")")
            sb.append("</small></font>")
            helper.setText(R.id.item_main_client_txt_title, Html.fromHtml(item.teamNo + sb.toString()))
        }
    }
}