package xinyiyun.chenfei.com.baselibrary.common

import android.accounts.NetworkErrorException
import android.content.Context
import android.graphics.Color
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.*
import android.util.TypedValue
import android.view.*
import android.widget.LinearLayout
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.elvishew.xlog.XLog
import com.google.gson.JsonSyntaxException
import com.jzxiang.pickerview.TimePickerDialog
import com.jzxiang.pickerview.data.Type
import com.scwang.smartrefresh.header.MaterialHeader
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.footer.ClassicsFooter
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener
import com.scwang.smartrefresh.layout.listener.OnRefreshListener
import com.trello.rxlifecycle2.LifecycleProvider
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.PublishSubject
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.jetbrains.anko.*
import org.jetbrains.anko.appcompat.v7.themedToolbar
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.findOptional
import xinyiyun.chenfei.com.baselibrary.R
import xinyiyun.chenfei.com.baselibrary.annotation.ContentView
import xinyiyun.chenfei.com.baselibrary.annotation.RefreshView
import xinyiyun.chenfei.com.baselibrary.annotation.StateView
import xinyiyun.chenfei.com.baselibrary.annotation.ToolbarView
import xinyiyun.chenfei.com.baselibrary.basic.*
import xinyiyun.chenfei.com.baselibrary.model.LoadMoreData
import xinyiyun.chenfei.com.baselibrary.model.Response
import xinyiyun.chenfei.com.baselibrary.utils.ReflectionUtils
import xinyiyun.chenfei.com.baselibrary.utils.RxUtils
import xinyiyun.chenfei.com.baselibrary.utils.ViewUtils
import xinyiyun.chenfei.com.baselibrary.view.HorizontalDivider
import xinyiyun.chenfei.com.baselibrary.view.MultiStateView
import xinyiyun.chenfei.com.baselibrary.view.VerticalDivider
import java.io.File
import java.net.ConnectException
import java.text.SimpleDateFormat
import java.time.format.DecimalStyle
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * Created by chenfei on 2018/5/10.
 */

interface OnRefreshLoadMoreListenr {
    fun loadData(page: Int, isRefresh: Boolean)
}
interface OnRefreshLoadMoreListenr0ther {
    fun loadData(page: Int, ym:String,isRefresh: Boolean)
}
val allMathParams: ViewGroup.LayoutParams = ViewGroup.LayoutParams(matchParent, matchParent)

inline fun BaseFragment.toolbarView(): Toolbar? = findOptional(R.id.common_toolbar_view)
inline fun BaseActivity.toolbarView(): Toolbar? = findOptional(R.id.common_toolbar_view)


inline fun BaseFragment.multiStateView(): MultiStateView? = findOptional(R.id.common_multi_state_view)
inline fun BaseActivity.multiStateView(): MultiStateView? = findOptional(R.id.common_multi_state_view)

inline fun BaseFragment.refreshView(): SmartRefreshLayout? = findOptional(R.id.common_refresh_view)
inline fun BaseActivity.refreshView(): SmartRefreshLayout? = findOptional(R.id.common_refresh_view)

inline fun BaseFragment.setState(state: Int) = multiStateView()?.setState(state)
inline fun BaseActivity.setState(state: Int) = multiStateView()?.setState(state)

inline fun RecyclerView.grid(spanCount: Int, size: Int = 0, colorRes: Int = R.color.transparent) {
    layout(GridLayoutManager(context, spanCount), size, colorRes)
}

inline fun RecyclerView.staggeredGrid(spanCount: Int, size: Int = 0, colorRes: Int = R.color.transparent) {
    layout(StaggeredGridLayoutManager(spanCount, StaggeredGridLayoutManager.VERTICAL), size, colorRes)
}

inline fun RecyclerView.vertical(size: Int = dip(0.5f), colorRes: Int = R.color.c_ebebeb) {
    layout(LinearLayoutManager(context), size, colorRes)
}
inline fun RecyclerView.horizontal() {
    layout(LinearLayoutManager(context,RecyclerView.HORIZONTAL,false))
}

inline fun RecyclerView.dontAnima() {
    itemAnimator.addDuration = 0
    itemAnimator.changeDuration = 0
    itemAnimator.moveDuration = 0
    itemAnimator.removeDuration = 0
}

