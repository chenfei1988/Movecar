package xinyiyun.chenfei.com.movecar.base

import com.trello.rxlifecycle2.LifecycleProvider
import com.trello.rxlifecycle2.android.ActivityEvent
import com.trello.rxlifecycle2.android.FragmentEvent
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import com.trello.rxlifecycle2.kotlin.bindUntilEvent
import io.reactivex.ObservableTransformer
import xinyiyun.chenfei.com.baselibrary.common.bindAndroid
import xinyiyun.chenfei.com.baselibrary.common.bindView
import xinyiyun.chenfei.com.movecar.net.ApiInterface
import xinyiyun.chenfei.com.movecar.net.ApiService
import java.util.concurrent.TimeUnit

/**
 * Created by YangQiang on 2017/11/6.
 */
abstract class BasePresenter(private var lifecycleProvider: LifecycleProvider<*>) {

    inline fun apiService(block: ApiInterface.() -> Unit): ApiInterface {
        return ApiService.getApi().apply(block)
    }

    fun apiService(): ApiInterface {
        return ApiService.getApi()
    }

    protected fun <T> apiTransformer(delay: Long = 500L, timeUnit: TimeUnit = TimeUnit.MILLISECONDS): ObservableTransformer<T, T> {
        return ObservableTransformer { ots ->
            ots.delay(delay, timeUnit)
                    .bindAndroid()
                    .bindView(lifecycleProvider)
                    .let {
                        if (lifecycleProvider is RxAppCompatActivity) {
                            it.bindUntilEvent(lifecycleProvider as LifecycleProvider<ActivityEvent>, ActivityEvent.DESTROY)
                        } else {
                            it.bindUntilEvent(lifecycleProvider as LifecycleProvider<FragmentEvent>, FragmentEvent.DESTROY_VIEW)
                        }
                    }
        }
    }
}