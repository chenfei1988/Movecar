package xinyiyun.chenfei.com.baselibrary.basic

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import com.luck.picture.lib.entity.LocalMedia
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import me.jessyan.autosize.internal.CustomAdapt
import okhttp3.MediaType
import okhttp3.RequestBody
import org.jetbrains.anko.toast
import xinyiyun.chenfei.com.baselibrary.R
import xinyiyun.chenfei.com.baselibrary.common.AppManager
import xinyiyun.chenfei.com.baselibrary.common.ViewWrap
import xinyiyun.chenfei.com.baselibrary.common.createViewByAnnocations
import xinyiyun.chenfei.com.baselibrary.utils.AndroidWorkaround
import xinyiyun.chenfei.com.baselibrary.utils.StatusbarUtils
import java.io.File
import java.util.concurrent.atomic.AtomicInteger


/**
 * Created by chenfei on 2018/5/10.
 */


abstract class BaseActivity : RxAppCompatActivity(), CustomAdapt {
    private var lastTime = 0L
    lateinit var viewWrap: ViewWrap

    var pageIndex = AtomicInteger(1)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppManager.instance.addActivity(this)
     //   ViewUtils.translucentStatus(this)
        viewWrap = createViewByAnnocations().apply {
            setContentView(rootView)
        }

        if (AndroidWorkaround.checkDeviceHasNavigationBar(this)) {
            AndroidWorkaround.assistActivity(findViewById(android.R.id.content));
        }
        StatusbarUtils.from(this)
                .setTransparentStatusbar(true)
                .setLightStatusBar(true)
                .process()
        //StatusBarCompat.setStatusBarColor(this, resources.getColor(R.color.white))
        onViewCreated()
    }

    override fun onBackPressed() {
        var time = System.currentTimeMillis()
        if (isTaskRoot && (time - lastTime) / 1000 > 2) {
            toast(resources.getString(R.string.click_agin_exit))
            lastTime = time
        } else {
            super.onBackPressed()
        }
    }

    abstract fun onViewCreated()
    fun ui(contentView: ViewGroup.() -> Unit) = contentView(viewWrap.contentView)




    fun getListImage(list: List<LocalMedia>): Map<String,RequestBody> {
        var mList=HashMap<String,RequestBody>()
        for(i in 0..list.size-1) {
            if (!TextUtils.isEmpty(list.get(i).compressPath)) {
                var file = File(list.get(i).compressPath)
                mList.put( "files" + "\"; filename=\"" + file.getName() + "", RequestBody.create(MediaType.parse("multipart/form-data"),file))
            }
        }
        return mList
    }
    fun SetTitle(title:String){
        findViewById<TextView>(R.id.common_toolbar_title_view).text=title
    }

    override fun onDestroy() {
        super.onDestroy()
        AppManager.instance.finishActivity(this)
    }
    /**
     * 关闭软键盘
     */
    fun closeKeyBord(mEditText: EditText, mContext: Context) {
        val imm = mContext.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(mEditText.windowToken, 0)
    }

    override fun isBaseOnWidth(): Boolean {
        return false
    }

    override fun getSizeInDp(): Float {
        return 700f
    }
}