inline fun RecyclerView.layout(layout: RecyclerView.LayoutManager, size: Int = 0, colorRes: Int = R.color.transparent) {
    layoutManager = layout
    addItemDecoration(HorizontalDivider.Builder(context).size(size).colorResId(colorRes).build())
    if (layout is GridLayoutManager) {
        addItemDecoration(VerticalDivider.Builder(context).size(size).colorResId(colorRes).build())
    }
}

inline fun <T : Any> RecyclerView.bindAdapter(data: List<T>, layoutId: Int, bind: Map<Int, String>): BaseQuickAdapter<T, BaseViewHolder> {

    adapter = object : BaseQuickAdapter<T, BaseViewHolder>(layoutId, data) {
        override fun convert(helper: BaseViewHolder, item: T) {
            bind.forEach {
                helper.getView<View>(it.key)?.apply {
                    when (this) {
                        is TextView -> if (it.value.isEmpty()) text = item.toString() else
                            ReflectionUtils.getField(item, it.value)?.apply { text = toString() }
                    }
                }
            }

        }
    }
    return adapter as BaseQuickAdapter<T, BaseViewHolder>
}

fun Context.alertDialog(msg: String = "您确定提交吗?", tit: String = "信息提示", block: () -> Unit) {
    alert {
        message = msg
        title = tit
        negativeButton("取消",{

        })
        positiveButton("确定", {
            block.invoke()
        })
        show()

    }
}


infix fun <T> Boolean.t(block: () -> T): T? {
    return if (this) {
        block.invoke()
    } else {
        null
    }
}

infix fun <T> Boolean.f(block: () -> T): T? {
    return if (!this) {
        block.invoke()
    } else {
        null
    }
}

fun createPartWithAllImageFormats(requestKey: String, file: File): MultipartBody.Part
        = MultipartBody.Part
        .createFormData(requestKey, file.name, RequestBody.create(MediaType.parse("image/*"), file))

fun TextView.isEmpty(toastHint: Boolean = true, msg: CharSequence = hint): Boolean {
    if (toastHint && text.isEmpty()) {
        msg?.apply(context::toast)
        text.isEmpty()
    }
    return text.isEmpty()
}

fun <T, V : BaseViewHolder> BaseQuickAdapter<T, V>.setNewData(state: MultiStateView?, data: List<T>) {

    setNewData(data)
    state?.apply {
        if (data.isEmpty()) {
            state.setState(MultiStateView.EMPTY)
        } else {
            state.setState(MultiStateView.CONTENT)
        }
    }
}


inline fun AppCompatActivity.timePicker(init: String = "", title:String="请选择时间", format: String = "yyyy-MM-dd HH:mm:ss", crossinline block: (TimePickerDialog, String) -> Unit) {
    TimePickerDialog.Builder().setCallBack { v1, v2 ->
        block.invoke(v1, SimpleDateFormat(format).format(Date(v2)))
    }.setTitleStringId(title)
            .setCyclic(true)
            .setMinMillseconds(System.currentTimeMillis())
            .setCurrentMillseconds(if (init.isEmpty()) System.currentTimeMillis() else SimpleDateFormat(format).parse(init).time)
            .setThemeColor(resources.getColor(R.color.timepicker_dialog_bg))
            .setType(Type.ALL)
            .setWheelItemTextNormalColor(resources.getColor(R.color.timetimepicker_default_text_color))
            .setWheelItemTextSelectorColor(resources.getColor(R.color.blue))
            .setWheelItemTextSize(13)
            .build().show(supportFragmentManager, "")
}

