
package  xinyiyun.chenfei.com.baselibrary.annotation

import xinyiyun.chenfei.com.baselibrary.R


/**
 * Created by chenfei on 2018/5/10.
 */


@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class ToolbarView(val title: String = "", val showBack: Boolean = false, val flotage: Boolean = false, val menuId: Int = 0)


@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class ContentView(val res: Int = 0, val lazyload: Boolean = false, val fullScreen: Boolean = false)


@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class RefreshView(val bindViewId: Int = 0, val loadMore: Boolean = false, val refresh: Boolean = true)

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class StateView(val bindViewId: Int = 0,
                           val transLoading: Boolean = true,
                           val isShowError: Boolean = true,
                           val isShowEmpty: Boolean = true)