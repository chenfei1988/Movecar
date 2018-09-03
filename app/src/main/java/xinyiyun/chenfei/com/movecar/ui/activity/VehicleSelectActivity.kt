package xinyiyun.chenfei.com.movecar.ui.activity

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import ittiger.indexlist.adapter.IndexStickyViewAdapter
import ittiger.indexlist.listener.OnItemClickListener
import kotlinx.android.synthetic.main.activity_vehicle_list.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.sdk25.coroutines.textChangedListener
import xinyiyun.chenfei.com.baselibrary.annotation.ContentView
import xinyiyun.chenfei.com.baselibrary.annotation.ToolbarView
import xinyiyun.chenfei.com.baselibrary.basic.BaseActivity
import xinyiyun.chenfei.com.movecar.R
import org.jetbrains.anko.startActivity
import xinyiyun.chenfei.com.movecar.base.MyApplication
import xinyiyun.chenfei.com.movecar.category.VehicleInfo
import xinyiyun.chenfei.com.movecar.gen.VehicleInfoDao
import java.util.*

@ContentView(R.layout.activity_vehicle_list)
@ToolbarView(title = "车辆列表", showBack = true)
class VehicleSelectActivity : BaseActivity() , OnItemClickListener<VehicleInfo> {

    private var mContentList = ArrayList<VehicleInfo>()
    private var initdatas = ArrayList<VehicleInfo>()
    private var companyid = 0;
    private var teamno = "";
    private lateinit var mContext: Context
    internal lateinit var mAdapter: MyIndexStickyViewAdapter
    override fun onViewCreated() {
        InitDatas()
        search.textChangedListener {
            afterTextChanged {
                val searchtext = search.text.toString()
                if (!searchtext.isNullOrEmpty()) {
                    mAdapter.clear()
                    mAdapter.add(Filter(searchtext))
                } else {
                    mAdapter.clear()
                    mAdapter.add(mContentList)
                }
            }
        }
    }

    private fun InitDatas() {
        mContext = this
        companyid = intent.getIntExtra("companyid", 0)
        teamno = intent.getStringExtra("teamno")
        var vehicledao = MyApplication.mDaoSession.vehicleInfoDao
        val select = vehicledao.queryBuilder().where(VehicleInfoDao.Properties.Client.eq(companyid), VehicleInfoDao.Properties.TeamNo.eq(teamno), VehicleInfoDao.Properties.IsSelected.eq(1)).count()
        choice_count.text = select.toString()
        mContentList.addAll(vehicledao.queryBuilder().where(VehicleInfoDao.Properties.Client.eq(companyid), VehicleInfoDao.Properties.TeamNo.eq(teamno)).build().list())
        initdatas.addAll(mContentList)
        mAdapter = MyIndexStickyViewAdapter(initdatas)
        indexStickyView.setAdapter(mAdapter)
        mAdapter.setOnItemClickListener(this)

    }

    internal inner class MyIndexStickyViewAdapter(list: List<VehicleInfo>) : IndexStickyViewAdapter<VehicleInfo>(list) {

        override fun onCreateIndexViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {

            val view = LayoutInflater.from(mContext).inflate(R.layout.indexsticky_item_index, parent, false)
            return IndexViewHolder(view)
        }

        override fun onCreateContentViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {

            val view = LayoutInflater.from(mContext).inflate(R.layout.indexsticky_item_contact, parent, false)
            return ContentViewHolder(view)
        }

        override fun onBindIndexViewHolder(holder: RecyclerView.ViewHolder, position: Int, indexName: String) {

            val indexViewHolder = holder as IndexViewHolder
            indexViewHolder.mTextView.text = indexName
        }

        override fun onBindContentViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int, entity: VehicleInfo) {

            val holder = viewHolder as ContentViewHolder
            // 设置选择框
            if (entity.isSelected != null && entity.isSelected !== -1) {
                when (entity.isSelected) {
                    1//选中
                    -> holder.mImgCheck.setImageResource(R.mipmap.btn_check_on)
                    0//不选中
                    -> holder.mImgCheck.setImageResource(R.mipmap.btn_check_off)
                    else// 部分选中
                    -> holder.mImgCheck.setImageResource(R.mipmap.btn_check_off)
                }
            } else {
                holder.mImgCheck.setImageResource(R.mipmap.btn_check_off)
            }

            if (entity.cph != null)
                holder.mTxtVehicle.text = entity.cph.trim()
            else
                holder.mTxtVehicle.text = ""

            holder.mImgCheck.onClick {
                if(entity.isSelected==1){
                    entity.isSelected = 0
                    holder.mImgCheck.setImageResource(R.mipmap.btn_check_off)
                }
                else{
                    entity.isSelected = 1
                    holder.mImgCheck.setImageResource(R.mipmap.btn_check_on)
                }
                var vehicledao = MyApplication.mDaoSession.vehicleInfoDao
                vehicledao.update(entity)
            }
        }
    }

    internal inner class IndexViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var mTextView: TextView

        init {
            mTextView = itemView.findViewById(R.id.tv_index)
        }
    }


    internal inner class ContentViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var mImgCheck: ImageView
        var mTxtVehicle: TextView

        init {
            mImgCheck = view.findViewById(R.id.item_vehicle_img_choice)
            mTxtVehicle = view.findViewById(R.id.choice_car_item_txt_name)

        }
    }

    private fun Filter(cph: String): List<VehicleInfo> {
        var newapplydatalist = listOf<VehicleInfo>()
        newapplydatalist = mContentList;
        newapplydatalist = newapplydatalist.filter {
            it.cph.contains(cph);
        }
        return newapplydatalist
    }

    override fun onItemClick(childView: View, position: Int, entity: VehicleInfo) {
           if (entity != null) {
               startActivity<VehicleDetailActivity>("cph" to entity.cph,"vehId" to entity.vehId)
           }
    }
}