inline fun AppCompatActivity.timePickers(init: String = "", title:String="请选择时间", format: String = "yyyy-MM-dd HH:mm", crossinline block: (TimePickerDialog, String) -> Unit) {
    TimePickerDialog.Builder().setCallBack { v1, v2 ->
        block.invoke(v1, SimpleDateFormat(format).format(Date(v2)))
    }.setTitleStringId(title)
            .setCyclic(true)
            .setCurrentMillseconds(if (init.isEmpty()) System.currentTimeMillis() else SimpleDateFormat(format).parse(init).time)
            .setThemeColor(resources.getColor(R.color.timepicker_dialog_bg))
            .setType(Type.ALL)
            .setWheelItemTextNormalColor(resources.getColor(R.color.timetimepicker_default_text_color))
            .setWheelItemTextSelectorColor(resources.getColor(R.color.blue))
            .setWheelItemTextSize(13)
            .build().show(supportFragmentManager, "")
}
inline fun AppCompatActivity.timePickerYMD(init: String = "",title:String="请选择时间", format: String = "yyyy-MM-dd", crossinline block: (TimePickerDialog, String) -> Unit) {
    TimePickerDialog.Builder().setCallBack { v1, v2 ->
        block.invoke(v1, SimpleDateFormat(format).format(Date(v2)))
    }.setTitleStringId(title)
            .setCyclic(true)
            .setMaxMillseconds(System.currentTimeMillis())
            .setCurrentMillseconds(if (init.isEmpty()) System.currentTimeMillis() else SimpleDateFormat(format).parse(init).time)
            .setThemeColor(resources.getColor(R.color.timepicker_dialog_bg))
            .setType(Type.YEAR_MONTH_DAY)
            .setWheelItemTextNormalColor(resources.getColor(R.color.timetimepicker_default_text_color))
            .setWheelItemTextSelectorColor(resources.getColor(R.color.orange))
            .setWheelItemTextSize(13)
            .build().show(supportFragmentManager, "")
}
inline fun AppCompatActivity.timePickerMouth(init: String = "",title:String="请选择时间",type:Int =1, format: String = "MM-dd HH:mm", crossinline block: (TimePickerDialog, String) -> Unit) {
    TimePickerDialog.Builder().setCallBack { v1, v2 ->
        block.invoke(v1, SimpleDateFormat(format).format(Date(v2)))
    }.setTitleStringId(title)
            .setCyclic(true)
            .setMinMillseconds(System.currentTimeMillis())
            // .setMaxMillseconds(if (type==1) System.currentTimeMillis()+60*1000*60*24*30 else SimpleDateFormat(format).parse(init).time)
            .setCurrentMillseconds(if (init.isEmpty()) System.currentTimeMillis() else SimpleDateFormat(format).parse(init).time)
            .setThemeColor(resources.getColor(R.color.timepicker_dialog_bg))
            .setType(Type.MONTH_DAY_HOUR_MIN)
            .setWheelItemTextNormalColor(resources.getColor(R.color.timetimepicker_default_text_color))
            .setWheelItemTextSelectorColor(resources.getColor(R.color.blue))
            .setWheelItemTextSize(13)
            .build().show(supportFragmentManager, "")
}


inline fun BaseFragment.timePicker(init: String = "", title:String="请选择时间",format: String = "yyyy-MM-dd HH:mm:ss", crossinline block: (TimePickerDialog, String) -> Unit) {
    TimePickerDialog.Builder().setCallBack { v1, v2 ->
        block.invoke(v1, SimpleDateFormat(format).format(Date(v2)))
    }.setTitleStringId(title)
            .setCyclic(true)
            .setMinMillseconds(System.currentTimeMillis())
            .setCurrentMillseconds(if (init.isEmpty()) System.currentTimeMillis() else SimpleDateFormat(format).parse(init).time)
            .setThemeColor(resources.getColor(R.color.timepicker_dialog_bg))
            .setType(Type.ALL)
            .setWheelItemTextNormalColor(resources.getColor(R.color.timetimepicker_default_text_color))
            .setWheelItemTextSelectorColor(resources.getColor(R.color.blue))
            .setWheelItemTextSize(13)
            .build().show(fragmentManager,"")
}

