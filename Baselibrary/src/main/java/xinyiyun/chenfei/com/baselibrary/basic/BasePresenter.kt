package xinyiyun.chenfei.com.baselibrary.basic

import com.trello.rxlifecycle2.LifecycleProvider
import com.trello.rxlifecycle2.android.ActivityEvent
import com.trello.rxlifecycle2.android.FragmentEvent
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import com.trello.rxlifecycle2.kotlin.bindUntilEvent
import io.reactivex.ObservableTransformer
import xinyiyun.chenfei.com.baselibrary.common.bindAndroid
import xinyiyun.chenfei.com.baselibrary.common.bindView
import java.util.concurrent.TimeUnit

/**
 * Created by chenfei on 2018/5/10.
 */
abstract class BasePresenter(private var lifecycleProvider: LifecycleProvider<*>) {


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
