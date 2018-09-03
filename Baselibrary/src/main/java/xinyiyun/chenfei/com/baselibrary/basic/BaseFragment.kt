package xinyiyun.chenfei.com.baselibrary.basic

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.luck.picture.lib.PictureSelector
import com.luck.picture.lib.compress.Luban
import com.luck.picture.lib.config.PictureConfig
import com.luck.picture.lib.config.PictureMimeType
import com.luck.picture.lib.entity.LocalMedia
import com.trello.rxlifecycle2.components.support.RxFragment
import me.jessyan.autosize.AutoSizeConfig
import me.jessyan.autosize.internal.CustomAdapt
import okhttp3.MediaType
import okhttp3.RequestBody
import xinyiyun.chenfei.com.baselibrary.R
import xinyiyun.chenfei.com.baselibrary.annotation.ContentView
import xinyiyun.chenfei.com.baselibrary.common.ViewWrap
import xinyiyun.chenfei.com.baselibrary.common.createViewByAnnocations
import xinyiyun.chenfei.com.baselibrary.utils.FullyGridLayoutManager
import xinyiyun.chenfei.com.baselibrary.utils.GridImageAdapter
import java.io.File
import java.util.ArrayList
import java.util.concurrent.atomic.AtomicInteger

/**
 * Created by chenfei on 2018/5/10.
 */
abstract class BaseFragment : RxFragment(), CustomAdapt {
    lateinit var viewWrap: ViewWrap
    private var isFragmentVisible: Boolean = false
    private var isPrepared: Boolean = false
    private var isFirstLoad = true
    private var isLazyLoad: Boolean = false
    var selectList: List<LocalMedia> = ArrayList()
    var pageIndex = AtomicInteger(1)
    val manager = FullyGridLayoutManager(activity, 3, GridLayoutManager.VERTICAL, false)
    lateinit var adapter: GridImageAdapter
    private val onAddPicClickListener = GridImageAdapter.onAddPicClickListener {
        PictureSelector.create(activity)
                .openGallery(PictureMimeType.ofImage())
                .theme(R.style.picture_white_style)
                .maxSelectNum(3)// 最大图片选择数量
                .minSelectNum(1)// 最小选择数量
                .selectionMode(PictureConfig.MULTIPLE)// 多选 or 单选
                .previewImage(true)// 是否可预览图片
                .cropCompressQuality(90)// 裁剪压缩质量 默认90 int
                .minimumCompressSize(100)// 小于100kb的图片不压缩
                .synOrAsy(true)//同步true或异步false 压缩 默认同步
                .isCamera(true)// 是否显示拍照按钮
                .compress(true)// 是否压缩
                .glideOverride(100, 100)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
                .selectionMedia(selectList)// 是否传入已选图片
                .forResult(PictureConfig.CHOOSE_REQUEST)//结果回调onActivityResult code
    }
    abstract fun onViewCreated(view: View)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        isFirstLoad = true
        viewWrap = createViewByAnnocations()
        isPrepared = true
        return viewWrap.rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        AutoSizeConfig.getInstance().isCustomFragment = true
        javaClass.getAnnotation(ContentView::class.java)?.apply {
            if (lazyload) {
                isLazyLoad = true
                lazyLoad()
            } else {
                onViewCreated(viewWrap.rootView)
            }
        }
    }


    fun ui(view: ViewGroup.() -> Unit) {
        view(viewWrap.contentView)
    }

    protected fun lazyLoad() {
        if (isPrepared && isFragmentVisible) {
            if (isFirstLoad) {
                isFirstLoad = false
                if (isLazyLoad) {
                    onViewCreated(viewWrap.rootView)
                }
            }
        }
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)

        if (!hidden) {
            isFragmentVisible = true
            lazyLoad()
        } else {
            isFragmentVisible = false
        }
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (userVisibleHint) {
            isFragmentVisible = true
            lazyLoad()
        } else {
            isFragmentVisible = false
        }
    }
    fun getListImage(list: List<LocalMedia>): Map<String, RequestBody> {
        var mList=HashMap<String, RequestBody>();
        for(i in 0 until list.size) {
            if (!TextUtils.isEmpty(list.get(i).compressPath)) {
                var file = File(list.get(i).compressPath)
                mList.put( "pic" +(i+1) + "\"; filename=\"" + file.getName() + "", RequestBody.create(MediaType.parse("multipart/form-data"),file))
            }
        }
        return mList
    }

    override fun onDestroyView() {
        isPrepared = false
        super.onDestroyView()
    }

    fun CreatPictureSelecter(recycler:RecyclerView){

        adapter = GridImageAdapter(activity, onAddPicClickListener)
        recycler.layoutManager = manager
        adapter.setList(selectList)
        adapter.setSelectMax(3)
        recycler.adapter = adapter
        adapter.setOnItemClickListener { position, v ->
            if (selectList.size > 0) {
                val media = selectList[position]
                val pictureType = media.pictureType
                val mediaType = PictureMimeType.pictureToVideo(pictureType)
                when (mediaType) {
                    1 ->
                        PictureSelector.create(activity).externalPicturePreview(position, selectList)
                }
            }
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            PictureConfig.CHOOSE_REQUEST-> data?.apply {
                // 图片选择
                selectList = PictureSelector.obtainMultipleResult(data)
                // 例如 LocalMedia 里面返回两种path
                // 1.media.getPath(); 为原图path
                // 2.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true
                adapter.setList(selectList)
                adapter.notifyDataSetChanged()
            }
        }
    }

    override fun isBaseOnWidth(): Boolean {
        return false
    }

    override fun getSizeInDp(): Float {
        return 700f
    }
}