inline fun BaseFragment.timePickerMouth(init: String = "",title:String="请选择时间",type:Int =1, format: String = "MM-dd HH:mm", crossinline block: (TimePickerDialog, String) -> Unit) {
    TimePickerDialog.Builder().setCallBack { v1, v2 ->
        block.invoke(v1, SimpleDateFormat(format).format(Date(v2)))
    }.setTitleStringId(title)
            .setCyclic(true)
            .setMinMillseconds(System.currentTimeMillis())
           // .setMaxMillseconds(if (type==1) System.currentTimeMillis()+60*1000*60*24*30 else SimpleDateFormat(format).parse(init).time)
            .setCurrentMillseconds(if (init.isEmpty()) System.currentTimeMillis() else SimpleDateFormat(format).parse(init).time)
            .setThemeColor(resources.getColor(R.color.timepicker_dialog_bg))
            .setType(Type.MONTH_DAY_HOUR_MIN)
            .setWheelItemTextNormalColor(resources.getColor(R.color.timetimepicker_default_text_color))
            .setWheelItemTextSelectorColor(resources.getColor(R.color.blue))
            .setWheelItemTextSize(14)
            .build().show(fragmentManager,"")
}
inline fun <reified T : BasePresenter> LifecycleProvider<*>.api(block: T.() -> Unit) {
    block(T::class.java.getConstructor(LifecycleProvider::class.java).newInstance(this))
}

fun <T : Any> Observable<T>.apiObservable(block: (T) -> Unit) {
    subscribe(object : ApiObserver<T>() {
        override fun onNext(t: T) {
            block(t)
        }
    })
}


inline fun <T> Observable<T>.bindAndroid(): Observable<T> {
    return compose(RxUtils.androidTransformer())
}

inline fun <T, V> Observable<T>.bindView(view: V): Observable<T> {
    var disposable: Disposable? = null
    var subject = PublishSubject.create<Boolean>()
    return this.observeOn(AndroidSchedulers.mainThread()).doOnSubscribe {
        disposable = it
        if (view is BaseActivity) {

            if (view.refreshView() == null) {
                view.setState(MultiStateView.LOADING)
            } else {
                if (!view.refreshView()!!.isRefreshing && !view.refreshView()!!.isLoading) {
                    view.setState(MultiStateView.LOADING)
                }
            }

        }
        if (view is BaseFragment) {
            if (view.refreshView() == null) {
                view.setState(MultiStateView.LOADING)
            } else {
                if (!view.refreshView()!!.isRefreshing && !view.refreshView()!!.isLoading) {
                    view.setState(MultiStateView.LOADING)
                }
            }
        }

    }.doOnComplete {
        if (view is BaseActivity) {
            view.refreshView()?.apply {
                finishRefresh()
                finishLoadmore()
            }
        }
        if (view is BaseFragment) {
            view.refreshView()?.apply {
                finishRefresh()
                finishLoadmore()
            }
        }
    }.doOnError {
        when (it) {
            is JsonSyntaxException -> BaseApplication.application.longToast("服务器返回数据解析错误")
            is NetworkErrorException -> BaseApplication.application.longToast("网络异常请稍后再试")
            is ConnectException -> BaseApplication.application.longToast("网络异常请稍后再试")
            else -> BaseApplication.application.toast("系统异常")
        }

        XLog.e(it.localizedMessage, it)

        if (view is BaseActivity) {
            view.refreshView()?.apply {
                finishRefresh()
                finishLoadmore()
            }
            view.pageIndex.apply {
                set(if (view.pageIndex.get() <= 1) 1 else view.pageIndex.decrementAndGet())
            }
            view.setState(MultiStateView.ERROR)
            view.multiStateView()?.errorView?.onClick {
                view.setState(MultiStateView.LOADING)
                subject.onNext(true)
            }

            view.refreshView()?.apply {
                finishRefresh()
                finishLoadmore()
            }
        }

        if (view is BaseFragment) {
            view.pageIndex.apply {
                set(if (view.pageIndex.get() <= 1) 1 else view.pageIndex.decrementAndGet())
            }
            view.setState(MultiStateView.ERROR)
            view.multiStateView()?.errorView?.onClick {
                view.setState(MultiStateView.LOADING)
                subject.onNext(true)
            }
            view.refreshView()?.apply {
                finishRefresh()
                finishLoadmore()
            }
        }
    }.doOnNext {
        if (it is List<*>) {
            if (!it.isEmpty()) {
                (it.first() as? Response)?.apply {
                    (status=="ok").t{ BaseApplication.application.toast(msg) }
                }

                (it.first() as? LoadMoreData)?.apply load@ {
                    this.javaClass.getDeclaredField("pages")?.apply {
                        isAccessible = true
                        var pages = get(this@load).toString().toInt()
                        if (view is BaseActivity) {
                            hasMore = view.pageIndex.get() < pages
                            view.refreshView()?.apply {
                                if (!hasMore) {
                                    finishLoadmoreWithNoMoreData()
                                } else {
                                    resetNoMoreData()
                                }
                            }
                        }

                        if (view is BaseFragment) {
                            hasMore = view.pageIndex.get() < pages
                            view.refreshView()?.apply {
                                if (!hasMore) {
                                    finishLoadmoreWithNoMoreData()
                                } else {
                                    resetNoMoreData()
                                }
                            }
                        }


                    }

                }
            }

            if (it.isEmpty()) {
                if (view is BaseActivity) {
                    view.setState(MultiStateView.EMPTY)
                } else if (view is BaseFragment) {
                    view.setState(MultiStateView.EMPTY)
                }
                return@doOnNext
            }
        } else if (it == null) {
            if (view is BaseActivity) {
                view.setState(MultiStateView.EMPTY)
                disposable?.dispose()
            } else if (view is BaseFragment) {
                view.setState(MultiStateView.EMPTY)
                disposable?.dispose()
            }
            return@doOnNext
        }


        if (view is BaseActivity) {
            view.setState(MultiStateView.CONTENT)
        } else if (view is BaseFragment) {
            view.setState(MultiStateView.CONTENT)
        }
    }.retryWhen {
        subject.delay(500, TimeUnit.MILLISECONDS)
    }
}


