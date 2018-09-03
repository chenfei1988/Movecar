package xinyiyun.chenfei.com.baselibrary.basic

import com.elvishew.xlog.XLog
import io.reactivex.observers.DefaultObserver
import org.jetbrains.anko.toast

/**
 * Created by chenfei on 2018/5/10.
 */
open class ApiObserver<T> : DefaultObserver<T>() {
    override fun onNext(t: T) {

    }

    override fun onError(e: Throwable) {
        BaseApplication.application.toast("onError!")
        XLog.e(e.localizedMessage, e)
    }

    override fun onComplete() {
    }
}