data class ViewWrap(var rootView: View, var contentView: ViewGroup)

inline fun BaseActivity.createViewByAnnocations(): ViewWrap = createContentView(this)

inline fun BaseFragment.createViewByAnnocations(): ViewWrap = createContentView(this)
/**
 * 创建contextview
 */
inline fun createContentView(obj: Any): ViewWrap {

    var ctAnnotation = obj.javaClass.getAnnotation(ContentView::class.java)
    var tlAnnotation = obj.javaClass.getAnnotation(ToolbarView::class.java)
    var frameLayout: ViewGroup? = null
    val context = with(obj) {
        when (obj) {
            is AppCompatActivity -> {
                obj.ctx
            }
            is Fragment -> obj.ctx
            else -> BaseApplication.application
        }
    }
    var contentView: ViewGroup? = null
    ctAnnotation?.apply {
        if (fullScreen) {
            if (obj is AppCompatActivity) {
                obj.requestWindowFeature(Window.FEATURE_NO_TITLE);
                obj.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                        WindowManager.LayoutParams.FLAG_FULLSCREEN);
            }
        }

        if (res != 0) {
            context.apply {
                contentView = include(res) {
                    layoutParams = allMathParams
                }
            }
        }
    }

    //根节点
    val rootView = _LinearLayout(context).apply root@ {
        orientation = LinearLayout.VERTICAL
        //添加toolbar
        tlAnnotation?.apply tl@ {

            var toolBarHegith = resources.getDimensionPixelOffset(R.dimen.toolbar_height)
            val typeValue = TypedValue()
            if (context.theme.resolveAttribute(android.R.attr.actionBarSize, typeValue, true)) {
                toolBarHegith = TypedValue.complexToDimensionPixelSize(typeValue.data, context.resources.displayMetrics)
            }
            val stateHeight = ViewUtils.getStatusBarHeight(context)

            val toolbar = themedToolbar(R.style.Toolbar_Theme) {
                id = R.id.common_toolbar_view
                popupTheme = R.style.Toolbar_PopupTheme
                backgroundColor = resources.getColor(R.color.colorPrimary)
                topPadding = stateHeight
                if (showBack) {
                    navigationIcon = resources.getDrawable(R.drawable.ic_back)
                    if (obj is AppCompatActivity) {
                        setNavigationOnClickListener { obj.onBackPressed() }
                    }
                }
                if (menuId != 0) {
                    inflateMenu(menuId)
                    if (menu?.javaClass?.simpleName.equals("MenuBuilder")) {
                        try {
                            val method = menu.javaClass.getDeclaredMethod("setOptionalIconsVisible", java.lang.Boolean.TYPE)
                            method.isAccessible = true
                            method.invoke(menu, true)
                        } catch (e: Exception) {
                        }
                    }
                    if (obj is Toolbar.OnMenuItemClickListener) setOnMenuItemClickListener(obj)
                }
                textView {
                    id = R.id.common_toolbar_title_view
                    this@tl.title?.let {
                            text = it
                    }
                    textColor = Color.BLACK
                    textSize = 18f
                }.lparams(wrapContent, wrapContent, Gravity.CENTER)
            }.lparams(matchParent, toolBarHegith + stateHeight)

            contentView?.let {
                if (flotage) {
                    this@root.removeView(toolbar)
                    frameLayout = frameLayout {
                        addView(contentView, allMathParams)
                        addView(toolbar.lparams(matchParent, wrapContent, Gravity.TOP))
                    }.lparams(matchParent, matchParent)
                } else {
                    contentView?.let { this@root.addView(it, allMathParams) }
                }

            }
        }
    }

    contentView?.let {
        if (tlAnnotation == null) {
            rootView.addView(it, allMathParams)
        }
    }

    //添加stateView
    obj.javaClass.getAnnotation(StateView::class.java)?.apply {
        (if (bindViewId == 0) contentView else rootView.findOptional(bindViewId))?.let {
            (it.parent as ViewGroup).apply {
                indexOfChild(it).apply {
                    removeView(it)
                    addView(MultiStateView(context).apply {
                        this.transparentLoading = transLoading
                        id = R.id.common_multi_state_view
                        layoutParams = it.layoutParams
                        showError = isShowError
                        showEmpty = isShowEmpty
                        setViewForState(R.layout.view_common_loading, MultiStateView.LOADING)
                        setViewForState(R.layout.view_common_error, MultiStateView.ERROR)
                        setViewForState(R.layout.view_common_empty, MultiStateView.EMPTY)
                        setViewForState(it, MultiStateView.CONTENT)
                    }, this)
                }
            }
        }
    }

    //添加refreshView
    obj.javaClass.getAnnotation(RefreshView::class.java)?.apply {
        (if (bindViewId == 0) contentView else rootView.findOptional(bindViewId))?.let {
            (it.parent as ViewGroup).apply viewGroup@ {
                indexOfChild(it).apply index@ {
                    removeView(it)

                    SmartRefreshLayout(context).apply {
                        id = R.id.common_refresh_view
                        refreshHeader = MaterialHeader(context)
                        refreshFooter = ClassicsFooter(context)
                        layoutParams = it.layoutParams
                        isEnableOverScrollBounce = false
                        setDisableContentWhenRefresh(true)
                        setEnableHeaderTranslationContent(false)
                        setEnableFooterTranslationContent(true)
                        setDisableContentWhenLoading(true)
                        isEnableRefresh = refresh
                        isEnableAutoLoadmore = false
//                        setEnableFooterFollowWhenLoadFinished(true)
                        if (obj is OnRefreshLoadMoreListenr) {

                            setOnRefreshListener {
                                if (obj is BaseActivity) {
                                    obj.pageIndex.set(1)
                                } else if (obj is BaseFragment) {
                                    obj.pageIndex.set(1)
                                }
                                obj.loadData(1, true)
                            }
                            setOnLoadmoreListener { rf ->
                                var page = 1
                                if (obj is BaseActivity) {
                                    page = obj.pageIndex.incrementAndGet()
                                } else if (obj is BaseFragment) {
                                    page = obj.pageIndex.incrementAndGet()
                                }
                                obj.loadData(page, false)
                            }

                        } else {
                            if (obj is OnRefreshListener) {
                                setOnRefreshListener(obj)
                            }
                            if (obj is OnLoadmoreListener) {
                                setOnLoadmoreListener(obj)
                            }
                        }

                        isEnableLoadmore = loadMore
                        addView(it)
                        if (this@viewGroup is MultiStateView) {
                            setViewForState(this, MultiStateView.CONTENT)
                            setState(MultiStateView.LOADING)
                        } else {
                            this@viewGroup.addView(this, this@index)
                        }
                    }

                }
            }
        }
    }

    return ViewWrap(rootView, if (contentView == null && frameLayout == null) {
        rootView
    } else {
        if (contentView != null) {
            contentView!!
        } else {
            frameLayout!!
        }
    })


}


